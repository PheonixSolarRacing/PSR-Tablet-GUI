package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.TimingComponent.TimingType;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class PDBTab {
	
	public static void battery_1_voltage(com.artemis.World world, Node n){
		Entity e2 = world.createEntity();
		EntityEdit edit2 = e2.edit();
		edit2.add(new TextComponent("14.1v", "battery_1_voltage", (Label) n));
		edit2.add(new TimingComponent(250, TimingType.ASTABLE));
	}
}
