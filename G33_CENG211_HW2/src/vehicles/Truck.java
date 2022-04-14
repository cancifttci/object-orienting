package vehicles;

public class Truck extends Vehicle{

	private String truckBedType;
	
	public Truck() {
		super();
		this.truckBedType = "Truck Bed Type didn't set.";
	}
	
	public Truck(String insuranceNumber, int insuranceYear, String city, double engineVolume, int productionYear, String gearType, int fuelTankVolume,String truckBedType) {
		super(insuranceNumber,insuranceYear,city,engineVolume,productionYear,gearType,fuelTankVolume);
		this.truckBedType = truckBedType;
	}
	
	public Truck(Truck truck) {
		this(truck.getInsuranceNumber(),truck.getInsuranceYear(),truck.getCity(),truck.getEngineVolume(),truck.getProductionYear(),truck.getGearType(),truck.getFuelTankVolume(),truck.getTruckBedType());
	}

	public String getTruckBedType() {
		return truckBedType;
	}

	public void setTruckBedType(String truckBedType) {
		this.truckBedType = truckBedType;
	}
	
	private double getBedTypeRiskFactor() {
		String truckBedType = this.truckBedType;
		if (truckBedType.equalsIgnoreCase("trailer")) {
			return 0.87;
		}else if (truckBedType.equalsIgnoreCase("tanker")) {
			return 0.84;
		}else if (truckBedType.equalsIgnoreCase("regular")) {
			return 0.15;
		}else {
			return 0.0;
		}
	}
	
	@Override
	public double calculateRiskFactor() {
		int age = 2020-this.getProductionYear();
		double fuelTankVolumeRiskFactor = this.getFuelTankVolume()*0.008;
		double engineVolumeRiskFactor = this.getEngineVolume();
		double plateCityRiskFactor = this.getPlateCityRiskFactor()*0.03;
		double truckBedTypeRiskFactor = this.getBedTypeRiskFactor();
		
		return Math.pow(age, 3)/fuelTankVolumeRiskFactor*engineVolumeRiskFactor*plateCityRiskFactor/Math.pow(truckBedTypeRiskFactor, 2);
	}
	
	@Override
	public String toString() {
		return "Truck";
	}
	

}
