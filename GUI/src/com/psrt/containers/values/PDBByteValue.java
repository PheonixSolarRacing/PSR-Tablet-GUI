package com.psrt.containers.values;

import com.psrt.containers.PDBValue;

public class PDBByteValue extends PDBValue<Byte>{
	
	public PDBByteValue(byte byteValue, byte[] bytes) {
		super(PDBValueType.BYTE, byteValue, bytes);
	}
}
