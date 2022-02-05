package StepDefs;

import TestRunner.TestRunner;
import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Spec;

public class PaymentApprovalStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();

    @Given("Launch the browser Payment Approval")
    public void launchTheBrowserPaymentApproval() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Payment Approval page on your browser {string}")
    public void openPaymentApprovalPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }
}
