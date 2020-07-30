package Helpers;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportConfig implements IReporter {

    private ExtentReports rConfig;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        rConfig = new ExtentReports(outputDirectory + File.separator + "ApiReport.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        rConfig.flush();
        rConfig.close();
    }


    private void buildTestNodes(IResultMap tests, LogStatus status) {

        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = rConfig.startTest(result.getMethod().getMethodName());

                for(String group :result.getMethod().getGroups())
                    test.assignCategory(group);
                String message = "Test"+ status.toString();

                if (result.getThrowable()!=null)
                    message = result.getThrowable().getMessage();
                test.log(status,message);
                rConfig.endTest(test);
            }
        }
    }

    private Date getTime(long mills){
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(mills);
        return calender.getTime();
    }
}