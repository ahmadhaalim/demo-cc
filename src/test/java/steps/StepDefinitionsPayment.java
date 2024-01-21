package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import home.pages.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import home.locator.*;

import static home.webdriver.WebDriverInstance.driver;

public class StepDefinitionsPayment {
    MerchantPage merchantPage = new MerchantPage();
    PaymentMethodPage methodPage = new PaymentMethodPage();
    CreditCardDetailsPage cardDetailsPage = new CreditCardDetailsPage();
    BankJumpPage bankJumpPage = new BankJumpPage();
    BankPage bankPage = new BankPage();
    PaymentFinishedPage finishedPage = new PaymentFinishedPage();
    int trxAmount;
    int trxAmountWithoutDiscount;

    @Given("User already in the merchant page")
    public void userAlreadyInTheMerchantPage() {
        driver.switchTo().defaultContent();
        Assert.assertTrue(merchantPage.openMerchantPage(driver));
    }

    @Given("User click the buy button")
    public void userClickTheBuyButton() {
        merchantPage.clickOnCheckoutButton(driver);
    }

    @And("User is in the payment detail modal")
    public void userIsInThePaymentDetailModal() {
        Assert.assertTrue(merchantPage.userSeePaymentModal(driver));
    }

    @And("User input {string},{string},{string},{string},{string},{string} on payment detail modal")
    public void userInputOnPaymentDetailModal(String name, String email, String phonenumber, String city,
                                              String address, String postalcode) {
        merchantPage.insertDetailsOnPaymentModal(driver,name,email,phonenumber,city,address,postalcode);
    }

    @And("User click checkout")
    public void userClickCheckout() {
        merchantPage.clickOnCheckoutPaymentModalButton(driver);
    }

    @And("User is in the payment method page")
    public void userIsInThePaymentMethodPage() {
        Assert.assertTrue(methodPage.userIsInThePaymentMethodPage(driver, true));

    }

    @And("User click the credit card payment method")
    public void userClickTheCreditCardPaymentMethod() {
        methodPage.clickCreditCardTab(driver);
    }

    @And("User input card Number {string}")
    public void userInputCardNumber(String cardNumber) {
        trxAmount = cardDetailsPage.getTransactionAmount(driver);
        trxAmountWithoutDiscount = trxAmount;
        cardDetailsPage.inputCardNumber(cardNumber,driver);
    }

    @And("User input expiration date {string}")
    public void userInputExpirationDate(String expiryDt ) {
        cardDetailsPage.inputExpiryDt(expiryDt,driver);
    }

    @And("User input cvv {string}")
    public void userInputCvv(String cvvNumber) {
        cardDetailsPage.inputCvv(cvvNumber,driver);

    }

    @And("User click pay now")
    public void userClickPayNow() {
        cardDetailsPage.clickPaymentButton(driver);
        Assert.assertTrue(cardDetailsPage.payLoadingInfo(driver));
    }

    @And("User redirected to the issuing bank page")
    public void userRedirectedToTheIssuingBankPage() {
        Assert.assertTrue(bankJumpPage.userIsInBankJumpPage(driver));
//        bankJumpPage.clickContinueButton(driver);
        Assert.assertTrue(bankPage.userIsInTheIssuingBankPage(driver));
        Assert.assertEquals(trxAmount,bankPage.getTrxAmount(driver)/100);
    }

    @And("User input the OTP {string}")
    public void userInputTheOTP(String otp) {
        bankPage.inputTheOtp(otp,driver);
    }

    @When("User click ok button")
    public void userClickOkButton() {
        bankPage.clickOkButton(driver);
    }

    @Then("User is in the payment success page")
    public void userIsInThePaymentSuccessPage() {
        Assert.assertTrue(finishedPage.userIsInThePaymentFinishPage(driver));
    }

    @And("User will be redirected after five seconds")
    public void userWillBeRedirectedAfterFiveSeconds() {
        driver.switchTo().parentFrame();
        Assert.assertTrue(merchantPage.userIsInTheMerchantPage(driver));
    }

    @And("User is in the credit card details info")
    public void userIsInTheCreditCardDetailsInfo() {
        Assert.assertTrue(cardDetailsPage.userIsInTheCreditCardDetailsPage(driver));
    }

    @And("User select proceed with selected promo {string}")
    public void userSelectProceedWithSelectedPromo(String promo) {
        int promoAmount = cardDetailsPage.selectPromo(promo,driver);
        int trxAmountAfterDiscount = cardDetailsPage.getTransactionAmount(driver);
        Assert.assertEquals(trxAmount-promoAmount,trxAmountAfterDiscount);
        trxAmount = trxAmountAfterDiscount;
    }

    @Then("User get error for not inputting the email")
    public void userGetErrorForNotInputtingTheEmail() {
        Assert.assertEquals("Sorry, something went wrong.",merchantPage.errorStartingPayment(driver));
    }

    @And("User continue payment until payment method page")
    public void userContinuePaymentUntilPaymentMethodPage() {

        merchantPage.insertDetailsOnPaymentModal(driver,"name","halimprabowo@xmail.com","6271222222",
                "city","address","postalcode");
        merchantPage.clickOnCheckoutPaymentModalButton(driver);
        Assert.assertTrue(methodPage.userIsInThePaymentMethodPage(driver,true));
    }

    @And("User input card creds card= {string} cvv {string} expiration date {string}")
    public void userInputCardCredsCardCvvExpirationDate(String card, String cvvNumber, String expiryDt) {
        trxAmount = cardDetailsPage.getTransactionAmount(driver);
        trxAmountWithoutDiscount = trxAmount;
        cardDetailsPage.inputCardNumber(card,driver);
        cardDetailsPage.inputExpiryDt(expiryDt,driver);
        cardDetailsPage.inputCvv(cvvNumber,driver);
    }

