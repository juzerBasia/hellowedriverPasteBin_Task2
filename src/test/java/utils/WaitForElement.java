package utils;

import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitForElement {

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementsAreVisible(List<WebElement> elementsList) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elementsList));
    }

}
