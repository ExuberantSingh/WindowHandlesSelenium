package tests;

import org.testng.annotations.Test;

import base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	
	
	@Test(priority = 0)
	public void enterLoginDetails() {
		obj.getLoginPage().userLogin();
	}
	
	@Test(priority = 1)
	public void verifyTitleOfUser() {
		obj.getLoginPage().validateUserName();		
	}
	
	@Test(priority = 2)
	public void verifyGetWindowHandles() {
		obj.getLoginPage().getWindowHandle();
	}
	
}
