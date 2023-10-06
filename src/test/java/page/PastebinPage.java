package page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waits;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //below are used to get text from WebElements -> findElements(By by)
    private final By created_code = By.xpath("//*[contains(@class,'source')]/ol/li");
    private final By created_pasteName_Title = By.xpath("//div[@class='info-top']/h1");
    private final By created_syntax_highlighting = By.xpath("//div[@class='top-buttons']/div[@class='left']/a[1]");

    Waits wait = new Waits();
    private static final Map<String, String> resultMap = new HashMap<>();

    public static final String CODE = "code",
    PASTE_EXPIRATION = "paste_expiration",
    PASTE_NAME_TITLE = "paste_name_title",
    SYNTAX_HIGHLIGHTING = "syntax_highlighting";

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
            resultMap.put(CODE, "to be updated");
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
                return getTextFromWebElements(created_code);
            case ("paste_name_title"):
                return wait.getTextFromWebElement(created_pasteName_Title);
            case ("syntax_highlighting"):
                return wait.getTextFromWebElement(created_syntax_highlighting);
            default:
                System.out.println("getSwitch to be updated as element is not yet considered in the function");
        }
        return "n/a";
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
}
