package application.layer;

import data.layer.FileIO;
import object.layer.Item;
import object.layer.ItemTransaction;
import object.layer.Transaction;
import object.layer.AnnualSale;


public class StoreQuery {
	//StoreQuery Properties
	private AnnualSale annualSale;
	private Item[] itemArray;
	private Transaction[][][] stores;

	
	public StoreQuery() {
		//Default Constructor
		
		//Initialize the File I/O adaptor. 
		FileIO fileAdaptor = new FileIO();
		
		//Retrieve the item csv data.
		String[][] items = fileAdaptor.getItems("HW1_Items.csv");
		
		//Convert the String[][] item array to Item[] array. 
 		this.itemArray =  convertStringArrayToItemArray(items);

		//Define the csv paths to get store data (4 store).
		String[] csvPaths = new String[]{
			"HW1_Transactions_Store1.csv",
			"HW1_Transactions_Store2.csv",
			"HW1_Transactions_Store3.csv",
			"HW1_Transactions_Store4.csv"
		};
		
		//Get the store data (4 store).
		stores = this.getStores(fileAdaptor, csvPaths);
		
		//Generate the annualSale property from items and stores.
		this.annualSale = generateAnnualSale(itemArray, stores);
	}
	
	public StoreQuery(StoreQuery storeQuery) {
		//Copy Constructor
		this.annualSale = storeQuery.getAnnualSale();
		this.itemArray = storeQuery.itemArray;
		this.stores = storeQuery.stores;
	}
	
	//----------STOREQUERY CLASS GETTER-SETTER METHODS----------//

	public AnnualSale getAnnualSale() {
		return annualSale;
	}

	public void setAnnualSale(AnnualSale annualSale) {
		this.annualSale = annualSale;
	}

	//----------STOREQUERY CLASS PRIVATE HELPER METHODS----------//
	
	//Converts the String[][] item array to Item[] array.
	private Item[] convertStringArrayToItemArray(String[][] items) {
		//Initialize the Item[] array from String[][] items array's length. THey will have same length.
		Item[] itemArray = new Item[items.length];
		
		int index= 0;
		//Iterate over the items array elements.
		for (String[] item : items) {
			
			//Get the Item properties.
			int id = Integer.parseInt(item[1]);
			String name = item[0];
			String category = item[2];
			
			//Store the Item objects which initialized from String[] items.
			itemArray[index] = new Item(id, name, category);
			index++;
		}
		//Return to the Item[] object.
		return itemArray;
	}
	
	//Converts the String[][] transaction array to Transaction[][] array.
	private Transaction[][] convertStringArrayToTransactionArray(String[][] transactions){
		//Initialize the Transaction[][] array from String[][] transactions array's length. THey will have same length.
		Transaction[][] transactionArray = new Transaction[transactions.length][];
		
		//Iterate over the transactions array elements.
		for (String[] itemTransactionSeries : transactions) {
			
			//Parse the Item ID from the first column.
			int itemId = Integer.parseInt(itemTransactionSeries[0]);
			
			//Initialize a transaction array. Length is 12 because we have 12 months. 
			Transaction[] transactionSeries = new Transaction[12];
			
			//Month Counter
			int monthIndex = 0;
			
			/*
			 * Ignore the Item ID columns and iterate over array and get month properties by triplets
			 * We ignored the ID column because we already got it.
			*/
			for (int i = 1; i < itemTransactionSeries.length; i+=3) {
				
				//Get monthly sale properties.
				float purchasePrice = Float.parseFloat(itemTransactionSeries[i]);
				float salePrice = Float.parseFloat(itemTransactionSeries[i+1]);
				int numberOfSales = Integer.parseInt(itemTransactionSeries[i+2]);
				
				//Initialize a Transaction object and store it in relevant month index.
				transactionSeries[monthIndex] = new Transaction(purchasePrice,salePrice,numberOfSales);
				monthIndex++;
			}
			
			//Store the Transaction[] array in relevant item index.
			transactionArray[itemId-1] = transactionSeries;
		}
		
		//Return the Transaction[][] array.
		return transactionArray;
	}
	
	//It takes csv path array and File Reader. Returns the store data which equal to Transaction[][][]
	private Transaction[][][] getStores(FileIO fileAdaptor, String[] csvPaths){
		//Initialize the store(Transaction[][]} array. 
		Transaction[][][] stores = new Transaction[csvPaths.length][][];
		
		int index = 0;
		//Iterate over csvPaths elements.
		for (String csvPath : csvPaths) {
			//For each path get the relevant store data.
			String[][] storeStringArray = fileAdaptor.getTransactions(csvPath);
			//Convert the store data to Transaction[][] and store in stores(Transaction[][][]) array.
			stores[index] = this.convertStringArrayToTransactionArray(storeStringArray);
			index++;
		}
		//Return Transaction[][][] array which equals to all the store data.
		return stores;
	}
	
