package pagefactory;

import pages.LoginPage;

public class ObjectFactory {
	
	private LoginPage loginPage ;
	
	
	public LoginPage getLoginPage() {
		
		if (loginPage==null) {
			loginPage = new LoginPage();
		}
		return loginPage ;
	}




	
}
