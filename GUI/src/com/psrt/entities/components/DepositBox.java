package com.psrt.entities.components;

import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

import com.psrt.containers.CanID;
import com.psrt.containers.CanValue;


public class DepositBox {
	//private ArrayListValuedHashMap<CanID, CanValue> box;
	@SuppressWarnings("rawtypes")
	private HashMap<CanID, CanValue> box;
	private StampedLock lock;
	
	public DepositBox(){
		this(10);
	}

	@SuppressWarnings("rawtypes")
	public DepositBox(int size){
		//box = new ArrayListValuedHashMap<CanID, CanValue>();
		box = new HashMap<CanID, CanValue>(10);
		lock = new StampedLock();
	}
	
	@SuppressWarnings("rawtypes")
	public DepositBox put(CanID key, CanValue value){
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
	 * return a <b>null</b> {@link CanValue} in the case that the value isn't found or given that the
	 * box is currently being accessed (usually written to) by another thread
	 * 
	 * @param key - Key to unlock the box and get the value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public CanValue get(CanID key){
		long stamp = lock.tryOptimisticRead();
		CanValue value = null;
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
