package com.psrt.entities.components;

import com.artemis.Component;

import javafx.beans.property.Property;
import javafx.scene.Node;

public abstract class ValueComponent<V, T extends Node> extends Component {
	public static enum ComponentType{
		LABEL,
		PROGRESS_BAR,
		IMAGE,
		TEXT_AREA;
	}
	
	protected Property<V> value;
	protected Property<V> initialValue;
	protected T element;
	protected String reference;
	
	protected boolean hasChanged = false;
	private int timeout;
	private long lastWrite;
	
	private ComponentType type;
	
	public ValueComponent(Property<V> value, T element, String reference, ComponentType type){
		this(value, element, reference, type, -1);
	}
	
	public ValueComponent(Property<V> value, T element, String reference, ComponentType type, int timeout){
		this.value = value;
		this.element = element;
		this.reference = reference;
		this.timeout = timeout;
		this.type = type;
	}
	
	public T getElement(){ return this.element;}
	
	public synchronized void setValue(V value){
		V oldValue = this.value.getValue();
		//System.out.println("Old value: " + oldValue.toString() + ", new value: " + value.toString());
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
	
	public ComponentType getType(){return this.type;}

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
