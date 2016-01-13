package com.psrt.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.EntitySystem;
import com.artemis.utils.IntBag;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.TimingComponent.TimingType;
import com.psrt.entities.components.ValueComponent;

public class TimingSystem extends EntitySystem{
	EntitySubscription sub;
	ComponentMapper<TimingComponent> timeM;
	
	@SuppressWarnings("rawtypes")
	ComponentMapper<ValueComponent> vm;
	
		
	long ticks = 0;

	public TimingSystem() {
		super(Aspect.all(TimingComponent.class));
	}
	
	@Override
	protected void begin(){
		sub = super.getSubscription();
		
	}

	@Override
	protected void processSystem() {
		if(ticks == 0){
			timeM = world.getMapper(TimingComponent.class);
		}
		else{
			IntBag b = sub.getEntities();
			for(int i = 0; i < b.size(); i++){
				process(b.get(i));
			}
		}
		
		ticks++;
	}
	int count = 38;
	float voltage = 0.0f;
	boolean toggle = true;
	
	private void process(int entityId){
		TimingComponent timeC = timeM.getSafe(entityId);
		//just for testing purposes
		if(timeC.getType() == TimingType.MONOSTABLE){
			if(System.currentTimeMillis() - timeC.getLastUpdate() >= timeC.getDelay() && !timeC.triggered()){ 
				//if the delay time has passed, and the the component hasn't yet been triggered.
				timeC.update();
			}
		}else if(timeC.getType() == TimingType.ASTABLE){
			if(System.currentTimeMillis() - timeC.getLastUpdate() >= timeC.getDelay()){
				//If the time has passed for this 
				timeC.update();
			}
		}
	}
}
