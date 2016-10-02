package com.psrt.guitabs;

import java.util.Vector;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.containers.BMSID;
import com.psrt.containers.BMSValue;
import com.psrt.containers.ImageHolder;
import com.psrt.entities.components.DepositBox;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.TextComponent;
import com.psrt.threads.SerialMonitor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BMSTab{
	public static void x6F7_u8_0_x08(com.artemis.World world, Node n, ImageHolder imageHolder){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = imageHolder.getImage("relay_off");
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "x6F7_u8_0_x08", imageHolder);
		imageC.addImagePair(0, "relay_off")
			  .addImagePair(1, "relay_on");
		edit.add(imageC);
		edit.add(new BMSID(0x6F7, 0x08));
	}
	
	public static void x6F7_u8_0_x20(com.artemis.World world, Node n, ImageHolder imageHolder){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = imageHolder.getImage("relay_off");
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "x6F7_u8_0_x20", imageHolder);
		imageC.addImagePair(0, "relay_off")
			  .addImagePair(1, "relay_on");
		edit.add(imageC);
		edit.add(new BMSID(0x6F7, 0x20));
	}
	
	public static void x6F7_u8_0_x04(com.artemis.World world, Node n, ImageHolder imageHolder){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = imageHolder.getImage("relay_off");
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "x6F7_u8_0_x04", imageHolder);
		imageC.addImagePair(0, "relay_off")
			  .addImagePair(1, "relay_on");
		edit.add(imageC);
		edit.add(new BMSID(0x6F7, 0x04));
	}
	
	public static void x6FA_u32_0(com.artemis.World world, Node n){ //max cell voltage
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6FA_u32_0", (Label) n, SerialMonitor.TIMEOUT, "mV"));
		edit.add(new BMSID(0x6FA, 0));
	}
	public static void x6FA_u32_1(com.artemis.World world, Node n){ //max cell voltage
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6FA_u32_1", (Label) n, SerialMonitor.TIMEOUT, "mA"));
		edit.add(new BMSID(0x6FA, 1));
	}
	
	public static void x6F8_u16_1(com.artemis.World world, Node n){ //max cell voltage
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6F8_u16_1", (Label) n, SerialMonitor.TIMEOUT, "mV"));
		edit.add(new BMSID(0x6F8, 1));
	}
	
	
	public static void x601_16_2(com.artemis.World world, Node n){ //cmu temp
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x601_16_2", (Label) n, SerialMonitor.TIMEOUT, "V"));
		edit.add(new BMSID(0x601, 2));
	}
	
	public static void x6F9_u16_1(com.artemis.World world, Node n){ //cell temp
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6F9_16_1", (Label) n, SerialMonitor.TIMEOUT, "V"));
		edit.add(new BMSID(0x6F9, 1));
	}
	
	public static void x6F8_u16_0(com.artemis.World world, Node n){ //min cell voltage
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6F8_u16_0", (Label) n, SerialMonitor.TIMEOUT, "V"));
		edit.add(new BMSID(0x6F8, 0));
	}
	
	public static void x6F4_fp_0(com.artemis.World world, Node n){ //SOC Ah
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6F4_data_fp_0", (Label) n, SerialMonitor.TIMEOUT, "Ah"));
		edit.add(new BMSID(0x6F4, 0));
	}
	public static void x6F4_fp_1(com.artemis.World world, Node n){ //SOC %
		//Entity e = MyEntityFactory.createEntity(world, n, ElementType.ProgressBar, "0", "soc_indicator");
		//MyEntityFactory.addTimer(e, 200, TimingType.ASTABLE);
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x6F4_data_fp_1", (Label) n, SerialMonitor.TIMEOUT, "%"));
		edit.add(new BMSID(0x6F4, 1));
	}
	
	public static void x608_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x608_16_0", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x608, 0));
	}
	
	public static void x608_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x608_16_1", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x608, 1));
	}
	
	public static void x608_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x608_16_2", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x608, 2));
	}
	
	public static void x608_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x608_16_3", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x608, 3));
	}
	
	public static void x609_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x609_16_0", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x609, 0));
	}
	
	public static void x609_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x609_16_1", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x609, 1));
	}
	
	public static void x609_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x609_16_2", (Label) n, SerialMonitor.TIMEOUT, "mV"))
			.add(new BMSID(0x609, 2));
	}
	
	public static void x605_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x605_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x605, 0));
	}
	
	public static void x605_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x605_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x605, 1));
	}
	
	public static void x605_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x605_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x605, 2));
	}
	
	public static void x605_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x605_16_3", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x605, 3));
	}
	
	public static void x606_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x606_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x606, 0));
	}
	
	public static void x606_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x606_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x606, 1));
	}
	public static void x606_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x606_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x606, 2));
	}
	public static void x606_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x606_16_3", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x606, 3));
	}
	public static void x604_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x604_16_3", (Label) n, SerialMonitor.TIMEOUT, "*C"))
			.add(new BMSID(0x604, 3));
	}
	
	public static void x60A_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60A_16_3", (Label) n, SerialMonitor.TIMEOUT, "*C"))
			.add(new BMSID(0x60A, 3));
	}
	
	public static void x601_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x601_16_3", (Label) n, SerialMonitor.TIMEOUT, "*C"))
			.add(new BMSID(0x601, 3));
	}
	
	public static void x607_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x607_16_3", (Label) n, SerialMonitor.TIMEOUT, "*C"))
			.add(new BMSID(0x607, 3));
	}
	
	public static void x603_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x603_16_3", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x603, 3));
	}
	
	public static void x603_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x603_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x603, 2));
	}
	public static void x603_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x603_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x603, 1));
	}
	public static void x603_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x603_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x603, 0));
	}
	public static void x602_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x602_16_3", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x602, 3));
	}
	public static void x602_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x602_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x602, 2));
	}
	public static void x602_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x602_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x602, 1));
	}
	public static void x602_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x602_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x602, 0));
	}
	public static void x60C_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60C_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60C, 2));
	}
	public static void x60C_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60C_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60C, 1));
	}
	public static void x60C_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60C_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60C, 0));
	}
	public static void x60B_16_3(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60B_16_3", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60B, 3));
	}
	public static void x60B_16_2(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60B_16_2", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60B, 2));
	}
	public static void x60B_16_1(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60B_16_1", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60B, 1));
	}
	public static void x60B_16_0(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "x60B_16_0", (Label) n, SerialMonitor.TIMEOUT, ""))
			.add(new BMSID(0x60B, 0));
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
				// end Pack SOC 
			//------------------------------------------------------------------------------------				
			case 0x6F7: //Precharge status 
				int f = (int) data[0];
				
				//0x08 - Output status of contactor 2 driver
				if(f == 0x08){
					v = new BMSValue(data){ //data_u8[0]
						
						@Override
						public Number getValue(){
							//TODO
							return 0;
						}
					};
				//0x04 - Output status of contactor 1 driver
				}else if(f == 0x04){
					v = new BMSValue(data){
						@Override
						public Number getValue(){
							//TODO
							return 0;
						}
					};
				//0x20 - Error status of contactor 3 driver
				}else if(f == 0x20){
					v = new BMSValue(data){
						@Override
						public Number getValue(){
							//TODO
							return 0;
						}
					};
				}
				box.put(new BMSID(0x6F7, 0), v);
				break;
				
				//end Precharge status
			//---------------------------------------------------------------------------------
			case 0x6FA: //Battery pack voltage/current
				v = new BMSValue(data){ //data_u32[0]
					@Override
					public Number getValue(){
						byte[] longValue = new byte[]{
							data[0],
							data[1],
							data[2],
							data[3]
						};
						long val = bytesToLong(longValue);
						return val;
					}
				};
				box.put(new BMSID(0x6FA, 0), v);
				
				v = new BMSValue(data){ //data_32[1]
					@Override
					public Number getValue(){
						byte[] longValue = new byte[]{
							data[4],
							data[5],
							data[6],
							data[7]
						};
						long val = bytesToLong(longValue);
						return val;
					}
				};
				box.put(new BMSID(0x6FA, 1), v);
				break;
			//--------------------------------------------------------------------
			case 0x6F8:
				v = new BMSValue(data){ //data_u16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//val is a "16 bit" integer from
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x6F8, 1), v);
				
				v = new BMSValue(data){ //data_u16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//val is a "16 bit" integer from
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x6F8, 0), v);
				break;
			//--------------------------------------------------------------------
			case 0x601:
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//val is a "16 bit" integer from
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x601, 2), v);
				
				v = new BMSValue(data){ //data_u16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//val is 16 bit int
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x601, 3), v);
				break;
			//----------------------------------------------------------------------
			case 0x6F9:
				v = new BMSValue(data){ //data_u16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//val is 16 bit int
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x601, 1), v);
				break;
			//---------------------------------------------------------------------
			case 0x608:
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x608, 0), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x608, 1), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x608, 2), v);
				
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x608, 3), v);
				break;
			//---------------------------------------------------------------------
			case 0x609:
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x609, 0), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x609, 1), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x609, 2), v);
				break;
			//---------------------------------------------------------------------
			case 0x605:
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x605, 0), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x605, 1), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x605, 2), v);
				
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x605, 3), v);
				break;
			//---------------------------------------------------------------------
			case 0x606: //cell 4->7 voltage
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x606, 0), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x606, 1), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x606, 2), v);
				
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x606, 3), v);
				break;
			//---------------------------------------------------------------------
			case 0x604:  //cmu 2 cell temp
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x604, 3), v);
				break;
			//---------------------------------------------------------------------
			case 0x60A:  //cmu 4 cell temp
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x60A, 3), v);
				break;
			//--------------------------------------------------------------------
			case 0x607: //cmu 3 cell temp
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x607, 3), v);
				break;
			//--------------------------------------------------------------------
			case 0x603:
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x603, 3), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x603, 2), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x603, 1), v);
				
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x603, 0), v);
				break;
			//--------------------------------------------------------------------
			case 0x602:
				v = new BMSValue(data){ //data_16[3]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 6);
						//data[6]
						//data[7]
						return val;
					}
				};
				box.put(new BMSID(0x602, 3), v);
				
				v = new BMSValue(data){ //data_16[2]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 4);
						//data[4]
						//data[5]
						return val;
					}
				};
				box.put(new BMSID(0x602, 2), v);
				
				v = new BMSValue(data){ //data_16[1]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 2);
						//data[2]
						//data[3]
						return val;
					}
				};
				box.put(new BMSID(0x602, 1), v);
				
				v = new BMSValue(data){ //data_16[0]
					@Override
					public Number getValue(){
						int val = bytesTo16BitInt(data, 0);
						//data[0]
						//data[1]
						return val;
					}
				};
				box.put(new BMSID(0x602, 0), v);
				break;
			//---------------------------------------------------------------------
			case 0x60C:
				
				break;
		}
	}
	
	public static float bytesToFloat(byte[] bytes, int index){
		int f = (( bytes[index]      + 128)  << 24) | 
				(((bytes[index + 1]  + 128)) << 16) | 
				(((bytes[index + 2]  + 128)) << 8)  | 
				(( bytes[index + 3]  + 128));
		
		return Float.intBitsToFloat(f);
	}
	
	public static int bytesTo16BitInt(byte[] bytes, int index){
		int f = (((bytes[index]      + 128)) << 8)  | 
				(( bytes[index + 1]  + 128));
		
		return f;
	}
 

	public static float bytesToFloat(byte[] bytes){
		return bytesToFloat(bytes, 0);
	}
	
	public static byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    public static long bytesToLong(byte[] b) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (b[i] & 0xFF);
        }
        return result;
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
