package page;

import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected abstract AbstractPage openPage() throws InterruptedException;
    protected final int WAIT_TIME_SECONDS = 10;

    protected AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}