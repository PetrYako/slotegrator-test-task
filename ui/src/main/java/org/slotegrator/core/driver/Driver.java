package org.slotegrator.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    private static Driver driverClass;
    private static WebDriver driver;

    private Driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void openPage(String url) {
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setUpDriver() {
        if (driver == null) {
            driverClass = new Driver();
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        driverClass = null;
    }
}
