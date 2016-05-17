package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.containers.BMSID;
import com.psrt.containers.BMSValue;
import com.psrt.entities.components.DepositBox;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent.TimingType;
import com.psrt.entities.systems.Bank;
import com.psrt.guitabs.factories.MyEntityFactory;
import com.psrt.guitabs.factories.MyEntityFactory.ElementType;
import com.psrt.threads.SerialMonitor;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class BMSTab{
	public static void SOC_Label(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "SOC_Label", (Label) n, SerialMonitor.TIMEOUT, "Ah"));
		edit.add(new BMSID(0x6F4, 0));
	}
	public static void soc_indicator(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.ProgressBar, "0", "soc_indicator");
		//MyEntityFactory.addTimer(e, 200, TimingType.ASTABLE);
		EntityEdit edit = e.edit();
		edit.add(new BMSID(0x6F4, 1));
	}
	
	public static void BMS_TREE(int id, byte[] data, DepositBox box){
		BMSValue v = null;
		//System.out.println("Data len: " + data.length);
		switch(id){
			case 0x6F4: //Pack SOC -----------------------------------------
				v = new BMSValue(data){ //data_fp[0]
					@Override
					public Number getValue(){
						byte[] floatValue = new byte[]{
							data[0],
							data[1],
							data[2],
							data[3]
						};
						float val = bytesToFloat(floatValue);
						return val;
					}
				};
				box.put(new BMSID(0x6F4, 0), v);
				
				v = new BMSValue(data){ //data_fp[1]
					@Override
					public Number getValue(){
						byte[] floatValue = new byte[]{
							data[4],
							data[5],
							data[6],
							data[7]
						};
						float val = bytesToFloat(floatValue) / 100;
						return val;
					}
				};
				box.put(new BMSID(0x6F4, 1), v);
				break;
				// end Pack SOC ---------------------------------------------
		}
	}
	
	public static float bytesToFloat(byte[] bytes, int index){
		int f = (( bytes[index]      + 128)  << 24) | 
				(((bytes[index + 1]  + 128)) << 16) | 
				(((bytes[index + 2]  + 128)) << 8)  | 
				(( bytes[index + 3]  + 128));
		 
		return Float.intBitsToFloat(f);
	}
 

	public static float bytesToFloat(byte[] bytes){
		return bytesToFloat(bytes, 0);
	}
	 
	//@SuppressWarnings("unused")
	private byte[] subArray(byte[] b, int start, int len){
		byte[] n = new byte[len];
		for(int i = 0; i < len; i++){
			n[i] = b[i + start];
		}
		return n;
	}
	
}
