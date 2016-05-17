package com.psrt.containers;

import com.psrt.containers.PSRValue.PSRValueType;

public class PSRCSVInfo {
	PSRValueType type;
	int start;
	
	public PSRCSVInfo(PSRValueType type, int start){
		this.type = type;
		this.start = start;
	}
	
	public PSRValueType getType(){
		return this.type;
	}
	
	public int startIndex(){
		return this.start;
	}
	
}
