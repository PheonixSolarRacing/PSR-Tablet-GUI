package com.psrt.entities.components;

import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

import com.psrt.containers.AbstractID;
import com.psrt.containers.AbstractValue;
import com.psrt.containers.PSRValue;


public class DepositBox {
	//private ArrayListValuedHashMap<CanID, CanValue> box;
	//@SuppressWarnings("rawtypes")
	private HashMap<AbstractID, AbstractValue> box;
	private StampedLock lock;
	
	public DepositBox(){
		this(10);
	}

	//@SuppressWarnings("rawtypes")
	public DepositBox(int size){
		//box = new ArrayListValuedHashMap<CanID, CanValue>();
		box = new HashMap<AbstractID, AbstractValue>(10);
		lock = new StampedLock();
	}
	
	//@SuppressWarnings("rawtypes")
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
	 * return a <b>null</b> {@link PSRValue} in the case that the value isn't found or given that the
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
