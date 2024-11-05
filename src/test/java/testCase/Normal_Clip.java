package testCase;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;

public class Normal_Clip extends BaseTest {

	// Method to hover and click on submenus for any meta menu
	public void hoverAndClickSubMenus(String metaMenuLocator, List<String> submenuLocators)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);

		// Hover on the meta menu and click each submenu
		for (String submenu : submenuLocators) {
			// Hover over the meta menu
			WebElement metaMenu = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty(metaMenuLocator))));
			actions.moveToElement(metaMenu).perform();

			// Click on the submenu item
			WebElement submenuItem = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty(submenu))));
			submenuItem.click();

			// Verify the page title after each click
			String pageTitle = driver.getTitle();
			System.out.println("Page Title for " + submenu + " is " + pageTitle);

			// Optionally wait before moving on to the next submenu item
			Thread.sleep(2000); // Adjust as needed for page load
		}
	}

	@Test
	public void testFurnitureMenu() throws InterruptedException {
		// List of submenu locators under Furniture
		List<String> furnitureSubmenus = List.of("3_seater_sofa", "1_seater_sofa", "2_seater_sofa", "gaming_chair",
				"2_searter_recliners");
		// Call the method for Furniture meta menu
		hoverAndClickSubMenus("Furniture_Meta", furnitureSubmenus);
		System.out.println("Furniture Meta Verified");
	}

	@Test
	public void testHomeDecorMenu() throws InterruptedException {
		// List of submenu locators under Home Decor
		List<String> homeDecorSubmenus = List.of("religious_idols", "vaccum_cleaners", "wall_clocks",
				"desk_pots");
		// Call the method for Home Decor meta menu
		hoverAndClickSubMenus("Home_Decor_Meta", homeDecorSubmenus);
		System.out.println("Home Decor Meta Verified");
	}

	@Test
	public void testMattressesMenu() throws InterruptedException {
		// List of submenu locators under Home Decor
		List<String> mattressesSubmenus = List.of("king_size_mattresses", "spring_single_mattresses",
				"latex_queensize_mattresses");
		// Call the method for Home Decor meta menu
		hoverAndClickSubMenus("Mattresses_Meta", mattressesSubmenus);
		System.out.println("Mattresses Meta Verified");
		Thread.sleep(2000);
	}

}
