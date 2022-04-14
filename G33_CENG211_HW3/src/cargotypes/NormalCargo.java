package cargotypes;
import exceptions.CargoCodeLengthMismatchException;
import exceptions.IDNotCorrectException;
import interfaces.ICargoPrice;

public class NormalCargo extends Cargo implements ICargoPrice {

	private String senderID;
	private String senderName;
	private String recipientName;
	private String recipientAddress;
	private double price;
	private int cargoCode; 

	//default constructor
	public NormalCargo() {
		//constructor method
		this(0, 0, 0, 0, "00000000000", "not set", "not set", "not set");
	}

	//constructor
	public NormalCargo(int height, int weight, int width, int length,String senderID,String senderName,String recipientName,String recipientAddress) {
		super("NormalCargo", height, weight, width, length);

		//checking sender id length
		try {
			if (senderID.length()==11) {
				this.senderID=senderID;
			}
			//throws exception for not correct id
			else {
				throw new IDNotCorrectException();      
			}
			//handling the exception
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		this.senderName=senderName;
		this.recipientName=recipientName;
		this.recipientAddress=recipientAddress;

		// default price and cargo code
		this.price = 0;
		this.cargoCode = -1111111;
	}

	//overriding calculate price method to calculate price
	@Override
	public double calculatePrice() {
		return 18.5+3*this.getSize();
	}

	// its an empty method by that can calculate the discount if we wish
	@Override
	public double calculateDiscountedPrice(double discountRatePercentage) {
		return 0;
	}
	
	// getters and setters for fields
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCargoCode() {
		return cargoCode;
	}

	public void setCargoCode(int cargoCode) {

		//checking cargo code length
		try {
			if (String.valueOf(cargoCode).length() == 7) {

				this.cargoCode = cargoCode;
			}
			//throws code length mismatch exception in case of need
			else {
				throw new CargoCodeLengthMismatchException();
			}

		}
		//handling the exception
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	//getter methods
	public String getSenderID() {
		return senderID;
	}
	public String getSenderName() {
		return senderName;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}



}