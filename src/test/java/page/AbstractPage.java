package page;

import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected final int WAIT_TIME_SECONDS = 10;
    protected abstract AbstractPage openPage() throws InterruptedException;

    protected AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}