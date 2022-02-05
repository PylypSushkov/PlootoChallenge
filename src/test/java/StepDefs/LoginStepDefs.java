package StepDefs;

import TestRunner.TestRunner;
import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.Spec;

public class LoginStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();

    @Given("Launch the browser Login")
    public void launchTheBrowserLogin() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Login page on your browser {string}")
    public void openLoginPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }

    @Then("Enter email {string} in the email text box")
    public void enterEmailInTheEmailTextBox(String arg0) {
        By byEmailInput = By.id("email");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byEmailInput));
            driver.findElement(byEmailInput).clear();
            driver.findElement(byEmailInput).sendKeys(arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("Enter password {string} in the password text box")
    public void enterPasswordInThePasswordTextBox(String arg0) {
        By byPasswordInput = By.id("passphrase");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(byPasswordInput));
            driver.findElement(byPasswordInput).clear();
            driver.findElement(byPasswordInput).sendKeys(arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("Click {string} button")
    public void clickButton(String arg0) {
        By bySignInBtn = By.xpath("//span[text()='"+arg0+"']");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(bySignInBtn));
            driver.findElement(bySignInBtn).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Company Selection page {string} should open.")
    public void companySelectionPageShouldOpen(String arg0) {
        By byClientsLbl = By.xpath("//span[contains(text(), 'Clients')]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byClientsLbl));
            softas.assertTrue(driver.getCurrentUrl().contains(arg0), "Company Selection page URL should contain '"+arg0+"' value");
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
        softas.assertAll();
    }
}
