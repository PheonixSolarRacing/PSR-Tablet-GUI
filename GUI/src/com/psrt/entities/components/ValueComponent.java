package com.psrt.entities.components;

import com.artemis.Component;

import javafx.beans.property.Property;
import javafx.scene.control.Control;

public abstract class ValueComponent<V, T extends Control> extends Component {
	protected Property<V> value;
	protected Property<V> initialValue;
	protected T element;
	protected String reference;
	
	protected boolean hasChanged = false;
	private int timeout;
	private long lastWrite;
	
	public ValueComponent(Property<V> value, T element, String reference){
		this.value = value;
		this.element = element;
		this.reference = reference;
		this.timeout = -1;
	}
	
	public ValueComponent(Property<V> value, T element, String reference, int timeout){
		this(value, element, reference);
		this.timeout = timeout;
	}
	public T getElement(){ return this.element;}
	
	public synchronized void setValue(V value){
		V oldValue = this.value.getValue();
		//System.out.println("Old value: " + oldValue + ", new value: " + value);
		if(!oldValue.equals(value)){
			//System.out.println(reference + "Has changed");
			this.value.setValue(value);
			this.hasChanged = true;
		}else{
			//System.out.println(reference + "Hasn't changed");
		}
		this.lastWrite = System.currentTimeMillis();
	}
	
	public V getValue(){
		return this.value.getValue();
	}
	
	public abstract void update();
	
	public boolean hasChanged(){
		return this.hasChanged;
	}

	public String getReference(){return this.reference;}
	
	public V getInitialValue(){
		return initialValue.getValue();
	}	
	
	public long lastWrite(){
		return this.lastWrite;
	}
	
	public int timeOut(){
		return this.timeout;
	}
	
	public void reset(){this.hasChanged = false;}
}
