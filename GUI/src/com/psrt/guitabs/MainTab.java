package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.psrt.entities.components.TextComponent;
import com.psrt.threads.UIThread;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainTab {
	
	public static void speed_display(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("NA", "speed_display", (Label) n));
		//edit.add(new TimingComponent(500, TimingType.ASTABLE));
	}
	
	public static void btn_quit(Stage st, Node n){
		Button b = (Button) n;
		b.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				//System.out.println("Hello!");
				st.close();
			}
		});
	}
	
	public static void btn_fullscreen(UIThread th, Node n){
		Button b = (Button) n;
		b.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				th.getPrimaryStage().setFullScreen(!th.getPrimaryStage().isFullScreen());
			}
		});
	}
}
