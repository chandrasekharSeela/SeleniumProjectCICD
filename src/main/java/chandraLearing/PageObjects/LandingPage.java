package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage extends AbstractComponent {

      @FindBy(id="userEmail")
      WebElement userEmail;

      @FindBy(id="userPassword")
      WebElement userPassword;

      @FindBy(id="login")
      WebElement loginBtn;

      @FindBy(css="[aria-label='Incorrect email or password.']")
      WebElement loginPageError;
      WebDriver driver;

    public  LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

      public ProductCatLog loginApplication(String userName, String password){
          userEmail.sendKeys(userName);
          userPassword.sendKeys(password);
          loginBtn.click();
          waitForElementToAppear(toastMsg);
          waitForElementToBeInvisible();
          return new ProductCatLog(driver);
      }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public void loginPageError(){
        Assert.assertTrue(loginPageError.getText().trim().equalsIgnoreCase("Incorrect email or password."));
    }

}
