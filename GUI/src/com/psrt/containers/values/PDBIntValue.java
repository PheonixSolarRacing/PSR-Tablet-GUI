package com.psrt.containers.values;

import com.psrt.containers.PDBValue;

public class PDBIntValue extends PDBValue<Integer>{
	public PDBIntValue(int intValue, byte[] bytes) {
		super(PDBValueType.INT, intValue, bytes);
	}
}
