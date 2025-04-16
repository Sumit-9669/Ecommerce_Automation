package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader fr1;

	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\config.properties");
			fr1 = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\locators.properties");

			prop.load(fr);
			loc.load(fr1);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("testurl"));

		enforceZoomLevel(80);
	}

	// Utility method to set zoom level and persist it
	public void enforceZoomLevel(int zoomPercentage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Inject JavaScript to enforce zoom even after navigation
		String script = "window.addEventListener('load', function() { " + "document.body.style.zoom='" + zoomPercentage
				+ "%'; " + "});";
		js.executeScript(script);

		// Also apply immediately after setting up
		js.executeScript("document.body.style.zoom='" + zoomPercentage + "%'");

		System.out.println("Zoom level set and locked at " + zoomPercentage + "%");
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Teardown Successful");
		}
	}
}
