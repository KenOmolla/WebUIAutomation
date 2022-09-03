package pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopularPage {
	
	WebDriver driver;
	String label;
	String price;
	double convertedPrice;
	HashMap<String, Double> productList = new HashMap<>();
	
	@FindBy(className = "homefeatured")
    WebElement popular;
    
	@FindAll
	({
	    @FindBy(xpath = "//ul[@id='homefeatured']//div[@class='right-block']")
	})
	List<WebElement> products;
	
	public PopularPage (WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }
	
	
	public void sortProducts(){

		int i = 0;
		while (i < products.size()) {
			label = ((List<WebElement>) driver.findElements(
					By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//a[@class='product-name']"))).get(i)
					.getText();

			price = ((List<WebElement>) driver.findElements(By.xpath(
					"//ul[@id='homefeatured']//div[@class='right-block']//parent::h5//following-sibling::div//span[@itemprop='price']")))
					.get(i).getText().substring(1);

			System.out.println(label);
			System.out.println(price);

			convertedPrice = Double.parseDouble(price);

			// modifies the name of apparel if one already exists in the hashmap
			if (productList.containsKey(label)) {
				label += " 2";
			}

			productList.put(label, convertedPrice);

			i++;

		}

		// Sorts and prints products based on price
		Map<String, Double> sortedProductList = productList.entrySet().stream().sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (Map.Entry entry : sortedProductList.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
    }
	
	
}
