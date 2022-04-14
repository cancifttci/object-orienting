package vehicles;

public class Automobile extends Vehicle{

	private String roofType;
	
	public Automobile() {
		super();
		this.roofType = "Roof Type didn't set.";
	}
	
	public Automobile(String insuranceNumber, int insuranceYear, String city, double engineVolume, int productionYear, String gearType, int fuelTankVolume,String roofType) {
		super(insuranceNumber,insuranceYear,city,engineVolume,productionYear,gearType,fuelTankVolume);
		this.roofType = roofType;
	}
	
	public Automobile(Automobile automobile) {
		this(automobile.getInsuranceNumber(),automobile.getInsuranceYear(),automobile.getCity(),automobile.getEngineVolume(),automobile.getProductionYear(),automobile.getGearType(),automobile.getFuelTankVolume(),automobile.getRoofType());
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}
	
	private double getRoofTypeRiskFactor() {
		String roofType = this.roofType;
		if (roofType.equalsIgnoreCase("regular")) {
			return 0.1;
		}else if (roofType.equalsIgnoreCase("sunroof")) {
			return 0.64;
		}else if (roofType.equalsIgnoreCase("moonroof")) {
			return 0.48;
		}else {
			return 0.0;
		}
	}
	
	@Override
	public double calculateRiskFactor() {
		
		double engineVolumeRiskFactor = this.getEngineVolume();
		double fuelTankVolumeRiskFactor = this.getFuelTankVolume()*0.004;
		int age = 2020-this.getProductionYear();
		double plateCityRiskFactor = this.getPlateCityRiskFactor()*0.03;
		double roofTypeRiskFactor = this.getRoofTypeRiskFactor();
		
		return engineVolumeRiskFactor*fuelTankVolumeRiskFactor*age*plateCityRiskFactor/roofTypeRiskFactor;
	}
	
	@Override
	public String toString() {
		return "Automobile";
	}
}
