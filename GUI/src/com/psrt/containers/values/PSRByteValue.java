package com.psrt.containers.values;

import com.psrt.containers.PSRValue;

public class PSRByteValue extends PSRValue<Byte>{
	
	public PSRByteValue(byte byteValue, byte[] bytes) {
		super(PSRValueType.BYTE, byteValue, bytes);
	}
}
