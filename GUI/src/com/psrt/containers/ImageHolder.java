package com.psrt.containers;

import java.io.InputStream;
import java.util.HashMap;

import com.psrt.main.Main;

import javafx.scene.image.Image;

/**
 * Class for holding images attached to strings.  The class is basically just a layer over a {@link HashMap}.
 * It holds utilities for placement and retrieval of images based on String keys.
 * @author Austin Dibble
 *
 */
public class ImageHolder {

	HashMap<String, Image> images;
	
	public ImageHolder(){
		images = new HashMap<String, Image>(15);
	}
	
	/**
	 * Puts an image into this holder (into the backing {@link HashMap}) with the String name as the key for retrieval.
	 * See {@link HashMap#put(Object, Object)}.
	 * @param image
	 * @param name
	 */
	public void putImage(Image image, String name){
		images.put(name, image);
	}
	
	/**
	 * Loads the image into RAM and stores it with the given name as the key to a {@link HasMap}
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
	 * Returns the given image without removing it.
	 * Will return null if the Image doesn't exist in this map.
	 * See {@link HashMap#get(Object)}
	 * @param name
	 * @return
	 */
	public synchronized Image getImage(String name){
		return images.get(name);
	}
	
	/**
	 * Removes the image from the map and returns it. See {@link HashMap#remove(Object)}
	 * @param name
	 * @return
	 */
	public synchronized Image takeImage(String name){
		return images.remove(name);
	}
}
