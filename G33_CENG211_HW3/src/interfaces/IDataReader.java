package interfaces;

import java.util.ArrayList;

import cargotypes.Cargo;
import exceptions.CargoCodeFormatMismatchException;

//IDataReader implementation for new cargo data reading methods
public interface IDataReader {
	
	public ArrayList<Cargo> readCargoData() throws CargoCodeFormatMismatchException;

}
