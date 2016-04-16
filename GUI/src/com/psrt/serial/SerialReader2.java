package com.psrt.serial;

import static com.psrt.threads.SerialMonitor.log;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.psrt.threads.SerialMonitor;

import jssc.SerialPortException;

/**
 * New SerialReader class based on jssc. Reads in serial data from the COM port given in the SerialMonitor class (for now). 
 * Future usage would give the ability to search the ports for a given behavior.
 * Currently the read function of this class is called on its own thread to fill an internal FIFO buffer with serial data in the form of bytes. 
 * The bytes are converted to unsigned integers for readability (this may be changed later, and probably will be) and placed into the FIFO buffer.  
 * The read function then calls the SerialParser's cut method in order to cut the newly read in data into pieces, given by the stop bits 
 * (FF FF FF FF FE FE FF FF FF FF). These pieces are then converted back into unsigned bytes and placed
 * into another FIFO buffer of byte arrays, which are then parsed by the SerialParser's parse method.  
 * This parsed information is then passed onto the GUI via magicks.
 * @author Austin Dibble
 *
 */
public class SerialReader2 {
	 public jssc.SerialPort seport;
	 public SerialParser sp;
	 
	 private CircularFifoQueue<Integer> internalBuffer;

	 private final String PORT_NAMES[] = {                 
		"/dev/tty.usbserial-A9007UX1", // Mac OS X
     	"/dev/ttyUSB0", // Linux
     	"COM6" // Windows
	 };
		
	 
	 public SerialReader2(SerialParser sp, CircularFifoQueue<Integer> internalBuffer){
		 this.sp = sp;
		 this.internalBuffer = internalBuffer;
		 log("Opening serial reader.");
		 openPort();
	 }
	 
	 private void openPort(){
		 String[] portNames = jssc.SerialPortList.getPortNames();
		 
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
	 
	 /**
	  * Reads the information from the hardware buffer or whatever I don't really understand how all that works! 
	  * Just gets byte data from the COM port and passes it to the 
	  * input buffer that is checked by the SerialParser....
	  */
	 public void read(){
		 int i = 0;
		 try {
			if(seport.getInputBufferBytesCount() > 0 && i < SerialMonitor.MAX_CHECK_SIZE){
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
	 
	 /**
	  * Closes the currently open serial port. This SerialReader object should probably not be used after this
	  */
	 public void close(){
		 try {
			if(seport != null) seport.closePort();
		 } catch (SerialPortException e) {
			e.printStackTrace();
			log("Couldn't close port.");
		 }
	 }
	 
	 /**
	 * Does what it says. But it turns the byte into an int. Java doesn't have unsigned bytes. Wow
	 * @param b
	 * @return
	 */
	 private int unsign(byte b){ return b & 0xFF;}
		
		 
}
