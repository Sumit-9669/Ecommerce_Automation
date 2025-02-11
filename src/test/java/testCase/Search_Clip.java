package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import io.qameta.allure.Description;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class Search_Clip extends BaseTest {

	@Test(priority = 1)
	@Description("To verify the Search functionality with different search queries")
	public void autoSuggestSearchFlow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		By searchBarLocator = By.xpath(loc.getProperty("search_bar"));
		By autoSuggestProductLocator = By.xpath(loc.getProperty("autosuggest_product"));
		By autoSuggestSearchRes = By.xpath(loc.getProperty("autosuggest_showing_res"));

		try {
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator));
			searchBar.clear();
			searchBar.sendKeys("Table");

			WebElement autoSuggestProduct = wait
					.until(ExpectedConditions.elementToBeClickable(autoSuggestProductLocator));
			autoSuggestProduct.click();
			WebElement autoSuggestRes = wait.until(ExpectedConditions.visibilityOfElementLocated(autoSuggestSearchRes));
			System.out.println("Category Search Result: " + autoSuggestRes.getText());

			searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator)); // Re-fetch element
			searchBar.clear();
		} catch (Exception e) {
			System.out.println("Exception in autoSuggestSearchFlow: " + e.getMessage());
		}
		
		driver.navigate().refresh();
	    System.out.println("Page refreshed successfully");
		Thread.sleep(1000);
	}

	@Test(priority = 3)
	public void diveIntoCatSearchFlow() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		By searchBarLocator = By.xpath(loc.getProperty("search_bar"));
		By diveIntoCatLocator = By.xpath(loc.getProperty("dive_into_cat_locator"));
		By diveIntoCatSkuLocator = By.xpath(loc.getProperty("dive_into_cat_sku"));
		By diveCatShowingResLocator = By.xpath(loc.getProperty("dive_cat_showing_res"));

		try {
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator));
			searchBar.sendKeys("Bean");

			WebElement diveIntoCat = wait.until(ExpectedConditions.elementToBeClickable(diveIntoCatLocator));
			System.out.println("Dive Into Category: " + diveIntoCat.getText());

			WebElement diveIntoCatSku = wait.until(ExpectedConditions.elementToBeClickable(diveIntoCatSkuLocator));
			diveIntoCatSku.click();

			WebElement divIntoRes = wait.until(ExpectedConditions.visibilityOfElementLocated(diveCatShowingResLocator));
			System.out.println("Category Search Result: " + divIntoRes.getText());

		} catch (Exception e) {
			System.out.println("Exception in diveIntoCatSearchFlow: " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void directSearchFlow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		By searchBarLocator = By.xpath(loc.getProperty("search_bar"));
		By directSearchResLocator = By.xpath(loc.getProperty("direct_search_showing_res"));

		try {
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator));
			searchBar.sendKeys("Office" + Keys.RETURN);

			WebElement directSearchRes = wait
					.until(ExpectedConditions.visibilityOfElementLocated(directSearchResLocator));
			System.out.println("Direct Search Result: " + directSearchRes.getText());

			searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLocator)); // Re-fetch element
			searchBar.clear();
		} catch (Exception e) {
			System.out.println("Exception in directSearchFlow: " + e.getMessage());
		}
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void searchClipFilters() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement moreFilters = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement materialFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("material_filter"))));
		materialFilter.click(); // Click to expand the accordion

		// Wait for the checkbox to appear in the expanded accordion
		WebElement materialCheckbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("leatherette_mtrl"))));
		materialCheckbox.click();
		System.out.println("Clicked on Leatherette Material");
		Thread.sleep(1000);

		WebElement applyFilterButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		System.out.println("Clicked on apply filter button");
		Thread.sleep(2000);

		moreFilters = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement discountFilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("discount_filter"))));
		js.executeScript("arguments[0].scrollIntoView(true);", discountFilter);
		discountFilter.click();
		WebElement discountCheckbox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("discnt_ten_per_filter"))));
		js.executeScript("arguments[0].scrollIntoView(true);", discountCheckbox);
		discountCheckbox.click();
		System.out.println("Clicked on 10% Discount");

		applyFilterButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		System.out.println("Clicked on apply filter button");
		Thread.sleep(2000);

		moreFilters = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement clearAllButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("clear_all"))));
		clearAllButton.click();
		System.out.println("Clicked on 'Clear All' to remove the applied filters");
		Thread.sleep(1000);

		WebElement sortByfilter = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("sortBy_filter"))));
		sortByfilter.click();
		System.out.println("Clicked on Sort By filter");

		WebElement fastestShipping = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("fastest_shipping_filter"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", fastestShipping);
		js.executeScript("arguments[0].click();", fastestShipping);
		System.out.println("Clicked on Fastest Shipping filter");
		Thread.sleep(1000);

		sortByfilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("sortBy_filter"))));
		sortByfilter.click();
		System.out.println("Clicked on Sort By filter");

		WebElement customerRatings = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("cust_ratings_filter"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", customerRatings);
		js.executeScript("arguments[0].click();", customerRatings);
		System.out.println("Clicked on Customer Ratings filter");

	}

	@Test(priority = 5)
	public void addToWishlist() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement addTowishlist = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("add_to_wishlist"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addTowishlist);
		js.executeScript("arguments[0].click();", addTowishlist);
		System.out.println("Clicked on Wishlist Icon");
	}

	@Test(priority = 6)
	public void pagination() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement navigateToNextPage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("sec_page_nav_loc"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", navigateToNextPage);
		js.executeScript("arguments[0].click();", navigateToNextPage);
		System.out.println("Clicked on second page");
		Thread.sleep(2000);
	}

	@Test(priority = 7)
	public void popularSearches() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		By searchBarLoc = By.xpath(loc.getProperty("search_bar"));

		try {
			// Click on the search bar
			WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(searchBarLoc));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", searchBar);
			searchBar.click();
			System.out.println("Clicked on Search Bar");

			// Wait for popular searches to be visible
			WebElement popSearches = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("pop_searches"))));
			System.out.println("Popular Searches Displayed: " + popSearches.getText());

			// Click on the first category
			WebElement popSearchesFirstCat = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pop_searches_first_cat"))));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
					popSearchesFirstCat);
			System.out.println("First Popular Search: " + popSearchesFirstCat.getText());
			popSearchesFirstCat.click();
			System.out.println("Clicked on First Popular Search Category");

			// Navigate back to the homepage
			WebElement pepLogo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
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
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
					popSearchesSecondCat);
			System.out.println("Second Popular Search: " + popSearchesSecondCat.getText());
			popSearchesSecondCat.click();
			System.out.println("Clicked on Second Popular Search Category");

		} catch (Exception e) {
			System.out.println("Exception in popularSearches: " + e.getMessage());
		}
	}

	@Test(priority = 8)
	public void backToHomepage() {
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));

		WebElement pepLogo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
		pepLogo.click();
		System.out.println("Navigated to Homepage successfully");
	}

}
