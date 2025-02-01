package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ExtentReportNG {
    static Properties props = new Properties();
    public static ExtentReports getReportObject(){

        String username = System.getProperty("user.name");
        String path = System.getProperty("user.dir")+"//reports//index_"+returnDate()+".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        FileInputStream file = null;
        try {
            file = new FileInputStream(
                    System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
            props.load(file);
            username =  props.getProperty(username);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        extent.setSystemInfo("Tester",username);
        return extent;
    }

    public static String returnDate(){
        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
        return formatter.format(dt);
    }
}
