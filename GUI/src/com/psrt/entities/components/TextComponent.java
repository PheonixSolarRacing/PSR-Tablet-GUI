package com.psrt.entities.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public class TextComponent extends ValueComponent<String, Label>{

	public TextComponent(String text, String reference, Label label){
		this(text, reference, label, -1);
	}
	
	public TextComponent(String text, String reference, Label label, int timeout){
		super(new SimpleStringProperty(text), label, reference, timeout);
		setValue(text);
		SimpleStringProperty p = new SimpleStringProperty();
		p.setValue(text);
		super.initialValue = p;
	}

	@Override
	public void update() {
		super.element.setText(super.getValue());
	}
}