	//It takes Item[] array and Transaction[][][] array. Initializes an AnnualSale object.
	private AnnualSale generateAnnualSale(Item[] items, Transaction[][][] stores) {
		//Initialize itemTransactions array which will be property of AnnualSale
		ItemTransaction[] itemTransactions = new ItemTransaction[items.length];
		
		//Iterate over all of the items.
		for (Item storeItem : items) {
			
			//Initialize transactions which rows are stores and columns are months. It will be property of ItemTransaction objects.
			Transaction[][] transactions = new Transaction[stores.length][];
			
			int itemId = storeItem.getId();
			int storeIndex = 0;
			
			//Iterate over store data and add transactions to the relevant item ID.
			for (Transaction[][] store : stores) {
				
				transactions[storeIndex] = store[itemId-1];
				storeIndex++;
			}
			//Initialize a new item from reference item.
			Item item = new Item(storeItem);
			//Initialize the and store the ItemTransaction object. Stored in relevant item id.
			itemTransactions[itemId-1] = new ItemTransaction(item,transactions);
		}
		//Return the generated AnnualSale object.
		return new AnnualSale(itemTransactions);
	}

	//It parses the unique category names and returns.
	private String[] getCategories(Item[] items) {
		
		//Initialize the String[] category array. Default length is 5.
		String[] categories = new String[5];
		
		//Iterate over the items.
		for (Item item : items) {
			
			//Get the item category.
			String category = item.getCategory();
			
			//Check if exists in category array.
			if (searchString(categories, category) != -1) {
				//If exists, skip this value.
				continue;
			}else {
				//If it's not exists, add to thee category array.
				categories = add(categories, category);
			}
		}
 		
		//Return parsed String[] categories array.
		return categories;
	}
	
	//-----HELPER METHODS FOR ARRAY OPERATIONS-----//
	
	//It adds +1 new slot to the given array and returns.
	private String[] increaseArrayCapacity(String[] array) {
		//Initialize a new array which has +1 length bigger than given array.
		String[] newArray = new String[array.length+1];
		
		//Add old array's values to the new array with index.
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}	
		
