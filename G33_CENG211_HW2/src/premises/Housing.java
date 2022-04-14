package premises;

public class Housing extends Premises{
	
	private String residentSituation;

	public Housing() {
		super();
		this.residentSituation = "Resident Situation didn't set.";
	}
	
	public Housing(String insuranceNumber, int insuranceYear, String city, int numberOfFloors,int constructionYear, String typeOfConstruction, int surfaceArea, String residentSituation) {
		super(insuranceNumber,insuranceYear,city,numberOfFloors,constructionYear,typeOfConstruction,surfaceArea);
		this.residentSituation = residentSituation;
	}
	
	public Housing(Housing housing) {
		this(housing.getInsuranceNumber(),housing.getInsuranceYear(),housing.getCity(),housing.getNumberOfFloors(),housing.getConstructionYear(),housing.getTypeOfConstruction(),housing.getSurfaceArea(),housing.getResidentSituation());
	}

	public String getResidentSituation() {
		return residentSituation;
	}

	public void setResidentSituation(String residentSituation) {
		this.residentSituation = residentSituation;
	}
	
	private double getResidentSituationRiskFactor() {
		String residentSituation = this.residentSituation;
		double residentSituationRiskFactor = 0.0;
		if (residentSituation.equalsIgnoreCase("tenant")) {
			residentSituationRiskFactor = 0.18;
		}else if (residentSituation.equalsIgnoreCase("landlord")) {
			residentSituationRiskFactor = 0.42;
		}
		return residentSituationRiskFactor;
	}
	
	@Override
	public double calculateRiskFactor() {
		double premisesCityRiskFactor = getCityRiskFactor();
		double floorRiskFactor = getFloorRiskFactor();
		double constructionYearRiskFactor = getConstructionYearRiskFactor();
		double constructionTypeRiskFactor = getConstructionTypeRiskFactor();
		double surfaceAreaRiskFactor = this.getSurfaceArea();
		double residentSituationRiskFactor = getResidentSituationRiskFactor();
		
		return premisesCityRiskFactor*floorRiskFactor*constructionYearRiskFactor*constructionTypeRiskFactor*surfaceAreaRiskFactor/residentSituationRiskFactor;
	}
	
	@Override
	public String toString() {
		return "Housing";
	}
}
