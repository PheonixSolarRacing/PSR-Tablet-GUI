package com.psrt.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySubscription;
import com.artemis.EntitySystem;
import com.artemis.utils.IntBag;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.LabelComponent;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextAreaComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.ValueComponent;
import com.psrt.main.Main;

public class ValueSystem extends EntitySystem {
	EntitySubscription sub;
	ComponentMapper<TimingComponent> timeM;
		
	long ticks = 0;
	private Main main;

	public ValueSystem(Main main) {
		super(Aspect.one(LabelComponent.class, ProgressComponent.class, ImageComponent.class, TextAreaComponent.class));
		this.main = main;
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
		else if(ticks == 1){
			defaultValues(sub.getEntities());
		}
		else{
			IntBag b = sub.getEntities();
			for(int i = 0; i < b.size(); i++){
				process(b.get(i));
			}
		}
		
		ticks++;
	}
	
	private void defaultValues(IntBag b){
		for(int i = 0; i < b.size(); i++){
			main.sendToUI(world.getEntity(b.get(i)));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void process(int entityId){
		Entity e = world.getEntity(entityId);
		//just for testing purposes

		TimingComponent t = timeM.getSafe(entityId);
		
		ValueComponent v = main.getValueFactory().getValue(entityId);
		
		boolean hasChanged = v.hasChanged();

		if(hasChanged){
			if(t != null){
				if(t.available()){
					main.sendToUI(e);
					t.makeUnavailable();
				}
			}else{
				main.sendToUI(e);
			}
			v.reset();
		}
		if(v.timeOut() > 0){
			if(System.currentTimeMillis() - v.lastWrite() > v.timeOut()){
				v.setValue(v.getInitialValue());
			}
		}
	}
}
