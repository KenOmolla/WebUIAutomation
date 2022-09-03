package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ConfirmPopupPage {

	WebDriver driver;
	String sQuantity;
	String sTotalProduct;
	String sShippingCost;
	String sTotalCost;
	String sColor;
	String sSize;
	
    @FindBy(xpath = "//div[@class='header-container']//div[@class='container']//div[@class='clearfix']//span[@id='layer_cart_product_quantity']")
    WebElement quantity;
    
    @FindBy(xpath = "//div[@class='header-container']//div[@class='container']//div[@class='clearfix']//span[@class='ajax_block_products_total']")
    WebElement totalProduct;
    
    @FindBy(xpath = "//div[@class='header-container']//div[@class='container']//div[@class='clearfix']//span[@class='ajax_cart_shipping_cost']")
    WebElement shippingCost;
    
    @FindBy(xpath = "//div[@class='header-container']//div[@class='container']//div[@class='clearfix']//span[@class='ajax_block_cart_total']")
    WebElement totalCost;
    
    @FindBy(xpath = "//div[@class='header-container']//div[@class='container']//div[@class='clearfix']//span[@id='layer_cart_product_attributes']")
    WebElement sizeAndColor;
    

    public ConfirmPopupPage (WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }
    
    public void purchaseDetails(){
    	
    	sQuantity = quantity.getText();
    	sTotalCost = totalProduct.getText();
    	sShippingCost = shippingCost.getText();
    	sTotalCost = totalCost.getText();

    	System.out.println("You have " + sQuantity + " in your cart");
		Assert.assertEquals(sQuantity, "3");
		System.out.println("Total product cost is " + sTotalProduct);
		System.out.println("Total Shipping cost is " + sShippingCost);
		System.out.println("This will cost you " + sTotalCost + " when you check out");

		double convertedTotalProduct = Double.parseDouble(sTotalProduct.substring(1));
		double convertedShippingCost = Double.parseDouble(sShippingCost.substring(1));
		double convertedTotalCost = Double.parseDouble(sTotalCost.substring(1));
		Assert.assertEquals(convertedTotalCost, convertedTotalProduct + convertedShippingCost);
    	
    	
    }
    
public void sizeAndColor(){
    	
    	String [] arraySizeAndColor = sizeAndColor.getText().split("\\s+");
    	
    	String color = arraySizeAndColor[0];
		color = color.substring(0, color.length() - 1);
		Assert.assertEquals(color, "Pink");
		String size = arraySizeAndColor[1];
		Assert.assertEquals(size, "M");

		System.out.println("Color of purchase is: " + color);
		System.out.println("Size of purchase is: " + size);
    	
    }
    
}
