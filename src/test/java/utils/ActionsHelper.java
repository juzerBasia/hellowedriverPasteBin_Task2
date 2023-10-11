package utils;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static utils.WaitForElement.waitUntilElementIsVisible;

@Slf4j
public class ActionsHelper {

    public void sendText(WebElement element, String text) {
        waitUntilElementIsVisible(element);
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        log.info("Text sent: " + text);
    }

    public void clickElement(WebElement element) {
        for (int i = 0; i <= 2; i++) {
            try {
                waitUntilElementIsVisible(element);
                element.click();
                break;
            } catch (Exception e) {
                log.info("Trying to click element again - wish me luck :)");
            }
        }
    }

}
