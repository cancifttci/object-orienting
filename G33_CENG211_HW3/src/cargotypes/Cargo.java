package cargotypes;

import application.Day;
import interfaces.ICargo;

public abstract class Cargo implements ICargo{

	private final String type;
	private final int height;
	private final int weight;
	private final int width;
	private final int length;
	private Day deliveryDay;

	//default constructor method
	public Cargo() {
		this("unknown cargo type", 0, 0, 0, 0);
	}
	//constructor method
	public Cargo(String type,int height,int weight,int width,int length){

		this.type=type;
		this.height=height;
		this.weight=weight;
		this.width=width;
		this.length=length;
		this.deliveryDay=null;
	}

	// getter and setter for delivery day 
	public Day getDeliveryDay() {
		return this.deliveryDay;
	}
	
	public void setDeliveryDay(Day deliveryDay) {
		this.deliveryDay=deliveryDay;    
	}
	
	// abstract method for calculating size
	public int getSize() {
		int desi = (this.getWidth()*this.getLength()*this.getHeight())/3000;
		return Math.max(desi,this.getWeight());
	};

	//getters and setters for fields
	public int getHeight() {
		return height;
	}
	public int getWeight() {
		return weight;
	}
	public int getWidth() {
		return width;
	}
	public int getLength() {
		return length;
	}
	public String getType() {
		return type;
	}


}