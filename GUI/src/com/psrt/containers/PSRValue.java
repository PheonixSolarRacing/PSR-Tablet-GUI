package com.psrt.containers;

public abstract class PSRValue<V extends Number> extends AbstractValue{
	public static enum PDBValueType{
		FLOAT,
		BYTE,
		INT;
	}
	private PDBValueType type;
	//protected byte[] bytes;
	protected V value;
	
	public PSRValue(PDBValueType type, V value, byte[] bytes){
		super(bytes);
		this.type = type;
		this.value = value;
	}
	
	public byte[] getBytes(){
		return bytes;
	}
	
	public PDBValueType getType(){
		return this.type;
	}
	
	public V getValue(){
		return this.value;
	}
}