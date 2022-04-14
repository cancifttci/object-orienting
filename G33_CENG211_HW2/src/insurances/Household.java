package insurances;

import object.layer.InsuredValue;
import premises.Housing;
import premises.Workplace;

public class Household extends Insurance{

	public Household() {
		super();
	}
	
	@Override
	public double calculatePolicyCharge(InsuredValue insuredValue) {
				
		if (!(insuredValue instanceof Housing || insuredValue instanceof Workplace)) {
			throw new IllegalArgumentException(insuredValue.getClass()+" | ILLEGAL TYPE");
		}
		
		double riskFactor = insuredValue.calculateRiskFactor();
		int insuranceRank = 2020 - insuredValue.getInsuranceYear();
		int fixedFee = this.getFixedFee();
		double policyCharge = fixedFee*Math.pow(riskFactor, 2);
		if (insuranceRank > 2) {
			return policyCharge-(policyCharge*0.1);
		}else {
			return policyCharge;
		}
	}

}
