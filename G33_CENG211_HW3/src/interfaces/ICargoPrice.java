package interfaces;

//ICargoPrice implementation for NormalCargo custom price calculation.
public interface ICargoPrice {

	public double calculatePrice();
	
	public double calculateDiscountedPrice(double discountRatePercentage);

}
