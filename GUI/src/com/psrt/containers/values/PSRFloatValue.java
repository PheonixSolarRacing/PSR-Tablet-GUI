package com.psrt.containers.values;

import com.psrt.containers.PSRValue;

public class PSRFloatValue extends PSRValue<Float> {
	public PSRFloatValue(float floatValue, byte[] bytes) {
		super(PSRValueType.FLOAT, floatValue, bytes);
	}
}
