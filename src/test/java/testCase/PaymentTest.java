package testCase;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class PaymentTest extends BaseTest {
    @Test
    public void paymentOptions() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Scroll and ensure 'Gift Card' element is visible and clickable
        WebElement giftCard = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(loc.getProperty("gift_card"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCard);
       
        js.executeScript("arguments[0].click();", giftCard);
        
        //Gift Card Number input field
        WebElement giftCardNumber = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gift_card_number"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCardNumber);
        js.executeScript("arguments[0].click();", giftCardNumber);
        giftCardNumber.sendKeys(prop.getProperty("gc_number"));

        //Gift Card PIN input field
        WebElement giftCardPin = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gift_card_pin"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", giftCardPin);
        js.executeScript("arguments[0].click();", giftCardPin);
        giftCardPin.sendKeys(prop.getProperty("gc_pin"));
                
        //Click on Verify button to check the entered credentials are correct or not
        WebElement gcVerifyButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("gc_verify_button"))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", gcVerifyButton);
        js.executeScript("arguments[0].click();", gcVerifyButton);
        //gcVerifyButton.click();
        System.out.println("GC verified sussessfully");
        Thread.sleep(2000);
        
        WebElement placeOrder = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("place_order"))));
        placeOrder.click();
        System.out.println("Clicked on Pay Now sussessfully");
        
        Thread.sleep(10000);
        
    }
}
