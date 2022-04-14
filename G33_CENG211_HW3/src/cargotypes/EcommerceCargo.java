package cargotypes;

import application.Status;
import exceptions.CargoCodeLengthMismatchException;
import websites.CommercialWebsite;

public class EcommerceCargo<T> extends Cargo {

	//generic cargo code type for different websites
	private T cargoCode;
	private CommercialWebsite<T> website;
	private Status status;

	//default constructor
	public EcommerceCargo() {
		this(0, 0, 0, 0, null,null);
	}
	//contructor
	public EcommerceCargo(int height,int weight,int width,int length,T cargoCode,CommercialWebsite<T> website) {
		//constructor method
		super("EcommerceCargo", height, weight, width, length);
		//initialize the default status
		this.status = Status.ACCEPTED;
		this.website = website;


		boolean formatCheck = website.checkCodeFormat(cargoCode);

		//checking code format 
		try {

			if (formatCheck) {

				this.cargoCode=cargoCode;
			}
			//throws exception for code length
			else {

				throw new CargoCodeLengthMismatchException();
			}
			//exception handling  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// return copy website(deep copy)
	public CommercialWebsite<T> getWebsite(){
		return this.website.copyWebsite();
	}
	//getters for fields
	public T getCargoCode() {
		return cargoCode;
	}

	public Status getCargoStatus() {
		return status;
	}
	// setter for cargostatus
	public void setCargoStatus(Status cargoStatus) {
		this.status = cargoStatus;
	}


}