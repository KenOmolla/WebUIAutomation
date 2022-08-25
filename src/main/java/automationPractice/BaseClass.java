package automationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	WebDriver driver;

	@BeforeClass
	public void setupApplication() {

		Reporter.log("=====Browser Session Started=====", true);

		System.setProperty("webdriver.chrome.driver", "D:\\Software\\ChromeDriver JARs\\V104\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://automationpractice.com/index.php");

		Reporter.log("=====Application Started=====", true);
		
	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
		Reporter.log("=====Browser Session End=====", true);

	}

}