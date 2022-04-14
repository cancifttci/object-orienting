package insurances;

import java.util.Random;

import object.layer.InsuredValue;

public class Insurance {
	
	private int fixedFee;

	public Insurance() {
		Random random = new Random();
		fixedFee = random.ints(1000, 3001).findFirst().getAsInt();
	}
	
	public int getFixedFee() {
		return fixedFee;
	}
	
	public double calculatePolicyCharge(InsuredValue insuredValue) {
		return 0.0;
	}
}
