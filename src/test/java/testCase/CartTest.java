package testCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Description;

public class CartTest extends BaseTest {

	@Test
	@Description("To verify the Qty in Cart & the functionality of clicking on 'Proceed To Pay' CTA")
	public void qtyDropDown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement quantityDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cart_qty_DD"))));
		quantityDropdown.click();

		WebElement quantityOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("qty_options"))));
		quantityOption.click(); // Set quantity to 1

		String selectedQuantityText = quantityDropdown.getText();
		System.out.println("Quantity in cart is: " + selectedQuantityText);
		
		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();

		System.out.println("Clicked on Proceed to Pay successfully.");

	}
}
