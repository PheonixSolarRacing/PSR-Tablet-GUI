package com.psrt.containers;

public abstract class PSRValue<V extends Number> extends AbstractValue{
	public static enum PSRValueType{
		FLOAT,
		BYTE,
		INT;
	}
	private PSRValueType type;
	//protected byte[] bytes;
	protected V value;
	
	public PSRValue(PSRValueType type, V value, byte[] bytes){
		super(bytes);
		this.type = type;
		this.value = value;
	}
	
	public byte[] getBytes(){
		return bytes;
	}
	
	public PSRValueType getType(){
		return this.type;
	}
	
	public V getValue(){
		return this.value;
	}
}