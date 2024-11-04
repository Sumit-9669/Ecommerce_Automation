package testCase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Description;

public class OrderConfirmationPage extends BaseTest {

	@Test
	@Description("To verify the To Verify the 'Order Confirmation Message' & 'Order ID' ")
	public void verifyOrderPlacement() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		WebElement confirmationMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("order_confirmation_message"))));
		// Extract the order ID text
		System.out.println("Order Confirmation Text: " + confirmationMessage.getText());

		WebElement orderIDElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("order_id"))));
		System.out.println("Extracted Order ID: " + orderIDElement.getText());

	}
}
