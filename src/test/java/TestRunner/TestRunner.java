package TestRunner;

import org.testng.annotations.*;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"StepDefs"},
        plugin = { "pretty", "json:target/cucumber/cucumber.json" },
        tags = "@Smoke"
)

public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;
    //protected static WebDriver connection;

    @BeforeClass
    public void beforeClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        testNGCucumberRunner.finish();
    }

}
