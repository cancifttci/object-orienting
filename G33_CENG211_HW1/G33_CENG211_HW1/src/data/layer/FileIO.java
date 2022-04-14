package data.layer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

	public FileIO() {
		// Nothing to do in here because we don't have any arguments.
	}
	
	
	public String[][] getItems(String csvPath){
		//Declarations for Reader
		File file;
		FileReader fileReader;
		BufferedReader bufferedReader;
		
		//Store the csv data at String rawData to avoid the multiple readings.
		String rawData = "";
		
		//Store the line data in counter to initialize the item array.
		int counter = 0;
		String line;
		
		
		try {
			//Assignments for reader
			file = new File(csvPath);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			//Read line and store at rawData String.
			while ((line = bufferedReader.readLine()) != null) {
				counter++;
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
		//Initialize the String[][] items array.
		String[][] items = new String[counter][];
		//Split to lines the rawData
		String lines[] = rawData.split("\n");
		
		int index = 0;
		//Store item data index by index
		for (String line2 : lines) {
			items[index] = line2.split(",");
			index++;
		}
		//Return the String[][] items array
		return items;
	}
	
	public String[][] getTransactions(String csvPath){
		//Declarations for Reader
		File file;
		FileReader fileReader;
		BufferedReader bufferedReader;
		
		//Store the csv data at String rawData to avoid the multiple readings.
		String rawData = "";
		
		//Store the line data in counter to initialize the transaction array.
		int counter = 0;
		String line;
		
		try {
			//Assignments for reader
			file = new File(csvPath);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			//Read line and store at rawData String.
			while ((line = bufferedReader.readLine()) != null) {
				counter++;
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
		
		//Initialize the String[][] transactions array.
		String[][] transactions = new String[counter][];
		String lines[] = rawData.split("\n");
		
		int index = 0;
		//Store transaction data index by index
		for (String line2 : lines) {
			transactions[index] = line2.split(",");
			index++;
		}
		
		//Return the String[][] transactions array
		return transactions;
	}

}
