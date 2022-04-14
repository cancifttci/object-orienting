package object.layer;

public class InsuredValue {

	private String insuranceNumber;
	private int insuranceYear;
	private String city;
	
	public InsuredValue() {
		this.insuranceNumber = "Insurance Number didn't set.";
		this.insuranceYear = 0000;
		this.city = "Insurance City didn't set.";
	}
	
	public InsuredValue(String insuranceNumber, int insuranceYear, String city) {
		this.insuranceNumber = insuranceNumber;
		if (insuranceYear > 0) {
			this.insuranceYear = insuranceYear;
		}else {
			throw new IllegalArgumentException(Integer.toString(insuranceYear));
		}
		this.city = city;
	}
	
	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public int getInsuranceYear() {
		return insuranceYear;
	}

	public void setInsuranceYear(int insuranceYear) {
		this.insuranceYear = insuranceYear;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double calculateRiskFactor() {
		return 0.0;
	}
	
}
