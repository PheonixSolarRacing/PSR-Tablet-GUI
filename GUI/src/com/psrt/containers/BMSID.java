package com.psrt.containers;

public class BMSID extends AbstractID{
	public int function;
	
	public BMSID(int id, int func) {
		super(id, ID_TYPE.BMS);
		this.function = func;
	}
	@Override
	public boolean equals(Object obj){
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}else{
			BMSID c = (BMSID) obj;
			if(this.id == c.id){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.id << 8) | this.function;
	}

	@Override
	public String toString() {
		return "BMSID: " + id + ", #"+ hashCode();
	}

}
