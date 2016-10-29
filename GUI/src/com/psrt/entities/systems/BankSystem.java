package com.psrt.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.EntitySystem;
import com.artemis.utils.IntBag;
import com.psrt.containers.AbstractID;
import com.psrt.containers.AbstractValue;
import com.psrt.containers.BMSID;
import com.psrt.containers.PDBID;
import com.psrt.entities.components.DepositBox;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;

public class BankSystem extends EntitySystem {
	EntitySubscription sub;
	
	ComponentMapper<PDBID> cm;
	ComponentMapper<BMSID> bm;
	ComponentMapper<TextComponent> tm;
	ComponentMapper<ProgressComponent> pm;
	ComponentMapper<ImageComponent> im;
		
	long ticks = 0;
	private Bank bank;
	
	private boolean debug = false;

	public BankSystem(Bank b) {
		super(Aspect.one(PDBID.class, BMSID.class));
		this.bank = b;
	}
	
	@Override
	protected void begin(){
		sub = super.getSubscription();
	}

	@Override
	protected void processSystem() {
		if(ticks == 0){
			cm = world.getMapper(PDBID.class);
			bm = world.getMapper(BMSID.class);
			tm = world.getMapper(TextComponent.class);
			pm = world.getMapper(ProgressComponent.class);
			im = world.getMapper(ImageComponent.class);
		}
		else{
			IntBag b = sub.getEntities();
			DepositBox box = bank.getTop();
			
			/*
			 * Note here: Needing to cycle through each entity against every HashMap of data that comes through
			 * sucks and is inefficient.  However, if I used only one HashMap total it would have to be constantly cleared and recreated anyway.
			 * Also, behavior is undefined if the HashMaps were allowed to contain data from multiple data packets, since there could be multiple pieces
			 * of data that refer to the same GUI object (multiple datas with the same key-hash combo). Which would be recieved? For now, this is probably the best solution. 
			 */
			
			for(int i = 0; i < b.size(); i++){
				if(box != null) process(b.get(i), box);
				else {
					if(debug) LogMonitor.print("Top box is null, ");
				}
			}
		}
		ticks++;
	}
	
	/**
	 * Unfortunately, in order to deal with non-numerical values (strings) each component type must be handled separately here.
	 * This may be handled later as it is in {@link ValueSystem}, but for now this is how it be.
	 * When adding new component types remember to add a component getter here along with its mapper, as is done with 
	 * examples below. Remember to also put the paper initialization method in procesSystem
	 * Ex: ExampleComponent ec = exampleMapper.getSafe(entityId);
	 * This also takes place in the {@link ValueFactory} in the getValue method.
	 * @param entityId
	 * @param box
	 */
	private void process(int entityId, DepositBox box){
		AbstractID id = null;
		
		PDBID pid = cm.getSafe(entityId);
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
				if(debug) LogMonitor.print("BankSystem.process(): Value - " + value.getValue() + ", Hash: " + id.hashCode());
			}else if(pc != null){
				pc.setValue(value.getValue());
			}else if(ic != null){
				//System.out.println("Image: " + ic.getReference());
				ic.setValue(value.getValue());
			}
		}
	}
}
