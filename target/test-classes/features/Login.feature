Feature: Login in to Plooto portal

  Background: To Launch the browser
    Given Launch the browser Login

  @SmokeW
  Scenario: Login page. Allows user to authenticate.
  "Sign In" button will navigate user to Company Selection screen
    When Open Login page on your browser "https://app.plooto.com/qa-test-j1K3eVzQ/login.html"
    Then Enter email "testuser@plooto.com" in the email text box
    Then Enter password "Asdfgh@1234" in the password text box
    Then Click "Sign In" button
    And Company Selection page "/company_select.html" should open.