package com.psrt.entities.systems;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

import com.psrt.containers.AbstractID;
import com.psrt.containers.AbstractValue;
import com.psrt.parsers.DictionaryParser;


/**
* Object used to pass CAN values between the threads. 
* I've attempted to make it analogous to a bank in that an object can place 
* values in its depositBox under a specific key, and then later get that value (or all values) placed under that key.
* 
* It is also analagous in that if another object is writing to the box, then the thread must wait until it has its turn.
* Synchronization is implemented through {@link StampedLock} but this SHOULD NOT block accessor threads. In the case that
* the Bank is in use then a value of null will be returned to the accessor.
* @author austi
*
*/ 
public class Bank {
	ConcurrentHashMap<AbstractID, AbstractValue> map;
	StampedLock lock;
	DictionaryParser dictionary;
	
	public Bank(DictionaryParser dictionary){
		map = new ConcurrentHashMap<AbstractID, AbstractValue>(4096);
		lock = new StampedLock();
		this.dictionary = dictionary;
	}
	
	public Bank() throws FileNotFoundException, URISyntaxException{
		this(new DictionaryParser(""));
	}
	
	public Bank put(AbstractID id, AbstractValue value){
		
		long stamp = lock.writeLock();
		try{
			if(lock.isWriteLocked()){
				map.put(id, value);
			}
		}finally{
			lock.unlockWrite(stamp);
		}
		return this;
	}
	
	public AbstractValue get(AbstractID key){
		long stamp = lock.tryOptimisticRead();
		AbstractValue value = null;
		try{
			if(lock.validate(stamp)){
				value = map.get(key);
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
	
	public DictionaryParser getDictionary(){
		return this.dictionary;
	}
}
