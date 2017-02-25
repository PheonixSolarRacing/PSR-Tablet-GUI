package com.psrt.guitabs.factories;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.ValueComponent;
import com.psrt.guitabs.BMSTab;
import com.psrt.guitabs.ErrorsTab;
import com.psrt.guitabs.MainTab;
import com.psrt.guitabs.PDBTab;
import com.psrt.main.Main;
import com.psrt.threads.UIThread;

import javafx.scene.Node;

public class ValueFactory {
	ComponentMapper<TextComponent> tm;
	ComponentMapper<ProgressComponent> pm;
	ComponentMapper<ImageComponent> im;
	
	World w;
	public ValueFactory(World w){
		tm = w.getMapper(TextComponent.class);
		pm = w.getMapper(ProgressComponent.class);
		im = w.getMapper(ImageComponent.class);
		this.w = w;
	}
	
	/**
	 * Get the value component associated with this entity. There really should only be one (e.g. progress bar, label, imageview, etc.)
	 * @param entityId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public synchronized ValueComponent getValue(int entityId){
		return getValue(w.getEntity(entityId));
	}
	
	/**
	 * IMPORTANT: When adding a new component type, you <b>MUST</b> include the code in this format here
		ExampleComponent ec = exampleMapper.getSafe(e).
		You <b>MUST</b> also include a mapper for that new component type here.
		If you don't do this, <b>none</b> of the elements based on that component type will work.
		In the same vein, the {@link BankSystem} also requires this level of customization. See {@link BankSystem#process()}
	 * @param e
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public synchronized ValueComponent getValue(Entity e){
		TextComponent tc = tm.getSafe(e);
		ProgressComponent pc = pm.getSafe(e);
		//TimingComponent t = timeM.getSafe(entityId);
		ImageComponent ic = im.getSafe(e);
		
		
		
		ValueComponent v = null;
		//if tc isn't null set it to v, if tc is null then set pc to v, if pc is null then set v to null
		if(tc != null) v = tc;
		else if(pc != null) v = pc;
		else if(ic != null) v = ic;
		
		return v;
	}
	
	public static void if_tree_of_doom(Node n, Main main, UIThread ui) {
		World world = main.getWorld();
		if(n.getId() != null){ //Example of retrieving all elements automatically... Could be easier? Hmm
			switch (n.getId()){
				case "txt_area_errors":
					ErrorsTab.txt_area_errors(n);
					break;
				case "chk_main_debug":
					ErrorsTab.chk_main_debug(n);
					break;
				case "chk_serial_parser_debug":
					ErrorsTab.chk_serial_parser_debug(n);
					break;
				case "chk_serial_reader_debug":
					ErrorsTab.chk_serial_reader_debug(n);
					break;
				case "chk_serial_monitor_debug":
					ErrorsTab.chk_serial_monitor_debug(n);
					break;
				case "chk_uithread_debug":
					ErrorsTab.chk_uithread_debug(n);
					break;
				case "chk_bank_system_debug":
					ErrorsTab.chk_bank_system_debug(n);
					break;
				case "test_label":
					MainTab.test_label(world, n);
					break;
				case "test_bar":
					MainTab.test_bar(world, n);
					break;
				case "battery_1_voltage":
					PDBTab.battery_1_voltage(world, n);
					break;
				case "speed_display":
					MainTab.speed_display(world, n);
					break;
				case "battery_2_voltage":
					PDBTab.battery_2_voltage(world, n);
					break;
				case "battery_3_voltage":
					PDBTab.battery_3_voltage(world, n);
					break;
				case "x6F4_fp_1": //SOC %
					BMSTab.x6F4_fp_1(world, n);
					break;
				case "x6F4_fp_0": //SOC Ah
					BMSTab.x6F4_fp_0(world, n);
					break;
				case "dc_to_dc_voltage":
					PDBTab.dc_to_dc_voltage(world, n);
					break;
				case "battery_4_voltage":
					PDBTab.battery_4_voltage(world, n);
					break;
				case "batteries_all_current":
					PDBTab.batteries_all_current(world, n);
					break;
				case "dc_to_dc_current":
					PDBTab.dc_to_dc_current(world, n);
					break;
				case "lv_bus_voltage":
					PDBTab.lv_bus_voltage(world, n);
					break;
				case "output_1_current":
					PDBTab.output_1_current(world, n);
					break;
				case "output_2_current":
					PDBTab.output_2_current(world, n);
					break;
				case "output_3_current":
					PDBTab.output_3_current(world, n);
					break;
				case "output_4_current":
					PDBTab.output_4_current(world, n);
					break;
				case "output_5_current":
					PDBTab.output_5_current(world, n);
					break;
				case "output_6_current":
					PDBTab.output_6_current(world, n);
					break;
				case "output_7_current":
					PDBTab.output_7_current(world, n);
					break;
				case "output_8_current":
					PDBTab.output_8_current(world, n);
					break;
				case "battery_1_led":
					PDBTab.battery_1_led(world, n, main.getImages());
					break;
				case "battery_2_led":
					PDBTab.battery_2_led(world, n, main.getImages());
					break;
				case "battery_3_led":
					PDBTab.battery_3_led(world, n, main.getImages());
					break;
				case "battery_4_led":
					PDBTab.battery_4_led(world, n, main.getImages());
					break;
				case "dc_dc_led":
					PDBTab.dc_dc_led(world, n, main.getImages());
					break;
				case "batteries_all_current_led":
					PDBTab.batteries_all_current_led(world, n, main.getImages());
					break;
				case "dc_to_dc_current_led":
					PDBTab.dc_to_dc_current_led(world, n, main.getImages());
					break;
				case "output_1_led":
					PDBTab.output_1_led(world, n, main.getImages());
					break;
				case "output_2_led":
					PDBTab.output_3_led(world, n, main.getImages());
					break;
				case "output_3_led":
					PDBTab.output_3_led(world, n, main.getImages());
					break;
				case "output_4_led":
					PDBTab.output_4_led(world, n, main.getImages());
					break;
				case "output_5_led":
					PDBTab.output_5_led(world, n, main.getImages());
					break;
				case "output_6_led":
					PDBTab.output_6_led(world, n, main.getImages());
					break;
				case "output_7_led":
					PDBTab.output_7_led(world, n, main.getImages());
					break;
				case "output_8_led":
					PDBTab.output_8_led(world, n, main.getImages());
					break;
				case "error_led":
					PDBTab.error_led(world, n, main.getImages());
					break;
				case "battery_1_percentage":
					PDBTab.battery_1_percentage(world, n);
					break;
				case "battery_2_percentage":
					PDBTab.battery_2_percentage(world, n);
					break;
				case "battery_3_percentage":
					PDBTab.battery_3_percentage(world, n);
					break;
				case "battery_4_percentage":
					PDBTab.battery_4_percentage(world, n);
					break;
				case "btn_quit":
					MainTab.btn_quit(ui.getPrimaryStage(), n);
					break;
				case "btn_fullscreen":
					MainTab.btn_fullscreen(ui, n);
					break;
				case "x6F7_u8_0_x08":
					BMSTab.x6F7_u8_0_x08(world, n, main.getImages());
					break;
				case "x6F7_u8_0_x20":
					BMSTab.x6F7_u8_0_x20(world, n, main.getImages());
					break;
				case "x6F7_u8_0_x04":
					BMSTab.x6F7_u8_0_x04(world, n, main.getImages());
					break;
				case "x601_16_2": //cmu temp
					BMSTab.x601_16_2(world, n);
					break;
				case "x6FA_u32_1": //cmu temp
					BMSTab.x6FA_u32_1(world, n);
					break;
				case "x6FA_u32_0": //cmu temp
					BMSTab.x6FA_u32_0(world, n);
					break;
				case "x6F9_u16_1": //cell temp
					BMSTab.x6F9_u16_1(world, n);
					break;
				case "x6F8_u16_1": //max cell voltage
					BMSTab.x6F8_u16_1(world, n);
					break;
				case "x6F8_u16_0": //min cell voltage
					BMSTab.x6F8_u16_0(world, n);
					break;
				case "x608_16_0":
					BMSTab.x608_16_0(world, n);
					break;
				case "x608_16_1":
					BMSTab.x608_16_1(world, n);
					break;
				case "x608_16_2":
					BMSTab.x608_16_2(world, n);
					break;
				case "x608_16_3":
					BMSTab.x608_16_3(world, n);
					break;
				case "x609_16_0":
					BMSTab.x609_16_0(world, n);
					break;
				case "x609_16_1":
					BMSTab.x609_16_1(world, n);
					break;
				case "x609_16_2":
					BMSTab.x609_16_2(world, n);
					break;
				case "x605_16_0":
					BMSTab.x605_16_0(world, n);
					break;
				case "x605_16_1":
					BMSTab.x605_16_1(world, n);
					break;
				case "x605_16_2":
					BMSTab.x605_16_2(world, n);
					break;
				case "x605_16_3":
					BMSTab.x605_16_3(world, n);
					break;
				case "x606_16_0":
					BMSTab.x606_16_0(world, n);
					break;
				case "x606_16_1":
					BMSTab.x606_16_1(world, n);
					break;
				case "x606_16_2":
					BMSTab.x606_16_2(world, n);
					break;
				case "x606_16_3":
					BMSTab.x606_16_3(world, n);
					break;
				case "x604_16_3":
					BMSTab.x604_16_3(world, n);
					break;
				case "x60A_16_3":
					BMSTab.x60A_16_3(world, n);
					break;
				case "x601_16_3":
					BMSTab.x601_16_3(world, n);
					break;
				case "x607_16_3":
					BMSTab.x607_16_3(world, n);
					break;
				case "x603_16_3":
					BMSTab.x603_16_3(world, n);
					break;
				case "x603_16_2":
					BMSTab.x603_16_2(world, n);
					break;
				case "x603_16_1":
					BMSTab.x603_16_1(world, n);
					break;
				case "x603_16_0":
					BMSTab.x603_16_0(world, n);
					break;
				case "x602_16_3":
					BMSTab.x602_16_3(world, n);
					//TODO
					break;
				case "x602_16_2":
					BMSTab.x602_16_2(world, n);
					break;
				case "x602_16_1":
					BMSTab.x602_16_1(world, n);
					break;
				case "x602_16_0":
					BMSTab.x602_16_0(world, n);
					break;
				case "x60C_16_2":
					BMSTab.x60C_16_2(world, n);
					break;
				case "x60C_16_1":
					BMSTab.x60C_16_1(world, n);
					break;
				case "x60C_16_0":
					BMSTab.x60C_16_0(world, n);
					break;
				case "x60B_16_3":
					BMSTab.x60B_16_3(world, n);
					break;
				case "x60B_16_2":
					BMSTab.x60B_16_2(world, n);
					break;
				case "x60B_16_1":
					BMSTab.x60B_16_1(world, n);
					break;
				case "x60B_16_0":
					BMSTab.x60B_16_0(world, n);
					break;
				
			}
		}
	}	 
}
