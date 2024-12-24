package testCase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.ScreenshotUtil;

public class Brand_Clip extends BaseTest {

	@Test(priority = 1)
	public void NavigateToMultipleBrandPages() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll to the bottom of the page to load all sections
		long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
		while (true) {
			js.executeScript("window.scrollBy(0, 500);");
			Thread.sleep(1000);
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");
			if (newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}
		System.out.println("Scrolled to the bottom of the page.");
		WebElement popularBrands = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("popular_brands"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", popularBrands);
		System.out.println("Scrolled successfully to the Popular Brands section.");
		List<WebElement> brands = driver.findElements(By.xpath(loc.getProperty("brand_links")));
		for (WebElement brand : brands) {
			String brandName = brand.getText().replace(",", "").trim();
			System.out.println("Brand found: " + brandName);
			Thread.sleep(1000);
			// Check for specific brands
			if (brandName.equals("Woodsworth") || brandName.equals("Amberville") || brandName.equals("Mintwud")
					|| brandName.equalsIgnoreCase("Duroflex") || brandName.equals("Nilkamal")) {
				String originalWindow = driver.getWindowHandle();
				try {
					// Ensure the element is visible before interacting
					wait.until(ExpectedConditions.visibilityOf(brand));
					js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", brand);
					wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
				} catch (Exception e) {
					System.out.println("Falling back to JavaScript click for: " + brandName);
					js.executeScript("arguments[0].click();", brand);
				}
				System.out.println("Navigated to " + brandName + " brand page.");
				wait.until(ExpectedConditions.numberOfWindowsToBe(2));
				for (String windowHandle : driver.getWindowHandles()) {
					if (!windowHandle.equals(originalWindow)) {
						driver.switchTo().window(windowHandle);
						break;
					}
				}
				wait.until(ExpectedConditions.titleContains(brandName));
				System.out.println("Current page title: " + driver.getTitle());
				driver.close();
				driver.switchTo().window(originalWindow);
				Thread.sleep(1000);
				ScreenshotUtil.takeScreenshot("Navigation to multiple brands is successful");
			}
		}
	}

	@Test(priority = 2)
	public void verifyMoreFilters() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement clickOnAnyBrand = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("spacewood_brand"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clickOnAnyBrand);
		clickOnAnyBrand.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		WebElement verifyMoreFilters = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", verifyMoreFilters);
		Thread.sleep(500);
		js.executeScript("arguments[0].click();", verifyMoreFilters);
		System.out.println("More filters section clicked successfully.");
		Thread.sleep(1000);
		WebElement priceFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("price_filter"))));
		priceFilter.click();
		WebElement priceMinRange = driver.findElement(By.xpath(loc.getProperty("priceMin_range")));
		priceMinRange.clear();
		priceMinRange.sendKeys("3000");
		WebElement priceMaxRange = driver.findElement(By.xpath(loc.getProperty("priceMax_range")));
		priceMaxRange.clear();
		priceMaxRange.sendKeys("20000");
		Thread.sleep(500);
		WebElement applyFilterButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		System.out.println("Clicked on apply filter button");
		Thread.sleep(3000);
		ScreenshotUtil.takeScreenshot("Successfully verified Primary filters for Brand CLIP");
	}

	@Test(priority = 3)
	public void verifySortFilter() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement verifySortByFilters = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sortBy_filter"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", verifySortByFilters);
		Thread.sleep(1000);
		WebElement sortByFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("sortBy_filter"))));
		sortByFilter.click();
		WebElement lowestPriceFilter = driver.findElement(By.xpath(loc.getProperty("lowestPrice_filter")));
		lowestPriceFilter.click();
		WebElement lowestPriceFirstProduct = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(loc.getProperty("lowest_highest_PriceFirst_product"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
				lowestPriceFirstProduct);
		String productName = lowestPriceFirstProduct.getText();
		System.out.println(productName);
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);
		WebElement verifySortByFiltersHighest = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sortBy_filter"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
				verifySortByFiltersHighest);
		Thread.sleep(2000);
		verifySortByFiltersHighest.click();
		WebElement highestPriceFilter = driver.findElement(By.xpath(loc.getProperty("highestPrice_filter")));
		highestPriceFilter.click();
		WebElement highestPriceFirstProduct = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(loc.getProperty("lowest_highest_PriceFirst_product"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
				highestPriceFirstProduct);
		String highestPriceProduct = highestPriceFirstProduct.getText();
		System.out.println(highestPriceProduct);
		ScreenshotUtil.takeScreenshot("Successfully verified the Sort Logic");
	}

	@Test(priority = 4)
	public void removefilter() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0, 0);");
		// System.out.println("Scrolled successfully");
		// js.executeScript("window.scrollTo(0, 50);");
		WebElement clearAllButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sticky_clear_all"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clearAllButton);
		Thread.sleep(500);
		clearAllButton.click();
		System.out.println("Clicked on 'Clear All' to remove the applied filters");
		Thread.sleep(1000);
		ScreenshotUtil.takeScreenshot("Successfully removed applied filters");

	}

	@Test(priority = 5)
	public void switchBackToFirstTab() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Assuming actions on the second tab are done
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.close();
		driver.switchTo().window(windowHandles.get(0));
		System.out.println("Switched back to the first tab.");
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);

	}
}