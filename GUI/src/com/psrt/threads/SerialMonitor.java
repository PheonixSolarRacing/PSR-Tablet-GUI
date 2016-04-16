package com.psrt.threads;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.psrt.main.Main;
import com.psrt.serial.SerialParser;
import com.psrt.serial.SerialReader2;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialMonitor  {
	private SerialReader2 sr; //Reads and cuts serial; sort of
	private SerialParser sp; //Cuts and parses serial. there's some overlap with the reader
	private CircularFifoQueue<Integer> internalBuffer;
	private com.artemis.World world;
	
	private boolean running = false;
	public static final int MAX_CHECK_SIZE = 1000;
	public static final int TIMEOUT = 1000;
	
	/*/
	 * Possible error states:
	 * -All 0 bytes : ?
	 * -Not receiving data : Almost handled
	 * -Too many positions between frames :  ?
	 * -Frame size not a multiple of 10 : Handled
	 */

	/**
	 * Class for holding all serial crap. Reading, cutting, parsing, etc. Monitors everything and handles all threads.  (Of which there should be 2)
	 * Ideally there should only be one instance of this class.
	 */
	public SerialMonitor(com.artemis.World world){
		log("Serial monitor started.");
	    this.world = world;
		internalBuffer = new CircularFifoQueue<Integer>(1024);
		
		
		sp = new SerialParser(world, internalBuffer);
		Thread parser = new Thread("Serial Parsing"){
			@Override
			public void run(){
				SerialParser parser = sp;
				while(running){
					if(parser != null){
						parser.parse();
					}else {
						log("SerialParser not started");
						break;
					}
				}
			}
		};
		parser.setDaemon(true);
		parser.start();
		//Read and cut data
		Thread reader = new Thread("SerialReader"){
			@Override
			public void run(){
				sr = new SerialReader2(sp, internalBuffer);
				while(running){
					if(sr.seport != null && sr != null) sr.read();
					else{
						running = false;
						log("SerialPort not opened.");
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
	
	 //------------------------------------###########################################-----------------------------------------------//
	 public static Object lock = new Object(); //Object for thread locking concurrency
	 
	 /**
	  * Quick logger method
	  * @param s
	  */
	 public static void log(String s){ 
		 if(Main.DEBUG) {
			 System.out.println(s);
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
		        for (String portName : new String[]{"Com6"}) {
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