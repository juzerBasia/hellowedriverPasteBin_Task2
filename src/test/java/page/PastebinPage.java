package page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ActionsHelper;


import java.util.List;
import java.util.Objects;

import static driver.DriverManager.getDriver;

@Slf4j
public class PastebinPage extends AbstractPage {

    @FindBy(xpath = "//button[@mode='primary']")
    private WebElement agreeButton;
    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textAreaField;
    @FindBy(xpath = "//span[contains(@id,'expiration-container')]")
    private WebElement pasteExpirationField;
    @FindBy(xpath = "//*[@id='select2-postform-expiration-results']/li")
    private List<WebElement> pasteExpirationFieldOptions;
    @FindBy(xpath = "//input[contains(@id,'postform-name')]")
    private WebElement pasteNameTitle;
    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement createNewPasteButton;
    @FindBy(xpath = "//*[contains(@class,'vliIgnore')]//vli[contains(@id,'hideSlideBanner')]")
    private WebElement banner;
    @FindBy(xpath = "//*[contains(@id,'format-container')]")
    private WebElement syntaxHighlightingField;
    @FindBy(xpath = "//*[contains(@class,'select2-search__field')]")
    private WebElement syntaxHighlightingInput;
    @FindBy(xpath = "//div[@class='error-summary']")
    private List<WebElement> afterCreatingErrorSummary;
    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement afterCreatingPasteNameTitle;
    @FindBy(xpath = "//div[@class='top-buttons']/div[@class='left']/a[1]")
    private WebElement afterCreatingSyntaxHighlighting;
    private final By afterCreatingCode = By.xpath("//*[contains(@class,'source')]/ol/li");

    public static final String homepageURL = "https://pastebin.com/";

    public PastebinPage() {
        super();
    }

    @Override
    public PastebinPage openPage() {
        getDriver().get(homepageURL);
        log.info("Page open");
        ActionsHelper.clickElement(agreeButton);
        ActionsHelper.clickElement(banner);
        return this;
    }

    private void checkForWeValueYourPrivacy() {
        try {
            ActionsHelper.clickElement(agreeButton);
            log.info("Pop up message closed");
        } catch (Exception ignored) {
        }
    }

    public PastebinPage enterText(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {
            textAreaField.sendKeys(text);
            log.info("Text entered: " + text);
        }
        return this;
    }

    public PastebinPage selectPasteExpiration(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {
            ActionsHelper.clickElement(pasteExpirationField);
            List<WebElement> element = pasteExpirationFieldOptions;
            for (WebElement webElement : element) {
                String temp = webElement.getText();
                if (temp.equals(text)) {
                    log.info("Selected: " + webElement.getText());
                    webElement.click();
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
            pasteNameTitle.sendKeys(text);
            log.info("Paste Name/Title entered: " + text);
        }
        return this;
    }

    public void createNewPaste() {
        ActionsHelper.clickElement(createNewPasteButton);
        log.info("Create New Paste button clicked ");
        checkForWeValueYourPrivacy();
        if (afterCreatingErrorSummary.size() > 0) {
            log.error("New Paste Not created");
        }
    }

    public PastebinPage selectSyntaxHighlighting(String text) {
        ActionsHelper.clickElement(syntaxHighlightingField);
        ActionsHelper.sendText(syntaxHighlightingInput, text);
        log.info("Syntax Highlighting data entered: " + text);
        return this;
    }

    public String getTextFromWebElements(By by) {
        List<WebElement> elements = getDriver().findElements(by);
        StringBuilder sb = new StringBuilder();
        for (WebElement we : elements) {
            sb.append(we.getText());
        }
        log.info("Text copied from filed");
        return sb.toString();
    }

    public WebElement getCreatedPasteNameTitle() {
        return afterCreatingPasteNameTitle;
    }

    public WebElement getCreatedSyntaxHighlighting() {
        return afterCreatingSyntaxHighlighting;
    }

    public By getCreatedCode() {
        return afterCreatingCode;
    }
}
