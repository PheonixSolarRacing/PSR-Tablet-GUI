package com.psrt.entities.components;

import javafx.scene.control.Label;

public class PercentComponent extends TextComponent{
	double min;
	double max;
	double percent;
	
	public PercentComponent(String reference, Label label, double min, double max) {
		super("NA %", reference, label);
		this.min = min;
		this.max = max;
		this.percent = 0;
	}
	
	public synchronized void setValue(double n){
		double delta = min - max;
		double offset = n - min;
		double temp = offset / delta;
		percent = temp * 100;
		super.setValue(percent + "%");
	}
}
