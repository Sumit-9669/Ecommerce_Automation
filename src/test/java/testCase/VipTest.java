package testCase;

import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.ScreenshotUtil;

public class VipTest extends BaseTest {

	@Test
	public void vipAllScenarios() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Safe page refresh with timeout handling and screenshot on failure
		try {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.navigate().refresh();
		} catch (TimeoutException e) {
			System.out.println("Timeout during page refresh. Attempting JS reload...");
			ScreenshotUtil.takeScreenshot("Timeout during refresh - fallback to JS reload");
			js.executeScript("location.reload()");
		}

		WebElement threeseatersofa = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("Furniture_Meta"))));
		actions.moveToElement(threeseatersofa).perform();

		WebElement drawerOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("1_seater_sofa"))));
		drawerOption.click();
		Thread.sleep(2000);

		WebElement clipFirstItem = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("first_item"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clipFirstItem);
		// js.executeScript("arguments[0].click();", clipFirstItem);
		Thread.sleep(2000);
		actions.moveToElement(clipFirstItem).click().perform();
		Thread.sleep(2000);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size() - 1));
		WebElement skuTitle = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sku_name"))));
		String productTitle = skuTitle.getText();
		System.out.println("The Product Title is " + productTitle);
		Thread.sleep(1000);

		WebElement skuRating = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sku_rating"))));
		String productRating = skuRating.getText();
		ScreenshotUtil.takeScreenshot("SKU name & Rating verified");
		System.out.println("The Product Rating is " + productRating);
		Thread.sleep(1000);

		WebElement skuMrp = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sku_MRP"))));
		String productMrp = skuMrp.getText();
		System.out.println("The Product MRP is " + productMrp);
		Thread.sleep(1000);

		WebElement emiText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("EMI_text"))));
		String productEMIText = emiText.getText();
		ScreenshotUtil.takeScreenshot("SKU MRP & EMI verified");
		System.out.println("The Product EMI text is " + productEMIText);
		Thread.sleep(1000);

		WebElement pincodeField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("pincode_field"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", pincodeField);
		js.executeScript("arguments[0].click();", pincodeField);
		Thread.sleep(1000);
		WebElement changePincode = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("change_Pincode"))));
		changePincode.click();
		System.out.println("Clicked on Pincode Change button successfully");
		Thread.sleep(1000);
		pincodeField.sendKeys("560001");
		Thread.sleep(1000);
		System.out.println("Successfully entered the new pincode i.e. 560001");

		WebElement deliveryText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("delivery_text"))));
		String DeliveryText = deliveryText.getText();
		System.out.println("The delivery Text is " + DeliveryText);
		Thread.sleep(1000);

		WebElement assemblyText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("assembly_text"))));
		String AssemblyText = assemblyText.getText();
		ScreenshotUtil.takeScreenshot("Pincode & Delivery/Assembly verified successfully");
		System.out.println("The Assembly Text is " + AssemblyText);
		Thread.sleep(1000);

		WebElement addToCart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("add_to_cart"))));
		addToCart.click();
		Thread.sleep(1000);

		WebElement goToCart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("go_to_cart"))));
		goToCart.click();
		System.out.println("Product Added to the Cart Successfully");
		Thread.sleep(3000);
		driver.navigate().back();

		WebElement buyNow = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("buy_now"))));
		buyNow.click();
		System.out.println("Clicked on Buy Now CTA Successfully");
		Thread.sleep(2000);

	}

}
