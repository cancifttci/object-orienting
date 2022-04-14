package interfaces;

import websites.CommercialWebsite;

//ICommercialWebsite implementation for new Ecommerce Websites
public interface ICommercialWebsite<T> {
	
	public CommercialWebsite<T> copyWebsite();
	
	public boolean checkCodeFormat(T cargoCode);
	
	public int getWebsiteLimit();
}
