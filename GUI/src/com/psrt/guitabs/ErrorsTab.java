package com.psrt.guitabs;

import com.psrt.entities.systems.LogMonitor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class ErrorsTab {
	public static void txt_area_errors(Node n){
		
	}
	
	public static void chk_main_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.MAIN_DEBUG = new_val;
	        }
	    });
	}
	public static void chk_serial_parser_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.SERIAL_PARSER_PARSE_DEBUG = new_val;
	        }
	    });
	}
	public static void chk_serial_reader_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.SERIAL_READER_DEBUG = new_val;
	        }
	    });
	}
	public static void chk_serial_monitor_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.SERIAL_MONITOR_DEBUG = new_val;
	        }
	    });
	}
	public static void chk_uithread_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.UITHREAD_DEBUG = new_val;
	                System.out.println("Hello: " + new_val.toString());
	                
	        }
	    });
	}
	public static void chk_bank_system_debug(Node n) {
		CheckBox cb = (CheckBox) n;
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	                LogMonitor.BANK_SYSTEM_DEBUG = new_val;
	        }
	    });
	}
}

