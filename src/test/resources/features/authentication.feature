Feature: User Authentication
  This feature covers the authentication process for users.

  Background:
    Given user is on sauceDemo page

  Rule: Valid login
  This rule covers scenarios where the user successfully logs in to the application.

    @ValidLogin
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

    @InvalidLogin
    Scenario: Login to sauceDemo page with invalid credentials
      When user enters invalid credentials
        | username      | password     |
        | standarduser  | secret_sauce |
        | standarduser  | secretsauce  |
        | standard_user | secretsauce  |
      And user clicks on the login button
      Then the error message should be displayed
        | error                                                                     |
        | Epic sadface: Username and password do not match any user in this service |


  Rule:Locked out user
  This rule covers scenarios where the locked out user fails to log in to the application.

    @LockedUser
    Scenario: Locked out user attempts to login with correct credentials
      When locked user enters correct credentials
        | username         | password     |
        |  locked_out_user | secret_sauce |
      And user clicks on the login button
      Then the error message should be displayed
        | error                                                                     |
        | Epic sadface: Sorry, this user has been locked out. |



