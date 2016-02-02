package com.psrt.entities.components;

import com.artemis.Component;

import javafx.beans.property.Property;
import javafx.scene.control.Control;

public abstract class ValueComponent<V, T extends Control> extends Component {
	protected Property<V> value;
	protected T element;
	protected String reference;
	
	protected boolean hasChanged = false;
	
	public ValueComponent(Property<V> value, T element, String reference){
		this.value = value;
		this.element = element;
		this.reference = reference;
	}

	public T getElement(){ return this.element;}
	
	public void setValue(V value){
		this.value.setValue(value);
		this.hasChanged = true;
	}
	
	public V getValue(){
		return this.value.getValue();
	}
	
	public abstract void update();
	
	public boolean hasChanged(){
		return this.hasChanged;
	}
	
	public String getReference(){return this.reference;}
	
	public void reset(){this.hasChanged = false;}
}
