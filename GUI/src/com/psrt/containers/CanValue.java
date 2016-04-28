package com.psrt.containers;

public abstract class CanValue<V extends Number> {
	public static enum CanValueType{
		FLOAT,
		BYTE,
		INT;
	}
	private CanValueType type;
	protected byte[] bytes;
	
	protected V value;
	
	public CanValue(CanValueType type, V value, byte[] bytes){
		this.type = type;
		this.value = value;
	}
	
	public byte[] getBytes(){
		return bytes;
	}
	
	public CanValueType getType(){
		return this.type;
	}
	
	public V getValue(){
		return this.value;
	}
}
