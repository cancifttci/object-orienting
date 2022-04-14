package object.layer;


public class Transaction {

	//Transaction Properties
	private float purchasePrice;
	private float salePrice;
	private int numberOfSales;
	
	public Transaction() {
		// Default Constructor
		this.purchasePrice = 0.0f;
		this.salePrice = 0.0f;
		this.numberOfSales = 0;
	}
	
	public Transaction(float purchasePrice, float salePrice, int numberOfSales) {
		// Parameterized Constructor
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.numberOfSales = numberOfSales;
	}
	
	public Transaction(Transaction transaction) {
		// Copy Constructor
		this.purchasePrice = transaction.getPurchasePrice();
		this.salePrice = transaction.getSalePrice();
		this.numberOfSales = transaction.getNumberOfSales();
	}

	//----------TRANSACTION CLASS GETTER-SETTER METHODS----------//
	
	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public int getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}
	
	//----------TRANSACTION CLASS PUBLIC METHODS----------//
	
	//Calculates the monthly profit of transaction and returns it.
	public float getMonthlyProfit() {
		return (this.salePrice - this.purchasePrice)*this.numberOfSales;
	}
	
	//Calculates the single-sale profit of transaction and returns it.
	public float getSingleSaleProfit() {
		return (this.salePrice - this.purchasePrice);
	}
	
	

}
