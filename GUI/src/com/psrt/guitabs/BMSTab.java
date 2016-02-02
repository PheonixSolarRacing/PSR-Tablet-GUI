package com.psrt.guitabs;

import com.artemis.Entity;
import com.psrt.entities.components.TimingComponent.TimingType;
import com.psrt.guitabs.factories.MyEntityFactory;
import com.psrt.guitabs.factories.MyEntityFactory.ElementType;

import javafx.scene.Node;

public class BMSTab {
	public static void SOC_Label(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.Label, "NA%", "SOC_Label");
		MyEntityFactory.addTimer(e, 250, TimingType.ASTABLE);
	}
	
	public static void soc_indicator(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.ProgressBar, "0", "soc_indicator");
		MyEntityFactory.addTimer(e, 200, TimingType.ASTABLE);
	}
}
