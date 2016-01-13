package com.psrt.entities.components;

import com.artemis.Component;

public class TimingComponent extends Component{
	public static enum TimingType{
		ASTABLE,
		MONOSTABLE;
	}
	
	private int delay;
	private long lastHit;
	private TimingType type;
	private boolean triggered = false; //this pertains to monostable only
	private boolean available = false;
	
	public TimingComponent(int delay, TimingType type){
		this.delay = delay;
		this.type = type;
		this.lastHit = System.currentTimeMillis();
	}
	
	public TimingType getType(){return this.type;}
	
	public int getDelay(){return this.delay;}
	
	public void update(){
		lastHit = System.currentTimeMillis();
		triggered = true;
		makeAvailable();
	}
	
	public void resetTrigger(){this.triggered = false;}
	
	public long getLastUpdate(){
		return this.lastHit;
	}
	
	public boolean triggered(){return this.triggered;}
	
	public boolean available(){return this.available;}
	public void makeAvailable(){this.available = true;}
	public void makeUnavailable(){this.available = false;}
}
