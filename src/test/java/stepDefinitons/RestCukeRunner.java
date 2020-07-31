package stepDefinitons;


import Helpers.ConfigReader;
import Helpers.FileManager;
import com.cucumber.listener.Reporter;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import java.io.File;


@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"pretty", "json:target/cucumber-reports/results.json", "junit:target/cucumber-reports/results.xml", "html:target/cucumber-reports",
        "com.cucumber.listener.ExtentCucumberFormatter:output/report.html",
        },
        features = {"src/test/resources/features/"}
        //glue={"src/test/java/stepDefs/"}
)
public class RestCukeRunner extends AbstractTestNGCucumberTests {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("/Users/mgundala/Documents/RestAsuredBatch-3/src/test/java/Helpers/reports.xml"));
    }


}
