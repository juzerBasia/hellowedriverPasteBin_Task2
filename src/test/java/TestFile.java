import org.junit.jupiter.api.Assertions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinPage;

import java.util.Map;


@Slf4j
public class TestFile {
    private WebDriver driver;

    /*    private final String code = "git config --global user.name  New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m Legacy code)\n" +
                "git push origin master --force\n";*/
    private final String code = "xff\n" +
            "ffffdfgrtg\n" +
            "cdfv";
    private final String pasteExpiration = "10 Minutes";
    private final String pasteNameTitle = "how to gain dominance among developers";
    private final String syntaxHighlighting = "Bash";


    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions optionsChrome = new ChromeOptions();
        // optionsChrome.addArguments("incognito");
        //   optionsChrome.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //   optionsChrome.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(optionsChrome);
        driver.manage().window().maximize();

    }

    @Test(description = "create new pastebin ", invocationCount = 5)
    public void testRun() {
        PastebinPage pp = new PastebinPage(driver);

        pp.openPage()
                .enterText(code)
                .selectPasteExpiration(pasteExpiration)
                .selectSyntaxHighlighting(syntaxHighlighting)
                .enterPasteNameTitle(pasteNameTitle)
                .createNewPaste();


        Map<String, String> results = pp.collectResults(pp.SYNTAX_HIGHLIGHTING, pp.PASTE_NAME_TITLE, pp.CODE);
        Assertions.assertEquals(code.replace("\n", ""), results.get(pp.CODE));
        Assertions.assertEquals(syntaxHighlighting, results.get(pp.SYNTAX_HIGHLIGHTING));
        Assertions.assertEquals(pasteNameTitle, results.get(pp.PASTE_NAME_TITLE));
        Assertions.assertEquals(pasteExpiration, results.get(pp.PASTE_EXPIRATION));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}
