package page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waits;

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
    private WebElement pasteName_Title;
    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement createNewPasteButton;
    @FindBy(xpath = "//*[contains(@class,'vliIgnore')]//vli[contains(@id,'hideSlideBanner')]")
    private WebElement banner;
    @FindBy(xpath = "//*[contains(@id,'format-container')]")
    private WebElement syntaxHighlightingField;
    @FindBy(xpath = "//*[contains(@class,'select2-search__field')]")
    private WebElement syntaxHighlightingInput;
    @FindBy(xpath = "//div[@class='error-summary']")
    private List<WebElement> created_errorSummary;

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement created_pasteName_Title;

    @FindBy(xpath = "//div[@class='top-buttons']/div[@class='left']/a[1]")
    private WebElement created_syntax_highlighting;

    private final By created_code = By.xpath("//*[contains(@class,'source')]/ol/li");


    Waits wait = new Waits();

    public static final String HOMEPAGE_URL = "https://pastebin.com/";

    public PastebinPage() {
        super();
    }

    @Override
    public PastebinPage openPage() {
        getDriver().get(HOMEPAGE_URL);
        log.info("Page open");
        wait.clickElement(agreeButton);
        wait.clickElement(banner);
        return this;
    }

    private void checkForWeValueYourPrivacy() {
        try {
            wait.clickElement(agreeButton);
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
            wait.clickElement(pasteExpirationField);
            List<WebElement> element = pasteExpirationFieldOptions;
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

    public PastebinPage enterPasteNameTitle(String text) {
        if (Objects.equals(text, "")) {
            log.error("No text provided");
        } else {
            pasteName_Title.sendKeys(text);
            log.info("Paste Name/Title entered: " + text);

        }
        return this;
    }

    public PastebinPage createNewPaste() {
        wait.clickElement(createNewPasteButton);
        log.info("Create New Paste button clicked ");
        checkForWeValueYourPrivacy();
        if (created_errorSummary.size() > 0) {
            log.error("New Paste Not created");
        }
        return this;
    }

    public PastebinPage selectSyntaxHighlighting(String text) {
        wait.clickElement(syntaxHighlightingField);
        wait.sendText(syntaxHighlightingInput, text);
        log.info("Syntax Highlighting data entered: "+text);
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

    public WebElement getCreated_pasteName_Title() {
        return created_pasteName_Title;
    }

    public WebElement getCreated_syntax_highlighting() {
        return created_syntax_highlighting;
    }

    public By getCreated_code() {
        return created_code;
    }
}
