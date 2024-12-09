#Author: Nouha Nouman

Feature: Frontend testing for Ecommerce Fresh.com
  #Description:
  #As a customer, I should be able to add, edit, delete and search for a product

  Background: User is on Ecommerce Fresh landing page
    Given user lands on Ecommerce Fresh landing page

  #TC_EcommerceFresh_01 : Add Product Successfully
  Scenario: Validate adding a product successfully
    Then add product button is present
    When user clicks on add product button
    Then user lands on add product page
    When user uploads product image
    And user enters product title
    And user enters product description
    And user enters product price
    Then Create Product button is enabled
    And user clicks on Create Product button
    Then user lands on the landing page
    And newly added product is present

  #TC_EcommerceFresh_02 : Edit Product Successfully
  Scenario: Validate editing a product successfully
    When user clicks on edit product button
    Then user lands on edit product page
    When user edits product title field
    When user clicks on Save Product button
    Then user lands on the landing page
    And newly edited product is present

  #TC_EcommerceFresh_03 : Search for a Product Successfully
  Scenario Outline: Validate searching for a product successfully
    When user types search "<keyword>"
    Then product with the same "<keyword>" will appear
    Examples:
      | keyword       |
      | iphone        |
      | samsung       |

  #TC_EcommerceFresh_04 : Delete a Product Successfully
  Scenario: Validate deleting a product successfully
    When user deletes a product
    Then product is deleted successfully



