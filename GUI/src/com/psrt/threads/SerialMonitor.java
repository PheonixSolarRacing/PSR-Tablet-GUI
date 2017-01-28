package com.psrt.threads;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.psrt.entities.systems.Bank;
import com.psrt.entities.systems.LogMonitor;
//import com.psrt.main.Main;
import com.psrt.serial.SerialParser;
import com.psrt.serial.SerialReader2;

public class SerialMonitor  {
	
	/*************************************
				PRIVATE FIELDS
	**************************************/	
	private SerialReader2 sr; //Reads and cuts serial; sort of
	private SerialParser sp; //Cuts and parses serial. there's some overlap with the reader
	private CircularFifoQueue<Integer> internalBuffer;
	private boolean running = false;
	
    /*************************************
				PUBLIC FIELDS
 	**************************************/		
	public static final int MAX_CHECK_SIZE = 500;
	public static final int TIMEOUT = 1500;
	
	public static Object lock = new Object();
	
	
	/*/
	 * Possible error states:
	 * -All 0 bytes : ?
	 * -Not receiving data : Almost handled
	 * -Too many positions between frames :  ?
	 * -Frame size not a multiple of 10 : Handled
	 */

	/**
	 * Class for holding all serial crap. Reading, cutting, parsing, etc. Monitors everything and handles all serial threads.  (Of which there should be 2)
	 * Ideally there should only be one instance of this class.
	 */
	public SerialMonitor(Bank bank){
		LogMonitor.log("Serial monitor started.", LogMonitor.LogType.SERIAL_MONITOR);
		internalBuffer = new CircularFifoQueue<Integer>(1024);
		
		//Parse and organize data
		sp = new SerialParser(internalBuffer, bank);
		Thread parser = new Thread("Serial Parsing"){
			@Override
			public void run(){
				SerialParser parser = sp;
				while(running){
					if(parser != null){
						parser.parse();
					}else {
						LogMonitor.log("SerialParser not started", LogMonitor.LogType.SERIAL_MONITOR);
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
						//running = false;
						LogMonitor.log("SerialPort not opened.", LogMonitor.LogType.SERIAL_MONITOR);
						//break;
					}
					
					if(System.currentTimeMillis() - sr.lastUpdate > SerialMonitor.TIMEOUT){
						LogMonitor.log("Serial Reader timed out after " + (System.currentTimeMillis() - sr.lastUpdate) + " ms", LogMonitor.LogType.SERIAL_MONITOR);
						LogMonitor.log("Attempting reconnection", LogMonitor.LogType.SERIAL_MONITOR);
						sr.close();
						sr.seport = null;
						sr.findPort();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		};
		
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

	 /**
	  * Quick logger method
	  * @param s
	  */
	
	// to be removed
	/*@Deprecated
	 public static void log(String s){ 
		 if(Main.DEBUG) {
			 System.out.print(s);
		 }
	 }*/
}