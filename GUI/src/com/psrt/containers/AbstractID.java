package com.psrt.containers;

public abstract class AbstractID  extends com.artemis.Component{
	public int id;
	public ID_TYPE type;
	
	public static enum ID_TYPE{
		BMS,
		MOTOR_CONTROLLERS,
		PSR;
	}
	
	public AbstractID(int id, ID_TYPE type){
		this.id = id;
		this.type = type;
	}
	
	@Override
	public abstract boolean equals(Object obj);
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract String toString();
}
