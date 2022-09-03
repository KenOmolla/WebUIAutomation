package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ConfirmPopupPage;
import pages.EveningDresses;
import pages.HomePage;
import pages.LoginPage;
import pages.PopularPage;

public class Tests {
	WebDriver driver;
	LoginPage objectLogin;
	HomePage objectHome;
	PopularPage objectPopular;
	EveningDresses objectDresses;
	ConfirmPopupPage objectPopup;
	
	@BeforeClass
	public void setup() {

		System.setProperty("webdriver.edge.driver", "D:\\Software\\EdgeDriver\\V104\\msedgedriver.exe");

		driver = new EdgeDriver();
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php");

	}

	@Test(description = "This TC will perform login with invalid password", priority = 1)
	public void authenticationFailure() throws Throwable {

		objectLogin = new LoginPage(driver);
		objectLogin.login("testautomationmfs@gmail.com", "ThisIsWrongPassword" );
		Assert.assertEquals(objectLogin.getAuthErrorText(), "Authentication failed.");
		Thread.sleep(3000);
	}
	
	@Test(description = "This TC will perform login with valid credentials", priority = 2)
	public void successfulLogin() throws Throwable {

		objectLogin = new LoginPage(driver);
		objectLogin.login("testautomationmfs@gmail.com", "TestAutomation@123" );
		
		objectHome = new HomePage(driver);
		Assert.assertEquals(true, objectHome.getSignOut().isDisplayed());
		Thread.sleep(3000);
	}
	
	@Test(description = "This test sorts popular items", priority = 3)
	public void sortPopularItems() throws InterruptedException {
		objectHome = new HomePage(driver);
		objectHome.goToPopular();
		
		objectPopular = new PopularPage(driver);
		objectPopular.sortProducts();
		Thread.sleep(3000);
	}
	
	@Test(description = "This TC will set the attributes for the desired dress", priority = 4)
	public void setDressAttributes() throws Throwable {
		objectHome = new HomePage(driver);
		objectHome.goToEveningDresses();
		
		objectDresses = new EveningDresses(driver);
		objectDresses.chooseAttribute();
		objectDresses.currentSliderPosition();
		objectDresses.moveSlider1();
		objectDresses.moveSlider2();
		Assert.assertEquals(objectDresses.priceRange.getText(), "$50.00 - $52.28");
		Thread.sleep(3000);
		
	}
	
	@Test(description = "This TC will add selected dress to cart", dependsOnMethods = { "setDressAttributes" })
	public void addItemToCart() throws Throwable {
		objectDresses = new EveningDresses(driver);
		objectDresses.addToCart();
		
	}
	
	@Test(description = "This TC check that the item details on the popup are accurate", dependsOnMethods = { "addItemToCart" })
	public void confirmPurchaseDetails() throws Throwable {
		objectPopup = new ConfirmPopupPage(driver);
		objectPopup.purchaseDetails();
		objectPopup.sizeAndColor();
		
	}
	

	
	
}
