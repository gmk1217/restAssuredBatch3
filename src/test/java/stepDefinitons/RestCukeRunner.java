package stepDefinitons;


import Helpers.FileManager;
import com.cucumber.listener.Reporter;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;


@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"pretty", "html:target/cucumber.html","json:target/cucumber-reports/results.json","com.cucumber.listner.ExtentCucumberAdapter:target/output/report.html"},
        features = {"src/test/resources/features/"}
        //glue={"src/test/java/stepDefs/"}
)
public class RestCukeRunner extends AbstractTestNGCucumberTests {

    @AfterClass
    public static void generateReport(){
        Reporter.loadXMLConfig(new File(FileManager.getInstance().getConfigReader().getReportConfig()));
    }

}
