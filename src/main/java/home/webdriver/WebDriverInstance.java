package home.webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.Browser;


import java.util.concurrent.TimeUnit;

public class WebDriverInstance {
    public static WebDriver driver;

    public static void initialize(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "disable-extension");
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
//        options.setCapability(CapabilityType.BROWSER_NAME,Browser.CHROME);
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public static void quit(){
        driver.quit();
    }
}
