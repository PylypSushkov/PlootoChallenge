package StepDefs;

import TestRunner.TestRunner;
import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;
import utils.Spec;

public class FirstStepDef extends TestRunner {

    protected static WebDriver driver;

    @Given("Launch the browser")
    public void launchTheBrowser() {
        driver = new Spec().init().getDriver();
    }

    @When("Hit Google on your browser")
    public void hitGoogleOnYourBrowser() throws InterruptedException {
        driver.get("https://app.plooto.com/qa-test-j1K3eVzQ/login.html");
        Thread.sleep(4000);
    }

    @Then("Enter {string} in the search text box.")
    public void enterInTheSearchTextBox(String arg0) {
        System.out.println("Java 11 first " + arg0);
    }

    @And("Select the first result.")
    public void selectTheFirstResult() {
        driver.quit();
    }

}
