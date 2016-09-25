package com.psrt.containers.values;

import com.psrt.containers.PSRValue;

public class PSRIntValue extends PSRValue<Integer>{
	public PSRIntValue(int intValue, byte[] bytes) {
		super(PDBValueType.INT, intValue, bytes);
		// TODO Auto-generated constructor stub
	}
}
