package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class IntValue extends CanValue<Integer>{
	public IntValue(int intValue, byte[] bytes) {
		super(CanValueType.INT, intValue, bytes);
		// TODO Auto-generated constructor stub
	}
}
