package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import io.qameta.allure.Description;
import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class SearchTestProduct extends BaseTest {

	@Test(dependsOnMethods = { "testCase.HomepageTest.testClickingHomepageSections" })
	@Description("To verify the Search functionality with different search queries followed by a Test SKU search")
	public void searchProductAfterLogin() throws InterruptedException {
		System.out.println("INSIDE 'searchProductAfterLogin' METHOD ");

		String[] keywords = { "3 seater sofa", "queen size mattresses", "wing chair", "Bar Stools & Chairs" };

		for (String keyword : keywords) {
			try {
				// Wait for the search bar to be clickable and enter the keyword
				System.out.println("Attempting to search for keyword: " + keyword);
				WebElement searchBar = fluentWait(By.xpath(loc.getProperty("search_bar"))); // Fetch search bar again
																							// after each navigation
				searchBar.clear(); // Clear the search bar before entering the next keyword
				searchBar.sendKeys(keyword); // Enter the keyword

				// Click on the search button using JavaScript in case of overlap issues
				WebElement searchButton = fluentWait(By.xpath(loc.getProperty("search_button"))); // Fetch the search
																									// button each time
				clickElementUsingJS(searchButton); // Using JS click to avoid element clickability issues
				System.out.println("Clicked search for keyword: " + keyword);

				Thread.sleep(2000);

				// Navigate back to the previous page (homepage)
				driver.navigate().back();
				System.out.println("Navigated back to the homepage.");

				// FluentWait after navigating back to the homepage before starting the next
				// search
				fluentWait(By.xpath(loc.getProperty("search_bar")));

			} catch (Exception e) {
				System.out.println("Error searching for keyword: " + keyword + ". Skipping to the next.");
				e.printStackTrace();
			}
		}

		try {
			WebElement pepperfryLogo = fluentWait(By.xpath(loc.getProperty("pepperfry_logo")));
			clickElementUsingJS(pepperfryLogo);
			System.out.println("Clicked on Pepperfry logo to go back to homepage.");
		} catch (Exception e) {
			System.out.println("Error clicking on Pepperfry logo.");
			e.printStackTrace();
		}
		// Now search for the specific SKU
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased wait time
			WebElement searchBar = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_bar"))));

			searchBar.clear();
			searchBar.sendKeys("SKU: SR1861084-P-WH5254");

			WebElement searchButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_button"))));
			clickElementUsingJS(searchButton); // Use JS click for the SKU search as well

			WebElement searchResult = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("search_result"))));
			searchResult.click();

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size() - 1));

			System.out.println("Specific SKU search completed.");
		} catch (Exception e) {
			System.out.println("Error searching for specific SKU.");
			e.printStackTrace();
		}
	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
	}

	// Helper method to perform a JavaScript click in case of element clickability
	// issues
	public void clickElementUsingJS(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}
}
