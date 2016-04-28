package com.psrt.guitabs.factories;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.containers.CanID;
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
	
	/**
	 * Used to easily create entities for the GUI
	 * @param world - entity system's world object
	 * @param n - Node; how the GUI element is represented on the GUI (JavaFX crap)
	 * @param elementType - Just specifies how to cast the Node object properly.
	 * @param initValue - Can be used to set the default value (when the object isn't receiving data)
	 * @param name - name of the element (must match the name given in the GUI)
	 * @return
	 */
	public static Entity createEntity(com.artemis.World world, Node n, ElementType elementType, String initValue, String name){
		return createEntity(world, n, elementType, initValue, name, -1);
	}
	
	public static Entity createEntity(com.artemis.World world, Node n, ElementType elementType, String initValue, String name, int timeout){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		switch(elementType){
			case Label:
				edit.add(new TextComponent(initValue, name, (Label) n, timeout));
				break;
			case ProgressBar:
				edit.add(new ProgressComponent(Double.parseDouble(initValue), name, (ProgressBar) n, timeout));
				break;
			case ImageView:
				break;
			default:
				System.out.println("ElementFactory: An unrecognized ElementType was used.");
				break;
		}
		
		return e;
	}
	/**
	 * Just adds a timed updater to the component. Should be unnecessary, but could be useful later
	 * @param e - the entity object for the GUI node
	 * @param delay - a delay to specify
	 * @param t - Two types of timers, I guess...
	 * @return
	 */
	public static void addTimer(Entity e, int delay, TimingType t){
		e.edit().add(new TimingComponent(delay, t));
	}
	
	public static void addTimer(Entity e, int delay){
		e.edit().add(new TimingComponent(delay, TimingType.ASTABLE));
	}
	
	public static void addID(Entity e, int id, int function, int entry){
		e.edit().add(new CanID(id, function, entry));
	}
	
	public static void addPrefix(com.artemis.World world, Entity e, String p){
		ComponentMapper<TextComponent> tm = world.getMapper(TextComponent.class);
		TextComponent t = tm.getSafe(e);
		if(t != null){
			t.setPrefix(p);
		}
	}
	
	public static void addSuffix(com.artemis.World world, Entity e, String s){
		ComponentMapper<TextComponent> tm = world.getMapper(TextComponent.class);
		TextComponent t = tm.getSafe(e);
		if(t != null){
			t.setSuffix(s);
		}
	}
	
}
