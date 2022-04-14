package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import cargotypes.*;
import websites.*;



public class Agency {

	private ArrayList<Cargo> normalCargos;
	private ArrayList<Cargo> eCommerceCargos;
	private Map<String, Integer> countingTable;
	private int systemDay;
	private Day todayDay;

	public Agency() {
		//Agency constructor
		this.normalCargos = new ArrayList<Cargo>();
		this.eCommerceCargos = new ArrayList<Cargo>();
		this.countingTable = new HashMap<String,Integer>();
		this.systemDay = Day.getCurrentDayIndex();
		this.todayDay = Day.getDayByID(systemDay);

	}

	//Processes the cargo packages for acceptance
	@SuppressWarnings({ "rawtypes", "unused" })
	public void startAcceptanceProcess(ArrayList<Cargo> cargos) {
		//Initializes the websites for limit checking.
		Amazon<Integer> amazon = new Amazon<Integer>();
		Trendyol<Integer> trendyol = new Trendyol<Integer>();
		Hepsiburada<String> hepsiburada = new Hepsiburada<String>();
		N11<String> n11 = new N11<String>();

		//Process every cargo
		for (Cargo cargo : cargos) {

			//If current day is workday, process the cargo packages
			if(todayDay.isWorkday()) {

				//Calculate the cargo delivery day.
				cargo = calculateDeliveryDay(cargo);

				//If cargo is normal cargo package. Process with normal cargo procedure.
				if (cargo.getType() == "NormalCargo") {
					//Typecast to NormalCargo
					NormalCargo cargopackage = (NormalCargo)cargo;
					//Calculate Price
					double cost = cargopackage.calculatePrice();
					//Set price to the cargo package
					cargopackage.setPrice(cost);
					//Generate a 7-digit cargo code and set to cargo.  
					cargopackage.setCargoCode(generateCargoCode());
					//add cargo package to stack
					normalCargos.add(cargopackage);

				}
				//If cargo is ecommerce cargo package. Process with ecommerce cargo procedure.
				else if(cargo.getType() == "EcommerceCargo") {
					//Typecast to EcommerceCargo
					EcommerceCargo<?> tempCargo = (EcommerceCargo<?>)cargo;
					//Initialize the limit and website data 
					int limit = 0;
					String website = ""; 

					//Get corgo package website and define limit and website data
					if (tempCargo.getWebsite().toString() == "Hepsiburada") {
						limit = tempCargo.getWebsite().getWebsiteLimit();
						website = "Hepsiburada";
					}else if (tempCargo.getWebsite().toString() == "Trendyol") {
						limit = tempCargo.getWebsite().getWebsiteLimit();
						website = "Trendyol";
					}else if (tempCargo.getWebsite().toString() == "Amazon") {
						limit = tempCargo.getWebsite().getWebsiteLimit();
						website = "Amazon";
					}else if (tempCargo.getWebsite().toString() == "N11") {
						limit = tempCargo.getWebsite().getWebsiteLimit();
						website = "N11";
					}

					//counts package limits with key-value or website-limit relation using Hashmap
					if(countingTable.containsKey(website)) {
						//if a website reaches its limits, its status will be not accepted.
						if (countingTable.get(website) == limit) {
							tempCargo.setCargoStatus(Status.NOTACCEPTED);
						}
						//if not, continue to acceptance
						else if (countingTable.get(website) < limit) {
							tempCargo.setCargoStatus(Status.ACCEPTED);
							int currentCount = countingTable.get(website);
							countingTable.replace(website, currentCount+1);
						}
					}else {
						tempCargo.setCargoStatus(Status.ACCEPTED);
						countingTable.put(website,1);
					}

					//Add eCommerceCargo to the list
					eCommerceCargos.add(tempCargo);

				}
			}
			//It it is Sunday not accept or process any cargo.
			else {
				//Set delivery day to null
				cargo.setDeliveryDay(null);
				//Set ecommerce cargo status to "not accepted"
				if (cargo instanceof EcommerceCargo<?>) {
					((EcommerceCargo) cargo).setCargoStatus(Status.NOTACCEPTED);
					eCommerceCargos.add(cargo);
				}else {
					normalCargos.add(cargo);
				}

			}
		}

	}

	//It combines and prints parts of the summary message
	public void printAgencySummary() {
		printHeader();
		printNormalCargos();
		printEcommerceCargos();
	}

