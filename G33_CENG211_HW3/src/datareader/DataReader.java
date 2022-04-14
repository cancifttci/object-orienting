package datareader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import cargotypes.*;
import exceptions.CargoCodeFormatMismatchException;
import interfaces.IDataReader;

import websites.*;

public class DataReader implements IDataReader{

	private String filePath;

	public DataReader(String filePath) {

		this.filePath=filePath;

	}

	@Override
	public ArrayList<Cargo> readCargoData() throws CargoCodeFormatMismatchException {
		//Declaration for reader	
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

			while ((line=bufferedReader.readLine()) != null) {

				rawData += line + "\n";

			}
			//Finish reading process and close the readers.

			fileReader.close();
			bufferedReader.close();

			//Handle exceptions
		} catch (FileNotFoundException f) {
			System.out.println(f);
		} catch(IOException i){
			System.out.println(i);
		}
		//Initialize the String[][] Cargo array.
		ArrayList<Cargo> cargos = new ArrayList<>();
		//Split to lines the rawData
		String lines[] = rawData.trim().split("\n");

		//Store item data index by index

		for (String temp : lines) {

			String cargoRow[] = temp.split(";");
			String cargoType = cargoRow[0];

			switch (cargoType) {
			case "Normal": {
				String senderID = cargoRow[1];
				String senderName= cargoRow[2];
				String recipientName = cargoRow[3];
				String recipientAddress = cargoRow[4];
				int weight = Integer.parseInt(cargoRow[5]);
				int width = Integer.parseInt(cargoRow[6]);
				int length = Integer.parseInt(cargoRow[7]);
				int height = Integer.parseInt(cargoRow[8]);

				NormalCargo cargo = new NormalCargo(height, weight, width, length, senderID, senderName, recipientName, recipientAddress); 
				cargos.add(cargo);
				break;

			}
			case "Ecommerce": {

				String eCommerceSiteName = cargoRow[1];
				String cargoCode = cargoRow[2];
				int weight = Integer.parseInt(cargoRow[3]);
				int width = Integer.parseInt(cargoRow[4]);
				int length = Integer.parseInt(cargoRow[5]);
				int height = Integer.parseInt(cargoRow[6]);

				switch (eCommerceSiteName) {
				case "Amazon": {
					int tempCargoCode;

					try {
						tempCargoCode = Integer.parseInt(cargoCode);
					} catch (Exception e) {
						throw new CargoCodeFormatMismatchException();
					}

					Amazon<Integer> website = new Amazon<Integer>();
					EcommerceCargo<Integer> cargo = new EcommerceCargo<Integer>(height, weight, width, length, tempCargoCode, website);
					cargos.add(cargo);

					break;
				}case "N11" : {
					N11<String> website = new N11<String>();
					EcommerceCargo<String> cargo = new EcommerceCargo<String>(height, weight, width, length, cargoCode, website);
					cargos.add(cargo);
					break;
				}case "Hepsiburada":{
					Hepsiburada<String> website = new Hepsiburada<String>();
					EcommerceCargo<String> cargo = new EcommerceCargo<String>(height, weight, width, length, cargoCode, website);
					cargos.add(cargo);
					break;
				}case "Trendyol":{
					int tempCargoCode;

					try {
						tempCargoCode = Integer.parseInt(cargoCode);
					} catch (Exception e) {
						throw new CargoCodeFormatMismatchException();
					}

					Trendyol<Integer> website = new Trendyol<Integer>();
					EcommerceCargo<Integer> cargo = new EcommerceCargo<Integer>(height, weight, width, length, tempCargoCode, website);

					cargos.add(cargo);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + eCommerceSiteName);
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + cargoType);
			}
		}


		//Return the String[][] items array
		return cargos;
	}

}
