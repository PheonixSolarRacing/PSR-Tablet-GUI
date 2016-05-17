package com.psrt.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.EntitySystem;
import com.artemis.utils.IntBag;
import com.psrt.containers.AbstractID;
import com.psrt.containers.AbstractValue;
import com.psrt.containers.BMSID;
import com.psrt.containers.PSRID;
import com.psrt.entities.components.DepositBox;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;

public class BankSystem extends EntitySystem {
	EntitySubscription sub;
	
	ComponentMapper<PSRID> cm;
	ComponentMapper<BMSID> bm;
	ComponentMapper<TextComponent> tm;
	ComponentMapper<ProgressComponent> pm;
	ComponentMapper<ImageComponent> im;
		
	long ticks = 0;
	private Bank bank;
	
	private boolean debug = false;

	public BankSystem(Bank b) {
		super(Aspect.one(PSRID.class, BMSID.class));
		this.bank = b;
	}
	
	@Override
	protected void begin(){
		sub = super.getSubscription();
	}

	@Override
	protected void processSystem() {
		if(ticks == 0){
			cm = world.getMapper(PSRID.class);
			bm = world.getMapper(BMSID.class);
			tm = world.getMapper(TextComponent.class);
			pm = world.getMapper(ProgressComponent.class);
			im = world.getMapper(ImageComponent.class);
		}
		else{
			IntBag b = sub.getEntities();
			DepositBox box = bank.getTop();
			for(int i = 0; i < b.size(); i++){
				if(box != null) process(b.get(i), box);
				else {
					if(debug) System.out.println("Top box is null, ");
				}
			}
		}
		ticks++;
	}
	
	@SuppressWarnings("rawtypes")
	private void process(int entityId, DepositBox box){
		AbstractID id = null;
		
		PSRID pid = cm.getSafe(entityId);
		BMSID bid = bm.getSafe(entityId);
		AbstractValue value = null;
		
		if(pid != null) id = pid;
		else if(bid != null) id = bid;
		
		if(id != null) {
			value = box.get(id);
		}
		TextComponent tc = tm.getSafe(entityId);
		ProgressComponent pc = pm.getSafe(entityId);
		ImageComponent ic = im.getSafe(entityId);
		
		if(value != null){
			if(tc != null){
				tc.setValue("" + value.getValue());
				if(debug) System.out.println("BankSystem.process(): Value - " + value.getValue() + ", Hash: " + id.hashCode());
			}else if(pc != null){
				pc.setValue(value.getValue());
			}else if(ic != null){
				//System.out.println("Image: " + ic.getReference());
				ic.setValue(value.getValue());
			}
		}
	}
}
