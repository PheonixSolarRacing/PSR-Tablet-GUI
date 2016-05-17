package com.psrt.threads;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.psrt.entities.systems.Bank;
import com.psrt.main.Main;
import com.psrt.serial.SerialParser;
import com.psrt.serial.SerialReader2;

public class SerialMonitor  {
	private SerialReader2 sr; //Reads and cuts serial; sort of
	private SerialParser sp; //Cuts and parses serial. there's some overlap with the reader
	private CircularFifoQueue<Integer> internalBuffer;
	private com.artemis.World world;
	
	
	private boolean running = false;
	public static final int MAX_CHECK_SIZE = 1000;
	public static final int TIMEOUT = 1500;
	
	public static Object lock = new Object();
	
	private Bank bank;
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
	public SerialMonitor(com.artemis.World world, Bank bank){
		log("Serial monitor started.");
	    this.world = world;
	    this.bank = bank;
		internalBuffer = new CircularFifoQueue<Integer>(1024);
		
		
		sp = new SerialParser(world, internalBuffer, bank);
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
						//running = false;
						log("SerialPort not opened.");
						//break;
					}
					
					if(System.currentTimeMillis() - sr.lastUpdate > SerialMonitor.TIMEOUT){
						log("Serial Reader timed out after " + (System.currentTimeMillis() - sr.lastUpdate) + " ms");
						log("Attempting reconnection");
						sr.close();
						sr.seport = null;
						sr.findPort();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

	 /**
	  * Quick logger method
	  * @param s
	  */
	 public static void log(String s){ 
		 if(Main.DEBUG) {
			 System.out.println(s);
		 }
	 }
	 
	 private static String formatHttpStream(String serverAddress, int serverPort) {
	        StringBuilder sb = new StringBuilder(60);
	        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
	        sb.append("dst=");
	        sb.append(serverAddress);
	        sb.append(':');
	        sb.append(serverPort);
	        sb.append("}}");
	        return sb.toString();
	    }
}