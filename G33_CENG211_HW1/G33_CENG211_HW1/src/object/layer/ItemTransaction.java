package object.layer;
public class ItemTransaction {
	
	//ItemTransaction Properties
	private Item item;
	private Transaction[][] transactions;

	public ItemTransaction() {
		// Default Constructor
		this.item = null;
		this.transactions = null;
	}
	
	public ItemTransaction(Item item, Transaction[][] transactions) {
		// Parameterized Constructor
		this.item = item;
		this.transactions = transactions;
	}
	
	public ItemTransaction(ItemTransaction itemTransaction) {
		// Copy Constructor
		this.item = itemTransaction.getItem();
		this.transactions = itemTransaction.getTransactions();
	}

	//----------ITEMTRANSACTION CLASS GETTER-SETTER METHODS----------//
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Transaction[][] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[][] transactions) {
		this.transactions = transactions;
	}
}
