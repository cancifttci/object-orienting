package object.layer;

import java.util.ArrayList;

public class PolicyRecord {
	
	private ArrayList<InsuredValue> insuredValueList;

	public PolicyRecord() {
		this.insuredValueList = new ArrayList<InsuredValue>();
	}
	
	public PolicyRecord(ArrayList<InsuredValue> insuredValues) {
		this.insuredValueList = insuredValues;
	}
	
	private String getInsuranceType(InsuredValue insuredValue) {
		String insuranceExpression = insuredValue.getInsuranceNumber().substring(0,1);
		if (insuranceExpression.equalsIgnoreCase("W")) {
			return "Workplace";
		}else if (insuranceExpression.equalsIgnoreCase("H")) {
			return "Housing";
		}else if (insuranceExpression.equalsIgnoreCase("P")) {
			return "Person";
		}else if (insuranceExpression.equalsIgnoreCase("A")) {
			return "Automobile";
		}else {
			return "Truck";
		}
	}
	
	public ArrayList<InsuredValue> getWorkPlaceList() {
		ArrayList<InsuredValue> insuredWorkplaceValues = new ArrayList<InsuredValue>();
		for (InsuredValue insuredValue : this.insuredValueList) {
			if (getInsuranceType(insuredValue).equals("Workplace")) {
				insuredWorkplaceValues.add(insuredValue);
			}
		}
		return insuredWorkplaceValues;
	}
	
	public ArrayList<InsuredValue> getHousingList() {
		ArrayList<InsuredValue> insuredHousingValues = new ArrayList<InsuredValue>();
		for (InsuredValue insuredValue : this.insuredValueList) {
			if (getInsuranceType(insuredValue).equals("Housing")) {
				insuredHousingValues.add(insuredValue);
			}
		}
		return insuredHousingValues;
	}
	
	public ArrayList<InsuredValue> getPersonList() {
		ArrayList<InsuredValue> insuredPersonList = new ArrayList<InsuredValue>();
		for (InsuredValue insuredValue : this.insuredValueList) {
			if (getInsuranceType(insuredValue).equals("Person")) {
				insuredPersonList.add(insuredValue);
			}
		}
		return insuredPersonList;
	}
	
	public ArrayList<InsuredValue> getAutomobileList() {
		ArrayList<InsuredValue> insuredAutomobileList = new ArrayList<InsuredValue>();
		for (InsuredValue insuredValue : this.insuredValueList) {
			if (getInsuranceType(insuredValue).equals("Automobile")) {
				insuredAutomobileList.add(insuredValue);
			}
		}
		return insuredAutomobileList;
	}
	
	public ArrayList<InsuredValue> getTruckList() {
		ArrayList<InsuredValue> insuredTruckList = new ArrayList<InsuredValue>();
		for (InsuredValue insuredValue : this.insuredValueList) {
			if (getInsuranceType(insuredValue).equals("Truck")) {
				insuredTruckList.add(insuredValue);
			}
		}
		return insuredTruckList;
	}
	
}
