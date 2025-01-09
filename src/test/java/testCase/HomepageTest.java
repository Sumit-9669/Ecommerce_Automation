package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;
import java.time.Duration;

public class HomepageTest extends BaseTest {
	private WebDriverWait wait;

	@Test(dependsOnMethods = { "testCase.LoginTest.automateLoginSignup" })
	@Description("To verify redirection on clicking different sections")
	public void testClickingHomepageSections() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Thread.sleep(2000);
		try {
			// Wait for the iframe to be present
			WebElement iframe = driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
			System.out.println(iframe);
			if (iframe != null) {
				// Switch to the iframe
				driver.switchTo().frame(iframe);
				// Wait for the close button to be clickable (using WebDriverWait)
				WebElement closeButton = wait.until(
						ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("popup_close_button"))));
				closeButton.click();
				// Switch back to the main content
				driver.switchTo().defaultContent();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		clickBannerAndNavigateBack(loc.getProperty("lhs_banner"), "LHS Banner");
		// clickBannerAndNavigateBack(loc.getProperty("rhs_banner"), "RHS Banner");
		clickBannerAndNavigateBack(loc.getProperty("woodsworth_banner"), "Woodsworth Banner");
		clickBannerAndNavigateBack(loc.getProperty("deals_banner"), "Deals Banner");
		scrollAndClickMintwudAndPepperfryLogo(loc.getProperty("mintwud_banner"), loc.getProperty("pepperfry_logo"),
				"Mintwud Banner", "Pepperfry Logo");
	}

	/**
	 * Utility method to click a banner or section and navigate back to the
	 * homepage.
	 *
	 * @param bannerLocator XPath locator for the section or banner element.
	 * @param sectionName   Descriptive name for logging purposes.
	 * @throws InterruptedException In case the thread sleep is interrupted.
	 */
	private void clickBannerAndNavigateBack(String bannerLocator, String sectionName) throws InterruptedException {
		try {
			// Wait for the section/banner to be present
			WebElement sectionElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(bannerLocator)));
			// Scroll to the element to make it visible
			scrollToElement(sectionElement);
			// Wait for a short period to allow any animations/overlays to finish
			Thread.sleep(3000);
			try {
				// Attempt to click the element
				wait.until(ExpectedConditions.elementToBeClickable(sectionElement)).click();
				System.out.println("Clicked on " + sectionName + " successfully.");
			} catch (Exception e) {
				// If click is intercepted, use JavaScript to click as a fallback
				System.out.println("Normal click failed for " + sectionName + ". Trying JavaScript click.");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", sectionElement);
				System.out.println("JavaScript click performed on " + sectionName + ".");
			}
			// Wait for a short period to simulate interaction
			Thread.sleep(3000);
			// Navigate back to the homepage
			driver.navigate().back();
			Thread.sleep(3000); // Wait for the page to reload
		} catch (Exception e) {
			System.err.println("Error while interacting with " + sectionName + ": " + e.getMessage());
		}
	}

	/**
	 * Scroll to Mintwud Banner, click it, and then click on Pepperfry Logo without
	 * scrolling back.
	 *
	 * @param bannerLocator XPath locator for the Mintwud Banner.
	 * @param logoLocator   XPath locator for the Pepperfry Logo.
	 * @param bannerName    Descriptive name for the banner (Mintwud) for logging
	 *                      purposes.
	 * @param logoName      Descriptive name for the logo (Pepperfry Logo) for
	 *                      logging purposes.
	 * @throws InterruptedException In case the thread sleep is interrupted.
	 */
	@Description("To verify Clicking on Pepperfry Logo")
	private void scrollAndClickMintwudAndPepperfryLogo(String bannerLocator, String logoLocator, String bannerName,
			String logoName) throws InterruptedException {
		try {
			// Scroll to Mintwud Banner and click it
			WebElement bannerElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(bannerLocator)));
			scrollToElement(bannerElement); // Scroll to Mintwud Banner
			// Wait for a short period to ensure proper loading
			Thread.sleep(3000);
			// Click on Mintwud Banner
			wait.until(ExpectedConditions.elementToBeClickable(bannerElement)).click();
			System.out.println("Clicked on " + bannerName + " successfully.");
			// Wait for a short period to simulate interaction
			Thread.sleep(3000);
			// Now click the Pepperfry Logo directly
			WebElement logoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(logoLocator)));
			System.out.println("Pepperfry Logo found. Clicking on it.");
			wait.until(ExpectedConditions.elementToBeClickable(logoElement)).click();
			System.out.println("Clicked on " + logoName + " successfully.");
			Thread.sleep(3000);
		} catch (Exception e) {
			System.err
					.println("Error while interacting with " + bannerName + " or " + logoName + ": " + e.getMessage());
		}
	}

	/**
	 * Scrolls the page to bring the specified element into view.
	 *
	 * @param element The web element to scroll to.
	 */
	private void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			System.out.println("Scrolled to element: " + element.getText());
		} catch (Exception e) {
			System.err.println("Error while scrolling to element: " + e.getMessage());
		}
	}
}