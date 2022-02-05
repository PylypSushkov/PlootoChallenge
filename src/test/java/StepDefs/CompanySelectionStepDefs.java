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

public class CompanySelectionStepDefs extends TestRunner {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SoftAssert softas = new SoftAssert();

    @Given("Launch the browser Company Selection")
    public void launchTheBrowserCompanySelection() {
        driver = new Spec().init().getDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @When("Open Company Selection page on your browser {string}")
    public void openCompanySelectionPageOnYourBrowser(String arg0) {
        driver.get(arg0);
    }

    @Then("Displays list of companies and their status available to the user more than {int} with more {int} symbols")
    public void displaysListOfCompaniesAndTheirStatusAvailableToTheUserMoreThanWithMoreSymbols(int arg0, int arg1) {
        By byCompanyNameLbl = By.xpath("//span[contains(@class, 'company-name')]");
        String sStatusLbl = "//span[contains(text(), \"%s\")]/../following-sibling::td/span[contains(@class, 'text')]";
        int count = 0;

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byCompanyNameLbl));
            List<WebElement> elsCompanyNameLbl = driver.findElements(byCompanyNameLbl);
            for(WebElement e : elsCompanyNameLbl) {

                // if Company Name value is displayed then validate the length
                if (e.isDisplayed()) {
                    softas.assertTrue(e.getText().length()>arg1,
                            "Company Name value should contain more than "+arg1+" symbols");
                    By byStatusLbl = By.xpath(String.format(sStatusLbl, e.getText()));

                    // Validate if Company status is displayed and the length > 2 symbols
                    softas.assertTrue(driver.findElement(byStatusLbl).isDisplayed(),
                            "Company status value should be displayed");
                    softas.assertTrue(driver.findElement(byStatusLbl).getText().length()>arg1,
                            "Company status value should contain more than "+arg1+" symbols");
                    count += 1;
                }
            }

            // Validate that the number of displayed companies more than 40
            softas.assertTrue(count>arg0, "Company Name item number should be more than " + arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("Only {string} company will lead to Dashboard {string}")
    public void onlyCompanyWillLeadToDashboard(String arg0, String arg1) {
        By byOnclickUrl = By.xpath("//tr[contains(@onclick, '"+arg1+"')]//following-sibling::span[contains(@class, 'company-name')]");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(byOnclickUrl));

            // Validate if the found element is "Plooto Inc" company
            softas.assertTrue(driver.findElement(byOnclickUrl).getText().equals(arg0),
                    "Company Name should be equal '"+arg0+"' value");

            // Validate that we have only 1 item with Dashboard page redirection
            List<WebElement> elsbyOnclickUrl = driver.findElements(byOnclickUrl);
            softas.assertTrue(elsbyOnclickUrl.size()==1,
                    "Only 1 item 'Plooto Inc' should contain Dashboard page redirection");
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
        softas.assertAll();
    }
}
