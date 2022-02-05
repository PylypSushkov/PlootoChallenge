package StepDefs;

import TestRunner.TestRunner;
import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Spec;

import java.util.List;

public class DeashboardStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();
    private static Actions action;
    private static WebElement elPaymentApprovalBtn;

    @Given("Launch the browser Dashboard")
    public void launchTheBrowserDashboard() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Company Dashboard page on your browser {string}")
    public void openCompanyDashboardPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }

    @Then("Displays current status {string} of the company verification {string}")
    public void displaysCurrentStatusOfTheCompanyVerification(String arg0, String arg1) {
        By byH3lbl = By.xpath("//h3/a[contains(text(), '"+arg1+"')]");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byH3lbl));
            softas.assertTrue(driver.findElement(byH3lbl).getText().contains(arg0),
                    "The company verification status should contain: " + arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Verify actionable items")
    public void verifyActionableItems(List<String> data) {
        By byOnClickUrl = By.xpath("//a[contains(@onclick, 'location.href')]");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byOnClickUrl));
            List<WebElement> elsOnClickUrl = driver.findElements(byOnClickUrl);
            for(int i=0; i<elsOnClickUrl.size(); i++) {
                // Verify actionable items - should be active and contain the specific text
                softas.assertTrue(elsOnClickUrl.get(i).getText().contains(data.get(i)),
                        "The active element should contain the text: " + data.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("{string} is hovered by mouse")
    public void paymentApprovalsIsHoveredByMouse(String arg0) {
        By byPaymentApprovalLi = By.xpath("//li[contains(@data-bind, '"+arg0+"')]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byPaymentApprovalLi));
            elPaymentApprovalBtn = driver.findElement(byPaymentApprovalLi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Creating object of an Actions class
        action = new Actions(driver);

        // Performing the mouse hover action on the target element.
        action.moveToElement(elPaymentApprovalBtn).perform();
    }

    @Then("Verify the item {string} is highlighted by colour {string}")
    public void verifyTheItemIsHighlightedByColour(String arg0, String arg1) throws InterruptedException {
        String bckgclr = elPaymentApprovalBtn.getCssValue("background-color");

        // Verify the item is highlighted by background colour
        softas.assertTrue(bckgclr.equals(arg1), "The highlighted background colour of "+arg0+"should be " + arg1);
    }

    @When("Click the menu item {string}")
    public void clickTheMenuItem(String arg0) {
        By byPaymentApprovalBtn = By.xpath("//a[contains(text(), '"+arg0+"')]");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byPaymentApprovalBtn));
            driver.findElement(byPaymentApprovalBtn).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("{string} page opens")
    public void pageOpens(String arg0) {
        By byPaymentApprovalH3 = By.xpath("//h3[(text()='"+arg0+"')]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byPaymentApprovalH3));
            // Verify the page contains H# with the text Payment approvals
            softas.assertTrue(driver.findElement(byPaymentApprovalH3).getText().equals(arg0),
                    "The page contains H# with the text "+arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Finish Dashboard test")
    public void finishDashboardTest() {
        driver.quit();
        softas.assertAll();
    }
}
