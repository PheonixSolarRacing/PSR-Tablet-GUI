package com.psrt.guitabs.factories;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.ValueComponent;
import com.psrt.guitabs.BMSTab;
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
				case "battery_1_voltage":
					PDBTab.battery_1_voltage(world, n);
					break;
				case "SOC_Label":
					BMSTab.SOC_Label(world, n);
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
				case "soc_indicator":
					BMSTab.soc_indicator(world, n);
					break;
				case "SOC_label":
					BMSTab.SOC_Label(world, n);
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
			}
		}
	}	 
}
