package object.layer;

public class AnnualSale {
	
	//AnnualSale Properties
	private ItemTransaction[] itemTransactions;

	public AnnualSale() {
		// Default Constructor
		this.itemTransactions = null;
	}
	
	public AnnualSale(ItemTransaction[] itemTransactions) {
		// Parameterized Constructor
		this.itemTransactions = itemTransactions;
	}
	
	public AnnualSale(AnnualSale annualSale) {
		// Copy Constructor
		this.itemTransactions = annualSale.getItemTransactions();
	}
	
	//----------ANNUALSALE CLASS GETTER-SETTER METHODS----------//

	public ItemTransaction[] getItemTransactions() {
		return itemTransactions;
	}

	public void setItemTransactions(ItemTransaction[] itemTransactions) {
		this.itemTransactions = itemTransactions;
	}
	
	

}
