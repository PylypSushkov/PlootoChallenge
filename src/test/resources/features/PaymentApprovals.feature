Feature: Displays a single payment that still requires user's approval

  Background: To Launch the browser
    Given Launch the browser Payment Approvals

  @SmokeW
  Scenario: Payment Approvals page. Displays a single payment that still requires user's approval.
  No interactive elements
    When Open Payment Approvals page on your browser "https://app.plooto.com/qa-test-j1K3eVzQ/payment_approvals.html"
    Then Single payment 1 only that still requires user's approval in "My Approval" tab
    And All Payments details are displayed "contactName" "date" "amount" and contains more than 2 symbols
    And Finish Payment Approvals test