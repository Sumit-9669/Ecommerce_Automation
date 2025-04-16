package testCase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.ScreenshotUtil;

public class Brand_Clip extends BaseTest {
	@BeforeMethod
	public void handleWebEngagePopupBeforeEachTest() {
		closeWebEngagePopupIfPresent();
	}

	public void closeWebEngagePopupIfPresent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			List<WebElement> frames = driver
					.findElements(By.id("webklipper-publisher-widget-container-notification-frame"));
			if (!frames.isEmpty()) {
				driver.switchTo().frame(frames.get(0));
				WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//*[contains(text(), '×') or contains(text(), 'Close') or contains(@class, 'close')]")));
				closeBtn.click();
				driver.switchTo().defaultContent();
				System.out.println("WebEngage popup closed successfully.");
			}
		} catch (Exception e) {
			System.out.println("WebEngage popup not found or already closed.");
			driver.switchTo().defaultContent();
		}
	}

    @Test(priority = 1)
    public void NavigateToMultipleBrandPages() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            js.executeScript("window.scrollBy(0, 500);");
            Thread.sleep(1000);
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) break;
            lastHeight = newHeight;
        }
        System.out.println("Scrolled to the bottom of the page.");
        Assert.assertTrue(lastHeight > 0, "Page did not scroll properly.");

        WebElement popularBrands = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("popular_brands"))));
        Assert.assertNotNull(popularBrands, "Popular Brands section not found.");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", popularBrands);
        System.out.println("Scrolled successfully to the Popular Brands section.");

        List<WebElement> brands = driver.findElements(By.xpath(loc.getProperty("brand_links")));
        Assert.assertFalse(brands.isEmpty(), "Brand links are not visible.");

        for (WebElement brand : brands) {
            String brandName = brand.getText().replace(",", "").trim();
            Thread.sleep(1000);

            if (brandName.equals("Woodsworth") || brandName.equals("Amberville") || brandName.equals("Mintwud")
                    || brandName.equalsIgnoreCase("Duroflex") || brandName.equals("Nilkamal")) {

                String originalWindow = driver.getWindowHandle();
                try {
                    wait.until(ExpectedConditions.visibilityOf(brand));
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", brand);
                    wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
                } catch (Exception e) {
                    System.out.println("Falling back to JavaScript click for: " + brandName);
                    js.executeScript("arguments[0].click();", brand);
                }
                System.out.println("Navigated to " + brandName + " brand page.");
                Assert.assertTrue(driver.getWindowHandles().size() > 1, "New window for brand did not open.");

                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandle.equals(originalWindow)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }

                wait.until(ExpectedConditions.titleContains(brandName));
                Assert.assertTrue(driver.getTitle().contains(brandName), "Page title does not contain brand name.");
                System.out.println("Current page title: " + driver.getTitle());
                driver.close();
                driver.switchTo().window(originalWindow);
                
            }
        }
    }

    @Test(priority = 2)
    public void verifyMoreFilters() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement clickOnAnyBrand = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("spacewood_brand"))));
        Assert.assertNotNull(clickOnAnyBrand, "Brand link is not clickable.");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clickOnAnyBrand);
        clickOnAnyBrand.click();
        System.out.println("Successfully navigated to spacewood brand page");

        Thread.sleep(1000);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println("navigated back to first tab");

        WebElement verifyMoreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
        Assert.assertTrue(verifyMoreFilters.isDisplayed(), "More filters not visible.");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", verifyMoreFilters);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", verifyMoreFilters);
        System.out.println("More filters section clicked successfully.");

        Thread.sleep(1000);
        WebElement priceFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("price_filter"))));
        priceFilter.click();
        System.out.println("clicked on price filter");

        WebElement priceMinRange = driver.findElement(By.xpath(loc.getProperty("priceMin_range")));
        priceMinRange.clear();
        System.out.println("cleared the min. price field");
        priceMinRange.sendKeys("3000");
        System.out.println("entered 3000 min price");

        WebElement priceMaxRange = driver.findElement(By.xpath(loc.getProperty("priceMax_range")));
        priceMaxRange.clear();
        System.out.println("cleared max price field");
        priceMaxRange.sendKeys("20000");
        System.out.println("entered 20000 max price");

        WebElement applyFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
        applyFilterButton.click();
        System.out.println("Clicked on apply filter button");
      
    }

    @Test(priority = 3)
    public void verifySortFilter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement verifySortByFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sortBy_filter"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", verifySortByFilters);
        Thread.sleep(1000);

        WebElement sortByFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("sortBy_filter"))));
        sortByFilter.click();
        System.out.println("clicked on sort filter");

        WebElement lowestPriceFilter = driver.findElement(By.xpath(loc.getProperty("lowestPrice_filter")));
        lowestPriceFilter.click();
        System.out.println("clicked on lowest price filter");

        WebElement lowestPriceFirstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("lowest_highest_PriceFirst_product"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", lowestPriceFirstProduct);
        String productName = lowestPriceFirstProduct.getText();
        System.out.println(productName);
        Assert.assertFalse(productName.isEmpty(), "Lowest price product name is empty.");

        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(500);

        try {
            List<WebElement> frames = driver.findElements(By.id("webklipper-publisher-widget-container-notification-frame"));
            if (!frames.isEmpty()) {
                driver.switchTo().frame(frames.get(0));
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(text(), '×') or contains(text(), 'Close') or contains(@class, 'close')]")));
                closeBtn.click();
                driver.switchTo().defaultContent();
                System.out.println("Popup closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("iFrame popup not found or could not be closed.");
        }

        WebElement verifySortByFiltersHighest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sortBy_filter"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", verifySortByFiltersHighest);
        Thread.sleep(2000);
        verifySortByFiltersHighest.click();

        WebElement highestPriceFilter = driver.findElement(By.xpath(loc.getProperty("highestPrice_filter")));
        highestPriceFilter.click();
        System.out.println("clicked on highest price filter");

        WebElement highestPriceFirstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("lowest_highest_PriceFirst_product"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", highestPriceFirstProduct);
        String highestPriceProduct = highestPriceFirstProduct.getText();
        System.out.println(highestPriceProduct);
        Assert.assertFalse(highestPriceProduct.isEmpty(), "Highest price product name is empty.");
        Thread.sleep(3000);
        ScreenshotUtil.takeScreenshot("Successfully verified the Sort Logic");
    }

    @Test(priority = 4)
    public void removefilter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement clearAllButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("sticky_clear_all"))));
        Assert.assertTrue(clearAllButton.isDisplayed(), "'Clear All' button not visible.");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clearAllButton);
        Thread.sleep(500);
        clearAllButton.click();
        System.out.println("Clicked on 'Clear All' to remove the applied filters");
        
    }

    @Test(priority = 5)
    public void switchBackToFirstTab() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(windowHandles.size() > 1, "Only one tab is open.");
        driver.close();
        driver.switchTo().window(windowHandles.get(0));
        System.out.println("Switched back to the first tab.");
        Thread.sleep(1000);
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);
    }
}
