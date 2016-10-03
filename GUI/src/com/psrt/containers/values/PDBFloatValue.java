package com.psrt.containers.values;

import com.psrt.containers.PDBValue;

public class PDBFloatValue extends PDBValue<Float> {
	public PDBFloatValue(float floatValue, byte[] bytes) {
		super(PDBValueType.FLOAT, floatValue, bytes);
	}
}
