package StepDefs;

import TestRunner.TestRunner;
import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Spec;

public class PaymentApprovalsStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();

    @Given("Launch the browser Payment Approvals")
    public void launchTheBrowserPaymentApprovals() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Payment Approvals page on your browser {string}")
    public void openPaymentApprovalsPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }

    @Then("Single payment {int} only that still requires user's approval in {string} tab")
    public void singlePaymentOnlyThatStillRequiresUserSApprovalInTab(int arg0, String arg1) {
        By byApprovePaymentsTabLbl = By.xpath("//a[contains(text(), '"+arg1+"')]/span");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byApprovePaymentsTabLbl));
            int num = Integer.parseInt(driver.findElement(byApprovePaymentsTabLbl).getText());

            // Verify that only one payment still requires user's approval in the Payments Awaiting My Approval tab
            softas.assertTrue(num == arg0,
                    "Only "+arg0+" payment record should requires user's approval in the Payments Awaiting My Approval tab");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("All Payments details are displayed {string} {string} {string} and contains more than {int} symbols")
    public void allPaymentsDetailsAreDisplayed(String arg0, String arg1, String arg2, int arg3) {
        By byApprovePaymentsTabLbl = By.xpath("//a[contains(text(), 'My Approval')]/span");
        By byPaymentsPendingApprovalTbl = By.xpath("//tbody[contains(@data-bind, 'paymentsPendingApproval')]");
        By byContactLbl = By.xpath("//tbody[contains(@data-bind, 'paymentsPendingApproval')]//span[contains(@data-bind, '"+arg0+"')]");
        By byRequestedDebitDateLbl = By.xpath("//tbody[contains(@data-bind, 'paymentsPendingApproval')]//td[contains(@data-bind, '"+arg1+"')]");
        By byAmountLbl = By.xpath("//tbody[contains(@data-bind, 'paymentsPendingApproval')]//td[contains(@data-bind, '"+arg2+"')]");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byApprovePaymentsTabLbl));
            // Make sure that Payments Awaiting My Approval tab is active
            driver.findElement(byApprovePaymentsTabLbl).click();

            // Check if PaymentsPendingApproval table is displayed
            wait.until(ExpectedConditions.presenceOfElementLocated(byPaymentsPendingApprovalTbl));
            if(driver.findElement(byPaymentsPendingApprovalTbl).isDisplayed()) {

                // Validate all required Payments values are displayed and contains enough symbols
                WebElement elContactLbl = driver.findElement(byContactLbl);
                softas.assertTrue((elContactLbl.isDisplayed()) & (elContactLbl.getText().length()>arg3),
                        arg0+" value should be visible and contains more than "+arg3+" symbols!");

                WebElement elRequestedDebitDateLbl = driver.findElement(byRequestedDebitDateLbl);
                softas.assertTrue((elRequestedDebitDateLbl.isDisplayed()) & (elRequestedDebitDateLbl.getText().length()>arg3),
                        arg1+" value should be visible and contains more than "+arg3+" symbols!");

                WebElement elAmountLbl = driver.findElement(byAmountLbl);
                softas.assertTrue((elAmountLbl.isDisplayed()) & (elAmountLbl.getText().length()>arg3),
                        arg2+" value should be visible and contains more than "+arg3+" symbols!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Finish Payment Approvals test")
    public void finishPaymentApprovalsTest() {
        driver.quit();
        softas.assertAll();
    }
}
