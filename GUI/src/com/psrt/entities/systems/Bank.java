package com.psrt.entities.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.psrt.containers.CanID;
import com.psrt.containers.CanValue;

/**
 * Object used to pass CAN values between the threads. 
 * I've attempted to make it analogous to a bank in that an object can place 
 * values in its bucket under a specific key, and then later get that value (or all values) placed under that key.
 * 
 * It is also analagous in that if another object is writing to the Bank, then the thread must wait until it has its turn.
 * Synchronization is implemented through {@link StampedLock} but this SHOULD NOT block accessor threads. In the case that
 * the Bank is in use then a value of null will be returned to the accessor.
 * @author austi
 *
 */ 
public class Bank {
	private ArrayListValuedHashMap<CanID, CanValue> box;
	private StampedLock lock;
	
	public Bank(){
		box = new ArrayListValuedHashMap<CanID, CanValue>();
		lock = new StampedLock();
	}
	
	public void put(CanID key, CanValue value){
		long stamp = lock.writeLock();
		try{
			box.put(key, value);
		}finally{
			lock.unlockWrite(stamp);
		}
	}
	
	public void put(CanID key, Iterable<? extends CanValue> value){
		long stamp = lock.writeLock();
		try{
			box.putAll(key, value);
		}finally{
			lock.unlockWrite(stamp);
		}
	}
	
	/**
	 * 
	 * Note: THIS IS THE SLOWEST IMPLEMENTATION OF THIS FUNCTION. The given array must be copied to an Iterable Object ( an {@link ArrayList}, at this point)
	 * before being placed into the Bank
	 * @param key
	 * @param value
	 */
	public void put(CanID key, CanValue[] value){
		List<CanValue> values = new ArrayList<CanValue>();
		for(int i = 0; i < value.length; i++){
			values.add(value[i]);
		}
		long stamp = lock.writeLock();
		try{
			box.putAll(key, values);
		}finally{
			lock.unlockWrite(stamp);
		}
	}
	
	/**
	 * Gets the values out of the Bank based on the given key. This is allowed to 
	 * return <b>null</b> in the case that the values aren't found or given that the Bank
	 * box is currently being accessed (usually written to) by another thread
	 * 
	 * @param key - Key to unlock the box and get the value
	 * @return
	 */
	public List<CanValue> get(CanID key){
		List<CanValue> v = null;
		long stamp = lock.tryOptimisticRead();
		try{
			if(lock.validate(stamp)){
				v = box.get(key);
			}
		}finally{
			lock.unlockRead(stamp);
		}
		return v;
	}
	
}
