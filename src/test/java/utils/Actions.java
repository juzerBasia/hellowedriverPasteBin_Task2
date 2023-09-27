package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AbstractPage;

import java.time.Duration;
import java.util.List;

public class Actions extends AbstractPage {

    public Actions(WebDriver driver) {
        super(driver);
    }

    public Actions sendText(By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        return this;
    }
    public WebElement getWebElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void clickElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }
    public String getTextFromWebElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element.getText();
    }
    @Override
    protected AbstractPage openPage() throws InterruptedException {
        System.out.println("You are sure you want to open Utils page ");
        return null;
    }

    public String getTextFromWebElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        StringBuilder sb = new StringBuilder();
        for (WebElement we : elements) {
            sb.append(we.getText());
        }
        return sb.toString();
    }
}
