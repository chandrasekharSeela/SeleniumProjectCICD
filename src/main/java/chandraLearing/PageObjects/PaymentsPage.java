package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class PaymentsPage extends AbstractComponent {

    @FindBy(css=".form__cc input")
    List<WebElement> personalInfoTxtBoxes;

    @FindBy(css="input[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css="button.ta-item.list-group-item.ng-star-inserted")
    List<WebElement> countryDropDown;

    @FindBy(css=".btnn.action__submit.ng-star-inserted")
    WebElement placeOrder;
    WebDriver driver;
    public PaymentsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getPersonalInfoTxtEleByName(String elementName){
      return Objects.requireNonNull (personalInfoTxtBoxes.stream().filter(personalInfoBox ->
                personalInfoBox.findElement(By.xpath("preceding-sibling::div"))
                        .getText().trim().contains(elementName.substring(0,elementName.length()-2))).findFirst().orElse(null));
    }

    public void enterCountryNameAndSelect(String countryName){
        selectCountry.sendKeys(countryName.substring(0,3));
        Objects.requireNonNull(countryDropDown.stream().filter(dropDown->
                dropDown.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null)).click();
    }
    public ThanksForOrder clickOnPlaceOrder(){
        placeOrder.click();
        return new ThanksForOrder(driver);
    }

}
