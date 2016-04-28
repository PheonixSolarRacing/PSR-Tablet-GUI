package com.psrt.entities.components;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;

public class ProgressComponent extends ValueComponent<Number, ProgressBar> {

	public ProgressComponent(double value, String reference, ProgressBar bar) {
		this(value, reference, bar, -1);
	}
	
	public ProgressComponent(double value, String reference, ProgressBar bar, int timeout){
		super(new SimpleDoubleProperty(value), bar, reference, ComponentType.PROGRESS_BAR, timeout);
		setValue(value);
	}

	@Override
	public void update() {
		ProgressBar p = super.element;
		Number n = super.getValue();
		if(n == null) System.out.println("ProgressComponent: number is null");
		if(p == null) System.out.println("ProgressComponent: bar is null");
		p.setProgress(n.doubleValue());
	}
}
