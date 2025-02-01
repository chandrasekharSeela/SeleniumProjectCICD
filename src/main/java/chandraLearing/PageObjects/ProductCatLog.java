package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Objects;

public class ProductCatLog extends AbstractComponent {


    /***
     * Find By Web Elements
     *
     */
      @FindBy(css = ".mb-3") List<WebElement> products;



    /***
     * By type values
     */
      By productsBy = By.cssSelector(".mb-3");
      By productElement = By.cssSelector(".card-body button:last-of-type");


    /***
     * Default variables
     */
    public static String productAddedConfirmTxt;
    WebDriver driver;
    public ProductCatLog(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList(){
       waitForElementToAppear(productsBy);
       return products;
   }
   public WebElement getProductByName(String productName){
     return Objects.requireNonNull(getProductList().stream().filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
               .findFirst().orElse(null));
   }

   public void addProductToCart(String productName){
       getProductByName(productName).findElement(productElement).click();
       productAddedConfirmTxt =waitForElementToAppear(toastMsg).getText();
       waitForElementToBeInvisible();
   }


}
