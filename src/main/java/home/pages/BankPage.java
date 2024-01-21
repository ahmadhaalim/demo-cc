package home.pages;

import home.WebMethods;
import org.openqa.selenium.WebDriver;
import static home.locator.IssuingBankPageLocator.*;

public class BankPage extends WebMethods {
    public boolean userIsInTheIssuingBankPage(WebDriver driver){
        return checkIfDisplayed(OK_BUTTON,driver)
                && checkIfDisplayed(CANCEL_BUTTON,driver)
                && checkIfDisplayed(RESEND_BUTTON, driver)
                && checkIfDisplayed(AMOUNT_VALUE, driver)
                && checkIfDisplayed(PASSWORD_FIELD, driver);
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
