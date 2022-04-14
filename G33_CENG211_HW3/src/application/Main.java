package application;

import java.util.ArrayList;
import cargotypes.Cargo;
import datareader.DataReader;

public class Main {

	public static void main(String[] args) {
		
		//Initialize the data reader
		DataReader dataReader = new DataReader("HW3_PackagesToAccept.csv");
		//Initialize the empty cargo list
		ArrayList<Cargo> cargos = null;
		//Handle the exception because readCargoData throws CargoCodeFormatMismatchException exception.
		try {
			//Get the cargo data.
			cargos = dataReader.readCargoData();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//Initialize a cargo agency.
		Agency agency = new Agency();
		//Process the given cargo packages.
		agency.startAcceptanceProcess(cargos);
		//Print a summary about the given cargo packages.
		agency.printAgencySummary();
		
		
		
	}
}
