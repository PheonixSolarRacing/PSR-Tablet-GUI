package com.psrt.entities.components;

import com.artemis.Component;

import javafx.beans.property.Property;
import javafx.scene.control.Control;

public abstract class ValueComponent<V, T extends Control> extends Component {
	protected Property<V> value;
	protected T element;
	protected String reference;
	
	protected boolean hasChanged = false;
	
	public ValueComponent(T element, String reference){
		this.element = element;
		this.reference = reference;
	}
	
	public abstract void setValue(V value);
	
	public abstract V getValue();
	
	public boolean hasChanged(){
		return this.hasChanged;
	}
	
	public String getReference(){return this.reference;}
	
	public void reset(){this.hasChanged = false;}
}
