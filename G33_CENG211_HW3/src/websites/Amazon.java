package websites;


public class Amazon<T> extends CommercialWebsite<T>{

	//Final parameters
	private final int limit = 5;
	private final String websiteName = "Amazon";

	public Amazon() {
		super();
	}

	//checks the cargo code is integer and 7-digit 
	@Override
	public boolean checkCodeFormat(T cargoCode) {	
		int code=(Integer)cargoCode;
		int codeLength = String.valueOf(code).length();
		if (codeLength==7) {
			return true;

		}else {
			return false;
		}
	}

	//Getters and other methods
	@Override
	public int getWebsiteLimit() {
		return this.limit;
	}

	@Override
	public CommercialWebsite<T> copyWebsite() {
		return new Amazon<>();
	}

	@Override
	public String toString() {
		return websiteName;
	}

}
