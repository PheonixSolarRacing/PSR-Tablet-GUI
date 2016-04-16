package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.TimingComponent.TimingType;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class MainTab {
	
	public static void speed_display(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "speed_display", (Label) n));
		//edit.add(new TimingComponent(500, TimingType.ASTABLE));
	}
}
