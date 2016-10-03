package com.psrt.containers;

import com.psrt.containers.PDBValue.PDBValueType;

public class PDBCSVInfo {
	PDBValueType type;
	int start;
	
	public PDBCSVInfo(PDBValueType type, int start){
		this.type = type;
		this.start = start;
	}
	
	public PDBValueType getType(){
		return this.type;
	}
	
	public int startIndex(){
		return this.start;
	}
	
}
