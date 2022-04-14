package websites;

public class Trendyol<T> extends CommercialWebsite<T>{

	//Final parameters
	private final int limit = 9;
	private final String websiteName = "Trendyol";

	public Trendyol() {

		super();
	}

	//checks the cargo code is integer and 7-digit 
	@Override
	public boolean checkCodeFormat(T cargoCode) {
		try {
			int code=(Integer)cargoCode;
			int codeLength = String.valueOf(code).length();
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
		return new Trendyol<>();
	}

	@Override
	public String toString() {
		return websiteName;
	}
}
