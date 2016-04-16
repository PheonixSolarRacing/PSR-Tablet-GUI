package com.psrt.containers;

public class CanID {
	public int id;
	public int function;
	public int entry;
	
	public CanID(int id, int function, int entry){
		this.id = id;
		this.function = function;
		this.entry = entry;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}else{
			CanID c = (CanID) obj;
			if(this.id == c.id && this.function == c.function && this.entry == c.entry){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = (id << 15) | (function << 7) | (entry);
		return hash;
	}
	
	@Override
	public String toString(){
		return "CanID: " + id + " " + function + " " + entry + " " + this.hashCode();
	}
}
