package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static driver.DriverManager.closeDriver;
import static driver.DriverManager.getDriver;


public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        closeDriver();
    }
}