		//Return to the stretched array. 
		return newArray;
	}
	
	//It checks if array is full.
	private boolean isFull(String[] array) {
		return !(array[array.length-1] == null);
	}
	
	//It searches for given string at array. If it finds, returns the index number. If not, returns -1.
	private int searchString(String[] array, String string) {
		int index = 0;
		//Iterate over the elements of array.
		for (String element : array) {
			//If it finds the value...
			if (element != null && element.equals(string)) {
				//Returns the index number.
				return index;
			}
			index++;
		}
		//If not, returns the -1.
		return -1;
	}
	
	//Adds a new String to the String[] array.
	private String[] add(String[] array, String string) {
		//Initializes the array.
		String[] array2 = array;
		
		//It checks if array is full. If array is full, it stretches the array.
		if (isFull(array2)) {
			array2 = increaseArrayCapacity(array2);
		}
		
		//If iterates over the array to find an empty place.
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] == null) {
				//Stores the string in empty place.
				array2[i] = string;
				break;
			}
		}
		//Returns to the updated array.
		return array2;
	}
		
	//----------PUBLIC PRINT METHODS----------//
	
	/* 
	 * Prints to the console "Most/Least profitable item for the whole year according to the given query string."
	 * query = "most" => Prints the most profitable item
	 * query = "least" => Prints the least profitable item
	 */
	public void printItemProfits(String query) {
		//Initialize the float[] array which stores profits for items index by index.
		float[] itemProfits = new float[this.itemArray.length];
		
		//Iterate all over the ItemTransaction objects.
		for (ItemTransaction itemTransaction : this.annualSale.getItemTransactions()) {
			
			//Get item properties.
			Item item = itemTransaction.getItem();
			int itemId = item.getId();
			
			//Initialize item profit.
			float itemProfit = 0.0f;
			
			//Iterate all over the transactions which belongs to the one item.
			for (Transaction[] transactions : itemTransaction.getTransactions()) {
				for (Transaction transaction : transactions) {
				
					//Add transactions profits to the item profit.
					itemProfit += transaction.getMonthlyProfit();
				}
			}
			
			//Store item profit at relevant id.
			itemProfits[itemId-1] = itemProfit;
		}
		
		//Make comparison operation according to the given query string.
		
		//query => "most" | Find the most profitable item.
		if (query.equals("most")) {
			
			//Set control value to the minimum float value.
			float mostProfitableValue = Float.MIN_VALUE;
			
			//Set index to -1.
			int mostProfitableIndex = -1;
			
			//Iterate all over the item profits.
			for (int i = 0; i < itemProfits.length; i++) {
				
				//Compare profit with current most profitable value.
				if (itemProfits[i] > mostProfitableValue) {
					//If it is bigger than current value. Assign it to the current value.
					mostProfitableValue = itemProfits[i];
					//Assign it's index to the current index.
					mostProfitableIndex = i;
				}
			}
			
			//Get Item objecy from index which is found. 
			Item mostProfitableItem = this.annualSale.getItemTransactions()[mostProfitableIndex].getItem();
			//Print the item name in a formatted way.
			System.out.println(String.format("1-Most profitable item for whole year is %s", mostProfitableItem.getName()));
			
		//query => "least" | Find the least profitable item.
		}else if (query.equals("least")) {
			
			//Set control value to the maximum float value.
			float leastProfitableValue = Float.MAX_VALUE;
			
			//Set index to -1.
			int leastProfitableIndex = -1;
			
			//Iterate all over the item profits.
			for (int i = 0; i < itemProfits.length; i++) {
				
				//Compare profit with current least profitable value.
				if (itemProfits[i] < leastProfitableValue) {
					//If it is smaller than current value. Assign it to the current value.
					leastProfitableValue = itemProfits[i];
					//Assign it's index to the current index.
					leastProfitableIndex = i;
				}
			}
			
			//Get Item objecy from index which is found. 
			Item leastProfitableItem = this.annualSale.getItemTransactions()[leastProfitableIndex].getItem();
			//Print the item name in a formatted way.
			System.out.println(String.format("3-Least profitable item for whole year is %s", leastProfitableItem.getName()));
		
		//query => Wrong Format | Print an error.	
		}else {
			System.err.println("Wrong Query!");
		}
	}
	
	/* 
	 * Prints to the console "Most/Least profitable category for the whole year according to the given query string."
	 * query = "most" => Prints the most profitable category
	 * query = "least" => Prints the least profitable category
	 */
	public void printCatalogueProfits(String query) {
		
		//Get the category names
		String[] categories = getCategories(this.itemArray);
		
		//Initialize the float[] array which stores profits for categories index by index.
		float[] profits = new float[categories.length];
		
		//Iterate all over the ItemTransaction objects.
		for (ItemTransaction itemTransaction : this.annualSale.getItemTransactions()) {
			
			//Get item properties.
			Item item = itemTransaction.getItem();
			String category = item.getCategory();
			
			//Iterate  all over the transactions.
			for (Transaction[] transactions : itemTransaction.getTransactions()) {
				for (Transaction transaction : transactions) {
					
					//Get monthly profit amount.
					float monthlyProfit = transaction.getMonthlyProfit();
					
					//Get the index of the category
					int index = searchString(categories, category);
					
					//Add profit to index which belongs to the current category.
					profits[index] += monthlyProfit;
				}
			}
		}
		
		//Make comparison operation according to the given query string.

		
		//query => "most" | Find the most profitable category.
		if (query.equals("most")) {
			
			//Set index to -1.
			int mostProfitIndex = -1;
			
			//Set control value to the minimum float value.
			float mostProfit = Float.MIN_VALUE;
			
			//Iterate all over the category profits.
			for (int i = 0; i < profits.length; i++) {
				
				//Compare profit with current most profitable value.
				if (profits[i] > mostProfit) {
					
					//If it is bigger than current value. Assign it to the current value.
					mostProfit = profits[i];
					
					//Assign it's index to the current index.
					mostProfitIndex = i;
				}
			}
			
			//Print the category name in a formatted way.
			System.out.println(String.format("2-Most profitable category for whole year is %s", categories[mostProfitIndex]));
		
		//query => "least" | Find the least profitable category.
		}else if (query.equals("least")) {
			
			//Set index to -1
			int leastProfitIndex = -1;
			
			//Set control value to the maximum float value.
			float leastProfit = Float.MAX_VALUE;
			
			//Iterate all over the category profits.
			for (int i = 0; i < profits.length; i++) {

				//Compare profit with current most profitable value.
				if (profits[i] < leastProfit) {
					
					//If it is smaller than current value. Assign it to the current value.
					leastProfit = profits[i];
					
					//Assign it's index to the current index.
					leastProfitIndex = i;
				}
			}
			
			//Print the category name in a formatted way.
			System.out.println(String.format("4-Least profitable category for whole year is %s", categories[leastProfitIndex]));

		//query => Wrong Format | Print an error.	
		}else {
			System.err.println("Wrong Query!");
		}
		
	}

	//Prints to the console "Most profitable item for a single sale"
	public void printMostProfitableSingleSale() {
		
		//Define the best profit with minimum float value.
		float bestSingleSaleProfit = Float.MIN_VALUE;
		
		//Define an empty string variable which will store the best single sale profit item. 
		String bestSingleSaleItemName = "";
		
		//Iterate all over the ItemTransaction objects.
		for (ItemTransaction itemTransaction : this.annualSale.getItemTransactions()) {
			
			//Get item properties.
			Item item = itemTransaction.getItem();
			String itemName = item.getName();
			
			//Iterate all over the transactions.
			for (Transaction[] transactions : itemTransaction.getTransactions()) {
				for (Transaction transaction : transactions) {
					
					//If an item transaction is more profitable than current item.
					if (transaction.getSingleSaleProfit() > bestSingleSaleProfit) {
						
						//Assign to the most profitable single-sale.
						bestSingleSaleProfit = transaction.getSingleSaleProfit();
						bestSingleSaleItemName = itemName;
					}
				}
			}
		}
		
		//Print the most profitable single-sale item in a formatted way.
		System.out.println(String.format("5-Best-selling item for the whole year is %s", bestSingleSaleItemName));
	}
	
	//Prints to the console "Best-selling item for the whole year"
	public void printBestSellingItem() {
		//Initialize a integer which holds the item sales.
		int[] numberOfSales = new int[this.itemArray.length];
		
		//Iterate all of the ItemTransaction objects.
		for (ItemTransaction itemTransaction : this.annualSale.getItemTransactions()) {
			
			//Get the item id property.
			int itemId = itemTransaction.getItem().getId();
			
			//Iterate all over the the transactions.
			for (Transaction[] transactions : itemTransaction.getTransactions()) {
				for (Transaction transaction : transactions) {
					//Add the sale amounts to the relevant item indexes.
					int itemSales = transaction.getNumberOfSales();
					numberOfSales[itemId-1] += itemSales; 
				}
			}
		}
		
		//Assign the best seller index -1.
		int bestSellerIndex = -1;
		
		//Assign the best seller amount which is minimum float value.
		int bestSaleAmount = Integer.MIN_VALUE;
		
		//Iterate all over the item sales to find the maximum sale amount.
		for (int i = 0; i < numberOfSales.length; i++) {
			
			//If it's amount bigger than current best seller amount. Assign new best-seller. 
			if (numberOfSales[i] > bestSaleAmount) {
				bestSaleAmount = numberOfSales[i];
				bestSellerIndex = i;
			}
		}
		
		//Get the best-selling item from the founded index.
		Item bestSellingItem = this.annualSale.getItemTransactions()[bestSellerIndex].getItem();
		
		//Print the best-selling item in a formatted way.
		System.out.println(String.format("6-Best-selling item for the whole year is %s", bestSellingItem.getName()));
		
	}
	
	//Prints to the console "Most profitable store for each month"
	public void printMostProfitableStoresByMonth() {
		
		//Print the header.
		System.out.println("\n-----------------------------------");
		System.out.println("7-MOST PROFITABLE STORES FOR MONTHS");
		System.out.println("-----------------------------------\n");

		//Define a string array from months of the year.
		String[] months = {"January",
		                   "February",
		                   "March",
		                   "April",
		                   "May",
		                   "June",
		                   "July",
		                   "August",
		                   "September",
		                   "October",
		                   "November",
		                   "December"};
		
		//Iterate for 12 months of the year
		for (int i = 0; i < 12 ; i++) {
			
			//Initiale a float array for every iteration which holds the store profits
			float[] storeProfits = new float[this.stores.length];
			
			//Iterate all over the ItemTransaction objects.
			for (ItemTransaction itemTransaction : this.annualSale.getItemTransactions()) {
				
				//Initialize the store datas.
				Transaction[][] transactions = itemTransaction.getTransactions();
				
				//Iterate over the stores data which in the current month iteration.
				//Like matrix transpose operation. 
				for (int j = 0; j < transactions.length; j++) {
					//Add store profit to the relevant index.
					storeProfits[j] += transactions[j][i].getMonthlyProfit();
				}
			}
			
			//Define the most profitable store index
			int mostProfitIndex = -1;
			
			//Define the most profitable store amount from minimum float value.
			float mostProfit = Float.MIN_VALUE;
			
			//Iterate all over the store profits.
			for (int k = 0; k < storeProfits.length; k++) {
				
				//If it is bigger than current value.
				if (storeProfits[k] > mostProfit) {
					//Assign it to most profit value.
					mostProfit = storeProfits[k];
					//Assign it's index to most profit index.
					mostProfitIndex = k;
				}
			}
			
			//Print the most profitable store in every month in a formatted way.
			System.out.println(String.format("Most profitable store for %s => Store%s",months[i],mostProfitIndex+1));
			
		}
	}

}
