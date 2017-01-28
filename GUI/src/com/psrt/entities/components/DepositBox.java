package com.psrt.entities.components;

import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

import com.psrt.containers.AbstractID;
import com.psrt.containers.AbstractValue;
import com.psrt.containers.PDBValue;


public class DepositBox {
	
	/*Note on this.  May get rid of DepositBoxes completely in the future
	* and instead use ArrayListValuedHashMap directly in the bank class since it's
	* a very similar data structure that may be more efficient.
	*/
	//private ArrayListValuedHashMap<AbstractID, AbstractValue> box2;

	private HashMap<AbstractID, AbstractValue> box;
	private StampedLock lock;
	
	public DepositBox(){
		this(10);
	}

	
	public DepositBox(int size){
		//box2 = new ArrayListValuedHashMap<AbstractID, AbstractValue>();
		box = new HashMap<AbstractID, AbstractValue>(size);
		lock = new StampedLock();
	}

	public DepositBox put(AbstractID key, AbstractValue value){
		long stamp = lock.writeLock();
		try{
			if(lock.isWriteLocked()) {
				box.put(key, value);
			}
		}finally{
			lock.unlockWrite(stamp);
		}
		return this;
	}
	
	/**
	 * Gets the value out of the box based on the given key. This is allowed to 
	 * return a <b>null</b> {@link PDBValue} in the case that the value isn't found or given that the
	 * box is currently being accessed (usually written to) by another thread
	 * 
	 * @param key - Key to unlock the box and get the value
	 * @return
	 */
	//@SuppressWarnings("rawtypes")
	public AbstractValue get(AbstractID key){
		long stamp = lock.tryOptimisticRead();
		AbstractValue value = null;
		try{
			if(lock.validate(stamp)){
				value = box.get(key);
			}
		}finally{
			try{
				lock.unlockRead(stamp);
			}catch(IllegalMonitorStateException e){
				//System.out.println("Lock mixup");
				//I dunno, ignore it? Hmm
			}
		}
		return value;
	}
}
