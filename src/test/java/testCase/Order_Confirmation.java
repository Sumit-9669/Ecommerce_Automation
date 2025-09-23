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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// ✅ Close popup if present
		handlePopupIfPresent();

		WebElement confirmationMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("order_confirmation_message"))));
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
		handlePopupIfPresent(); // ✅ safety net

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
		handlePopupIfPresent(); // ✅ safety net

		WebElement address = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("delivery_address"))));
		System.out.println("The Delivery Address is: " + address.getText());
	}

	@Test(priority = 4)
	public void verifyCouponGenerated() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		handlePopupIfPresent(); // ✅ safety net

		String orderConfirmationTab = driver.getWindowHandle();
		try {
			WebElement couponGenerated = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("view_coupon_btn"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", couponGenerated);
			couponGenerated.click();
			Thread.sleep(500);

			WebElement verifyCouponCode = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("verify_coupon_code"))));
			System.out.println("Coupon code is: " + verifyCouponCode.getText());

			WebElement verifyRedeemNow = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("redeem_now_btn"))));
			verifyRedeemNow.click();
			System.out.println("Clicked on Redeem Now");

			Thread.sleep(2000);

			Set<String> allWindows = driver.getWindowHandles();
			if (allWindows.size() > 2) {
				for (String window : allWindows) {
					if (!window.equals(orderConfirmationTab)) {
						driver.switchTo().window(window);
						String postOrderTitle = driver.getTitle();
						System.out.println("Redeem Now redirection page title: " + postOrderTitle);
						driver.close();
						driver.switchTo().window(orderConfirmationTab);
						System.out.println("Switched back to Order Confirmation Tab.");
						break;
					}
				}
			} else {
				System.out.println("No new tab opened after clicking 'Redeem Now'.");
			}
		} catch (Exception e) {
			System.out.println("Exception in verifyCouponGenerated: " + e.getMessage());
		}
	}

	@Test(priority = 5)
	public void verifyWthDeals() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		handlePopupIfPresent(); // ✅ safety net

		String orderConfirmationTab = driver.getWindowHandle();
		try {
			WebElement WtfSection = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("wftdeals"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", WtfSection);
			System.out.println("WTF Section Text: " + WtfSection.getText());

			WebElement dealsProduct = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("wftdeals_product"))));
			js.executeScript("arguments[0].click();", dealsProduct);
			Thread.sleep(2000);

			Set<String> allWindows = driver.getWindowHandles();
			if (allWindows.size() > 2) {
				for (String window : allWindows) {
					if (!window.equals(orderConfirmationTab)) {
						driver.switchTo().window(window);
						String postOrderTitle = driver.getTitle();
						System.out.println("WTF deals product page title: " + postOrderTitle);
						driver.close();
						driver.switchTo().window(orderConfirmationTab);
						System.out.println("Switched back to Order Confirmation Tab.");
						break;
					}
				}
			} else {
				System.out.println("No new tab opened after clicking WTF Deals product.");
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

		handlePopupIfPresent(); // ✅ safety net

		WebElement trackItemBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("track_item_btn"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trackItemBtn);
		js.executeScript("arguments[0].click();", trackItemBtn);

		Thread.sleep(1000);

		wait.until(ExpectedConditions.titleContains("Account"));
		String pageTitle = driver.getTitle();
		System.out.println("Navigated Page Title: " + pageTitle);

		WebElement pepLogo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", pepLogo);
		pepLogo.click();
		System.out.println("Navigated to Homepage");
	}

	// 🔽 Utility Method for Popup Handling
	private void handlePopupIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// ✅ Check if iframe exists before switching
			if (driver.findElements(By.xpath(loc.getProperty("order_confirmation_popup"))).size() > 0) {
				wait.until(ExpectedConditions
						.frameToBeAvailableAndSwitchToIt(By.xpath(loc.getProperty("order_confirmation_popup"))));

				WebElement closeButton = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(loc.getProperty("order_confirmation_popup_close_button"))));
				closeButton.click();

				driver.switchTo().defaultContent();
				System.out.println("✅ Popup closed successfully.");
			} else {
				System.out.println("ℹ️ Popup iframe not found in DOM.");
			}
		} catch (Exception e) {
			driver.switchTo().defaultContent(); // fallback
			System.out.println("⚠️ Exception while closing popup: " + e.getMessage());
		}
	}
}
