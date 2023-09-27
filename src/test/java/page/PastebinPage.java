package page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Actions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
public class PastebinPage extends AbstractPage {
    Actions action = new Actions(driver);
    public String CODE = "code",
            PASTE_EXPIRATION = "paste_expiration",
            PASTE_NAME_TITLE = "paste_name_title",
            SYNTAX_HIGHLIGHTING = "syntax_highlighting";
    private Map<String, String> resultMap = new HashMap<>();

    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private final By agreeButton = By.xpath("//button[@mode='primary']");
    private final By textAreaField = By.xpath("//textarea[@id='postform-text']");
    private final By pasteExpirationField = By.xpath("//span[contains(@id,'expiration-container')]");
    private final By pasteExpirationFieldOptions = By.xpath("//*[@id='select2-postform-expiration-results']/li");
    private final By pasteName_Title = By.xpath("//input[contains(@id,'postform-name')]");
    private final By createNewPasteButton = By.xpath("//button[contains(text(),'Create New Paste')]");
    private final By banner = By.xpath("//*[contains(@class,'vliIgnore')]//vli[contains(@id,'hideSlideBanner')]");
    private final By syntaxHighlightingField = By.xpath("//*[contains(@id,'format-container')]");
    private final By syntaxHighlightingInput = By.xpath("//*[contains(@class,'select2-search__field')]");
    private final By created_pasteName_Title = By.xpath("//div[@class='info-top']/h1");
    private final By created_syntax_highlighting = By.xpath("//div[@class='top-buttons']/div[@class='left']/a[1]");
    private final By created_code = By.xpath("//*[contains(@class,'source')]/ol/li");
    private final By created_errorSummary = By.xpath("//div[@class='error-summary']");

    public PastebinPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PastebinPage openPage() {
        driver.get(HOMEPAGE_URL);
        checkForWeValueYourPrivacy();
        checkForBanner();
        log.info("Page open");
        return this;
    }

    private void checkForWeValueYourPrivacy() {
        try {
            if (action.getWebElement(agreeButton).isDisplayed()) {
                action.clickElement(agreeButton);
                log.info("Pop up message closed");
            }
        } catch (Exception ignored) {

        }
    }
    private void checkForBanner() {
        try {
            if (action.getWebElement(banner).isDisplayed()) {
                log.info("Banner closed");
                action.clickElement(banner);
            }
        } catch (Exception ignored) {
        }
    }

    public PastebinPage enterText(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {
            driver.findElement(textAreaField).sendKeys(text);
            resultMap.put(CODE, "to be updated");
            log.info("Text entered: " + text);
        }
        return this;
    }

    public PastebinPage selectPasteExpiration(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {

            action.clickElement(pasteExpirationField);

            List<WebElement> element = driver.findElements(pasteExpirationFieldOptions);
            for (int i = 0; i < element.size(); i++) {
                String temp = element.get(i).getText();
                if (temp.equals(text)) {
                    log.info("Selected: " + element.get(i).getText());
                    resultMap.put(PASTE_EXPIRATION, element.get(i).getText());
                    element.get(i).click();
                    break;
                }
            }
        }
        return this;
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
        action.clickElement(createNewPasteButton);
        log.info("Create New Paste button clicked ");

        checkForWeValueYourPrivacy();

        if (driver.findElements(created_errorSummary).size()>0) {
            log.error("New Paste Not created");
        }
        return this;
    }

    public PastebinPage selectSyntaxHighlighting(String text) {
        action.clickElement(syntaxHighlightingField);
        action.sendText(syntaxHighlightingInput, text);
        return this;
    }

    public Map<String, String> collectResults(String... elements) {
        for (String element : elements) {
            String text = getTextSwitch(element);
            resultMap.put(element, text);
        }
        return resultMap;
    }

    public String getTextSwitch(String element) {

        switch (element) {
            case "code":
                return action.getTextFromWebElements(created_code);
            case ("paste_name_title"):
                return action.getTextFromWebElement(created_pasteName_Title);
            case ("syntax_highlighting"):
                return action.getTextFromWebElement(created_syntax_highlighting);
            default:
                System.out.println("getSwitch to be updated as element is not yet considered in the function");
        }
        return "n/a";
    }
}
