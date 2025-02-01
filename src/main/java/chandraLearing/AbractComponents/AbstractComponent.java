package chandraLearing.AbractComponents;

import chandraLearing.PageObjects.MyCart;
import chandraLearing.PageObjects.Orders;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class AbstractComponent {

    static final String headerCart = "Cart";
    static final String orders = "ORDERS";
   /*** static final String  home = "HOME";
    static final String signOut = "Sign Out";*/

    WebDriver driver;
    private static final int numOfSec = 2;
   public @FindBy(css=".btn.btn-custom")
    List<WebElement> headerButtons;


   public By toastMsg = By.cssSelector("#toast-container");

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public WebElement waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(numOfSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    /***
     * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(numOfSec));
     try{wait.until(ExpectedConditions.invisibilityOf(ele)); }catch (NoSuchElementException ignored){}*/
    public void waitForElementToBeInvisible(){//WebElement ele){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ignored){

        }
    }

    public WebElement headerBtnByName(String btnName){
       return Objects.requireNonNull(headerButtons.stream().filter(headerBtn->
                headerBtn.getText().toUpperCase().contains(btnName.toUpperCase())).findFirst().orElse(null));
    }
    public MyCart gotoCartPage(){
        headerBtnByName(headerCart).click();
        return new MyCart(driver);
    }

    public Orders gotoOrdersPage(){
        headerBtnByName(orders).click();
        return  new Orders(driver);
    }



}
