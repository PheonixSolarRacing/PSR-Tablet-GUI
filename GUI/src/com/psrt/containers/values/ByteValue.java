package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class ByteValue extends CanValue{
	private byte byteValue;
	
	public ByteValue(byte byteValue, byte[] bytes) {
		super(CanValueType.BYTE, bytes);
		this.byteValue = byteValue;
	}
	
	public byte getValue(){
		return this.byteValue;
	}
}
