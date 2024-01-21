package home.pages;

import home.WebMethods;
import org.openqa.selenium.WebDriver;
import static home.locator.ThreeDsJumpPageLocator.*;
public class BankJumpPage extends WebMethods {
    public boolean userIsInBankJumpPage(WebDriver driver){
        switchToThreedsIframe(driver);
        return true;
    }

    public void switchToThreedsIframe(WebDriver driver){
        switchIframe(THREEDS_IFRAME, driver);
    }

    public void clickContinueButton (WebDriver driver){
        clickOn(CONTINUE_BUTTON, driver);
    }

}
