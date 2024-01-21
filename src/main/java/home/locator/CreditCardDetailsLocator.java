package home.locator;

import org.openqa.selenium.By;

public interface CreditCardDetailsLocator{
    By CREDIT_CARD_MODAL = By.className("credit-card__content");
    By CREDIT_CARD_INPUT = By.xpath("//div[@class=\"card-number-input-container\"]/input");
    By CVV_INPUT = By.id("card-cvv");
    By EXPIRY_DT = By.id("card-expiry");
    By RADIO_PROMO_FLASH = By.xpath("//label[@for='690']");
    By RADIO_PROMO = By.xpath("//label[@for='628']");
    By RADIO_NO_PROMO = By.xpath("//label[@for='no-promo']");
    By PROMO_FLASH_AMOUNT = By.xpath("//body/div[@id='app']/div[@id='application']" +
            "/div[@class='page-container scroll']/div[@class='credit-card__wrapper']" +
            "/div[@class='credit-card__content']/div[@class='promo-block']/div[2]/span[1]");
    By PROMO_TEST_AMOUNT = By.xpath("//body/div[@id='app']/div[@id='application']/div[@class='page-container scroll']" +
            "/div[@class='credit-card__wrapper']/div[@class='credit-card__content']" +
            "/div[@class='promo-block']/div[3]/span[1]");
    By TRX_AMOUNT = By.xpath("//div[@class='header-amount']");
    By PAY_BUTTON_CC = By.className("card-pay-button-part");
}
