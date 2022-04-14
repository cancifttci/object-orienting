package insurances;

import object.layer.InsuredValue;
import persons.Person;

public class Health extends Insurance{

	public Health() {
		super();
	}
	
	@Override
	public double calculatePolicyCharge(InsuredValue insuredValue) {
		
		if (!(insuredValue instanceof Person)) {
			throw new IllegalArgumentException(insuredValue.getClass()+" | ILLEGAL TYPE");
		}
		Person person = (Person)insuredValue;
		int insuranceRank = 2020 - person.getInsuranceYear();
		int personAge = 2020 - person.getYearOfBirth();
		int fixedFee = this.getFixedFee();
		double riskFactor = insuredValue.calculateRiskFactor();
		double policyCharge = fixedFee * Math.pow(riskFactor, 3) / 208;
		
		if (personAge > 50 && insuranceRank < 3) {
			return 1000000000;
		}else {
			return policyCharge;
		}
	}

}
