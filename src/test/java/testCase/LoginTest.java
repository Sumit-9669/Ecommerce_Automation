package testCase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import io.qameta.allure.Description;
import utilities.EmailOTPReader;
import utilities.ScreenshotUtil;

public class LoginTest extends BaseTest {

	@Test(priority = 1)
	public void InvalidLoginSignup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		waitForPageToLoad();
		handlePopupIfPresent();

		WebElement signUpNow = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("signup_icon"))));
		Actions actions = new Actions(driver);
		actions.moveToElement(signUpNow).perform();

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("login_button"))));
		loginButton.click();

		String[] invalidInputs = { "12345", "abcdefghij", "999999999999", "invalidemail@", "test@invaliddomain" };

		for (String input : invalidInputs) {
			try {
				WebElement emailInput = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("email_field"))));
				emailInput.clear();
				emailInput.sendKeys(input);

				WebElement continueButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("continue_button"))));
				continueButton.click();

				WebElement errorMessage = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("error_message"))));
				System.out.println("For input '" + input + "', error message: " + errorMessage.getText());

				Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for input: " + input);
				waitForShortInterval();

			} catch (Exception e) {
				System.out.println("Error encountered with input '" + input + "'.");
				e.printStackTrace();
			}
		}
		ScreenshotUtil.takeScreenshot("Attempting to login with Invalid Credentials");
	}

	@Test(priority = 2)
	@Description("To verify the Login functionality using valid Email")
	public void automateLoginSignup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();

		WebElement signUpNow = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("signup_icon"))));
		Actions actions = new Actions(driver);
		actions.moveToElement(signUpNow).perform();

		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("login_button"))));
		loginButton.click();

		WebElement emailInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("email_field"))));
		emailInput.sendKeys("sumit.p@pepperfry.com");

		WebElement continueButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("continue_button"))));
		continueButton.click();
		waitForShortInterval();

		String otp = EmailOTPReader.getOTPFromEmail("imap.gmail.com", "imaps", "sumit.p@pepperfry.com",
				"ieqbqpmkwitkbrxc");
		if (otp != null) {
			WebElement otpInput = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("otp_input"))));
			otpInput.sendKeys(otp);

			WebElement submitOTPButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("submit_otp_button"))));
			submitOTPButton.click();
		} else {
			System.out.println("Failed to fetch OTP");
		}

		System.out.println("Login successful");
		ScreenshotUtil.takeScreenshot("Successfully logged in with valid credentials");
	}

	private void waitForPageToLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
						.executeScript("return document.readyState").equals("complete"));
	}

	private void handlePopupIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
					By.id("webklipper-publisher-widget-container-notification-frame")));
			WebElement closeButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("popup_close_button"))));
			closeButton.click();
			driver.switchTo().defaultContent();
			System.out.println("Popup closed.");
		} catch (Exception e) {
			System.out.println("No popup found or already closed.");
		}
	}

	private void waitForShortInterval() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
