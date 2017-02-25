package com.psrt.entities.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

/**
 * Component for JavaFX labels.  Holds the data about them. This Component is used as part of the Artemis Entity system
 * @author austi
 *
 */
public class LabelComponent extends ValueComponent<String, Label>{
	private String suffix = "";
	private String prefix = "";

	public LabelComponent(String text, String reference, Label label){
		this(text, reference, label, -1);
	}
	
	public LabelComponent(String text, String reference, Label label, int timeout){
		this(text, reference, label, timeout, "");
	}
	
	public LabelComponent(String text, String reference, Label label, int timeout, String suffix){
		this(text, reference, label, timeout, suffix, "");
	}
	
	public LabelComponent(String text, String reference, Label label, int timeout, String suffix, String prefix){
		super(new SimpleStringProperty(text), label, reference, ComponentType.LABEL, timeout);
		this.suffix = suffix;
		this.prefix = prefix;
		setValue(text);
		SimpleStringProperty p = new SimpleStringProperty();
		p.setValue(text);
		super.initialValue = p;
	}

	@Override
	public void update() {
		super.element.setText(prefix + super.getValue() + suffix);
	}
	
	public void setSuffix(String s){
		this.suffix = s;
	}
	
	public void setPrefix(String p){
		this.prefix = p;
	}
}
