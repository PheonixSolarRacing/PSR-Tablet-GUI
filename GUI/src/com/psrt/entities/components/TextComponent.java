package com.psrt.entities.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class TextComponent extends ValueComponent<String, Label>{
	private Label label;
	
	public TextComponent(String text, String reference, Label label){
		super(label, reference);
		this.value = new SimpleStringProperty();
		this.label = label;
		//label.textProperty().bind(value);
		setValue(text);
	}

	@Override
	public void setValue(String value) {
		//System.out.println("Setting value to " + value);
		((SimpleStringProperty) this.value).set(value);
		this.hasChanged = true;
	}
	
	public Label getLabel(){
		return this.label;
	}
	
	public String getReference() {
		return super.reference;
	}

	@Override
	public String getValue() {
		return this.value.getValue();
	}
}
