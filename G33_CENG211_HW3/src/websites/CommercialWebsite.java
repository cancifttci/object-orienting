package websites;
import application.Status;
import interfaces.ICommercialWebsite;

public abstract class CommercialWebsite<T> implements ICommercialWebsite<T> {

	//this class is an abstract class. Because every website has check code format method but their type are different, also different limit. And theese classes
	//must implement these abstract methods to its definitions.
	private Status status;

	//constructor method
	public CommercialWebsite(){
		this.status = Status.ACCEPTED;
	}

	// abstract method for checking code format
	public abstract boolean checkCodeFormat(T cargoCode);

	//abstract getter method for website limit
	public abstract int getWebsiteLimit();

	//getters and setters for status
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}