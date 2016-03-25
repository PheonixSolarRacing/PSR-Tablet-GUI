package com.psrt.threads;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.artemis.World;
import com.psrt.main.Main;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialMonitor  {
	private SerialReader2 sr; //Reads and cuts serial; sort of
	private SerialParser sp; //Cuts and parses serial. there's some overlap with the reader
	private CircularFifoQueue<Integer> internalBuffer;
	private CircularFifoQueue<byte[]> parseBuffer;
	private com.artemis.World world;
	
	private boolean running = false;
	static final int MAX_CHECK_SIZE = 1000;
	static final int TIMEOUT = 1000;
	
	private final String PORT_NAMES[] = {                 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
	        "/dev/ttyUSB0", // Linux
	        "COM6", // Windows
	};
	
	/*/
	 * Possible error states:
	 * -All 0 bytes
	 * -Not receiving data 
	 * -Too many positions between frames
	 * -Frame size not a multiple of 10
	 */

	/**
	 * Class for holding all serial crap. Reading, cutting, parsing, etc. Monitors everything and handles all threads.  (Of which there should be 2)
	 */
	public SerialMonitor(com.artemis.World world){
		log("Serial monitor started.");
	    this.world = world;
		internalBuffer = new CircularFifoQueue<Integer>(1024);
		parseBuffer = new CircularFifoQueue<byte[]>(512);
		//Read and cut data
		Thread reader = new Thread("SerialReader"){
			@Override
			public void run(){
				sr = new SerialReader2();
				while(running){
					if(sr.seport != null) sr.read();
					else{
						//running = false;
						log("SerialPort not opened.");
						break;
					}
				}
			}
		};
		Thread parser = new Thread("SerialParser"){
			@Override
			public void run(){
				sp = new SerialParser();
				while(running){
					if(sp != null){
						sp.parse();
					}else {
						break;
					}
				}
			}
		};
		reader.setDaemon(true);
		reader.start();
		
		running = true;
	}
	
	/**
	 * Called on the serial monitor. Contents of this method are subject to change.  
	 * Should close and wrap up all the serial interfaces, ports, etc.
	 */
	public void close(){
		sr.close();
		running = false;
	}
	
	private int unsign(byte b){ return b & 0xFF;}
	
	/**
	 * <b>MarkerState</b> - debug states for frame marker 
	 * <p>MARKER - Uses this state to detail that the current index is a frame marker</p>
	 * <p>NOT_MARKER - Uses this state to detail that the current index isn't a frame marker</p>
	 * <p>END_OF_BUFFER - Uses this state in order to describe that the current position couldn't be checked because one of the position checks goes out of bounds</p>
	 * <p>This enum is used primarily by {@link SerialParser.cut()}
	 * @author Austin Dibble
	 *
	 */
	public static enum MarkerState{
		 NOT_MARKER,
		 IS_MARKER,
		 END_OF_BUFFER;
	}
	
	//-----------------------------------------------------------------###################################----------------------------------------------------------
	/**
	 * Parses serial data.  Holds the cut function (Currently called by the SerialReader) and the parse function.  Together they cut the data out from between the frame markers
	 * and parse it into its useful information based on the CAN dictionary.
	 * @author Austin Dibble
	 *
	 */
	 class SerialParser{
		 
		 private void print_range(int start, int end){
			 log("Printing range [" + start + ", " + end + "]: ");
			 for(int i = start; i < end; i++){
				 log("\t[" + i + "]: " + internalBuffer.get(i));
			 }
		 }
		 
		 void print_array(byte[] a){
			 log("Printing out byte array: ");
			 for(int i = 0; i < a.length; i++){
				 log("\t[" + i + "]: " + a[i]);
			 }
		 }
		 
		 //---------------------------
		 int m1 = -1;
		 int m2 = -1;
		 int index = 0;
		 
		 boolean cut_debug = false;
		 
		 /**
		  * Attempts to cuts the serial data lying in the internalBuffer. 
		  */
		 private void cut(){
			 //log("Frames in parseBuffer: " + parseBuffer.size());
			 if(parseBuffer.size() >= 50){
				 print_array(parseBuffer.poll());
			 }
			 else{
				 if(m1 != -1 && cut_debug) {
					 log("Mark1: " + m1);
				 }
				 if(m2 != -1 && cut_debug) {
					 log("Mark2: " + m2);
				 }
				 if(!internalBuffer.isEmpty()){
					 for(; true; ){
						 if(!running) break;
						 if(cut_debug) {
							 log("Checking index: " + index);
						 }
						 MarkerState mark1 = marker(internalBuffer, index);
						 if(mark1 == MarkerState.END_OF_BUFFER) {
							 if(cut_debug) {
								 log("End of buffer...");
							 }
							 //index++;
							 break;
						 }
						 else if(mark1 == MarkerState.IS_MARKER) {
							 if(cut_debug) {
								 print_range(index, index + 10);
							 }
							 if(m1 == -1) {
								 m1 = index + 10;
								 if(cut_debug) {
									 log("Found mark1 at " + m1);
								 }
								 index = m1;
							 }
							 else {
								 m2 = index + 10;
								 if(cut_debug) {
									 log("Found mark2 at " + m2);
								 }
								 int delta = (m2 - m1) - 10;
								 if(cut_debug) {
									 log("M1: " + m1 + ", M2: " + m2 + " - delta: " + delta);
								 }
								 if(delta % 10 == 0){
									 if(!cut_debug) {
										 log("Adding byte array to parseBuffer of length " + delta);
									 }
									 byte[] temp = new byte[delta];
									 for(int i = 0; i < delta; i++){
										 int temp_int = internalBuffer.get(m1 + i);
										 temp_int -= 128;
										 byte temp_byte = (byte) temp_int;
										 temp[i] = temp_byte;
									 }
									 parseBuffer.add(temp);
									 for(int i = 0; i < m2 - 10; i++){
										 internalBuffer.remove();
									 }
									 index = 10;
									 m1 = 10;
									 m2 = -1;
								 }else{
									 if(cut_debug) {
										 log("Data not a multiple of 10. Discarding frame.");
									 }
									 //delete index 0 through (m2 - 10)
									 for(int i = 0; i < m2 - 10; i++){
										 internalBuffer.remove();
									 }
									 index = 10;
									 m1 = 10;
									 m2 = -1;
								 }
							 }
						 }
						 else if(mark1 == MarkerState.NOT_MARKER) {
							 if(m1 == -1){
								 if(cut_debug) {
									 log("Not found+pl: " + internalBuffer.poll());
								 }else{
									 internalBuffer.poll();
								 }
								 index = 0;
							 }else{
								 if(cut_debug) {
									 log("Not found+pk: " + internalBuffer.get(index));
								 }
								 index++;
								 if(index - m1 >= MAX_CHECK_SIZE){
									 log("Error in SerialParser.cut(): Distance between current index and 1st check marker is greater than max check size (" + MAX_CHECK_SIZE + ")");
									 log("Starting frame check over.");
									 index = 0; 
									 m1 = -1; 
									 m2 = -1;
								 }
							 }
						 }
					 }
				 }
		 	 }
		 }
		 
		 /**
		  * Looks the given FIFO buffer at the given index i to see if there is a marker at that position.
		  * @param bytes - FIFO buffer to check
		  * @param i - index in buffer to check
		  * @return - {@link MarkerState} to describe exit condition.
		  */
		 private MarkerState marker(CircularFifoQueue<Integer> bytes, int i){
				MarkerState status = MarkerState.NOT_MARKER;
					try{
						if(bytes.get(i)  == 0xFF){
							if(cut_debug) log("Found FF, checking bytes...");
					 		if(bytes.get(i + 1) == 0xFF && 
							   bytes.get(i + 2) == 0xFF &&
							   bytes.get(i + 3) == 0xFF && 
							   bytes.get(i + 4) == 0xFE &&
							   bytes.get(i + 5) == 0xFE &&
							   bytes.get(i + 6) == 0xFF &&
							   bytes.get(i + 7) == 0xFF && 
							   bytes.get(i + 8) == 0xFF &&
							   bytes.get(i + 9) == 0xFF) 
							{	
								status = MarkerState.IS_MARKER;
							}else{
								status = MarkerState.NOT_MARKER;
							}
						}else{
							status = MarkerState.NOT_MARKER;
						}
								  
					}catch(NoSuchElementException e){
						//log("Reached the end of the buffer in marker, waiting...");
						status = MarkerState.END_OF_BUFFER;
						return status;
					}
				return status;
			}

		 //---------------------------
		 /**
		  * Checks for a marker in a byte array.  Marker is FF FF FF FF FE FE FF FF FF FF
		  * @param bytes
		  * @param i
		  * @return
		  */
		 @Deprecated
		 private boolean marker_bytes(byte[] bytes, int i){
				boolean end = false;
					if(bytes[i]     == 0xFF &&
					   bytes[i + 1] == 0xFF && 
					   bytes[i + 2] == 0xFF &&
					   bytes[i + 3] == 0xFF && 
					   bytes[i + 4] == 0xFE &&
					   bytes[i + 5] == 0xFE &&
					   bytes[i + 6] == 0xFF &&
					   bytes[i + 7] == 0xFF && 
					   bytes[i + 8] == 0xFF &&
					   bytes[i + 9] == 0xFF) 
					{	
						end = true;
					}
				return end;
			}
			
		 public void parse(){
			 //parsing here
		 }
		 /**
		  * Converts the given byte array into a float and returns it.
		  * @param bytes - Byte array to convert
		  * @return - Float representation of bytes.
		  */
		 private float bytesToFloat(byte[] bytes){
			 return ByteBuffer.wrap(bytes).getFloat();
		 }
	 }
	 
	 //------------------------------------###########################################-----------------------------------------------//
	 public static Object lock = new Object(); //Object for thread locking concurrency
	 
	 /**
	  * Quick logger method
	  * @param s
	  */
	 private void log(String s){ 
		 if(Main.DEBUG) {
			 System.out.println(s);
		 }
	 }
	 
	 /**
	  * New SerialReader class based on jssc. Reads in serial data from the COM port given in the SerialMonitor class (for now). Future usage would give the ability to search the ports for a given behavior.
	  * Currently the read function of this class is called on its own thread to fill an internal FIFO buffer with serial data in the form of bytes. The bytes are converted to 
	  * unsigned integers for readability (this may be changed later, and probably will be) and placed into the FIFO buffer.  The read function then calls the SerialParser's cut method
	  * in order to cut the newly read in data into pieces, given by the stop bits (FF FF FF FF FE FE FF FF FF FF). These pieces are then converted back into unsigned bytes and placed
	  * into another FIFO buffer of byte arrays, which are then parsed by the SerialParser's parse method.  This parsed information is then passed onto the GUI via magicks
	  * @author Austin Dibble
	  *
	  */
	 class SerialReader2 {
		 public jssc.SerialPort seport;
		 public SerialReader2(){
			 String[] portNames = jssc.SerialPortList.getPortNames();
			 log("Opening serial reader.");
			 for(String port : portNames){
				 if(port.equals(PORT_NAMES[2])) seport = new jssc.SerialPort(port);
			 }
			 if(seport != null){
				 try{
					 log("Opening port");
					 seport.openPort();
					 seport.setParams(jssc.SerialPort.BAUDRATE_115200, 
                             jssc.SerialPort.DATABITS_8,
                             jssc.SerialPort.STOPBITS_1,
                             jssc.SerialPort.PARITY_NONE);
				 }catch(jssc.SerialPortException e){
					 log("Couldn't open serial port...");
				 }
			 }else{
				 log("Serial port not found. Aborting");
			 }
		 }
		 
		 public void read(){
			 int i = 0;
			 try {
				if(seport.getInputBufferBytesCount() > 0 && i < MAX_CHECK_SIZE){
					 byte[] b = seport.readBytes(1);
					 int num = unsign(b[0]);
					 //log("Reading[" + i + "]: " + num);
					 internalBuffer.add(num);
					 i++;
				 }
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
			sp.cut();
		 }
		 
		 public void close(){
			 try {
				if(seport != null) seport.closePort();
			 } catch (SerialPortException e) {
				e.printStackTrace();
				log("Couldn't close port.");
			 }
		 }
	 }
	 
	 /**
	  * Old SerialReader class using RXTX.
	  * @author Austin Dibble
	  *
	  */
	 @Deprecated
	 class SerialReader implements SerialPortEventListener{
		SerialPort serialPort;
		private BufferedReader input;
		private static final int TIME_OUT = 2000;
		private static final int DATA_RATE = 115200;
		private InputStream in;
		
		public SerialReader(){
			initialize();
		    log("SerialReader started");
		}

		@SuppressWarnings("rawtypes")
		public void initialize() {
		    CommPortIdentifier portId = null;
		    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		
		    //First, Find an instance of serial port as set in PORT_NAMES.
		    while (portEnum.hasMoreElements()) {
		        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
		        for (String portName : PORT_NAMES) {
		            if (currPortId.getName().equals(portName)) {
		                portId = currPortId;
		                break;
		            }
		        }
		    }
		    if (portId == null) {
		        System.out.println("Could not find COM port.");
		        return;
		    }
		
		    try {
		        serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
		        serialPort.setSerialPortParams(DATA_RATE,
		                SerialPort.DATABITS_8,
		                SerialPort.STOPBITS_1,
		                SerialPort.PARITY_NONE);
			        
		        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		        in = serialPort.getInputStream();
		
		        serialPort.addEventListener(this);
		        serialPort.notifyOnDataAvailable(true);
		    } catch (Exception e) {
		        System.err.println(e.toString());
		    }
		}

		public synchronized void close() {
		    if (serialPort != null) {
		        serialPort.removeEventListener();
		        serialPort.close();
		    }
		}

		public synchronized void serialEvent(SerialPortEvent oEvent) {
		    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
		    	try {
					if(running) read();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    // Ignore all the other eventTypes, but you should consider the other ones.
		}
		 
		 public void read() throws IOException{
			 int i = 0;
			 while(in.available() > 0 && i < MAX_CHECK_SIZE){
				 int b = in.read();
				 internalBuffer.offer(b);
				 i++;
			 }
			 sp.cut();
			 return;
		 }
	 }
}