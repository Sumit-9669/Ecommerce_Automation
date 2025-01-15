package testCase;

import org.testng.annotations.Test;

import base.BaseTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyOrders extends BaseTest {
	@Test(dependsOnMethods = { "testCase.LoginTest.automateLoginSignup" }, priority = 1)
	public void navigateToMyOrders() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);

		WebElement LoginUsername = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("username_loc"))));

		actions.moveToElement(LoginUsername).perform();
		System.out.println("Hovered over the username successfully.");

		WebElement myOrdersOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("my_orders"))));
		myOrdersOption.click();
		System.out.println("Navigated to My Orders page.");
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void verifyTotalSavings() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement totalSavings = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("savings"))));
		System.out.println("Your Total savings till date is:" + totalSavings.getText());
	}

	@Test(priority = 3)
	public void verifyTotalOrders() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement totalOrders = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("all_orders"))));
		System.out.println(totalOrders.getText());
	}

	@Test(priority = 4)
	public void verifyMyOrdersTrackItem() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement trackItem = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("myorders_track_item"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", trackItem);
		System.out.println(trackItem.getText());
		trackItem.click();
		Thread.sleep(500);

	}

	@Test(priority = 5)
	public void verifyCancelOrder() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    try {
	        // Locate and click the "Cancel Order" button
	        WebElement cancelOrder = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cancel_order"))));
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", cancelOrder);
	        System.out.println(cancelOrder.getText());
	        cancelOrder.click();
	        Thread.sleep(500);

	        // Re-locate elements after DOM updates
	        WebElement selectReasonbtn = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("select_reason_btn"))));
	        selectReasonbtn.click();
	        Thread.sleep(500);

	        WebElement cancelReasonChangeProduct = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cnl_reason_chgn_product"))));
	        cancelReasonChangeProduct.click();

	        // Re-locate elements after DOM updates
	        WebElement confirmCancellation = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("confirm_cancellation"))));
	        System.out.println(confirmCancellation.getText());
	        confirmCancellation.click();
	        Thread.sleep(500);

	        WebElement optionToRefund = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("refund_options"))));
	        System.out.println(optionToRefund.getText());

	        WebElement pfWallet = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pfWallet"))));
	        System.out.println(pfWallet.getText());
	        pfWallet.click();

	        // Re-locate elements after DOM updates
	        WebElement proceedToCancelOrderBtn = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceedTo_cancel"))));
	        System.out.println(proceedToCancelOrderBtn.getText());
	        proceedToCancelOrderBtn.click();

	        WebElement cancelConfirmInfoMsg = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cancel_confirm_info_msg"))));
	        cancelConfirmInfoMsg.click();

	    } catch (StaleElementReferenceException e) {
	        System.out.println("Caught StaleElementReferenceException: Re-locating elements.");
	    } catch (Exception e) {
	        System.out.println("Exception in verifyCancelOrder: " + e.getMessage());
	    }
	}

}
