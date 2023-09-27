package page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;


@Slf4j
public class PastebinPage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private final By agreeButton = By.xpath("//button[@mode='primary']");
    private final By textAreaField = By.xpath("//textarea[@id='postform-text']");
    private final By pasteExpirationField = By.xpath("//span[contains(@id,'expiration-container')]");
    private final By pasteExpirationFieldOptions = By.xpath("//*[@id='select2-postform-expiration-results']/li");
    private final By pasteName_Title = By.xpath("//input[contains(@id,'postform-name')]");
    private final By createNewPasteButton = By.xpath("//button[contains(text(),'Create New Paste')]");
    private final By banner = By.xpath("//*[contains(@class,'vliIgnore')]//vli[contains(@id,'hideSlideBanner')]");

    public PastebinPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PastebinPage openPage() {
        driver.get(HOMEPAGE_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(agreeButton));

        if (popUp.isDisplayed()) {
            clickElement(agreeButton);
            log.info("Pop up message closed");
        }
        clickElement(banner);

        return this;
    }

    public PastebinPage enterText(String text) {
        if (Objects.equals(text, "")) {
           log.error("No text provided");
        } else {
            driver.findElement(textAreaField).sendKeys(text);
            log.info("Text entered: " + text);
        }
        return this;
    }

    public PastebinPage selectPasteExpiration(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {

            clickElement(pasteExpirationField);

            List<WebElement> element = driver.findElements(pasteExpirationFieldOptions);
            for (int i = 0; i < element.size(); i++) {
                String temp = element.get(i).getText();
                if (temp.equals(text)) {
                    log.info("Selected: " + element.get(i).getText());
                    element.get(i).click();
                    break;
                }
            }
        }
        return this;
    }

    private void clickElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public PastebinPage enterPasteNameTitle(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {
            driver.findElement(pasteName_Title).sendKeys(text);
            log.info("Paste Name/Title entered: " + text);
        }
        return this;
    }

    public PastebinPage createNewPaste() {
        clickElement(createNewPasteButton);
        log.info("Create New Paste button clicked ");
        return this;
    }
}
