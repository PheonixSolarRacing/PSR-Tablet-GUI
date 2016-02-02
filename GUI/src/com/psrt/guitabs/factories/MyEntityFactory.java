package com.psrt.guitabs.factories;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.entities.components.ProgressComponent;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.TimingComponent.TimingType;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MyEntityFactory {
	public static enum ElementType{
		Label,
		ProgressBar,
		ImageView;
	}
	
	public static Entity createEntity(com.artemis.World world, Node n, ElementType elementType, String initValue, String name){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		switch(elementType){
			case Label:
				edit.add(new TextComponent(initValue, name, (Label) n));
				break;
			case ProgressBar:
				edit.add(new ProgressComponent(Double.parseDouble(initValue), name, (ProgressBar) n));
				break;
			case ImageView:
				break;
			default:
				System.out.println("ElementFactory: An unrecognized ElementType was used.");
				break;
		}
		
		return e;
	}
	
	public static Entity addTimer(Entity e, int delay, TimingType t){
		e.edit().add(new TimingComponent(delay, t));
		return e;
	}
	
}
