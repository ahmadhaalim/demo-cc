package home.locator;

import org.openqa.selenium.By;

public interface ThreeDsJumpPageLocator {
    By WORDING_THREEDS = By.xpath("//div[@id='message']");
    By CONTINUE_BUTTON = By.xpath("//button[@id='manualredirect']");
    By RETRY_BUTTON = By.id("retry");
    By THREEDS_IFRAME = By.className("iframe-3ds");
    By VERITRANS = By.id("veritrans");
}
