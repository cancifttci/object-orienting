package exceptions;

/*
 *
 * CUSTOM EXCEPTION IMPLEMENTATION FOR INTEGER CARGO CODES
 * 
 */

@SuppressWarnings("serial")
public class CargoCodeFormatMismatchException extends Exception {

	public CargoCodeFormatMismatchException() {

		super("Cargo code format mismatch!");

	}

	public CargoCodeFormatMismatchException(String message){

		super(message);

	}
}
