package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagefactory.ObjectFactory;
import utils.TestUtils;

public class BaseTest {
	
	public static Properties prop ;
	public static WebDriver driver ;
	protected static ObjectFactory obj ;


		public BaseTest()  {
			try{
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//config//Config.properties");
				prop.load(ip);
				}catch(FileNotFoundException e){
					e.printStackTrace();
	   				}catch (IOException e) {
	   				e.printStackTrace();
	   			}

				}
		

		@BeforeClass
		public void intialization( )  {

			obj = new ObjectFactory();
			String browsername = prop.getProperty("browser");
			


	           if(browsername.equals("chrome")){
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver()  ;  	
	                   
	        }else if(browsername.equals("FireFox")){
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver(); 	
		
	        }

	
	           driver.manage().window().maximize();
	           driver.manage().deleteAllCookies();
	           driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.Page_Load_Time));
	           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.Implicit_Wait_Time));
	           driver.get(prop.getProperty("url"));


		
		}
}
