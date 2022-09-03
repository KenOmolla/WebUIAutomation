package pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EveningDresses {

	WebDriver driver;
	static double currentSlider1Postion;
	static double currentSlider2Postion;
	static double targetPosition1 = 50.00;
	static double targetPosition2 = 52.28;
	static double eachMove = 0.03;
	static int requiredMoves;
	WebDriverWait wait;
    
    @FindBy(id = "layered_id_attribute_group_2")
    WebElement mediumSize;
    
    @FindBy(id = "layered_id_attribute_group_24")
    WebElement colorPink;
    
    @FindBy(id = "layered_price_range")
    public WebElement priceRange;
    
    @FindBy(xpath = "//div[@id='layered_price_slider']/a[1]")
    WebElement slider1;
    
    @FindBy(xpath = "//div[@id='layered_price_slider']/a[2]")
    WebElement slider2;
    
    @FindBy(xpath = "//div[@class='product-container']")
    WebElement availableDress;
    
    @FindBy(css = "a.button.lnk_view.btn.btn-default > span")
    WebElement moreButton;
    
    @FindBy(id = "quantity_wanted")
    WebElement wantedQuantity;
    
    @FindBy(xpath = "//select[@id='group_1']")
    WebElement wantedSize;
    
    @FindBy(id = "color_24")
    WebElement wantedColor;
    
    @FindBy(css = "button[name=\"Submit\"] > span")
    WebElement addToCartButton;

    public EveningDresses (WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    
    public void chooseAttribute () {
    	mediumSize.click();
    	colorPink.click();
    }
    
    public void currentSliderPosition() {
		String range = priceRange.getText();

		String[] currentRange = range.split("\\s+");
		currentSlider1Postion = Double.parseDouble(currentRange[0].substring(1));
		currentSlider2Postion = Double.parseDouble(currentRange[2].substring(1));
	}

	public void moveSlider1() {

		requiredMoves = (int) Math.round((targetPosition1 - currentSlider1Postion) / eachMove);

		if (requiredMoves > 0) {
			for (int i = 1; i <= requiredMoves; i++) {
				slider1.sendKeys(Keys.ARROW_RIGHT);
			}
		}

	}

	public void moveSlider2() {
		
		requiredMoves = (int) Math.round((currentSlider2Postion - targetPosition2) / eachMove);
		System.out.println(requiredMoves);

		if (requiredMoves > 0) {
			for (int i = 1; i <= requiredMoves; i++) {
				slider2.sendKeys(Keys.ARROW_LEFT);
			}
		}
		
	}
	
	//Method hovers over item and selects more, the sets attributes before submitting
	public void addToCart(){

    	Actions mouseHover = new Actions(driver);
    	//wait.until(ExpectedConditions.visibilityOf(availableDress));
    	mouseHover.moveToElement(availableDress).perform();
    	moreButton.click();
    	wantedQuantity.click();
    	wantedQuantity.clear();
    	wantedQuantity.sendKeys("3");
    	
    	Select sl = new Select(wantedSize);
		sl.selectByValue("2");
		
		wantedColor.click();
		addToCartButton.click();
    	
    	
    }
    
}
