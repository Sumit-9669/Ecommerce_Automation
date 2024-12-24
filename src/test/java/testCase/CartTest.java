package testCase;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;
import utilities.ScreenshotUtil;

public class CartTest extends BaseTest {

	@Test(priority = 1)
	@Description("Verifying Cart Items")
	public void verifyCartItems() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement cartItems = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cart_items"))));
		String itemsInCart = cartItems.getText();
		System.out.println("In" + itemsInCart + "is/are present");
		ScreenshotUtil.takeScreenshot("Successfully verified Items in cart");
		Thread.sleep(500);

	}

	@Test(priority = 2)
	@Description("Verifying Cart Product Name")
	public void verifyCartProductName() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement cartProductName = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cart_product_name"))));
		String cartSKUName = cartProductName.getText();
		System.out.println("The Product Name Present on cart is " + cartSKUName);
		Thread.sleep(500);
	}

	@Test(priority = 3)
	@Description("Verifying wishlist functionality")
	public void clickOnWishlistCTA() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement wishlistCTA = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cart_item_wishlist"))));
		wishlistCTA.click();
		System.out.println("Clicked on Wishlist CTA");
	}

	@Test(priority = 4, dependsOnMethods = "clickOnWishlistCTA")
	public void switchFocusToPopup() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		@SuppressWarnings("unused")
		WebElement popUp = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("cart_popup"))));
		driver.switchTo().activeElement(); // Switch focus to the pop-up
		System.out.println("Focused switched to Pop-Up");
		Thread.sleep(1000);
	}

	@Test(priority = 5, dependsOnMethods = "switchFocusToPopup")
	public void cancelPopUp() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement closePopUp = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("close_wishlist_popup"))));
		closePopUp.click();
		System.out.println("Closed the wishlist pop-up");
	}

	@Test(priority = 6)
	public void againClickOnWishlistCTA() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement wishlistCTA = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cart_item_wishlist"))));
		wishlistCTA.click();
		System.out.println("Again clicked on Wishlist CTA");
	}

	@Test(priority = 7, dependsOnMethods = "againClickOnWishlistCTA")
	public void againSwitchFocusToPopup() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		@SuppressWarnings("unused")
		WebElement popUp = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("cart_popup"))));
		driver.switchTo().activeElement(); // Switch focus to the pop-up
		System.out.println("Again the Focused switched to wishlist Pop-Up");
		ScreenshotUtil.takeScreenshot("Successfully verified Wishlist Functionality");
		Thread.sleep(500);
	}

	@Test(priority = 8, dependsOnMethods = "againSwitchFocusToPopup")
	public void cancelWishListing() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		WebElement cancelWishlistCTA = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cancel_wishlist"))));
		cancelWishlistCTA.click();
		System.out.println("The product is cancelled from being wishlisted and You are again on the cart page");
		Thread.sleep(500);
	}

	@Test(priority = 9)
	@Description("Verifying Delivery Fees")
	public void verifyDeliveryFee() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		try {
			WebElement deliveryFee = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("delivery_fee"))));
			String feeAmount = deliveryFee.getText();
			System.out.println("The Delivery Fee is: " + feeAmount);
		} catch (TimeoutException e) {
			System.out.println("Delivery fee not present on this page. Skipping verification.");
		}
	}

	@Test(priority = 10)
	@Description("Verifying Assembly Fees")
	public void verifyAssemblyFee() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		try {
			WebElement assemblyFee = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("assembly_fee"))));
			String feeAmount = assemblyFee.getText();
			System.out.println("The Assembly Fee is: " + feeAmount);
		} catch (TimeoutException e) {
			System.out.println("Assembly fee not present on this page. Skipping verification.");
		}
	}

	@Test(priority = 11)
	@Description("Verifying Item Price & Inssurance")
	public void verifyPriceChangeWithFurnitureProtection() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate and get the item price before selecting the checkbox
		WebElement itemPriceElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("item_price"))));
		String initialPriceText = itemPriceElement.getText();
		System.out.println("Item price before selecting protection: " + initialPriceText);

		// Locate the furniture protection checkbox item_price_afterFurnitureProtection
		WebElement furnitureProtectionCheckbox = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(loc.getProperty("furniture_protection_checkbox"))));
		furnitureProtectionCheckbox.click();
		Thread.sleep(1000);
		WebElement itemPriceAfterFurnitureProtection = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("item_price"))));
		String furnitureProtectionPriceText = itemPriceAfterFurnitureProtection.getText();
		System.out.println("Item price after selecting protection: " + furnitureProtectionPriceText);
		Thread.sleep(500);

	}

	@Test(priority = 12)
	@Description("Verifying Delivery Date")
	public void verifyDeliveryByText() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate and get the item price before selecting the checkbox
		WebElement deliveryByElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("delivery_By"))));
		String deliveryByText = deliveryByElement.getText();
		System.out.println("Your Item has a " + deliveryByText);
		Thread.sleep(500);

	}

	@Test(priority = 13)
	public void verifyCartMrp() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate and get the item price before selecting the checkbox
		WebElement cartMrp = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("cart_MRP"))));
		String cartMRP = cartMrp.getText();
		System.out.println("You have products in your cart with " + cartMRP);
		Thread.sleep(500);

	}

	@Test(priority = 14)
	public void verifyCartSummaryFurnitureProtection() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement cartSummaryFP = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(loc.getProperty("cart_summary_furniture_protection"))));
		String cartSummaryFurProtection = cartSummaryFP.getText();
		System.out.println("The Furniture Protection amount in cart summary is " + cartSummaryFurProtection);
		ScreenshotUtil.takeScreenshot("Successfully verified Cart Summary-1");
		Thread.sleep(500);

	}

	@Test(priority = 15)
	public void verifySubTotalOnCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement cartSubTotal = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sub_total"))));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartSubTotal);
		String subTotal = cartSubTotal.getText();
		System.out.println("Cart " + subTotal);
		Thread.sleep(2000);

	}
	/*
	 * @Test(priority = 16) public void cartCharity() throws InterruptedException {
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 * 
	 * // Locate the charity element WebElement cartCharity = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty
	 * ("charity_text"))));
	 * 
	 * // Scroll to the element to ensure it’s in view ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", cartCharity);
	 * 
	 * // Wait again to ensure it's visible and interactable after scrolling
	 * wait.until(ExpectedConditions.visibilityOf(cartCharity));
	 * 
	 * // Get the text of the charity element String cartCharityText =
	 * cartCharity.getText(); System.out.println("The charity text is " +
	 * cartCharityText); Thread.sleep(2000); }
	 * 
	 * 
	 * @Test(priority = 17) public void verifyPriceChangeWithCharity() throws
	 * InterruptedException { WebDriverWait wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(10));
	 * 
	 * // Locate and get the item price before selecting the checkbox WebElement
	 * charityElement =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.
	 * getProperty("cart_charity_amount")))); String beforeCharity =
	 * charityElement.getText(); System.out.
	 * println("Charity amount before selecting the environment protection plan: " +
	 * beforeCharity); Thread.sleep(1000);
	 * 
	 * // Locate the furniture protection checkbox
	 * item_price_afterFurnitureProtection WebElement cartCharity =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.
	 * getProperty("charity_checkbox")))); ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", cartCharity);
	 * cartCharity.click(); Thread.sleep(1000); WebElement AfterCharity =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.
	 * getProperty("cart_charity_amount")))); String charityText =
	 * AfterCharity.getText(); System.out.
	 * println("Charity amount after selecting the environment protection plan: " +
	 * charityText); Thread.sleep(5000);
	 */

	@Test(priority = 16)
	public void verifyYouPayPrice() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate and get the item price before selecting the checkbox
		WebElement youPayPrice = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("you_pay"))));
		String youPay = youPayPrice.getText();
		System.out.println("The You Pay price is " + youPay);
		Thread.sleep(1000);
	}

	@Test(priority = 17)
	public void verifyYouSavePrice() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate and get the item price before selecting the checkbox
		WebElement youSavePrice = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("you_save"))));
		String youSave = youSavePrice.getText();
		ScreenshotUtil.takeScreenshot("Successfully verified Cart Summary-2");
		System.out.println("The You Save price is " + youSave);
		Thread.sleep(1000);
	}

	@Test(priority = 18)
	public void enterGSTINAndSave() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for GSTIN field to be clickable and click on it to open the modal
		WebElement gstinField = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("gstin_field_id"))));
		gstinField.click();
		Thread.sleep(2000); // Give time for modal to open

		WebElement gstinInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("gstin_reg_number"))));
		WebElement companyNameInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("reg_company_name"))));

		gstinInput.sendKeys("29AAACG1395D1ZQ");
		companyNameInput.sendKeys("GODREJ AND BOYCE MANUFACTURING CO LTD");
		Thread.sleep(1000);

		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("save_gstin_button"))));
		saveButton.click();
		System.out.println("Successfully Added GSTIN Details");
		ScreenshotUtil.takeScreenshot("Successfully verified Business Purchase");
		Thread.sleep(2000);
		
		WebElement cancelGstin = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cancel_applied_gstin"))));
		cancelGstin.click();
		System.out.println("Applied GSTIN has been removed");
		Thread.sleep(2000);
		driver.close();	
		// Switch to the remaining window/tab
	    Set<String> allWindows = driver.getWindowHandles();
	    for (String window : allWindows) {
	        driver.switchTo().window(window);
	    }
	    System.out.println("Switched to the CLIP tab: " + driver.getTitle());
		/*//PTP is referred to Proceed To Pay
		WebElement proceedToPay = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("proceed_to_pay"))));
		proceedToPay.click();
		System.out.println("Proceeded to Payment page successfully");
		Thread.sleep(2000);*/
		
	}

	/*@Test(priority = 19)
	public void switchBackToFirstTab() throws InterruptedException {
		// Assuming actions on the second tab are done
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(0));
		System.out.println("Switched back to the first tab.");
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement pepperfryLogo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("pepperfry_logo"))));

		// Scroll directly to the logo element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pepperfryLogo);
		System.out.println("Scrolled directly to the Pepperfry logo.");

		// Adding a brief wait to ensure scroll completes and the element is in focus
		Thread.sleep(1000);

		// Attempt clicking using JavaScript to bypass any interception
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", pepperfryLogo);
		System.out.println("Clicked on the Pepperfry logo using JavaScript.");*/
}
