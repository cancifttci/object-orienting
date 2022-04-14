package websites;

public class Hepsiburada<T> extends CommercialWebsite<T>{

	//Final parameters
	private final int limit = 7;
	private final String websiteName = "Hepsiburada";
	public Hepsiburada() {

		super();
	}


	//checks the cargo code is integer and 7-digit 
	@Override
	public boolean checkCodeFormat(T cargoCode) {
		try {
			String code=(String)cargoCode;
			int codeLength = code.length();
			if (codeLength==8) {
				return true;

			}else {
				return false;
			}
		} catch (Exception e) {
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
		return new Hepsiburada<>();
	}

	@Override
	public String toString() {
		return websiteName;
	}

}
