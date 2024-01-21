package home.locator;
import org.openqa.selenium.By;

public interface PaymentMethodLocator {
    By CREDIT_CARD_TAB = By.xpath("//a[@href='#/credit-card']");
    By TRX_AMOUNT = By.className("header-amount");
    By IFRAME_PAYMENT = By.id("snap-midtrans");

    By CLOSE_MODAL_BUTTON = By.cssSelector(".close-snap-button.clickable");

}
