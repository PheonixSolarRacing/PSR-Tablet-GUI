package com.psrt.entities.systems;

import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.utils.IntBag;
import com.psrt.entities.components.TextAreaComponent;

public class LogMonitor {
	public static enum LogType {
		MAIN,
		SERIAL_PARSER_PARSE,
		SERIAL_READER,
		SERIAL_MONITOR,
		UITHREAD,
		BANK_SYSTEM;
	}
  
	public static boolean MAIN_DEBUG = false;
	public static boolean SERIAL_PARSER_PARSE_DEBUG = false;
	//public static boolean SERIAL_PARSER_CUT_DEBUG = false;
	public static boolean SERIAL_READER_DEBUG = false;
	public static boolean SERIAL_MONITOR_DEBUG = false;
	public static boolean UITHREAD_DEBUG = false;
	public static boolean BANK_SYSTEM_DEBUG = false;
	
	public static LogMonitor lm;

	ComponentMapper<TextAreaComponent> tm;
	public static TextAreaComponent textArea;
	
	public LogMonitor(World world) {
		tm = world.getMapper(TextAreaComponent.class);
		ValueSystem v = world.getSystem(ValueSystem.class);
		EntitySubscription sub = v.getSubscription();
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		IntBag b = sub.getEntities();
		for(int i = 0; i < b.size(); i++){
			int id = b.get(i);
			TextAreaComponent tac = tm.getSafe(id);
			
			if (tac != null && tac.getReference().equals("txt_area_errors")) {
				textArea = tac;
			}
		}
	}
	
	public static void log(String s, LogType type) {
		if (type == LogType.MAIN && MAIN_DEBUG) {
			printToGUI("Main: " + s + "\n");
		} else if (type == LogType.SERIAL_PARSER_PARSE && SERIAL_PARSER_PARSE_DEBUG) {
			printToGUI("SerialParser.Parse: " + s + "\n");
		} else if (type == LogType.SERIAL_READER && SERIAL_READER_DEBUG) {
			printToGUI("SerialReader: " + s + "\n");
		} else if (type == LogType.SERIAL_MONITOR && SERIAL_MONITOR_DEBUG) {
			printToGUI("SerialMonitor: " + s + "\n");
		} else if (type == LogType.UITHREAD && UITHREAD_DEBUG) {
			printToGUI("UIThread: " + s + "\n");
		} else if (type == LogType.BANK_SYSTEM && BANK_SYSTEM_DEBUG) {
			printToGUI("BankSystem: " + s + "\n");
		} //else if (type == LogType.)
	}
	public static void print(String s) {
		System.out.println(s);
	}
	
	public static void printToGUI(String s){
		textArea.setValue(s);
	}
}
