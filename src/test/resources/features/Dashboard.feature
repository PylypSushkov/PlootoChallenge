Feature: Displays current status of the company verification as well highlights actionable items

  Background: To Launch the browser
    Given Launch the browser Dashboard

  @SmokeW
  Scenario: Dashboard page. Displays current status of the company verification as well highlights actionable items.
  Payment Approvals and Pending Payments are interactive
    When Open Company Dashboard page on your browser "https://app.plooto.com/qa-test-j1K3eVzQ/dashboard.html"
    Then Displays current status "Click here to finish" of the company verification "company verification"
    And Verify actionable items
      | Dashboard | Payment Approvals | Pending Payments |

    When "ApprovePayments" is hovered by mouse
    Then Verify the item "Payment Approvals" is highlighted by colour "rgba(252, 252, 252, 1)"

    When Click the menu item "Payment Approvals"
    Then "Payment approvals" page opens

    When "PendingPayments" is hovered by mouse
    Then Verify the item "Pending Payments" is highlighted by colour "rgba(252, 252, 252, 1)"

    When Click the menu item "Pending Payments"
    Then "Payments currently in transit" page opens

    And Finish Dashboard test