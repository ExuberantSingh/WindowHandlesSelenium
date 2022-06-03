package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import advantageDemo_interface.LoginPage_Interface;
import base.BaseTest;

public class LoginPage extends BaseTest implements LoginPage_Interface {
	
	
	
	@FindBy(xpath="//a[@id='hrefUserIcon']")
	WebElement loginLink ;
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement username ;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password ;
	
	@FindBy(id="sign_in_btnundefined")
	WebElement signInBtn ;
	
	@FindBy(xpath = "//input[@name='remember_me']")
	WebElement rememberMe ;
	
	@FindBy(xpath = "//a[@id='menuUserLink']//span[text()='Sahib123']")
	WebElement userTitle ;
	
	@FindBy(xpath="//img[@name='follow_facebook']")
	WebElement facebookLink;
	
	@FindBy(xpath="//img[@name='follow_twitter']")
	WebElement twitterLink ;
	
	@FindBy(xpath="//img[@name='follow_linkedin']")
	WebElement linkedlnLink ;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void userLogin() {		

		username.sendKeys(prop.getProperty("emailID"));
		password.sendKeys(prop.getProperty("password"));		
		rememberMe.click();
		signInBtn.click();
		
		}

	public void validateUserName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String userTittle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menuUserLink']//span[text()='Sahib123']"))).getText();
//		String userTittle = userTitle.getText();
		System.out.println("The Text of Username is : "+ userTittle);
		Assert.assertEquals("Sahib123", userTittle);
	}
	
	public void getWindowHandle() {
		
		
		//ParentToFacebook-- First Child Window
		String parentLink = driver.getWindowHandle();
		facebookLink.click();
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("The value of open tabs "+allWindows);
		
		int count = allWindows.size();
		System.out.println("Total Window Count For First Time : "+count);
		
		for(String child : allWindows) {
			if(!parentLink.equalsIgnoreCase(child)) {
				
				driver.switchTo().window(child);
				driver.findElement(By.xpath("(//span[contains(text(),'Log In')])[2]")).click();
									
			}
		}
		driver.switchTo().window(parentLink);
		
		
			//ParentToLinkedln -- Second Child Window
			linkedlnLink.click();
			Set<String> allWindow = driver.getWindowHandles();
	
			int counts = allWindow.size();
			System.out.println("Total Window Count For Second Time : "+counts);
		
			for(String child : allWindow) {
				if(!parentLink.equalsIgnoreCase(child)) {
					driver.switchTo().window(child);
				}
			}				
			driver.switchTo().window(parentLink);		
		
			
		//ParentToTwitter -- Second Child Window
		twitterLink.click();
		Set<String> allWindowss = driver.getWindowHandles();
	
		int countss = allWindowss.size();
		Iterator<String> it = allWindowss.iterator();
		System.out.println("Total Window Count For Third Time : "+countss);
		
		for(String child : allWindowss) {
			if(!parentLink.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
			}
		}
		driver.switchTo().window(parentLink);
		
		
		String parent = it.next();
		String newwin = it.next();
		String newone = it.next();
		String newtwo = it.next();
		
		//SwitchTo -- Second Child Window
		driver.switchTo().window(newone);		
		driver.findElement(By.xpath("//div[@class='login__form_action_container ']")).click(); 
		
	
		
		
		//SwitchTo -- Third Child Window
		driver.switchTo().window(newtwo);
	
		List<WebElement> linktext = driver.findElements(By.tagName("a"));
		System.out.println("Number of Links in Page : "+linktext.size());
        for (int i = 0; i < linktext.size(); i++) {
            String linktxt = linktext.get(i).getText();
	            String linkurl = linktext.get(i).getAttribute("href");
          System.out.println(linktxt + "    " + linkurl);

	        }
        
        driver.quit();
	}

}
