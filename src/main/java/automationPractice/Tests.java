package automationPractice;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Tests extends BaseClass {

	@Test(description = "This TC will perform invalid login", priority =1)
	public void authenticationFailure() throws Throwable {
		driver.findElement(By.className("login")).click();

		WebElement email = driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys("testautomationmfs@gmail.com");

		WebElement password = driver.findElement(By.id("passwd"));
		password.clear();
		password.sendKeys("testautomation@123");

		driver.findElement(By.id("SubmitLogin")).click();

		Thread.sleep(3000);
	}

	@Test(description = "This TC will perform successful login", priority =2)
	public void successfulLogin() throws InterruptedException {
		
		driver.findElement(By.className("login")).click();

		WebElement email = driver.findElement(By.id("email"));
		email.clear();
		email.sendKeys("testautomationmfs@gmail.com");

		WebElement password = driver.findElement(By.id("passwd"));
		password.clear();
		password.sendKeys("TestAutomation@123");

		driver.findElement(By.id("SubmitLogin")).click();

		Thread.sleep(3000);
		
		Reporter.log("=====Logged in Successfully with correct Credentials=====", true);

	}

	// Sorting Popular items
	@Test(description = "This test sorts popular items", priority =3)
	public void sortPopularItems() throws InterruptedException {
		
		Reporter.log("=====Accessing and Sorting out popular items=====", true);
		
		driver.findElement(By.xpath("//a[@title='Home']")).click();
		driver.findElement(By.className("homefeatured")).click();

		List<WebElement> products = driver
				.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']"));

		String label;
		String price;
		double convertedPrice;
		HashMap<String, Double> productList = new HashMap<>();
		//Multimap<String, Double> productList = ArrayListMultimap.create();

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
			
			//modifies the name of apparel if one already exists in the hashmap
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

		Thread.sleep(3000);

	}

	// Adding items to Cart

	@Test(description = "This test add the items to cart", priority =4)
	public void addingItemsToCart() throws InterruptedException {
		Reporter.log("=====Began looking for dress to add to Cart=====", true);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions mouseHover = new Actions(driver);

		WebElement Women = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Women']")));
		mouseHover.moveToElement(Women).perform();
		WebElement eveningDresses = driver.findElement(By.xpath("//a[@title='Evening Dresses']"));
		eveningDresses.click();

		driver.findElement(By.id("layered_id_attribute_group_2")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("layered_id_attribute_group_24")).click();
		Thread.sleep(3000);

		WebElement slider = driver.findElement(By.xpath("//div[@id='layered_price_slider']/a[2]"));

		double currentPosition = 53.00;
		double desiredPosition = 52.28;
		double difference = currentPosition - desiredPosition;
		double eachMove = 0.03;
		int requiredMoves = (int) Math.round(difference / eachMove);

		mouseHover.moveToElement(slider, Math.negateExact(requiredMoves), 0).click();
		mouseHover.build().perform();

		WebElement foundEntry = driver.findElement(By.xpath("//div[@class='product-container']"));
		WebElement more = driver.findElement(By.cssSelector("a.button.lnk_view.btn.btn-default > span"));
		mouseHover.moveToElement(foundEntry).perform();
		more.click();

		driver.findElement(By.id("quantity_wanted")).click();
		driver.findElement(By.id("quantity_wanted")).clear();
		driver.findElement(By.id("quantity_wanted")).sendKeys("3");

		WebElement n = driver.findElement(By.xpath("//select[@id='group_1']"));

		Select sl = new Select(n);
		// option by value
		sl.selectByValue("2");

		driver.findElement(By.id("color_24")).click();

		driver.findElement(By.cssSelector("button[name=\"Submit\"] > span")).click();

		Thread.sleep(30000);

		WebElement confirmPopup = driver.findElement(
				By.xpath("//div[@class='header-container']//div[@class='container']//div[@class='clearfix']"));

		String quantity = confirmPopup.findElement(By.xpath("//span[@id='layer_cart_product_quantity']")).getText();

		String totalProduct = confirmPopup.findElement(By.xpath("//span[@class='ajax_block_products_total']"))
				.getText();
		String shippingCost = confirmPopup.findElement(By.xpath("//span[@class='ajax_cart_shipping_cost']")).getText();
		String totalCost = confirmPopup.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();

		// Print Attributes to Console
		System.out.println("You have " + quantity + " in your cart");
		Assert.assertEquals(quantity, "3");
		System.out.println("Total product cost is " + totalProduct);
		System.out.println("Total Shipping cost is " + shippingCost);
		System.out.println("This will cost you " + totalCost + " when you check out");

		double convertedTotalProduct = Double.parseDouble(totalProduct.substring(1));
		double convertedShippingCost = Double.parseDouble(shippingCost.substring(1));
		double convertedTotalCost = Double.parseDouble(totalCost.substring(1));
		Assert.assertEquals(convertedTotalCost, convertedTotalProduct + convertedShippingCost);

		// Split and print Color and Size
		String[] sizeAndColor = confirmPopup.findElement(By.xpath("//span[@id='layer_cart_product_attributes']"))
				.getText().split("\\s+");

		String color = sizeAndColor[0];
		color = color.substring(0, color.length() - 1);
		Assert.assertEquals(color, "Pink");
		String size = sizeAndColor[1];
		Assert.assertEquals(size, "M");

		System.out.println("Color of purchase is: " + color);
		System.out.println("Size of purchase is: " + size);

	}

}