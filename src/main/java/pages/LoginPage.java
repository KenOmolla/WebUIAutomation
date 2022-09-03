package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

    @FindBy(className="login")
    WebElement signInButton;

    @FindBy(id="email")
    WebElement emailField;    

    @FindBy(id="passwd")
    WebElement passwordField;

    @FindBy(id="SubmitLogin")
    WebElement submitLogin;
    
    @FindBy(xpath = "//div[@id='center_column']//li")
    WebElement authFailureNotifcation;

    public LoginPage (WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }


    public void login(String email, String password){

    	signInButton.click();
    	emailField.clear();
    	emailField.sendKeys(email);
    	passwordField.clear();
    	passwordField.sendKeys(password);
    	submitLogin.click();
    }
    
    public String getAuthErrorText () {
    	return authFailureNotifcation.getText();
    }
}
