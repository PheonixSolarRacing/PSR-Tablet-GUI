package com.psrt.entities.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextArea;

public class TextAreaComponent extends ValueComponent<String, TextArea> {
	
	
	public TextAreaComponent(String initValue, TextArea txtArea, String reference) {
		super(new SimpleStringProperty(initValue), txtArea, reference, ComponentType.TEXT_AREA);
		super.initialValue = super.value;
		setValue(initValue);
		
	}

	@Override
	public void update() {
		//super.hasChanged = true;
		if(!super.value.getValue().equals("")) super.element.appendText(super.getValue());
		super.value = new SimpleStringProperty("");
		
	}
}
