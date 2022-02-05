package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Spec {

    protected WebDriver connection;

    public Spec init() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("--start-fullscreen");
        connection = new ChromeDriver(options);
        connection.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return this;
    }

    public WebDriver getDriver() {
        return this.connection;
    }
}
