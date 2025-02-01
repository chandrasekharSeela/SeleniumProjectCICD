package chandraLearning.TestComponents;

import chandraLearing.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    public static LandingPage landingPage;
    Properties prop = new Properties();
    public  WebDriver driver;
    List<String> optional = new ArrayList<>();
    public  WebDriver getDriver(){


        String browserName;
        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
            prop.load(file);
            log.info ("BrowserName{}", System.getProperty("browser"));
            browserName = System.getProperty("browser")!=null?System.getProperty("browser") : prop.getProperty("browser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       switch (browserName) {
           case "Edge":
               WebDriverManager.edgedriver().setup();
               EdgeOptions opt = new EdgeOptions();
               gettingBrowserOptions(browserName);
               optional.forEach(opt::addArguments);
               driver = new EdgeDriver(opt);
               break;
           case "Firefox":
               WebDriverManager.firefoxdriver().setup();
               driver = new FirefoxDriver();
               break;
           case "Chrome":
           default:{
               WebDriverManager.chromedriver().setup();
               ChromeOptions options = new ChromeOptions();
               gettingBrowserOptions(browserName);
               optional.forEach(options::addArguments);
               driver = new ChromeDriver(options);
           }
           break;
       }
        if(browserName.equalsIgnoreCase("firefox")){
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      return  driver;
    }

    private void gettingBrowserOptions(String browserName) {
        for(Map.Entry<Object, Object> entry : prop.entrySet()){
            String key = (String) entry.getKey();
            if(key.toUpperCase().contains(browserName.toUpperCase())){
                optional.add(entry.getValue().toString());
            }
        }
    }

    public LandingPage  launchApplication(){
        driver = getDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }
    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void openApplication(){
        landingPage= launchApplication();
    }

    public String getScreenshot(String testName, WebDriver driver){

        String path = System.getProperty("user.dir")+"//reports//screenshots//"+testName+returnDate()+".png";
        File file = new File( path);
        TakesScreenshot ts = (TakesScreenshot) driver;
       File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source,file );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
    public static String returnDate(){
        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
        return formatter.format(dt);
    }

}
