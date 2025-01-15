package testCase;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;

public class Order_Confirmation extends BaseTest {
	@Test(priority = 1)
	@Description("To Verify the 'Order Confirmation Message' & 'Order ID' ")
	public void verifyOrderPlacement() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement confirmationMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("order_confirmation_message"))));
		// Extract the order ID text
		System.out.println("Order Confirmation Text: " + confirmationMessage.getText());
		WebElement orderIDPlaceHolder = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("order_id_place_holder"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", orderIDPlaceHolder);

		System.out.println("Scrolled successfully to Order ID container");

		Thread.sleep(1000);
		WebElement orderIDElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("order_id"))));
		System.out.println(orderIDElement.getText());
		Thread.sleep(500);

		orderIDElement.click();
		Thread.sleep(500);
	}

	@Test(priority = 2)
	@Description("To verify the 'Payment Method' & 'Total amount'")
	public void verifyPaymentMethod() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement paymentMethod = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("payment_method"))));

		System.out.println("The payment method used is: " + paymentMethod.getText());
		WebElement TotalAmount = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("total_amount"))));
		System.out.println(TotalAmount.getText());
	}

	@Test(priority = 3)
	@Description("To verify the Delivery Address")
	public void verifyAddress() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement address = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("delivery_address"))));
		System.out.println("The Delivery Address is: " + address.getText());
	}

	@Test(priority = 4)
	public void verifyCouponGenerated() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Store the current window handle (order confirmation Tab)
		String orderConfirmationTab = driver.getWindowHandle();

		try {
			// Click on "View Coupon" button
			WebElement couponGenerated = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("view_coupon_btn"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", couponGenerated);
			couponGenerated.click();
			Thread.sleep(500);

			// Verify and print the coupon code
			WebElement verifyCouponCode = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("verify_coupon_code"))));
			System.out.println("Coupon code is: " + verifyCouponCode.getText());

			// Click "Redeem Now" button
			WebElement verifyRedeemNow = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("redeem_now_btn"))));
			verifyRedeemNow.click();
			System.out.println("Clicked on Redeem Now");

			Thread.sleep(2000);

			// Wait for a new tab to open
			Set<String> allWindows = driver.getWindowHandles();
			if (allWindows.size() > 2) { // Check if a new tab is opened (assuming 3 tabs total)
				for (String window : allWindows) {
					if (!window.equals(orderConfirmationTab)) { // Exclude the Order Confirmation Tab
						driver.switchTo().window(window); // Switch to the new tab (AFter clicking on redeem now)
						String postOrderTitle = driver.getTitle();
						System.out.println("Redeem Now btn redirection page title is: " + postOrderTitle);

						// Close the Redeem Now btn redirection page Tab
						driver.close();

						// Switch back to the Order Confirmation Tab
						driver.switchTo().window(orderConfirmationTab);
						System.out.println("Switched back to the Order Confirmation Tab.");
						break;
					}
				}
			} else {
				System.out.println("No new tab opened after clicking 'Redeem Now'.");
			}

			// Now, you can continue interacting with other buttons on the Cart Tab

		} catch (Exception e) {
			System.out.println("Exception in verifyCouponGenerated: " + e.getMessage());
		}
	}

	@Test(priority = 5)
	public void verifyWthDeals() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String orderConfirmationTab = driver.getWindowHandle();
		try {

			WebElement WtfSection = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("wftdeals"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", WtfSection);
			System.out.println("WTF Section Text: " + WtfSection.getText());
			// Click on a product in WTF Deals section
			WebElement dealsProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("wftdeals_product"))));
			js.executeScript("arguments[0].click();", dealsProduct);
			Thread.sleep(2000);
			// Wait for a new tab to open
			Set<String> allWindows = driver.getWindowHandles();
			if (allWindows.size() > 2) { // Check if a new tab is opened (assuming 3 tabs total)
				for (String window : allWindows) {
					if (!window.equals(orderConfirmationTab)) { // Exclude the Order Confirmation Tab
						driver.switchTo().window(window); // Switch to the new tab (AFter clicking on redeem now)
						String postOrderTitle = driver.getTitle();
						System.out.println("WTF deals first product page title is: " + postOrderTitle);

						// Close the Redeem Now btn redirection page Tab
						driver.close();

						// Switch back to the Order Confirmation Tab
						driver.switchTo().window(orderConfirmationTab);
						System.out.println("Switched back to the Order Confirmation Tab.");
						break;
					}
				}
			} else {
				System.out.println("No new tab opened after clicking 'Redeem Now'.");
			}

		} catch (Exception e) {
			System.out.println("Exception in verifyWTFDeals: " + e.getMessage());
		}

	}
	@Test(priority = 6)
	@Description("To verify the navigation track item button functionality")
	public void verifyTrackItem() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		

		// Wait until the "Track Item" button is visible and click it
		WebElement trackItemBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("track_item_btn"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trackItemBtn);
		js.executeScript("arguments[0].click();", trackItemBtn);
		
		Thread.sleep(1000);

		// Wait for the new page to load and verify that the title contains "myorders"
		wait.until(ExpectedConditions.titleContains("Account"));

		// Capture and print the page title
		String pageTitle = driver.getTitle();
		System.out.println("Navigated Page Title: " + pageTitle);
		
		WebElement pepLogo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", pepLogo);
		pepLogo.click();
		System.out.println("Navigated to Homepage");
		
	}

}