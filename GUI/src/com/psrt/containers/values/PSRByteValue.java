package com.psrt.containers.values;

import com.psrt.containers.PSRValue;

public class PSRByteValue extends PSRValue<Byte>{
	
	public PSRByteValue(byte byteValue, byte[] bytes) {
		super(PDBValueType.BYTE, byteValue, bytes);
	}
}
