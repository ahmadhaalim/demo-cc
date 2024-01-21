package home.pages;

import home.WebMethods;
import static home.locator.PaymentMethodLocator.*;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage extends WebMethods {
    public boolean userIsInThePaymentMethodPage (WebDriver driver){
        switchIframe(IFRAME_PAYMENT,driver);
        boolean cctab = checkIfDisplayed(CREDIT_CARD_TAB, driver);
        boolean amount = checkIfDisplayed(TRX_AMOUNT, driver);
        return cctab == amount;
    }

    public void clickCreditCardTab (WebDriver driver){
        clickOn(CREDIT_CARD_TAB, driver);
    }
}