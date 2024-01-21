package home.locator;
import org.openqa.selenium.By;


public interface MerchantLocator {
    By BUY_NOW_BUTTON = By.xpath("//a[contains(text(),'BUY NOW') and @class='btn buy']");
    By PAYMENT_MODAL = By.xpath("//div[@class='cart-content buying']");
    By PAYMENT_MODAL_NAME = By.xpath("//tbody/tr[1]/td[2]/input[1]");
    By PAYMENT_MODAL_EMAIL = By.xpath("//tbody/tr[2]/td[2]/input[1]");
    By PAYMENT_MODAL_PHONE_NUMBER = By.xpath("//tbody/tr[3]/td[2]/input[1]");
    By PAYMENT_MODAL_CITY = By.xpath("//tbody/tr[4]/td[2]/input[1]");
    By PAYMENT_MODAL_ADDRESS = By.xpath("//textarea");
    By PAYMENT_MODAL_POSTAL_CODE = By.xpath("//tbody/tr[6]/td[2]/input[1]");

    By PAYMENT_MODAL_CHECKOUT_BUTTON = By.xpath("//div[contains(text(),'CHECKOUT') and @class='cart-checkout']");
    By PAYMENT_MODAL_CANCEL_BUTTON = By.xpath("//div[contains(text(),'cancel') and @class='cancel-btn']");
    By TRANS_SUCCESS_NOTIF = By.className("trans-status");
    By ERROR_MERCHANT = By.cssSelector("span[data-reactid='.0.0.0.2.0.1.0.0:0']");
}
