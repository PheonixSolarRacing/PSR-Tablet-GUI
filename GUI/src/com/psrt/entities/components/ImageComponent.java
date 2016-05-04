package com.psrt.entities.components;

import java.util.HashMap;

import com.psrt.containers.ImageHolder;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageComponent extends ValueComponent<Number, ImageView>{

	private HashMap<Number, String> images;
	private ImageHolder imageHolder;
	private Image image;
	
	public ImageComponent(ImageView element, String reference, ImageHolder imageHolder) {
		this(null, element, reference, imageHolder);
	}
	
	public ImageComponent(Image image, ImageView element, String reference, ImageHolder imageHolder) {
		this(image, element, reference, imageHolder, -1);
	}
	
	public ImageComponent(Image image, ImageView element, String reference, ImageHolder imageHolder, int timeout) {
		super(new SimpleObjectProperty<Number>(-1), element, reference, ComponentType.IMAGE, timeout);
		this.images = new HashMap<Number, String>();
		this.image = image;
		super.element.setImage(image);
		this.imageHolder = imageHolder;
		setValue(-1);
	}

	@Override
	public void update() {
		//System.out.println("Updating image component");
		if(super.getValue().intValue() != -1){
			int imageID = super.getValue().intValue();
			//System.out.println("Updating image component with image ID of " + imageID);
			String temp = images.get(imageID);
			Image tempImage = null;
			if(temp != null) tempImage = imageHolder.getImage(temp);
			if(tempImage != null) this.image = tempImage;
		}
		super.element.setImage(image);
	}
	
	public <N extends Number> ImageComponent addImagePair(N canResponseID, String imageName){
		images.put(canResponseID, imageName);
		return this;
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	public void setImage(String imageName){
		Image temp = imageHolder.getImage(imageName);
		setImage(temp);
	}
}
