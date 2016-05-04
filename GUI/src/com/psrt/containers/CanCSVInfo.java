package com.psrt.containers;

import com.psrt.containers.CanValue.CanValueType;

public class CanCSVInfo {
	CanValueType type;
	int start;
	
	public CanCSVInfo(CanValueType type, int start){
		this.type = type;
		this.start = start;
	}
	
	public CanValueType getType(){
		return this.type;
	}
	
	public int startIndex(){
		return this.start;
	}
	
}
