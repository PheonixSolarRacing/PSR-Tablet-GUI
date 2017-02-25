package com.psrt.guitabs;

import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.psrt.containers.AbstractID;
import com.psrt.containers.ImageHolder;
import com.psrt.containers.PDBID;
import com.psrt.entities.components.ImageComponent;
import com.psrt.entities.components.LabelComponent;
import com.psrt.guitabs.factories.MyEntityFactory;
import com.psrt.guitabs.factories.MyEntityFactory.ElementType;
import com.psrt.main.Main;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PDBTab {
	static final int timeout = 1000;
	public static void battery_1_voltage(com.artemis.World world, Node n){
		Entity e2 = world.createEntity();
		EntityEdit edit2 = e2.edit();
		edit2.add(new LabelComponent("NA", "battery_1_voltage", (Label) n, timeout, "V"));
		edit2.add(new PDBID(1, 0, 1));
	}
	
	public static void battery_2_voltage(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.Label, "NA", "battery_2_voltage", timeout);
		MyEntityFactory.addID(e, 1, 1, 1);
		//MyEntityFactory.addTimer(e, timeout);
		MyEntityFactory.addSuffix(world, e, "V");
	}
	
	public static void battery_3_voltage(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.Label, "NA", "battery_3_voltage", timeout);
		MyEntityFactory.addID(e, 1, 2, 1);
		MyEntityFactory.addSuffix(world, e, "V");
	}
	
	public static void battery_4_voltage(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "battery_4_voltage", (Label) n, timeout, "V"));
		edit.add(new PDBID(1, 3, 1));
	}
	
	public static void batteries_all_current(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "batteries_all_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 5, 1));
	}
	
	public static void dc_to_dc_voltage(com.artemis.World world, Node n){
		Entity e = MyEntityFactory.createEntity(world, n, ElementType.Label, "NA", "dc_to_dc_voltage", timeout);
		MyEntityFactory.addID(e, 1, 4, 1);
		MyEntityFactory.addSuffix(world, e, "V");
	}
	
	public static void dc_to_dc_current(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "dc_to_dc_voltage", (Label) n, timeout, "V"));
		edit.add(new PDBID(1, 6, 1));
	}
	
	public static void lv_bus_voltage(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "lv_bus_voltage", (Label) n, timeout, "V"));
		edit.add(new PDBID(1, 7, 1));
	}
	
	public static void output_1_current(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_1_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 8, 1));
	}

	public static void output_2_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_2_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 9, 1));
	}
	public static void output_3_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_3_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 10, 1));
	}
	
	public static void output_4_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_4_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 11, 1));
	}
	
	public static void output_5_current(com.artemis.World world, Node n){
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_5_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 12, 1));
	}

	public static void output_6_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_6_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 13, 1));
	}
	public static void output_7_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_7_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 14, 1));
	}
	
	public static void output_8_current(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "output_8_current", (Label) n, timeout, "A"));
		edit.add(new PDBID(1, 15, 1));
	}
	
	public static void battery_1_percentage(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "battery_1_percentage", (Label) n, timeout, "%"));
		edit.add(new PDBID(1, 0, 2));
	}
	
	public static void battery_2_percentage(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "battery_2_percentage", (Label) n, timeout, "%"));
		edit.add(new PDBID(1, 1, 2));
	}
	
	public static void battery_3_percentage(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "battery_3_percentage", (Label) n, timeout, "%"));
		edit.add(new PDBID(1, 2, 2));
	}
	
	public static void battery_4_percentage(World world, Node n) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		edit.add(new LabelComponent("NA", "battery_4_percentage", (Label) n, timeout, "%"));
		edit.add(new PDBID(1, 3, 2));
	}
	
	public static void battery_1_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "battery_1_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 0, 3));
	}
	
	public static void battery_2_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "battery_2_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 1, 3));
	}
	
	public static void battery_3_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "battery_3_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 2, 3));
	}
	
	public static void battery_4_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "battery_4_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 3, 3));
	}
	
	public static void dc_dc_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "dc_dc_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 4, 3));
	}
	
	public static void batteries_all_current_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "batteries_all_current_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 5, 3));
	}
	
	public static void dc_to_dc_current_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "dc_to_dc_current_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		AbstractID id = new PDBID(1, 6, 3);
		edit.add(id);
	}
	
	public static void output_1_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_1_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 8, 3));
	}
	
	public static void output_2_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_2_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 9, 3));
		
	}public static void output_3_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_3_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 10, 3));
		
	}public static void output_4_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_4_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 11, 3));
	}public static void output_5_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_5_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 12, 3));
	}
	public static void output_6_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_6_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 13, 3));
	}
	public static void output_7_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_7_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 14, 3));
	}
	public static void output_8_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "output_8_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(2, "yellow_light")
			  .addImagePair(3, "red_light")
			  .addImagePair(4, "off_light");
		
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 15, 3));
	}
	
	public static void error_led(World world, Node n, ImageHolder imageHolder) {
		Entity e = world.createEntity();
		EntityEdit edit = e.edit();
		Image image = Main.getMain().getImages().getImage("red_light"); //initial image
		ImageComponent imageC = new ImageComponent(image, (ImageView) n, "error_led", imageHolder);
		imageC.addImagePair(1, "green_light")
			  .addImagePair(0, "red_light");
		
		edit.add(imageC);
		//else System.out.println("Image null");
		edit.add(new PDBID(1, 16, 3));
	}

}