    @When("User input the wrong OTP {string}")
    public void userInputTheWrongOTP(String otp) {
        bankPage.inputTheOtp(otp,driver);
    }

    @And("User click back button")
    public void userClickBackButton() {
        cardDetailsPage.waitUntilEnabled(CreditCardDetailsLocator.BACK_BUTTON_ERROR_MODAL,driver);
        cardDetailsPage.clickOn(CreditCardDetailsLocator.BACK_BUTTON_ERROR_MODAL,driver);
    }

    @And("User Redirected to Payment detail Page")
    public void userRedirectedToPaymentDetailPage() {
        driver.switchTo().parentFrame();
        Assert.assertEquals("Payment declined by bank",cardDetailsPage.errorTitle(driver));
        Assert.assertEquals("Please use another card or change payment method. (Error code: )",cardDetailsPage.errorMessage(driver));
    }

    @Then("User back to merchant page with error notif")
    public void userBackToMerchantPageWithErrorNotif() {
        driver.switchTo().defaultContent();
        Assert.assertEquals("Sorry, something went wrong.",merchantPage.errorStartingPayment(driver));
    }

    @When("User click resend button")
    public void userClickResendButton() {
        bankPage.clickOn(IssuingBankPageLocator.RESEND_BUTTON,driver);
    }

    @Then("User still in the issuing bank page")
    public void userStillInTheIssuingBankPage() {
        Assert.assertTrue(bankPage.userIsInTheIssuingBankPage(driver));
        Assert.assertEquals(trxAmount,bankPage.getTrxAmount(driver)/100);
    }

    @When("User click cancel button")
    public void userClickCancelButton() {
        bankPage.clickOn(IssuingBankPageLocator.CANCEL_BUTTON,driver);
    }

    @When("User click close payment method modal")
    public void userClickClosePaymentMethodModal() {
        methodPage.clickOn(PaymentMethodLocator.CLOSE_MODAL_BUTTON,driver);
    }

    @Then("User will see payment method modal closed")
    public void userWillSeePaymentMethodModalClosed() {
        Assert.assertTrue(merchantPage.waitUntilInvisible(MerchantLocator.PAYMENT_MODAL,driver));
    }

    @And("User can click buy now button")
    public void userCanClickBuyNowButton() {
        driver.switchTo().parentFrame();
        Assert.assertTrue(merchantPage.waitUntilEnabled(MerchantLocator.BUY_NOW_BUTTON, driver));
        merchantPage.clickOn(MerchantLocator.BUY_NOW_BUTTON,driver);
        Assert.assertTrue(merchantPage.waitUntilDisplayed(MerchantLocator.PAYMENT_MODAL,driver,10));
    }

    @And("User click proceed payment without quota")
    public void userClickProceedPaymentWithoutQuota() {
        cardDetailsPage.clickOn(CreditCardDetailsLocator.CONFIRM_BUTTON_MODAL,driver);
    }

    @And("User redirected to the issuing bank page without quota")
    public void userRedirectedToTheIssuingBankPageWithoutQuota() {
        Assert.assertTrue(bankJumpPage.userIsInBankJumpPage(driver));
//        bankJumpPage.clickContinueButton(driver);
        Assert.assertTrue(bankPage.userIsInTheIssuingBankPage(driver));
        Assert.assertEquals(trxAmountWithoutDiscount,bankPage.getTrxAmount(driver)/100);
    }

    @When("User click back payment")
    public void userClickBackPayment() {
        cardDetailsPage.clickOn(CreditCardDetailsLocator.BACK_BUTTON_PROMO_USED,driver);
    }

    @When("User click return to merchant button")
    public void userClickReturnToMerchantButton() {
        finishedPage.clickOn(FinishPaymentRedirectPageLocator.RETURN_TO_MERCHANT_BUTTON,driver);
    }

    @Then("User will be back in merchant page with success message")
    public void userWillBeBackInMerchantPageWithSuccessMessage() {
        driver.switchTo().parentFrame();
        Assert.assertTrue(merchantPage.userIsInTheMerchantPage(driver));
    }

    @When("User click cancel button on payment detail modal")
    public void userClickCancelButtonOnPaymentDetailModal() {
        merchantPage.waitUntilDisplayed(MerchantLocator.PAYMENT_MODAL,driver,10);
        merchantPage.clickOn(MerchantLocator.PAYMENT_MODAL_CANCEL_BUTTON,driver);
    }

    @When("User click back button on credit card details page")
    public void userClickBackButtonOnCreditCardDetailsPage() {
        cardDetailsPage.clickOn(CreditCardDetailsLocator.BACK_BUTTON_TO_PAYMENT_METHOD,driver);
    }

    @And("User click yes cancel button")
    public void userClickYesCancelButton() {
        cardDetailsPage.waitUntilEnabled(CreditCardDetailsLocator.BACK_BUTTON_PROMO_USED,driver);
        cardDetailsPage.clickOn(CreditCardDetailsLocator.BACK_BUTTON_PROMO_USED,driver);
    }

    @And("User click back on confirmation modal")
    public void userClickBackOnConfirmationModal() {
        cardDetailsPage.waitUntilEnabled(CreditCardDetailsLocator.CONFIRM_BUTTON_MODAL,driver);
        cardDetailsPage.clickOn(CreditCardDetailsLocator.CONFIRM_BUTTON_MODAL,driver);
    }

    @Then("User is back in the payment method page")
    public void userIsBackInThePaymentMethodPage() {
        Assert.assertTrue(methodPage.userIsInThePaymentMethodPage(driver, false));

    }
}
