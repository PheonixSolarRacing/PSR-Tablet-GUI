package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class FloatValue extends CanValue {
	float floatValue;
	
	public FloatValue(float floatValue, byte[] bytes) {
		super(CanValueType.FLOAT, bytes);
		this.floatValue = floatValue;
	}
	
	public float getFloatValue(){
		return this.floatValue;
	}
}
