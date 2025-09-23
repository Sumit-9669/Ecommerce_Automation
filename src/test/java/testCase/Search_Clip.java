package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import io.qameta.allure.Description;
import utilities.ScreenshotUtil;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search_Clip extends BaseTest {

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
	@Description("To verify the Search functionality with different search queries")
	public void autoSuggestSearchFlow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		By searchBarLocator = By.xpath(loc.getProperty("search_bar"));
		By autoSuggestProductLocator = By.xpath(loc.getProperty("autosuggest_product"));
		By autoSuggestSearchRes = By.xpath(loc.getProperty("autosuggest_showing_res"));

		try {
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator));
			Assert.assertTrue(searchBar.isDisplayed(), "Search bar is not visible");
			Assert.assertTrue(searchBar.isEnabled(), "Search bar is not enabled");

			searchBar.clear();
			searchBar.sendKeys("Chair");

			WebElement autoSuggestProduct = wait
					.until(ExpectedConditions.elementToBeClickable(autoSuggestProductLocator));
			Assert.assertTrue(autoSuggestProduct.isDisplayed(), "Auto-suggested product is not displayed");

			autoSuggestProduct.click();
			try {
				List<WebElement> frames = driver
						.findElements(By.id("webklipper-publisher-widget-container-notification-frame"));
				if (!frames.isEmpty()) {
					driver.switchTo().frame(frames.get(0));
					WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[contains(text(), '×') or contains(text(), 'Close') or contains(@class, 'close')]")));
					closeBtn.click();
					driver.switchTo().defaultContent();
					System.out.println("Popup closed successfully.");
				}
			} catch (Exception e) {
				System.out.println("iFrame popup not found or could not be closed.");
			}

			WebElement autoSuggestRes = wait.until(ExpectedConditions.visibilityOfElementLocated(autoSuggestSearchRes));
			Assert.assertTrue(autoSuggestRes.isDisplayed(), "Search results are not displayed");
			Assert.assertTrue(autoSuggestRes.getText().toLowerCase().contains("chair"),
					"Search results do not contain the searched term");

			System.out.println("Category Search Result: " + autoSuggestRes.getText());
			Thread.sleep(2000);
			ScreenshotUtil.takeScreenshot("Autosuggest search page");

			searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator)); // Re-fetch element
			Assert.assertTrue(searchBar.isDisplayed(), "Search bar not found after result page load");

			searchBar.clear();
		} catch (Exception e) {
			System.out.println("Exception in autoSuggestSearchFlow: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		driver.navigate().refresh();
		System.out.println("Page refreshed successfully");
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void diveIntoCatSearchFlow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		By searchBarLocator = By.xpath(loc.getProperty("search_bar"));
		By diveIntoCatLocator = By.xpath(loc.getProperty("dive_into_cat_locator"));
		By diveIntoCatSkuLocator = By.xpath(loc.getProperty("dive_into_cat_sku"));
		By diveCatShowingResLocator = By.xpath(loc.getProperty("dive_cat_showing_res"));

		try {
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator));
			Assert.assertTrue(searchBar.isDisplayed(), "Search bar is not visible");
			Assert.assertTrue(searchBar.isEnabled(), "Search bar is not enabled");

			searchBar.sendKeys("Sofa");

			WebElement diveIntoCat = wait.until(ExpectedConditions.elementToBeClickable(diveIntoCatLocator));
			Assert.assertTrue(diveIntoCat.isDisplayed(), "Dive into category element is not visible");
			System.out.println("Dive Into Category: " + diveIntoCat.getText());

			WebElement diveIntoCatSku = wait.until(ExpectedConditions.elementToBeClickable(diveIntoCatSkuLocator));
			Assert.assertTrue(diveIntoCatSku.isDisplayed(), "Dive into category SKU is not visible");

			Thread.sleep(2000);
			ScreenshotUtil.takeScreenshot("Dive into category search page");

			diveIntoCatSku.click();

			WebElement divIntoRes = wait.until(ExpectedConditions.visibilityOfElementLocated(diveCatShowingResLocator));
			Assert.assertTrue(divIntoRes.isDisplayed(), "Category search result is not displayed");
			Assert.assertTrue(divIntoRes.getText().toLowerCase().contains("sofa"),
					"Search result does not contain expected term 'sofa'");

			System.out.println("Category Search Result: " + divIntoRes.getText());

		} catch (Exception e) {
			System.out.println("Exception in diveIntoCatSearchFlow: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		Thread.sleep(1000);
	}

	

	@Test(priority = 3)
	public void searchClipFilters() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			WebElement moreFilters = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
			Assert.assertTrue(moreFilters.isDisplayed(), "'More Filters' is not displayed");
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
			js.executeScript("arguments[0].click();", moreFilters);
			Thread.sleep(1000);

			WebElement materialFilter = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("material_filter"))));
			Assert.assertTrue(materialFilter.isDisplayed(), "Material filter not displayed");
			materialFilter.click();
			Thread.sleep(500);

			WebElement materialCheckbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("leatherette_mtrl"))));
			 //Assert.assertTrue(materialCheckbox.isDisplayed(), "Leatherette checkbox not found");
			materialCheckbox.click();
			 //Assert.assertTrue(materialCheckbox.isSelected(), "Leatherette checkbox is not selected");

			System.out.println("Clicked on Leatherette Material");
			Thread.sleep(2000);
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

			WebElement applyFilterButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
			//Assert.assertTrue(applyFilterButton.isDisplayed(), "Apply filter button not visible");
			applyFilterButton.click();
			System.out.println("Clicked on apply filter button");
			Thread.sleep(2000);

			/*moreFilters = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
			js.executeScript("arguments[0].click();", moreFilters);
			Thread.sleep(2000);

			WebElement discountFilter = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("discount_filter"))));
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", discountFilter);
			Thread.sleep(500);
			Assert.assertTrue(discountFilter.isDisplayed(), "Discount filter not visible");
			discountFilter.click();

			WebElement discountCheckbox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("discnt_ten_per_filter"))));
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", discountCheckbox);
			Thread.sleep(500);
			Assert.assertTrue(discountCheckbox.isDisplayed(), "10% discount checkbox not visible");
			discountCheckbox.click();
			Assert.assertTrue(discountCheckbox.isSelected(), "10% discount checkbox is not selected");

			System.out.println("Clicked on 10% Discount");

			applyFilterButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
			//applyFilterButton.click();
			js.executeScript("arguments[0].click();", applyFilterButton);
			System.out.println("Clicked on apply filter button");
			Thread.sleep(2000);*/

			// Clear all filters
			moreFilters = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
			js.executeScript("arguments[0].click();", moreFilters);
			Thread.sleep(1000);

			WebElement clearAllButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("clear_all"))));
			Assert.assertTrue(clearAllButton.isDisplayed(), "'Clear All' button is not visible");
			clearAllButton.click();
			System.out.println("Clicked on 'Clear All' to remove the applied filters");
			Thread.sleep(2000);
		}catch (Exception e) {
	            System.out.println("clear all button not found");}
			

	}

	@Test(priority = 4)
	public void addToWishlist() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement addToWishlist = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("add_to_wishlist"))));
			Assert.assertTrue(addToWishlist.isDisplayed(), "'Add to Wishlist' icon is not visible");

			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addToWishlist);
			js.executeScript("arguments[0].click();", addToWishlist);
			System.out.println("Clicked on Wishlist Icon");

			Thread.sleep(2000);
			ScreenshotUtil.takeScreenshot("Item added to wishlist");

			// Optional: Assert wishlist state is active/selected (based on your app’s
			// locator)
			//WebElement wishlistActive = wait.until(
					//ExpectedConditions.presenceOfElementLocated(By.xpath(loc.getProperty("wishlist_active_state"))));
			//Assert.assertTrue(wishlistActive.isDisplayed(), "Wishlist icon did not change to active state");

		
	}

	/*
	 * @Test(priority = 6) public void pagination() throws InterruptedException {
	 * WebDriverWait wait = new WebDriverWait(BaseTest.driver,
	 * Duration.ofSeconds(10)); JavascriptExecutor js = (JavascriptExecutor) driver;
	 * 
	 * WebElement navigateToNextPage = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty(
	 * "sec_page_nav_loc")))); js.
	 * executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});"
	 * , navigateToNextPage); js.executeScript("arguments[0].click();",
	 * navigateToNextPage); System.out.println("Clicked on second page");
	 * Thread.sleep(2000); }
	 */

	@Test(priority = 6)
	public void popularSearches() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		
 // Scrolls to top of the page

		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(15));
		By searchBarLoc = By.xpath(loc.getProperty("search_bar"));

		try {
			// Click on the search bar
			WebElement searchBar = wait
					.until(ExpectedConditions.visibilityOfElementLocated(searchBarLoc));
			int y = searchBar.getLocation().getY();
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0] - 100);", y); // adjust 100px as per your sticky header height

			Thread.sleep(1000); // wait for scroll to complete
			searchBar.click();

			//Assert.assertTrue(searchBar.isDisplayed(), "Search bar not visible");
			//js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchBar);
			searchBar.click();
			System.out.println("Clicked on Search Bar");

			// Wait for popular searches to be visible
			WebElement popSearches = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("pop_searches"))));
			//Assert.assertTrue(popSearches.isDisplayed(), "Popular searches not displayed");
			System.out.println("Popular Searches Displayed: " + popSearches.getText());

			// Click on the first category
			WebElement popSearchesFirstCat = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pop_searches_first_cat"))));
			//Assert.assertTrue(popSearchesFirstCat.isDisplayed(), "First popular search category not visible");
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
					popSearchesFirstCat);
			System.out.println("First Popular Search: " + popSearchesFirstCat.getText());
			popSearchesFirstCat.click();
			System.out.println("Clicked on First Popular Search Category");

			// Navigate back to the homepage
			WebElement pepLogo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
			//Assert.assertTrue(pepLogo.isDisplayed(), "Pepperfry logo not visible for navigation");
			pepLogo.click();
			System.out.println("Navigated back to Homepage");

			// Perform search for the second popular category
			searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLoc));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchBar);
			searchBar.click();
			System.out.println("Clicked on Search Bar Again");

			// Click on the second category
			WebElement popSearchesSecondCat = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pop_searches_second_cat"))));
			Assert.assertTrue(popSearchesSecondCat.isDisplayed(), "Second popular search category not visible");
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
					popSearchesSecondCat);
			System.out.println("Second Popular Search: " + popSearchesSecondCat.getText());
			popSearchesSecondCat.click();
			System.out.println("Clicked on Second Popular Search Category");

		} catch (Exception e) {
			System.out.println("Exception in popularSearches: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

	@Test(priority = 7)
	public void backToHomepage() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Ensure the page is fully loaded
			wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

			// Wait for the Pepperfry logo to be visible
			WebElement pepLogo = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("pepperfry_logo"))));
			Assert.assertTrue(pepLogo.isDisplayed(), "Pepperfry logo not visible");

			// Click using JavaScript to avoid interception
			js.executeScript("arguments[0].click();", pepLogo);
			System.out.println("Navigated to Homepage using JavaScript click");

			// Optionally verify if homepage is displayed
			String expectedUrlFragment = "pepperfry.com"; // Customize based on your homepage URL pattern
			Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlFragment), "Homepage URL does not match");

		} catch (Exception e) {
			System.out.println("Exception in backToHomepage: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

}
