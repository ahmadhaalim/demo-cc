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

    @TC05
    Scenario Outline: User Details input test for mandatory fields
      Given User click the buy button
      And User is in the payment detail modal
      When User input "<NAME>","<EMAIL>","<PHONUMBER>","<CITY>","<ADDRESS>","<POSTALCODE>" on payment detail modal
      And User click checkout
      Then User get error for not inputting the email
      Examples:
        | NAME | EMAIL | PHONUMBER | CITY | ADDRESS | POSTALCODE |
        |Budi||08112323232|Jakarta| Jl.Jeruk|12201   |