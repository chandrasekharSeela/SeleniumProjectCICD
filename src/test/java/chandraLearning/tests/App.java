package chandraLearning.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
   static final String url = "https://rahulshettyacademy.com/client/";
   static final String loginName = "Sample@Test.com";
   static final String password = "S@s12345";
   static final String loginBtnId = "#login";
   static final String nameInputBoXId ="#userEmail";
    static final String passwordInputBoXId ="#userPassword";
    public static WebDriver driver;

    public static void main( String[] args )
    {
        WebDriverManager.chromedriver().setup();
        String myCountry = "India";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
         driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<String> cartItems = new ArrayList<>(Arrays.asList("IPHONE 13 PRO", "Banarsi Saree", "qwerty"));
        driver.get(url);
        driver.findElement(By.cssSelector(nameInputBoXId)).sendKeys(loginName);
        driver.findElement(By.cssSelector(passwordInputBoXId)).sendKeys(password);
        driver.findElement(By.cssSelector(loginBtnId)).click();

        cartItems.forEach(s -> {
                   Objects.requireNonNull(Objects.requireNonNull(driver.findElements(By.xpath("//div[contains(@class,'mb-3')]//b[contains(.,'" + s + "')]"))).stream().findFirst()
                           .orElse(null)).findElement(By.xpath("ancestor::div/button[contains(.,'Add To Cart')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Product Added To Cart']")));
            String productTest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']"))).getText();
            try{
                wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ngx-spinner-overlay')]"))));
            }catch (NoSuchElementException ignored){

            }
            Assert.assertEquals(productTest,"Product Added To Cart");
                });
           Objects.requireNonNull(driver.findElements(By.cssSelector(".btn.btn-custom")).stream().filter(element -> element.getText().contains("Cart")).findFirst().orElse(null)).click();

        for (String cartItem : cartItems) {
            Assert.assertTrue(driver.findElements(By.xpath("//div[@class='cartSection']/h3")).stream().anyMatch(element ->
                    element.getText().equalsIgnoreCase(cartItem)));
        }
        Objects.requireNonNull(driver.findElements(By.cssSelector(".btn.btn-primary")).stream().filter(element -> element.getText().equalsIgnoreCase("Checkout")).findFirst().orElse(null)).click();
            driver.findElement(By.xpath("//div[contains(.,'CVV Code')][@class='title']/following-sibling::input")).sendKeys("123");
            driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(myCountry.substring(0,3));
            Objects.requireNonNull(driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']")).stream()
                    .filter(element -> element.getText().trim().equalsIgnoreCase(myCountry)).findFirst().orElse(null)).click();
            driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
            List<String> orderIds = driver.findElements(By.cssSelector("label.ng-star-inserted")).stream().map(element -> element.getText().trim().replace("|","").trim()).toList();
            Objects.requireNonNull(driver.findElements(By.cssSelector(".btn.btn-custom")).stream().
                    filter(
                            element -> element.getText().contains("ORDERS")).
                    findFirst().orElse(null)).click();
           orderIds.forEach(s->
                   Assert.assertTrue(driver.findElements(By.cssSelector("th[scope=row]")).stream().anyMatch(element -> element.getText().equalsIgnoreCase(s))));
            driver.quit();
        }



}
