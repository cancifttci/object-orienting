package application.layer;

public class ChainStoreApp {

	public static void main(String[] args){

		//Initialize the storeQuery object.
		StoreQuery storeQuery = new StoreQuery();
		
		//Print the most profitable item for the whole year.
		storeQuery.printItemProfits("most");
		
		//Print the most profitable category for the whole year.
		storeQuery.printCatalogueProfits("most");
		
		//Print the least profitable item for the whole year.
		storeQuery.printItemProfits("least");
		
		//Print the least profitable category for the whole year.
		storeQuery.printCatalogueProfits("least");
		
		//Print the most profitable item for a single sale.
		storeQuery.printMostProfitableSingleSale();
		
		//Print the best-selling item for the whole year.
		storeQuery.printBestSellingItem();
				
		//Print the most profitable store for each month.
		storeQuery.printMostProfitableStoresByMonth();
	
	}

}
