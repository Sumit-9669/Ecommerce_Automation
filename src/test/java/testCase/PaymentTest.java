package testCase;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;
import utilities.ScreenshotUtil;

public class PaymentTest extends BaseTest {

	@Test(priority = 1)
	@Description("Search Test for SKU Product")
	public void searchTestSku() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
		// Search Bar Interaction
		WebElement searchBar = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_bar"))));
		searchBar.clear();
		searchBar.sendKeys(loc.getProperty("test_sku") + Keys.RETURN);
		// Wait for and click the search result using JS Executor
		WebElement searchResult = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_result"))));
		System.out.println("Searched the test product successfully");
		Thread.sleep(2000);

		try {
			searchResult.click(); // Normal click
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JavaScript click if intercepted
			js.executeScript("arguments[0].click();", searchResult);
		}
		// Switch to the newly opened tab
		String currentTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab); // Switch to the new tab
				break;
			}
		}
		Thread.sleep(2000);
		System.out.println("Switched to new tab: " + driver.getTitle());
	}

// Internet Banking- Axis Bank As payment method
	@Test(priority = 2)
	@Description("Verifying Internet Banking Payment Method = Axis Bank")
	public void axisBankInternetBankingPM() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for 'Add to Cart' button and click on it
		WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		Thread.sleep(1000);

		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		System.out.println(
				"Clicked on Proceed to Pay successfully for verifying Internet Banking Payment method = Axis Bank");

		// Scroll and ensure 'Internet Banking' element is visible and clickable
		WebElement internetBanking = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("internet_banking"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", internetBanking);

		js.executeScript("arguments[0].click();", internetBanking);
		Thread.sleep(1000);
		// Select Axis bank for making payment
		WebElement axisBank = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("axis_bank_IB"))));
		axisBank.click();
		Thread.sleep(1000);

		WebElement payNow_axisbank = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pay_now"))));
		payNow_axisbank.click();
		ScreenshotUtil.takeScreenshot("Successfully verified Axis Internet Banking");
		Thread.sleep(3000);
		System.out.println("Successfully Verified Axis Bank-Internet Banking");
		driver.close();
		Thread.sleep(2000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
		}

	}

