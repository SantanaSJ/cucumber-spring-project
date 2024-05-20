Feature: User Authentication
  This feature covers the authentication process for users.

  Rule: Valid login
  This rule covers scenarios where the user successfully logs in to the application.

    Background:
      Given user is on sauceDemo page

    Scenario Outline: Login to sauceDemo page with valid credentials
      When user enters valid username "<username>" and password "<password>"
      And user clicks on the login button
      Then user should be logged in successfully and on "<page>"

      Examples:
        | username      | password     | page     |
        | standard_user | secret_sauce | Products |
        | visual_user   | secret_sauce | Products |

  Rule: Invalid Login
  This rule covers scenarios where the user fails to log in to the application.

    Scenario: Login to sauceDemo page with invalid credentials
      When user enters invalid username and password
        | username     | password     |
        | standarduser | secret_sauce |
      And user clicks on the login button
      Then the error message should be displayed
        | error                                                                     |
        | Epic sadface: Username and password do not match any user in this service |


