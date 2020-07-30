package Helpers;

public class ConfigReader {

    public String getReportConfig(){
        String reportConfig = Constants.ReportConfig;
        if(reportConfig!=null) return reportConfig;
        else throw new RuntimeException("Report Config file not found");
    }
}
