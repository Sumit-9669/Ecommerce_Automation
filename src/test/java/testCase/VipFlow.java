package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest; // Import BaseTest class to access driver
import io.qameta.allure.Description;

import java.time.Duration;

public class VipFlow extends BaseTest {

	// Method to click on 'Add to Cart' and then 'Go to Cart'
	@Test
	@Description("To verify the 'Add to Cart' & 'Go To Cart' functionality")
	public void addToCartAndGoToCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait for 'Add to Cart' button and click on it
		WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("add_to_cart"))));
		addToCart.click();
		Thread.sleep(1000);

		WebElement goToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("go_to_cart"))));
		goToCart.click();
		System.out.println("Product Added to Cart Successfully");
		Thread.sleep(100);
	}
}
