package vehicles;

import object.layer.InsuredValue;

public class Vehicle extends InsuredValue{
	
	private double engineVolume;
	private int productionYear;
	private String gearType;
	private int fuelTankVolume;
	
	public Vehicle() {
		super();
		this.engineVolume = 0.0;
		this.productionYear = 0000;
		this.gearType = "Gear Type didn't set.";
		this.fuelTankVolume = 0;
	}
	
	public Vehicle(String insuranceNumber, int insuranceYear, String city, double engineVolume, int productionYear, String gearType, int fuelTankVolume) {
		super(insuranceNumber,insuranceYear,city);
		if (engineVolume > 0) {
			this.engineVolume = engineVolume;
		}else {
			throw new IllegalArgumentException(Double.toString(engineVolume));
		}
		if (productionYear > 0) {
			this.productionYear = productionYear;
		}else {
			throw new IllegalArgumentException(Integer.toString(productionYear));
		}
		this.gearType = gearType;
		if (fuelTankVolume > 0) {
			this.fuelTankVolume = fuelTankVolume;
		}else {
			throw new IllegalArgumentException(Integer.toString(fuelTankVolume));
		}
	}

	public double getEngineVolume() {
		return engineVolume;
	}

	public void setEngineVolume(double engineVolume) {
		this.engineVolume = engineVolume;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public String getGearType() {
		return gearType;
	}

	public void setGearType(String gearType) {
		this.gearType = gearType;
	}

	public int getFuelTankVolume() {
		return fuelTankVolume;
	}

	public void setFuelTankVolume(int fuelTankVolume) {
		this.fuelTankVolume = fuelTankVolume;
	}
	
	@Override
	public double calculateRiskFactor() {
		return 0.0;
	}
	
	double getPlateCityRiskFactor() {
		String plateCity = this.getCity();
		if (plateCity.equalsIgnoreCase("izmir")) {
			return 0.78;
		}else if (plateCity.equalsIgnoreCase("istanbul")) {
			return 0.97;
		}else if (plateCity.equalsIgnoreCase("ankara")) {
			return 0.85;
		}else {
			return 0.65;
		}
	}
	
	double getGearTypeRiskFactor() {
		String gearType = this.getGearType();
		if (gearType.equalsIgnoreCase("manual")) {
			return 0.47;
		}else if (gearType.equalsIgnoreCase("automatic")) {
			return 0.98;
		}else {
			return 0.0;
		}
	}
	
	
	
	
	
}
