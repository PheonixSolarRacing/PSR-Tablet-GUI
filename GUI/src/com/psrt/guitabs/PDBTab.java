package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.containers.CanID;
import com.psrt.entities.components.TextComponent;
import com.psrt.guitabs.factories.MyEntityFactory;
import com.psrt.guitabs.factories.MyEntityFactory.ElementType;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class PDBTab {
	
	public static void battery_1_voltage(com.artemis.World world, Node n){
		Entity e2 = world.createEntity();
		EntityEdit edit2 = e2.edit();
		edit2.add(new TextComponent("NA", "battery_1_voltage", (Label) n, 200, "v"));
		edit2.add(new CanID(1, 0, 1));
	}
	
	public static void battery_2_voltage(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.Label, "NA", "battery_2_voltage", 200);
		MyEntityFactory.addID(e, 1, 1, 1);
		MyEntityFactory.addTimer(e, 200);
		MyEntityFactory.addSuffix(world, e, "v");
	}
}
