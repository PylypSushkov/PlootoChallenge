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

import java.util.List;

public class PendingPaymentsStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();

    @Given("Launch the browser Pending Payments")
    public void launchTheBrowserPendingPayments() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Pending Payments page on your browser {string}")
    public void openPendingPaymentsPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }

    @Then("{int} ongoing payments items {string} should be displayed on the page")
    public void ongoingPaymentsItemsShouldBeDisplayedOnThePage(int arg0, String arg1) {
        int count = 0;
        By byDisplayNameLbl = By.xpath("//tbody[contains(@data-bind, 'data:payments')]//span[contains(@data-bind, '"+arg1+"')]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byDisplayNameLbl));
            List<WebElement> elsDisplayNameLbl = driver.findElements(byDisplayNameLbl);
            for (WebElement e : elsDisplayNameLbl) {

                // if Company Name value is displayed then increase the counter
                if (e.isDisplayed()) {
                    count += 1;
                }
            }
            // Validate that the number of displayed companies is 10
            softas.assertTrue(count==arg0, "Company Name item number on the page should be equal to " + arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("Click {string} item in Pending Payments list")
    public void clickItemInPendingPaymentsList(String arg0) {
        By byCavagesLbl = By.xpath("//span[(@title='"+arg0+"')]");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byCavagesLbl));
            driver.findElement(byCavagesLbl).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("Check {string} page opens with {string} label")
    public void checkPageOpensWithLabel(String arg0, String arg1) {
        By bySentPaymentDetailsLbl = By.xpath("//span[contains(text(), '"+arg1+"')]");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(bySentPaymentDetailsLbl));
            // Validate if we open the correct Payment Approval page
            softas.assertTrue(driver.getCurrentUrl().contains(arg0), "Payment Approval page URL should contain '"+arg0+"' value");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Finish Pending Payments test")
    public void finishPendingPaymentsTest() {
        driver.quit();
        softas.assertAll();
    }
}
