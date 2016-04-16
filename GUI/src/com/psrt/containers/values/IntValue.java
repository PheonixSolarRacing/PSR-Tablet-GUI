package com.psrt.containers.values;

import com.psrt.containers.CanValue;

public class IntValue extends CanValue{
	private int intValue;
	public IntValue(int intValue, byte[] bytes) {
		super(CanValueType.INT, bytes);
		// TODO Auto-generated constructor stub
	}
	
	public int getValue(){
		return this.intValue;
	}
}
