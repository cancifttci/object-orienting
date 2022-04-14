package exceptions;

/*
 *
 * CUSTOM EXCEPTION IMPLEMENTATION FOR CARGO CODES LENGTH CHECK
 * 
 */

@SuppressWarnings("serial")
public class CargoCodeLengthMismatchException extends Exception {

	public CargoCodeLengthMismatchException() {

		super("Cargo Code Length Mismatch");

	}
	public CargoCodeLengthMismatchException(String message) {

		super(message);

	}

}
