package home.pages;

import org.openqa.selenium.WebDriver;
import home.WebMethods;
import static home.locator.MerchantLocator.*;


public class MerchantPage extends WebMethods {
    public boolean openMerchantPage(WebDriver driver) {
        driver.get("https://demo.midtrans.com/");
        return waitUntilDisplayed(BUY_NOW_BUTTON, driver,10);
    }

    public void clickOnCheckoutButton (WebDriver driver){
        clickOn(BUY_NOW_BUTTON, driver);
    }

    public boolean userSeePaymentModal(WebDriver driver){
        return waitUntilDisplayed(PAYMENT_MODAL,driver,10);
    }
    public void insertDetailsOnPaymentModal (WebDriver driver, String name, String email, String phonenumber, String city, String address, String postalcode){
        typeON(PAYMENT_MODAL_NAME,name,driver);
        typeON(PAYMENT_MODAL_EMAIL,email,driver);
        typeON(PAYMENT_MODAL_PHONE_NUMBER,phonenumber,driver);
        typeON(PAYMENT_MODAL_CITY,city,driver);
        typeON(PAYMENT_MODAL_ADDRESS,address,driver);
        typeON(PAYMENT_MODAL_POSTAL_CODE,postalcode,driver);
    }

    public boolean userIsInTheMerchantPage(WebDriver driver){
        return waitUntilDisplayed(BUY_NOW_BUTTON,driver,8) &&
                waitUntilDisplayed(TRANS_SUCCESS_NOTIF,driver,8);
    }

    public String errorStartingPayment(WebDriver driver){
        return getText(ERROR_MERCHANT,driver);
    }

    public void clickOnCheckoutPaymentModalButton (WebDriver driver){
        clickOn(PAYMENT_MODAL_CHECKOUT_BUTTON, driver);
    }

}
