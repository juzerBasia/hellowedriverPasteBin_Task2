package utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AbstractPage;

import java.time.Duration;

import static driver.DriverManager.getDriver;
@Slf4j
public class Waits extends AbstractPage {

    public Waits sendText(WebElement webElement, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        log.info("Text sent: "+text);
        return this;
    }
    public WebElement getWebElementByLocator(By by){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS));
        for(int i=0; i<= 2; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
              break;
            } catch (Exception e) {
             log.info("Trying to click element again - wish me luck :)");
            }
        }
    }
    public String getTextFromWebElement(By by){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element.getText();
    }
    @Override
    protected AbstractPage openPage() {
        log.error("You are sure you want to open Utils page");
        return null;
    }


}
