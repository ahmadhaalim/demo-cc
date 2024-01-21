package home.pages;

import home.WebMethods;
import static home.locator.CreditCardDetailsLocator.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CreditCardDetailsPage extends WebMethods {

    public boolean userIsInTheCreditCardDetailsPage(WebDriver driver){
        return checkIfDisplayed(CREDIT_CARD_INPUT,driver)==checkIfDisplayed(CREDIT_CARD_MODAL,driver);
    }

    public void inputCardNumber(String cardNumber, WebDriver driver){
        typeON(CREDIT_CARD_INPUT,cardNumber,driver);
    }

    public void inputExpiryDt(String expiryDt, WebDriver driver){
        typeON(EXPIRY_DT,expiryDt,driver);
    }

    public void inputCvv(String cvvNumber, WebDriver driver){
        typeON(CVV_INPUT,cvvNumber,driver);
    }

    public int getTransactionAmount(WebDriver driver){
        String trxAmount = getText(TRX_AMOUNT,driver).replaceAll("[^0-9]","");
        return Integer.parseInt(trxAmount);
    }
    public int selectPromo(String promo, WebDriver driver){
        String promoAmount = "0";
        switch (promo) {
            case "flash":
                if (!selectedByUser(RADIO_PROMO_FLASH,driver)){
                clickOn(RADIO_PROMO_FLASH, driver);
                promoAmount = getText(PROMO_FLASH_AMOUNT,driver).replaceAll("[^0-9]","");
                }
                break;
            case "promo":
                if (!selectedByUser(RADIO_PROMO,driver)) {
                    clickOn(RADIO_PROMO, driver);
                    promoAmount = getText(PROMO_TEST_AMOUNT, driver).replaceAll("[^0-9]", "");

                }
                break;
            case "nopromo":
                if (!selectedByUser(RADIO_NO_PROMO,driver)) {
                    jsScrollToView(RADIO_NO_PROMO, driver);
                    clickOn(RADIO_NO_PROMO, driver);
                }
                break;
        }
        return Integer.parseInt(promoAmount);
    }

    public void clickPaymentButton(WebDriver driver){
        clickOn(PAY_BUTTON_CC,driver);

    }
    public boolean payLoadingInfo(WebDriver driver){
        return  waitUntilDisplayed(By.className("centerload"), driver) && waitUntilInvisible(By.className("centerload"), driver);

    }
}