// Internet Banking- HDFC Bank As payment method
	@Test(priority = 3)
	@Description("Verifying Internet Banking Payment Method = HDFC Bank")
	public void hdfcBankInternetBankingPM() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement searchResult = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_result"))));
		Thread.sleep(1000);

		try {
			searchResult.click(); // Normal click
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JavaScript click if intercepted
			js.executeScript("arguments[0].click();", searchResult);
		}

		// Switch to the newly opened tab
		String currentTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab); // Switch to the new tab
				break;
			}
		}

		WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		Thread.sleep(1000);

		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		System.out.println(
				"Clicked on Proceed to Pay successfully for verifying Internet Banking Payment method = HDFC Bank");

		// Scroll and ensure 'Internet Banking' element is visible and clickable
		WebElement internetBanking = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("internet_banking"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", internetBanking);

		js.executeScript("arguments[0].click();", internetBanking);
		Thread.sleep(1000);

		// Select HDFC bank for making payment
		WebElement hdfcBank = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("hdfc_bank_IB"))));
		hdfcBank.click();
		Thread.sleep(1000);

		WebElement payNow_hdfcbank = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pay_now"))));
		payNow_hdfcbank.click();
		ScreenshotUtil.takeScreenshot("Successfully verified HDFC Internet Banking");
		System.out.println("Successfully Verified HDFC Bank-Internet Banking");
		Thread.sleep(2000);
		driver.close();
		Thread.sleep(2000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
		}
	}

	@Test(priority = 4)
	@Description("Verifying Wallet = Mobikwik")
	public void mobikwikWallet() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement searchResult = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_result"))));
		Thread.sleep(1000);

		try {
			searchResult.click(); // Normal click
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JavaScript click if intercepted
			js.executeScript("arguments[0].click();", searchResult);
		}

		// Switch to the newly opened tab
		String currentTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab); // Switch to the new tab
				break;
			}
		}

		WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		Thread.sleep(1000);

		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		System.out.println("Clicked on Proceed to Pay successfully for verifying Wallet Payment method = Mobikwik");

		// Scroll and ensure 'Wallet' element is visible and clickable
		WebElement mobikwikWallet = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("wallet"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", mobikwikWallet);

		js.executeScript("arguments[0].click();", mobikwikWallet);
		Thread.sleep(1000);

		// Select Mobikwik Wallet for making payment
		WebElement mobikwik = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("mobikwik"))));
		mobikwik.click();
		Thread.sleep(1000);

		WebElement payNow_Mobikwik = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pay_now"))));
		payNow_Mobikwik.click();
		ScreenshotUtil.takeScreenshot("Successfully verified Mobikwik Wallet");
		System.out.println("Successfully Verified Mobikwik Wallet");
		Thread.sleep(2000);
		driver.close();
		Thread.sleep(2000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
		}
	}

	@Test(priority = 5)
	@Description("Verifying Wallet = Paytm")
	public void paytmWallet() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement searchResult = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_result"))));
		Thread.sleep(1000);

		try {
			searchResult.click(); // Normal click
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JavaScript click if intercepted
			js.executeScript("arguments[0].click();", searchResult);
		}

		// Switch to the newly opened tab
		String currentTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab); // Switch to the new tab
				break;
			}
		}

		WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		Thread.sleep(1000);

		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		System.out.println("Clicked on Proceed to Pay successfully for verifying Wallet Payment method = Paytm");

		// Scroll and ensure 'Wallet' element is visible and clickable
		WebElement paytmWallet = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("wallet"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", paytmWallet);

		js.executeScript("arguments[0].click();", paytmWallet);
		Thread.sleep(1000);

		// Select Paytm Wallet for making payment
		WebElement paytm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("paytm"))));
		paytm.click();
		Thread.sleep(1000);

		WebElement payNow_Paytm = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pay_now"))));
		payNow_Paytm.click();
		ScreenshotUtil.takeScreenshot("Successfully verified Paytm Wallet");
		System.out.println("Successfully Verified Paytm Wallet");
		Thread.sleep(2000);
		driver.close();
		Thread.sleep(2000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			Thread.sleep(1000);
		}
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);

	}

	// Successful order placement
	@Test(priority = 6)
	@Description("Verifying Gift Card Payment Method")
	public void giftCard() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement searchResult = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("search_result"))));
		Thread.sleep(1000);

		try {
			searchResult.click(); // Normal click
		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
			// Use JavaScript click if intercepted
			js.executeScript("arguments[0].click();", searchResult);
		}

		// Switch to the newly opened tab
		String currentTab = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab); // Switch to the new tab
				break;
			}
		}
		
		WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		Thread.sleep(1000);

		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		Thread.sleep(1000);

		// Scroll and ensure 'Gift Card' element is visible and clickable
		WebElement giftCard = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("gift_card"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCard);

		js.executeScript("arguments[0].click();", giftCard);

		// Gift Card Number input field
		WebElement giftCardNumber = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gift_card_number"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCardNumber);
		js.executeScript("arguments[0].click();", giftCardNumber);
		giftCardNumber.sendKeys(prop.getProperty("gc_number"));
		System.out.println("GC Number entered sussessfully");
		Thread.sleep(500);

		// Gift Card PIN input field
		WebElement giftCardPin = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gift_card_pin"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCardPin);
		js.executeScript("arguments[0].click();", giftCardPin);
		giftCardPin.sendKeys(prop.getProperty("gc_pin"));
		System.out.println("GC Pin entered sussessfully");
		Thread.sleep(500);

		// Click on Verify button to check the entered credentials are correct or not
		WebElement gcVerifyButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gc_verify_button"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", gcVerifyButton);
		js.executeScript("arguments[0].click();", gcVerifyButton);
		// gcVerifyButton.click();
		System.out.println("GC verified sussessfully");
		Thread.sleep(2000);

		WebElement placeOrder = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("place_order"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", placeOrder);

		// Click on the 'Place Order' button after scrolling to it
		js.executeScript("arguments[0].click();", placeOrder);
		ScreenshotUtil.takeScreenshot("Successfully verified Gift Card Payment method");
		Thread.sleep(5000);
		ScreenshotUtil.takeScreenshot("Order Placed successfully");
		System.out.println("Clicked on Pay Now sussessfully");

	}

}
