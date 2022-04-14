package data.layer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import object.layer.InsuredValue;
import persons.Person;
import premises.Housing;
import premises.Workplace;
import vehicles.Automobile;
import vehicles.Truck;

public class FileIO {

	private String filePath;
	public FileIO(String filePath) {
		this.filePath = filePath;
	}

	
	public ArrayList<InsuredValue> getInsuredValues(){
		//Declarations for Reader
		File file;
		FileReader fileReader;
		BufferedReader bufferedReader;
		
		//Store the csv data at String rawData to avoid the multiple readings.
		String rawData = "";
		
		//Store the line data in counter to initialize the item array.
		String line;
		
		
		try {
			//Assignments for reader
			file = new File(this.filePath);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			//Read line and store at rawData String.
			while ((line = bufferedReader.readLine()) != null) {
				rawData += line+"\n";
			}
			
			//Finish reading process and close the readers.
			fileReader.close();
			bufferedReader.close();
		//Handle exceptions.	
		} catch (FileNotFoundException f) {
			System.out.println(f);
		} catch (IOException i) {
			System.out.println(i);
		}
		//Initialize the String[][] InsuredValues array.
		ArrayList<InsuredValue> insuredValues = new ArrayList<>();
		//Split to lines the rawData
		String lines[] = rawData.split("\n");
		
		//Store item data index by index
		for (String line2 : lines) {
			
			String[] insurance = line2.split(",");
			String insuranceType = insurance[0].substring(0,1);
			
			String insuranceNumber = insurance[0];
			int insuranceYear = Integer.parseInt(insurance[1]);
			String city = insurance[2];
			
			switch (insuranceType) {
			
			case "W": {
				
				int numberOfFloors = Integer.parseInt(insurance[3]);
				int constructionYear = Integer.parseInt(insurance[4]);
				String typeOfConstruction = insurance[5];
				int surfaceArea = Integer.parseInt(insurance[6]);
				int annualRevenue = Integer.parseInt(insurance[7]);
				
				Workplace workplace = new Workplace(insuranceNumber, insuranceYear, city, numberOfFloors, constructionYear, typeOfConstruction, surfaceArea, annualRevenue);
				insuredValues.add(workplace);
				break;
			}
			case "H": {
				
				int numberOfFloors = Integer.parseInt(insurance[3]);
				int constructionYear = Integer.parseInt(insurance[4]);
				String typeOfConstruction = insurance[5];
				int surfaceArea = Integer.parseInt(insurance[6]);
				String residentSituation = insurance[7];
				
				Housing housing = new Housing(insuranceNumber, insuranceYear, city, numberOfFloors, constructionYear, typeOfConstruction, surfaceArea, residentSituation);
				insuredValues.add(housing);
				break;
			}
			
			case "P":{
				String name = insurance[3];
				String nationalId = insurance[4];
				String gender = insurance[5];
				int yearOfBirth = Integer.parseInt(insurance[6]);
				String illnessType = insurance[7];
				
				Person person = new Person(insuranceNumber, insuranceYear, city, name, nationalId, gender, yearOfBirth, illnessType);
				insuredValues.add(person);
				break;
			}
			
			case "A": {
				double engineVolume = Double.parseDouble(insurance[3]);
				int productionYear = Integer.parseInt(insurance[4]);
				String gearType = insurance[5];
				int fuelTankVolume = Integer.parseInt(insurance[6]);
				String roofType = insurance[7];
				
				Automobile automobile = new Automobile(insuranceNumber, insuranceYear, city, engineVolume, productionYear, gearType, fuelTankVolume, roofType);
				insuredValues.add(automobile);
				break;
			}
			
			case "T":{
				double engineVolume = Double.parseDouble(insurance[3]);
				int productionYear = Integer.parseInt(insurance[4]);
				String gearType = insurance[5];
				int fuelTankVolume = Integer.parseInt(insurance[6]);
				String truckBedType = insurance[7];
				
				Truck truck = new Truck(insuranceNumber, insuranceYear, city, engineVolume, productionYear, gearType, fuelTankVolume, truckBedType);
				insuredValues.add(truck);
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + insuranceType);
			}
			
		}
		//Return the String[][] items array
		return insuredValues;
	}
	
	
}
