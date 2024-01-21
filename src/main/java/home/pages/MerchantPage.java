package home.pages;

import org.openqa.selenium.WebDriver;
import home.WebMethods;
import static home.locator.MerchantLocator.*;


public class MerchantPage extends WebMethods {
    public boolean openMerchantPage(WebDriver driver) {
        driver.get("https://demo.midtrans.com/");
        return checkIfDisplayed(BUY_NOW_BUTTON, driver);
    }

    public void clickOnCheckoutButton (WebDriver driver){
        clickOn(BUY_NOW_BUTTON, driver);
    }

    public boolean userSeePaymentModal(WebDriver driver){
        return checkIfDisplayed(PAYMENT_MODAL,driver);
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
        return waitUntilDisplayed(BUY_NOW_BUTTON,driver) &&
                waitUntilDisplayed(TRANS_SUCCESS_NOTIF,driver);
    }

    public void clickOnCheckoutPaymentModalButton (WebDriver driver){
        clickOn(PAYMENT_MODAL_CHECKOUT_BUTTON, driver);
    }

    public void clickOnCancelPaymentModalButton (WebDriver driver){
        clickOn(PAYMENT_MODAL_CANCEL_BUTTON, driver);
    }
}
