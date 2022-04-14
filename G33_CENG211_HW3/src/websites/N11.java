package websites;

public class N11<T> extends CommercialWebsite<T>{

	//Final parameters
	private final int limit = 6;
	private final String websiteName = "N11";
	public N11() {

		super();

	}

	//checks the cargo code is integer and 7-digit 
	@Override
	public boolean checkCodeFormat(T cargoCode) {
		try {
			String code=(String)cargoCode;
			int codeLength = code.length();
			if (codeLength==7) {
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
		return new N11<>();
	}

	@Override
	public String toString() {
		return websiteName;
	}

}
