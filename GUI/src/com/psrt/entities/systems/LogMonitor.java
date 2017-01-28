package com.psrt.entities.systems;

public class LogMonitor {
	public static enum LogType {
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
	
	public static void log(String s, LogType type) {
		if (MAIN_DEBUG) {
			if (type == LogType.SERIAL_PARSER_PARSE && SERIAL_PARSER_PARSE_DEBUG) {
				print("SerialParser.Parse: " + s);
			} else if (type == LogType.SERIAL_READER && SERIAL_READER_DEBUG) {
				print("SerialReader: " + s);
			} else if (type == LogType.SERIAL_MONITOR && SERIAL_MONITOR_DEBUG) {
				print("SerialMonitor: " + s);
			} else if (type == LogType.UITHREAD && UITHREAD_DEBUG) {
				print("UIThread: " + s);
			} else if (type == LogType.BANK_SYSTEM && BANK_SYSTEM_DEBUG) {
				print ("BankSystem: " + s);
			} //else if (type == LogType.)
		}
	}
	public static void print(String s) {
		System.out.println(s);
	}
}
