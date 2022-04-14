package interfaces;

import application.Day;

//ICargo implementation for Cargo Classes
public interface ICargo {
	
	public int getSize();
	
	public Day getDeliveryDay();
	
	public void setDeliveryDay(Day deliveryDay);
	
}
