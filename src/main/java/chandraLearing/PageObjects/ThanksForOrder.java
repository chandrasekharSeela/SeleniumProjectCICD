package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ThanksForOrder  extends AbstractComponent {
    WebDriver driver;

    @FindBy(css="label.ng-star-inserted")
    List<WebElement> orderLabels;



    public ThanksForOrder(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getOrderNumbers(){
        return  orderLabels.stream().map(Orders-> Orders.getText().trim().replaceAll("\\|","").trim()).toList();
    }



}
