package home.pages;

import home.WebMethods;
import org.openqa.selenium.WebDriver;
import static home.locator.FinishPaymentRedirectPageLocator.*;

public class PaymentFinishedPage extends WebMethods {
    public boolean userIsInThePaymentFinishPage(WebDriver driver){
        driver.switchTo().parentFrame();
//        switchIframe(IFRAME_PAYMENT, driver);
        return checkIfDisplayed(PAYMENT_SUCCESS_MESSAGE, driver) &&
                checkIfDisplayed(AMOUNT, driver) &&
                checkIfDisplayed(RETURN_TO_MERCHANT_BUTTON, driver);
    }
}
