package com.psrt.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.psrt.entities.components.TextComponent;
import com.psrt.entities.components.TimingComponent;
import com.psrt.entities.components.TimingComponent.TimingType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

public class UIController implements Initializable{
	private World world;
	private Main mainClass;
	
	public UIController(){
		this.world = Main.getWorld();
		this.mainClass = Main.getMain();
	}
	
	public void bindComponents(){
		speed_display_setup();
		
		//##############################################################################
		Entity e2 = world.createEntity();
		EntityEdit edit2 = e2.edit();
		//edit2.add(new TextComponent("14.1v", "battery_1_voltage", battery_1_voltage));
		//edit2.add(new TimingComponent(250, TimingType.ASTABLE));
		
		//##############################################################################
		Entity e3 = world.createEntity();
		EntityEdit edit3 = e3.edit();
		edit3.add(new TextComponent("14.1v", "battery_2_voltage", battery_2_voltage));
		edit3.add(new TimingComponent(500, TimingType.ASTABLE));
	}

	
	public void speed_display_setup(){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new TextComponent("38", "speed_display", speed_display));
		edit.add(new TimingComponent(500, TimingType.ASTABLE));
		
	}
	
////////////////////////////////////
	//Here it be.
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bindComponents();
	}
	
	@FXML
	private Label speed_display;
	
	 @FXML
	 private Label battery_3_percentage;

	    @FXML
	    private Tab status_tab;

	    @FXML
	    private ImageView output_6_led;

	    @FXML
	    private AnchorPane bms;

	    @FXML
	    private AnchorPane main;

	    @FXML
	    private Tab main_tab;

	    @FXML
	    private Label lv_bus_voltage;

	    @FXML
	    private ImageView output_7_led;

	    @FXML
	    private ImageView output_8_led;

	    @FXML
	    private LineChart<?, ?> motors_graph;

	    @FXML
	    private Label battery_4_voltage;

	    @FXML
	    private Label output_6_current;

	    @FXML
	    private Label battery_1_voltage;

	    @FXML
	    private Label output_2_current;

	    @FXML
	    private Label output_8_current;

	    @FXML
	    private Tab indicators_tab;

	    @FXML
	    private AnchorPane indicators;

	    @FXML
	    private Tab pdb_tab;

	    @FXML
	    private ImageView battery_1_led;

	    @FXML
	    private Label battery_4_percentage;

	    @FXML
	    private ImageView batteries_current_led;

	    @FXML
	    private Label output_4_current;

	    @FXML
	    private AnchorPane errors;

	    @FXML
	    private AnchorPane status;

	    @FXML
	    private AnchorPane other;

	    @FXML
	    private ImageView output_1_led;

	    @FXML
	    private AnchorPane solar;

	    @FXML
	    private ImageView output_2_led;

	    @FXML
	    private AnchorPane motors;

	    @FXML
	    private TabPane main_tabs;

	    @FXML
	    private ImageView battery_2_led;

	    @FXML
	    private MediaView cam_display;

	    @FXML
	    private Tab motos_tab;

	    @FXML
	    private ImageView battery_4_led;

	    @FXML
	    private AnchorPane can;

	    @FXML
	    private Label battery_2_percentage;

	    @FXML
	    private Tab errors_tab;

	    @FXML
	    private ImageView output_3_led;

	    @FXML
	    private ImageView battery_3_led;

	    @FXML
	    private Label output_5_current;

	    @FXML
	    private Label batteries_current;

	    @FXML
	    private Label battery_3_voltage;

	    @FXML
	    private Label battery_1_percentage;

	    @FXML
	    private Label battery_2_voltage;

	    @FXML
	    private ImageView output_4_led;

	    @FXML
	    private Label output_3_current;

	    @FXML
	    private Label output_7_current;

	    @FXML
	    private Label dc_to_dc_voltage;

	    @FXML
	    private ImageView dc_to_dc_voltage_led;

	    @FXML
	    private Tab can_tab;

	    @FXML
	    private Label output_1_current;

	    @FXML
	    private Tab solar_tab;

	    @FXML
	    private AnchorPane pdb;

	    @FXML
	    private Tab other_tab;

	    @FXML
	    private ImageView dc_to_dc_current_led;

	    @FXML
	    private Label dc_to_dc_current;

	    @FXML
	    private ImageView output_5_led;

	    @FXML
	    private Tab bms_tab;

	    @FXML
	    void e4e4e4(ActionEvent event) {

	    }


///////////////////////////////////
}
