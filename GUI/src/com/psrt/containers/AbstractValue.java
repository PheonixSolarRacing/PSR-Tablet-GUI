package com.psrt.containers;

public abstract class AbstractValue {
	protected byte[] bytes;
	
	public AbstractValue(byte[] bytes){
		this.bytes = bytes;
	}
	
	public abstract Number getValue();
}
