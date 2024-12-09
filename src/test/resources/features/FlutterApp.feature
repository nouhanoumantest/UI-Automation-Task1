#Author: Nouha Nouman

Feature: Frontend testing for Flutter Web App
  #Description:
  #As a customer, I should be able to interact with the app

  Background: User is on flutter web app landing page
    Given user lands on flutter web app landing page


  #TC_EcommerceFresh_01 : Elements validations
  Scenario: Validate clicking on the increment button will increase the counter
    When user clicks on increment button
    Then counter increases by one
