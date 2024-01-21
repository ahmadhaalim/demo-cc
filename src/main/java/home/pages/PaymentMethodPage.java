package home.pages;

import home.WebMethods;
import static home.locator.PaymentMethodLocator.*;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage extends WebMethods {
    public boolean userIsInThePaymentMethodPage (WebDriver driver, boolean switchIframe){
        boolean cctab;
        boolean amount;
        if (switchIframe){
            switchIframe(IFRAME_PAYMENT, driver);
        }
        cctab = waitUntilDisplayed(CREDIT_CARD_TAB, driver,5);
        amount = waitUntilDisplayed(TRX_AMOUNT, driver,5);
        return cctab == amount;
    }

    public void clickCreditCardTab (WebDriver driver){
        clickOn(CREDIT_CARD_TAB, driver);
    }
}
