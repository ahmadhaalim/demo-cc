package home.locator;

import org.openqa.selenium.By;

public interface IssuingBankPageLocator {
    By OK_BUTTON = By.xpath("//button[@name='ok']");
    By CANCEL_BUTTON = By.xpath("//button[@name='cancel']");
    By RESEND_BUTTON = By.xpath("//button[@name='resend']");
    By AMOUNT_VALUE = By.id("txn_amount");
    By PASSWORD_FIELD = By.id("otp");
}
