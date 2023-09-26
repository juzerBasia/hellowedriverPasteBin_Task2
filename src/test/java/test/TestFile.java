package test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinPage;


@Slf4j
public class TestFile {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("incognito");
        //   optionsChrome.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //     optionsChrome.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(optionsChrome);
        driver.manage().window().maximize();

    }

    @Test(description = "create new pastebin ", invocationCount = 5)
    public void testRun() throws InterruptedException {
        PastebinPage pp = new PastebinPage(driver);
        pp.openPage();
        log.info("Page open");
        pp.enterText("Hello from WebDriver");
        log.info("Text entered");
        pp.selectPasteExpiration("10 Minutes");
        pp.enterPasteNameTitle("helloweb");
        pp.createNewPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() throws InterruptedException {
        driver.quit();
        driver = null;
    }

}
