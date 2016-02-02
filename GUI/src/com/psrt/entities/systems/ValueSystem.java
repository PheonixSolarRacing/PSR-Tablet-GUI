package com.psrt.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySubscription;
import com.artemis.EntitySystem;
import com.artemis.utils.IntBag;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.ValueComponent;
import com.psrt.main.Main;

public class ValueSystem extends EntitySystem {
	EntitySubscription sub;
	
	ComponentMapper<TextComponent> tm;
	ComponentMapper<ProgressComponent> pm;
	ComponentMapper<TimingComponent> timeM;
		
	long ticks = 0;
	private Main main;

	public ValueSystem(Main main) {
		super(Aspect.one(TextComponent.class, ProgressComponent.class));
		this.main = main;
	}
	
	@Override
	protected void begin(){
		sub = super.getSubscription();
		
	}

	@Override
	protected void processSystem() {
		if(ticks == 0){
			tm = world.getMapper(TextComponent.class);
			timeM = world.getMapper(TimingComponent.class);
			pm = world.getMapper(ProgressComponent.class);
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
	
	int count = 38;
	float voltage = 0.0f;
	boolean toggle = true;
	
	private void process(int entityId){
		Entity e = world.getEntity(entityId);
		//just for testing purposes
		TextComponent tc = tm.getSafe(entityId);
		ProgressComponent pc = (tc == null) ? pm.getSafe(entityId) : null;
		TimingComponent t = timeM.getSafe(entityId);
		
		@SuppressWarnings("rawtypes")
		ValueComponent v = null;
		
		//if tc isn't null set it to v, if tc is null then set pc to v, if pc is null then set v to null
		v = ((tc != null) ? tc : ((pc != null) ? pc : null));
		
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
	}
}
