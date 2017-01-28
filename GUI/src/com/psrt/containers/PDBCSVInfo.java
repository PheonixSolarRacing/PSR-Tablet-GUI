package com.psrt.containers;

import com.psrt.containers.PDBValue.PDBValueType;

/**
 * In C/C++ this class would be the equivalent of a struct. 
 * It only holds data.  The data it holds is a {@link PDBValueType} to give information
 * about the data type attached to a specific {@link PDBID}, and an <b>int</b> start to describe the start of the data bytes in the data
 * frame in {@link SerialParser}.
 * @author Austin Dibble
 *
 */
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
