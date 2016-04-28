package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class ByteValue extends CanValue<Byte>{
	
	public ByteValue(byte byteValue, byte[] bytes) {
		super(CanValueType.BYTE, byteValue, bytes);
	}
}
