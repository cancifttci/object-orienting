package premises;

import object.layer.InsuredValue;

public class Premises extends InsuredValue{

	private int numberOfFloors;
	private int constructionYear;
	private String typeOfConstruction;
	private int surfaceArea;
	
	
	public Premises() {
		super();
		this.numberOfFloors = 0;
		this.constructionYear = 0000;
		this.typeOfConstruction = "Type of construction didn't set.";
		this.surfaceArea = 0;
	}
	
	public Premises(String insuranceNumber, int insuranceYear, String city, int numberOfFloors,int constructionYear, String typeOfConstruction, int surfaceArea) {
		super(insuranceNumber, insuranceYear, city);
		if (numberOfFloors > 0) {
			this.numberOfFloors = numberOfFloors;
		}else {
			throw new IllegalArgumentException(Integer.toString(numberOfFloors));
		}
		if (constructionYear > 0) {
			this.constructionYear = constructionYear;
		}else {
			throw new IllegalArgumentException(Integer.toString(constructionYear));
		}
		this.typeOfConstruction = typeOfConstruction;
		if (surfaceArea > 0) {
			this.surfaceArea = surfaceArea;
		}else {
			throw new IllegalArgumentException(Integer.toString(surfaceArea));
		}
	}
	
	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getConstructionYear() {
		return constructionYear;
	}

	public void setConstructionYear(int constructionYear) {
		this.constructionYear = constructionYear;
	}

	public String getTypeOfConstruction() {
		return typeOfConstruction;
	}

	public void setTypeOfConstruction(String typeOfConstruction) {
		this.typeOfConstruction = typeOfConstruction;
	}

	public int getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(int surfaceArea) {
		this.surfaceArea = surfaceArea;
	}
	
	@Override
	public double calculateRiskFactor() {
		return 0.0;
	}
	
	
	double getCityRiskFactor() {
		String city = this.getCity();
		if (city.equalsIgnoreCase("izmir")) {
			return 0.4;
		}else if (city.equalsIgnoreCase("istanbul")) {
			return 0.6;
		}else if (city.equalsIgnoreCase("ankara")) {
			return 0.15;
		}else {
			return 0.25;
		}
	}
	
	double getFloorRiskFactor() {
		int numberOfFloors = this.numberOfFloors;
		if(1 <= numberOfFloors && numberOfFloors <= 3) {
			return 0.1;
		}else if (4 <= numberOfFloors && numberOfFloors <= 7) {
			return 0.25;
		}else if (8 <= numberOfFloors && numberOfFloors <= 18) {
			return 0.5;
		}else if (numberOfFloors > 18) {
			return 0.85;
		}else {
			return 0.0;
		}
	}
	
	double getConstructionYearRiskFactor() {
		int constructionYear = this.constructionYear;
		double constructionYearRiskFactor = 0.0;
		if (constructionYear < 1975) {
			constructionYearRiskFactor = 0.58;
		}else if (1975 <= constructionYear && constructionYear <= 1999) {
			constructionYearRiskFactor = 0.32;
		}else if (constructionYear > 1999) {
			constructionYearRiskFactor = 0.1;
		}
		return constructionYearRiskFactor;
	}
	
	double getConstructionTypeRiskFactor() {
		String constructionType = this.typeOfConstruction;
		if (constructionType.equalsIgnoreCase("steel")) {
			return 0.1;
		}else if (constructionType.equalsIgnoreCase("concrete")) {
			return 0.37;
		}else if (constructionType.equalsIgnoreCase("wood")) {
			return 0.58;
		}else {
			return 0.92;
		}
	}

}
