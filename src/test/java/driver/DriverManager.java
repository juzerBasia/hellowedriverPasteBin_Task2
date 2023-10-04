package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
        private static WebDriver driver;

        private DriverManager() {
        }

        public static WebDriver getDriver() {
            if (driver == null) {
                driver = new ChromeDriver();
            }
            return driver;
        }
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
