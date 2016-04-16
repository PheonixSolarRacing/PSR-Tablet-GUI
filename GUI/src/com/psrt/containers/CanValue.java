package com.psrt.containers;

public class CanValue {
	public static enum CanValueType{
		FLOAT,
		BYTE,
		INT;
	}
	private CanValueType type;
	protected byte[] bytes;
	
	public CanValue(CanValueType type, byte[] bytes){
		this.type = type;
	}
	
	public byte[] getBytes(){
		return bytes;
	}
	
	public CanValueType getType(){
		return this.type;
	}
}
