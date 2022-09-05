package testngTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {

	static WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void setupApplication(String browser) throws Exception {

		Reporter.log("=====Browser Session Started=====", true);
		
		if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\Software\\ChromeDriver JARs\\V104\\chromedriver.exe");
			driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("Edge")){
			System.setProperty("webdriver.edge.driver", "D:\\Software\\EdgeDriver\\V104\\msedgedriver.exe");
			driver = new EdgeDriver();
			}
			else if(browser.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\Software\\FirefoxDriver\\v0.31.0\\geckodriver.exe");
			driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("Operamini")){
			System.setProperty("webdriver.opera.driver", "D:\\Software\\OperaMini\\104.0.5112.81\\operadriver.exe");
			driver = new OperaDriver();
				}
			else{
			//If no browser is passed throw exception
			throw new Exception("Incorrect Browser");
			}

		driver.manage().window().maximize();

		driver.get("http://automationpractice.com/index.php");
		
	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
		Reporter.log("=====Browser Session End=====", true);

	}

}