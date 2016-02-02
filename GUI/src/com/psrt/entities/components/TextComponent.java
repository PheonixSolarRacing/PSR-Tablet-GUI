package com.psrt.entities.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class TextComponent extends ValueComponent<String, Label>{
	
	public TextComponent(String text, String reference, Label label){
		super(new SimpleStringProperty(), label, reference);
		setValue(text);
	}
	
	@Override
	public void update() {
		super.element.setText(super.getValue());
	}
}
