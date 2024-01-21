@CCPayment
Feature: Payment with CC
  Background:
    Given User already in the merchant page

  @TC01-03
  Scenario Outline: Normal Payment Happy Flow
    Given User click the buy button
    And User is in the payment detail modal
    And User input "<NAME>","<EMAIL>","<PHONUMBER>","<CITY>","<ADDRESS>","<POSTALCODE>" on payment detail modal
    And User click checkout
    And User is in the payment method page
    And User click the credit card payment method
    And User is in the credit card details info
    And User input card Number "4811111111111114"
    And User input expiration date "0525"
    And User input cvv "123"
    And User select proceed with selected promo "<PROMO>"
    And User click pay now
    And User redirected to the issuing bank page
    And User input the OTP "112233"
    When User click ok button
    Then User is in the payment success page
    And User will be redirected after five seconds
    Examples:
      | NAME | EMAIL | PHONUMBER | CITY | ADDRESS | POSTALCODE | PROMO|
    |Budi  |Budi@xyztail.com|08112323232|Jakarta| Jl.Jeruk|12201|nopromo|
    |Halim |halimprabowo@gmail.com|08126022339|Jakarta|Mangga|12990|flash|
      |Budi  |Budi@xyztail.com|08112323232|Jakarta| Jl.Jeruk|12201|promo|

  @TC04-08
  Scenario Outline: User Details input test for non mandatory fields
    Given User click the buy button
    And User is in the payment detail modal
    When User input "<NAME>","<EMAIL>","<PHONUMBER>","<CITY>","<ADDRESS>","<POSTALCODE>" on payment detail modal
    And User click checkout
    Then User is in the payment method page
    Examples:
      | NAME | EMAIL | PHONUMBER | CITY | ADDRESS | POSTALCODE |
      ||Budi@xyztail.com|08112323232|Jakarta| Jl.Jeruk|12201|
      |Budi  |Budi@xyztail.com||Jakarta|Jl.Jeruk|12201|
      |Budi  |Budi@xyztail.com|08112323232||Jl.Jeruk|12201|
      |Budi  |Budi@xyztail.com|08112323232|Jakarta||12201|
      |Budi  |Budi@xyztail.com|08112323232|Jakarta|Jl.Jeruk||

  @TC09
  Scenario Outline: User Details input test for mandatory fields
    Given User click the buy button
    And User is in the payment detail modal
    When User input "<NAME>","<EMAIL>","<PHONUMBER>","<CITY>","<ADDRESS>","<POSTALCODE>" on payment detail modal
    And User click checkout
    Then User get error for not inputting the email
    Examples:
      | NAME | EMAIL | PHONUMBER | CITY | ADDRESS | POSTALCODE |
      |Budi||08112323232|Jakarta| Jl.Jeruk|12201   |

  @TC010
  Scenario: Payment with invalid OTP
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "nopromo"
    And User click pay now
    And User redirected to the issuing bank page
    When User input the wrong OTP "312323"
    And User click ok button
    And User Redirected to Payment detail Page
    And User click back button
    Then User back to merchant page with error notif

  @TC011
  Scenario: Test payment without inputting OTP
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "nopromo"
    And User click pay now
    And User redirected to the issuing bank page
    When User click ok button
    And User Redirected to Payment detail Page
    And User click back button
    Then User back to merchant page with error notif

  @TC012
  Scenario: Test payment resend otp button in issuing bank page
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "nopromo"
    And User click pay now
    And User redirected to the issuing bank page
    When User click resend button
    Then User still in the issuing bank page

  @TC013
  Scenario: Test payment cancel button in issuing bank page
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "nopromo"
    And User click pay now
    And User redirected to the issuing bank page
    When User click cancel button
    And User Redirected to Payment detail Page
    And User click back button
    Then User back to merchant page with error notif

  @TC014
  Scenario: Close Payment Method Modal
    Given User click the buy button
    And User continue payment until payment method page
    When User click close payment method modal
    Then User will see payment method modal closed
    And User can click buy now button

  @TC015
  Scenario: Payment with promo quota used
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "promo"
    And User click pay now
    And User click proceed payment without quota
    And User redirected to the issuing bank page without quota
    When User input the OTP "112233"
    And User click ok button
    Then User is in the payment success page
    And User will be redirected after five seconds

  @TC016
  Scenario: Payment with promo quota used cancelled
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "promo"
    And User click pay now
    When User click back payment
    Then User is in the credit card details info

  @TC017
  Scenario: Payment success redirect button functionality
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    And User input card creds card= "4811111111111114" cvv "123" expiration date "0525"
    And User select proceed with selected promo "nopromo"
    And User click pay now
    And User redirected to the issuing bank page
    And User input the OTP "112233"
    And User click ok button
    And User is in the payment success page
    When User click return to merchant button
    Then User will be back in merchant page with success message

  @TC018
  Scenario: Cancel on payment modal on info details
    Given User click the buy button
    And User is in the payment detail modal
    When User click cancel button on payment detail modal
    Then User will see payment method modal closed
    And User can click buy now button

  @TC019
  Scenario: Test continuing Back button functionality on card details page
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    When User click back button on credit card details page
    And User click yes cancel button
    Then User is back in the payment method page

  @TC020
  Scenario: Test cancelling on Back button functionality for card details page
    Given User click the buy button
    And User continue payment until payment method page
    And User click the credit card payment method
    When User click back button on credit card details page
    And User click back on confirmation modal
    Then User is in the credit card details info