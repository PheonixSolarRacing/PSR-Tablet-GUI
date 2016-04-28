package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class FloatValue extends CanValue<Float> {
	public FloatValue(float floatValue, byte[] bytes) {
		super(CanValueType.FLOAT, floatValue, bytes);
	}
}
