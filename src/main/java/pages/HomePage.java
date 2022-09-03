package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

    @FindBy(xpath = "//div[@class='header_user_info']//a[@class='logout']")
    WebElement signOut;

    @FindBy(xpath = "//a[@title='Home']")
    WebElement home;    

    @FindBy(xpath = "//a[@title='Women']")
    WebElement women;

    @FindBy(xpath = "//a[@title='Evening Dresses']")
    WebElement eveningDresses;
    
    @FindBy(id = "layered_id_attribute_group_2")
    WebElement mediumSize;
    
    @FindBy(id = "layered_id_attribute_group_24")
    WebElement colorPink;

    public HomePage (WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }
    
    public WebElement getSignOut(){

    	return signOut;
    }
    
    public void goToEveningDresses(){

    	Actions mouseHover = new Actions(driver);
    	mouseHover.moveToElement(women).perform();
    	eveningDresses.click();
    }
    
    public void goToPopular(){

    	home.click();
    }
}    
