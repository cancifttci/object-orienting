package persons;

import object.layer.InsuredValue;

public class Person extends InsuredValue{

	private String name;
	private String nationalId;
	private String gender;
	private int yearOfBirth;
	private String illnessType;
	
	public Person() {
		super();
		this.name = "Name didn't set.";
		this.nationalId = "National ID didn't set.";
		this.gender = "Gender didn't set.";
		this.yearOfBirth = 0000;
		this.illnessType = "Chronicle Illness type didn't set.";
	}
	
	public Person(String insuranceNumber, int insuranceYear, String city, String name, String nationalId, String gender, int yearOfBirth, String illnessType) {
		super(insuranceNumber,insuranceYear,city);
		this.name = name;
		this.nationalId = nationalId;
		this.gender = gender;
		if (yearOfBirth > 0) {
			this.yearOfBirth = yearOfBirth;
		}else {
			throw new IllegalArgumentException(Integer.toString(yearOfBirth));
		}
		this.illnessType = illnessType;
	}
	
	public Person(Person person) {
		this(person.getInsuranceNumber(),person.getInsuranceYear(),person.getCity(),person.getName(),person.getNationalId(),person.getGender(),person.getYearOfBirth(),person.getIllnessType());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getIllnessType() {
		return illnessType;
	}

	public void setIllnessType(String illnessType) {
		this.illnessType = illnessType;
	}
	
	private double getIllnessRiskFactor() {
		String chronicleIllness = this.illnessType;
		if (chronicleIllness.equalsIgnoreCase("cardiovascular")) {
			return 1.85;
		}else if (chronicleIllness.equalsIgnoreCase("diabetes")) {
			return 1.84;
		}else if (chronicleIllness.equalsIgnoreCase("respiratory")) {
			return 1.86;
		}else if (chronicleIllness.equalsIgnoreCase("other")) {
			return 1.8;
		}else if (chronicleIllness.equalsIgnoreCase("none")) {
			return 0.1;
		}else {
			return 0.0;
		}
	}
	
	@Override
	public double calculateRiskFactor() {
		int age = 2020-this.yearOfBirth;
		double chronicleIllnessRiskFactor = getIllnessRiskFactor()*18;
		
		return Math.pow(age, 2)/chronicleIllnessRiskFactor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person";
	}

}
