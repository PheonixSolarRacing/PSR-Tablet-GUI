package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.psrt.entities.components.TextAreaComponent;
import com.psrt.entities.systems.LogMonitor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class ErrorsTab {
	public static void clear_txt_area_errors(Node n) {
		Button b = (Button) n;
		b.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				//System.out.println("Hello!");
				LogMonitor.textArea.getElement().clear();
			}
		});
	}
	public static void txt_area_errors(Node n, World world) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextAreaComponent("Hello\n", (TextArea) n, "txt_area_errors"));
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

