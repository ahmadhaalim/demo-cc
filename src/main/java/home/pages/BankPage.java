package home.pages;

import home.WebMethods;
import org.openqa.selenium.WebDriver;
import static home.locator.IssuingBankPageLocator.*;

public class BankPage extends WebMethods {
    public boolean userIsInTheIssuingBankPage(WebDriver driver){
        return waitUntilDisplayed(OK_BUTTON,driver,5)
                && waitUntilDisplayed(CANCEL_BUTTON,driver,5)
                && waitUntilDisplayed(RESEND_BUTTON, driver,5)
                && waitUntilDisplayed(AMOUNT_VALUE, driver,5)
                && waitUntilDisplayed(PASSWORD_FIELD, driver,5);
    }

    public int getTrxAmount(WebDriver driver){
        String trxAmount = getText(AMOUNT_VALUE,driver).replaceAll("[^0-9]","");
        return Integer.parseInt(trxAmount);
    }

    public void inputTheOtp(String otp, WebDriver driver){
        typeON(PASSWORD_FIELD,otp, driver);
    }

    public void clickOkButton (WebDriver driver){
        clickOn(OK_BUTTON,driver);
    }
}
