package chandraLearning.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportNG;


public class Listeners extends BaseTest implements ITestListener, IRetryAnalyzer {
    ExtentReports extentReports = ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> testInstance = new ThreadLocal<>();
    int count = 0;
    int maxTry = 1;
    @Override
    public void onTestStart(ITestResult result) {
        test =  extentReports.createTest(result.getMethod().getMethodName());
        testInstance.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        testInstance.get().log(Status.PASS,"Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {

        try {
            driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        testInstance.get().log(Status.FAIL,result.getThrowable());
        testInstance.get().addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
    }
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry){
            count++;
            return true;
        }
        return false;
    }
}
