package exceptions;

/*
 *
 * CUSTOM EXCEPTION IMPLEMENTATION FOR INDIVIDUAL CUSTOMER ID LENGTH CHECK
 * 
 */

@SuppressWarnings("serial")
public class IDNotCorrectException extends Exception {

	public IDNotCorrectException() {

		super("Number of digits not correct!");

	}

	public IDNotCorrectException(String message){

		super(message);

	}




}
