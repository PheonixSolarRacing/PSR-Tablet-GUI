package com.psrt.entities.systems;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.StampedLock;

import com.psrt.entities.components.DepositBox;
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
	BlockingQueue<DepositBox> boxes;
	StampedLock lock;
	DictionaryParser dictionary;
	
	public Bank(DictionaryParser dictionary){
		boxes = new ArrayBlockingQueue<DepositBox>(256);
		lock = new StampedLock();
		this.dictionary = dictionary;
	}
	
	public Bank() throws FileNotFoundException, URISyntaxException{
		this(new DictionaryParser(""));
	}
	
	public Bank addBox(DepositBox b){
		
		long stamp = lock.writeLock();
		try{
			if(lock.isWriteLocked()){
				boxes.offer(b);
			}
		}finally{
			lock.unlockWrite(stamp);
		}
		return this;
	}
	
	public Bank addBoxes(DepositBox[] b){
		List<DepositBox> bs = new ArrayList<DepositBox>(b.length);
		for(int i = 0; i < b.length; i++){
			bs.add(b[i]);
		}
		long stamp = lock.writeLock();
		try{
			if(lock.isWriteLocked()){
				boxes.addAll(bs);
			}
		}finally{
			lock.unlockWrite(stamp);
		}
		return this;
	}
	
	public Bank addBoxes(Collection<? extends DepositBox> b){
		long stamp = lock.writeLock();
		try{
			if(lock.isWriteLocked()){
				boxes.addAll(b);
			}
		}finally{
			lock.unlockWrite(stamp);
		}
		return this;
	}
	
	public DepositBox getTop(){
		return boxes.poll();
	}
	
	public DictionaryParser getDictionary(){
		return this.dictionary;
	}
}
