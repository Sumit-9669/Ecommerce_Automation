package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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
			driver.get(prop.getProperty("testurl"));
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(prop.getProperty("testurl"));
		}
		driver.manage().window().maximize();

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit(); // Quit all browser sessions.
			System.out.println("Teardown Successful");
		}

	}

}
