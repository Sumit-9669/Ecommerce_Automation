package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.BaseTest;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class ScreenshotUtil {
	public static void takeScreenshot(String stepName) {
		try {
			ByteArrayInputStream screenshot = new ByteArrayInputStream(
					((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BYTES));
			Allure.addAttachment(stepName, screenshot);
		} catch (Exception e) {
			System.out.println("Error capturing screenshot for step: " + stepName);
			e.printStackTrace();
		}
	}
}