	//Prints the header part of summary
	private void printHeader() {
		System.out.println("Welcome!");
		System.out.println("Number of Accepted Cargo: "+ getAcceptedCargoPackages());
		System.out.println("Number of Not Accepted Cargo: "+((eCommerceCargos.size()+normalCargos.size()) - getAcceptedCargoPackages()));
		System.out.println("Here are the details:");
	}

	//Prints Normal Cargo Package Summary using format method
	private void printNormalCargos() {
		System.out.println("\nNormal Cargo Packages:\n");
		String format = "%1$-5s %2$-15s %3$-15s %4$-15s %5$-20s %6$-30s %7$-5s %8$-15s %9$-15s \n";
		System.out.format(format, "No", "Cargo Code", "Sender ID", "Sender Name", "Recipient Name", "Recipient Address", "Size", "Price", "Delivery Day");
		int counter = 1;
		for (Cargo cargo : normalCargos) {
			NormalCargo normalCargo = (NormalCargo)cargo;
			String deliveryDay = "-";
			if(normalCargo.getDeliveryDay() != null) {
				deliveryDay = normalCargo.getDeliveryDay().toString();
			}
			System.out.format(format, counter, normalCargo.getCargoCode(), normalCargo.getSenderID(), normalCargo.getSenderName(), normalCargo.getRecipientName(), normalCargo.getRecipientAddress(), normalCargo.getSize(),normalCargo.getPrice(), deliveryDay);
			counter++;
		}
	}

	//Prints Normal Cargo Package Summary using format method
	private void printEcommerceCargos() {
		System.out.println("\nE-commerce Cargo Packages:\n");
		String format = "%1$-5s %2$-15s %3$-15s %4$-15s %5$-10s %6$-30s \n";
		System.out.format(format, "No", "E-commerce Site", "Cargo Code", "Status", "Size", "Delivery Day");
		int counter = 1;
		for (Cargo cargo : eCommerceCargos) {
			EcommerceCargo<?> eCommerceCargo = (EcommerceCargo<?>)cargo;
			String deliveryDay = "-";
			if(eCommerceCargo.getDeliveryDay() != null) {
				deliveryDay = eCommerceCargo.getDeliveryDay().toString();
			}
			System.out.format(format, counter, eCommerceCargo.getWebsite(),eCommerceCargo.getCargoCode(),eCommerceCargo.getCargoStatus(),eCommerceCargo.getSize(),deliveryDay);
			counter++;
		}
	}

	//Finds the number of accepted cargo packages.
	private int getAcceptedCargoPackages(){
		if (todayDay.isWorkday()) {
			//All of the normal cargo packages accepted. Counter starts with normal cargo number
			int counter = normalCargos.size();
			//Checks every Ecommerce cargo packace for if it is accepted or not.
			for (Cargo cargo : eCommerceCargos) {
				EcommerceCargo<?> eCargo = (EcommerceCargo<?>)cargo;
				//if its accepted adds to counter
				if (eCargo.getCargoStatus() == Status.ACCEPTED) {
					counter++;
				}
			}
			//returns accepted cargo packages number.
			return counter;
		}
		//If current day sunday agency is closed. No acceptance.
		else {
			return 0;
		}
	}

	//Gets a cargo package and calculates the delivery day.
	private Cargo calculateDeliveryDay(Cargo cargo){
		int deliveryDayIndex;
		//If current day is friday or saturday it calculates 3 day for delivery because sunday is not a workday and skips it.
		if (systemDay == 5 || systemDay == 6) {
			//Takes the modulo of the day index because of the overflow.
			deliveryDayIndex=(systemDay+3)%7;	
		}
		//Other days calculates 2 day for delivery
		else {
			deliveryDayIndex = (systemDay+2);
		}
		//Gets a delivery Day
		Day deliveryDay = Day.getDayByID(deliveryDayIndex);
		//Sets it to cargı
		cargo.setDeliveryDay(deliveryDay);
		//Returns the cargo
		return cargo;
	}

	//Generates a 7-digit random integer
	public int generateCargoCode() {
		Random random = new Random();
		long nextLong = Math.abs(random.nextLong());
		return Integer.parseInt(String.valueOf(nextLong).substring(0, 7));
	}
}
