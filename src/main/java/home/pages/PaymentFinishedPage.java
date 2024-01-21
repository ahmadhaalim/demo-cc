package home.pages;

import home.WebMethods;
import org.openqa.selenium.WebDriver;
import static home.locator.FinishPaymentRedirectPageLocator.*;

public class PaymentFinishedPage extends WebMethods {
    public boolean userIsInThePaymentFinishPage(WebDriver driver){
        driver.switchTo().parentFrame();
//        switchIframe(IFRAME_PAYMENT, driver);
        return waitUntilDisplayed(PAYMENT_SUCCESS_MESSAGE, driver,5) &&
                waitUntilDisplayed(AMOUNT, driver,5) &&
                waitUntilDisplayed(RETURN_TO_MERCHANT_BUTTON, driver,5);
    }
}
