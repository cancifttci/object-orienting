package application.layer;


import java.util.ArrayList;
import java.util.Scanner;

import data.layer.FileIO;
import insurances.Health;
import insurances.Household;
import insurances.Traffic;
import object.layer.InsuredValue;
import object.layer.PolicyRecord;

public class InsurancePolicyCalculatorApp {

	public static void main(String[] args) {

		FileIO fileIO = new FileIO("HW2_InsuredValues.csv");
		
		ArrayList<InsuredValue> insuredValues = fileIO.getInsuredValues();
		PolicyRecord policyRecord = new PolicyRecord(insuredValues);
		
		ArrayList<InsuredValue> workplaces = policyRecord.getWorkPlaceList();
		ArrayList<InsuredValue> housings = policyRecord.getHousingList();
		ArrayList<InsuredValue> people = policyRecord.getPersonList();
		ArrayList<InsuredValue> automobiles = policyRecord.getAutomobileList();
		ArrayList<InsuredValue> trucks = policyRecord.getTruckList();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("1 All insured values, 2 Workplaces, 3 Housings, 4 Persons, 5 Automobiles, 6 Trucks.");
		while (true) {
			int choice;
			String outputTemplate = "Insured Value: %s Insurance Number: %s Year of Insurance: %s\nThe policy charge of %s: %s TL\n";
			System.out.print("Please enter your choice:");
			try {
				choice = scanner.nextInt();
				System.out.println();
			} catch (Exception e) {
				System.err.println("Entered value can't be another type except integer. Program terminated.");
				break;
			}
			
			switch (choice) {
			case 1: {
				for (InsuredValue insuredValue : workplaces) {
					Household householdInsurance = new Household();
					double policyCharge = householdInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				for (InsuredValue insuredValue : housings) {
					Household householdInsurance = new Household();
					double policyCharge = householdInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				for (InsuredValue insuredValue : people) {
					Health healthInsurance = new Health();
					double policyCharge = healthInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				for (InsuredValue insuredValue : automobiles) {
					Traffic trafficInsurance = new Traffic();
					double policyCharge = trafficInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				for (InsuredValue insuredValue : trucks) {
					Traffic trafficInsurance = new Traffic();
					double policyCharge = trafficInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			case 2: {
				for (InsuredValue insuredValue : workplaces) {
					Household householdInsurance = new Household();
					double policyCharge = householdInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			case 3: {
				for (InsuredValue insuredValue : housings) {
					Household householdInsurance = new Household();
					double policyCharge = householdInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			case 4: {
				for (InsuredValue insuredValue : people) {
					Health healthInsurance = new Health();
					double policyCharge = healthInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			case 5: {
				for (InsuredValue insuredValue : automobiles) {
					Traffic trafficInsurance = new Traffic();
					double policyCharge = trafficInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			case 6: {
				for (InsuredValue insuredValue : trucks) {
					Traffic trafficInsurance = new Traffic();
					double policyCharge = trafficInsurance.calculatePolicyCharge(insuredValue);
					String insuranceNumber = insuredValue.getInsuranceNumber();
					int yearOfInsurance = insuredValue.getInsuranceYear();
					String insuranceType = insuredValue.toString();
					System.out.println(String.format(outputTemplate, insuranceType,insuranceNumber,yearOfInsurance,insuranceNumber,String.format("%,.2f", policyCharge)));
				}
				break;
			}
			default:
				System.err.println("Please enter a value between 1-6");
			}
			System.out.println("…………………………………………………………………………………………………………………");
			
		}
		scanner.close();
	}

}
