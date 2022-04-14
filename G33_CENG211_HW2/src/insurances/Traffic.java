package insurances;

import object.layer.InsuredValue;
import vehicles.Automobile;
import vehicles.Truck;

public class Traffic extends Insurance{
	
	public Traffic() {
		super();
	}
	
	@Override
	public double calculatePolicyCharge(InsuredValue insuredValue) {
		
		if (!(insuredValue instanceof Automobile || insuredValue instanceof Truck)) {
			throw new IllegalArgumentException(insuredValue.getClass()+" | ILLEGAL TYPE");
		}
		int insuranceRank = 2020-insuredValue.getInsuranceYear();
		double riskFactor = insuredValue.calculateRiskFactor();
		int fixedFee = this.getFixedFee();
		double policyCharge = (fixedFee-fixedFee*0.1)+(fixedFee*Math.pow(riskFactor, 0.5));
		
		if (insuranceRank > 1) {
			return policyCharge - (policyCharge*0.2);
		}else {
			return policyCharge;
		}
	}

}
