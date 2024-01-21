package home.locator;

import org.openqa.selenium.By;

public interface FinishPaymentRedirectPageLocator {
    By PAYMENT_SUCCESS_MESSAGE = By.cssSelector(".text-headline.medium");
    By AMOUNT = By.cssSelector(".text-headline.large");
    By RETURN_TO_MERCHANT_BUTTON = By.cssSelector(".btn.full.primary");
    By IFRAME_PAYMENT = By.id("snap-midtrans");
}
