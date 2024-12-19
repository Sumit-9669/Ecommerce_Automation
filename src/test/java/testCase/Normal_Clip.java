package testCase;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;
import utilities.ScreenshotUtil;

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
			Thread.sleep(2000);

		}
	}

	@Test
	@Description("Verifying Furniture Meta")
	public void testFurnitureMenu() throws InterruptedException {
		// List of submenu locators under Furniture
		List<String> furnitureSubmenus = List.of("3_seater_sofa", "1_seater_sofa", "2_seater_sofa", "gaming_chair",
				"2_searter_recliners");
		// Call the method for Furniture meta menu
		hoverAndClickSubMenus("Furniture_Meta", furnitureSubmenus);
		System.out.println("Furniture Meta Verified");
		ScreenshotUtil.takeScreenshot("Successfully verified Furniture Meta");
	}

	@Test
	@Description("Verifying Home Decor Meta.")
	public void testHomeDecorMenu() throws InterruptedException {
		// List of submenu locators under Home Decor
		List<String> homeDecorSubmenus = List.of("religious_idols", "vaccum_cleaners", "wall_clocks", "desk_pots");
		// Call the method for Home Decor meta menu
		hoverAndClickSubMenus("Home_Decor_Meta", homeDecorSubmenus);
		System.out.println("Home Decor Meta Verified");
		ScreenshotUtil.takeScreenshot("Successfully verified Home Decor Meta");
	}

	@Test
	@Description("Verifying Mattresses Meta")
	public void testMattressesMenu() throws InterruptedException {
		// List of submenu locators under Home Decor
		List<String> mattressesSubmenus = List.of("king_size_mattresses", "spring_single_mattresses",
				"latex_queensize_mattresses");
		// Call the method for Home Decor meta menu
		hoverAndClickSubMenus("Mattresses_Meta", mattressesSubmenus);
		System.out.println("Mattresses Meta Verified");
		ScreenshotUtil.takeScreenshot("Successfully verified Mattresses Meta");
		Thread.sleep(2000);
	}
	
	@Test 
	@Description("Verify filters for Furniture Meta")
	public void verifyFurnitureFilters() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement threeseatersofa = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath(loc.getProperty("Furniture_Meta"))));        
        actions.moveToElement(threeseatersofa).perform();
        
        WebElement drawerOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("3_seater_sofa"))));
        drawerOption.click();
        
        WebElement moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
        Thread.sleep(1000);
        
        WebElement filterPanel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("brand_filter"))));
        filterPanel.click();  // Click to expand the accordion
        System.out.println("Clicked on the first accordion item.");

        // Wait for the checkbox to appear in the expanded accordion
        WebElement brandCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("casacraft_brand"))));
        brandCheckbox.click();
        System.out.println("Clicked on casacraft brand");
		Thread.sleep(1000);
		
		WebElement applyFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		System.out.println("Clicked on apply filter button");
		ScreenshotUtil.takeScreenshot("Brand Filter verified successfully");
		Thread.sleep(1000);
		
		moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement clearAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("clear_all"))));
		clearAllButton.click();
		System.out.println("Clicked on 'Clear All' to remove the applied filters");
		Thread.sleep(1000);
		
	}
	
	@Test
	@Description("Verify filters for Home Decor Meta")
	public void verifyHomeDecorFilters() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement religiousIdols = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath(loc.getProperty("Home_Decor_Meta"))));        
        actions.moveToElement(religiousIdols).perform();
        
        WebElement drawerOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("religious_idols"))));
        drawerOption.click();
        
        WebElement moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
        Thread.sleep(1000);
        
        WebElement filterPanel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("colour_filter"))));
        filterPanel.click();  // Click to expand the accordion
        
        // Wait for the checkbox to appear in the expanded accordion
        WebElement colourCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("gold_colour"))));
        colourCheckbox.click();
        System.out.println("Clicked on gold colour");
		Thread.sleep(1000);
		
		WebElement applyFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		ScreenshotUtil.takeScreenshot("Color Filter verified successfully");
		System.out.println("Clicked on apply filter button");
		Thread.sleep(1000);
		
		moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement clearAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("clear_all"))));
		clearAllButton.click();
		System.out.println("Clicked on 'Clear All' to remove the applied filters");
		Thread.sleep(1000);
		
	}
	
	@Test 
	@Description("Verify filters for Mattresses Meta")
	public void verifyMattressesFilters() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement springSingleMattresses = wait.until(ExpectedConditions
	            .elementToBeClickable(By.xpath(loc.getProperty("Mattresses_Meta"))));        
		actions.moveToElement(springSingleMattresses).perform();
		
		WebElement drawerOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("spring_single_mattresses"))));
		drawerOption.click();
		
		WebElement moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);
		
		WebElement filterPanel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("size_filter"))));
		filterPanel.click();  // Click to expand the accordion
		
		// Wait for the checkbox to appear in the expanded accordion
		WebElement colourCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("72X36Inch"))));
		colourCheckbox.click();
		System.out.println("Clicked on 72 X 36 Inch Size");
		Thread.sleep(1000);
		
		WebElement applyFilterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("apply_filter"))));
		applyFilterButton.click();
		ScreenshotUtil.takeScreenshot("Size Filter verified successfully");
		System.out.println("Clicked on apply filter button");
		Thread.sleep(1000);
		
		moreFilters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("more_filters"))));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", moreFilters);
		js.executeScript("arguments[0].click();", moreFilters);
		Thread.sleep(1000);

		WebElement clearAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("clear_all"))));
		clearAllButton.click();
		System.out.println("Clicked on 'Clear All' to remove the applied filters");
		Thread.sleep(1000);
	}
	
	/*@Test
	public void goToHome() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement pepperfryLogo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("pepperfry_logo"))));
		pepperfryLogo.click();
		System.out.println("Navigated back to the Homepage Successfully");
	}*/
	
}
