package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import home.pages.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static home.webdriver.WebDriverInstance.driver;

public class StepDefinitionsPayment {
    MerchantPage merchantPage = new MerchantPage();
    PaymentMethodPage methodPage = new PaymentMethodPage();
    CreditCardDetailsPage cardDetailsPage = new CreditCardDetailsPage();
    BankJumpPage bankJumpPage = new BankJumpPage();
    BankPage bankPage = new BankPage();
    PaymentFinishedPage finishedPage = new PaymentFinishedPage();
    int trxAmount;

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
        Assert.assertTrue(methodPage.userIsInThePaymentMethodPage(driver));

    }

    @And("User click the credit card payment method")
    public void userClickTheCreditCardPaymentMethod() {
        methodPage.clickCreditCardTab(driver);
    }

    @And("User input card Number {string}")
    public void userInputCardNumber(String cardNumber) {
        trxAmount = cardDetailsPage.getTransactionAmount(driver);
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
//        bankJumpPage.switchToThreedsIframe(driver);
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
    public void userSelectProceedWithSelectedPromo(String promo) throws InterruptedException {
        int promoAmount = cardDetailsPage.selectPromo(promo,driver);
        int trxAmountAfterDiscount = cardDetailsPage.getTransactionAmount(driver);
        Assert.assertEquals(trxAmount-promoAmount,trxAmountAfterDiscount);
        trxAmount = trxAmountAfterDiscount;
    }

    @Then("User get error for not inputting the email")
    public void userGetErrorForNotInputtingTheEmail() {

    }
}
