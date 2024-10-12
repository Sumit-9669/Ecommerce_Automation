package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import java.time.Duration;

public class HomepageTest extends BaseTest {

    private WebDriverWait wait;

    @Test(dependsOnMethods = { "testCase.LoginTest.automateLoginSignup" })
    public void heroBannerclick() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Clicking LHS Banner
        clickBannerAndNavigateBack(loc.getProperty("lhs_banner"), "LHS Banner");

        // Clicking RHS Banner
        clickBannerAndNavigateBack(loc.getProperty("rhs_banner"), "RHS Banner");
    }

    /**
     * This utility method clicks on a banner and navigates back to the homepage
     * @param bannerLocator XPath locator for the banner element
     * @param bannerName Descriptive name for logging purposes
     * @throws InterruptedException In case the thread sleep is interrupted
     */
    private void clickBannerAndNavigateBack(String bannerLocator, String bannerName) throws InterruptedException {
        try {
            // Wait for the banner to be clickable
            WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(bannerLocator)));
            banner.click();
            System.out.println("Clicked on " + bannerName + " successfully.");
            
            // Wait for a short period to simulate interaction
            Thread.sleep(3000);
            
            // Navigate back to the homepage
            driver.navigate().back();
            Thread.sleep(3000);  // Wait for the page to reload

        } catch (Exception e) {
            System.err.println("Error while interacting with " + bannerName + ": " + e.getMessage());
        }
    }
}
