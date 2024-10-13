package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import java.time.Duration;
import java.util.ArrayList;

import org.testng.annotations.Test;

public class SearchTestProduct extends BaseTest {

	@Test(dependsOnMethods = { "testCase.HomepageTest.testClickingHomepageSections" })
	public void searchProductAfterLogin() throws InterruptedException {
		// Wait for the search bar to be visible after login
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		WebElement searchBar = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_bar"))));

		// Enter "Test SKU ID" in the search bar
		searchBar.sendKeys("SKU: SR1861084-P-WH5254");

		// Click on the search button
		WebElement searchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_button"))));
		searchButton.click();

		// Wait for the search results to load
		WebElement searchResult = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_result"))));
		searchResult.click();

		// Switch to the newly opened tab for the product page
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size() - 1));

	}
}
