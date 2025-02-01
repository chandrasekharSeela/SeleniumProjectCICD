package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class MyCart extends AbstractComponent {
    String cartBtnCheckOut = "Checkout";
    @FindBy(css = "div.infoWrap") List<WebElement> cartElements;
    @FindBy(css = ".btn.btn-primary") List<WebElement> cartButtons;
    WebDriver driver;

    public MyCart(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyTheCartItemsAddedSuccessfully(List<String> cartItems){
        boolean verification = false;
        for(String cartItem: cartItems) {
            verification = cartElements.stream().anyMatch(cartElement ->
                    cartElement.findElement(By.tagName("h3")).getText().equalsIgnoreCase(cartItem));
        }
        return verification;
    }

    public WebElement CartBtnEleByName(String cartBtnName){
      return Objects.requireNonNull(cartButtons.stream().filter(cartBtn->
              cartBtn.getText().equalsIgnoreCase(cartBtnName)).findFirst().orElse(null));
    }


   public PaymentsPage clickCheckOut(){
       CartBtnEleByName(cartBtnCheckOut).click();
       return new PaymentsPage(driver);
   }


}
