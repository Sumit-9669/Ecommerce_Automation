package testCase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Description;
import utilities.EmailOTPReader;

public class LoginTest extends BaseTest {

	@Test
	@Description("To verify the Login functionality using Email")
	public void automateLoginSignup() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Move to "Sign In/Up Now" element
		WebElement signUpNow = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(loc.getProperty("signup_icon")))); //coming from locators.properties file
		Actions actions = new Actions(driver);
		actions.moveToElement(signUpNow).perform();

		// Click on Login/SignUp
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("login_button")))); //coming from locators.properties file
		loginButton.click();

		// Enter Phone Number or Email
		WebElement emailInput = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(loc.getProperty("email_field")))); //coming from locators.properties file
		emailInput.sendKeys("sumit.p@pepperfry.com");

		// Click on "Continue" button
		WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("continue_button"))));
		continueButton.click();
		Thread.sleep(5000);

		// Call the email reader function to get OTP from inbox
		String otp = EmailOTPReader.getOTPFromEmail("imap.gmail.com", "imaps", "sumit.p@pepperfry.com",
				"ieqbqpmkwitkbrxc");
		System.out.println("OTP fetched: " + otp);
		if (otp != null) {
			// Enter OTP into the field on the webpage
			WebElement otpInput = driver.findElement(By.xpath(loc.getProperty("otp_input")));
			otpInput.sendKeys(otp);

			// Submit the OTP
			WebElement submitOTPButton = driver.findElement(By.xpath(loc.getProperty("submit_otp_button")));
			submitOTPButton.click();
		} else {
			System.out.println("Failed to fetch OTP");
		}

		System.out.println("Login successful");
	}
}