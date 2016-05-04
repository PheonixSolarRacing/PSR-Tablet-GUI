package com.psrt.containers;

import java.io.InputStream;
import java.util.HashMap;

import com.psrt.main.Main;

import javafx.scene.image.Image;

public class ImageHolder {

	HashMap<String, Image> images;
	
	public ImageHolder(){
		images = new HashMap<String, Image>(15);
	}
	
	public void putImage(Image image, String name){
		images.put(name, image);
	}
	
	/**
	 * Loads the image into RAM and stores it with the given name
	 * @param URl
	 * @param name
	 */
	public void loadImage(String URl, String name){
		InputStream stream = Main.class.getResourceAsStream(URl);
		Image image = null;
		if(stream != null){
			image = new Image(stream);
		}
		if(image!= null) images.put(name, image);
		else{
			System.out.println("ImageHolder - Error - Image couldn't be loaded at specified path.");
		}
	}
	
	/**
	 * Returns the given image without removing it
	 * Will return null if the Image doesn't exist in this map.
	 * @param name
	 * @return
	 */
	public synchronized Image getImage(String name){
		return images.get(name);
	}
	
	/**
	 * Removes the image from the map and returns it
	 * @param name
	 * @return
	 */
	public synchronized Image takeImage(String name){
		return images.remove(name);
	}
}
