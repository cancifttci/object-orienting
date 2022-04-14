package premises;

public class Workplace extends Premises{

	private int annualRevenue;
	
	public Workplace() {
		super();
		this.annualRevenue = 0;
	}
	
	public Workplace(String insuranceNumber, int insuranceYear, String city, int numberOfFloors,int constructionYear, String typeOfConstruction, int surfaceArea, int annualRevenue) {
		super(insuranceNumber,insuranceYear,city,numberOfFloors,constructionYear,typeOfConstruction,surfaceArea);
		if (annualRevenue > 0) {
			this.annualRevenue = annualRevenue;
		}else {
			throw new IllegalArgumentException(Integer.toString(annualRevenue));
		}
	}
	
	public Workplace(Workplace workPlace) {
		this(workPlace.getInsuranceNumber(),workPlace.getInsuranceYear(),workPlace.getCity(),workPlace.getNumberOfFloors(),workPlace.getConstructionYear(),workPlace.getTypeOfConstruction(),workPlace.getSurfaceArea(),workPlace.getAnnualRevenue());
	}

	public int getAnnualRevenue() {
		return annualRevenue;
	}

	public void setAnnualRevenue(int annualRevenue) {
		this.annualRevenue = annualRevenue;
	}
	
	@Override
	public double calculateRiskFactor() {
		
		double premisesCityRiskFactor = getCityRiskFactor();
		double floorRiskFactor = getFloorRiskFactor();
		double constructionYearRiskFactor = getConstructionYearRiskFactor();
		double constructionTypeRiskFactor = getConstructionTypeRiskFactor();
		double surfaceAreaRiskFactor = this.getSurfaceArea()*0.2;
		double annualRevenueRiskFactor = this.annualRevenue*0.003;
		
		return premisesCityRiskFactor*floorRiskFactor*constructionYearRiskFactor*constructionTypeRiskFactor*surfaceAreaRiskFactor*annualRevenueRiskFactor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Workplace";
	}
	
}
