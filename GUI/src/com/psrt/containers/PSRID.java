package com.psrt.containers;

public class PSRID extends AbstractID{
//	public int id;
	public int function;
	public int entry;
	
	public PSRID(int id, int function, int entry){
		super(id, ID_TYPE.PSR);
		this.id = id;
		this.function = function;
		this.entry = entry;
	}
	
	public PSRID() {
		this(-1, -1, -1);
	}

	@Override
	public boolean equals(Object obj){
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}else{
			PSRID c = (PSRID) obj;
			if(this.id == c.id && this.function == c.function && this.entry == c.entry){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = (id << 16) | (function << 8) | (entry);
		return hash;
	}
	
	@Override
	public String toString(){
		return "CanID: " + id + ", " + function + ", " + entry + ", #" + this.hashCode();
	}
}
