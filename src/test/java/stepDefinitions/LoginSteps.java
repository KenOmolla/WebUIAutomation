package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {
	WebDriver driver;
	LoginPage objectLogin;
	HomePage objectHome;
	
	@Given("^User is on LoginPage$")
	public void user_is_on_LoginPage() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.edge.driver", "D:\\Software\\EdgeDriver\\V104\\msedgedriver.exe");

		driver = new EdgeDriver();
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php");
	
	}

	@When("^User enters UserName and wrong Password$")
	public void user_enters_UserName_and_wrong_Password() throws Throwable {
		
		objectLogin = new LoginPage(driver);
		objectLogin.login("testautomationmfs@gmail.com", "ThisIsWrongPassword" );
		
	}

	@Then("^Message should display Authentication failed$")
	public void message_should_display_Authentication_failed() throws Throwable {
		
		objectLogin = new LoginPage(driver);
		assertEquals(objectLogin.getAuthErrorText(), "Authentication failed.");
	
	}

	@When("^User enters UserName and correct Password$")
	public void user_enters_UserName_and_correct_Password() throws Throwable {
	    
		objectLogin = new LoginPage(driver);
		objectLogin.login("testautomationmfs@gmail.com", "TestAutomation@123" );
	}

	@Then("^User should be redirected to the landing page$")
	public void user_should_be_redirected_to_the_landing_page() throws Throwable {
	    
		objectHome = new HomePage(driver);
		assertEquals(true, objectHome.getSignOut().isDisplayed());

	}
}
