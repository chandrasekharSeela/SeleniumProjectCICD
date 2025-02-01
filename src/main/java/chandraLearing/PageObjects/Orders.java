package chandraLearing.PageObjects;

import chandraLearing.AbractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orders extends AbstractComponent {

    @FindBy(css = "th[scope=row]")
    List<WebElement> orderIds;

    @FindBy(css=".btn.btn-danger")
    List<WebElement> deleteButtons;

    @FindBy(css="tr.ng-star-inserted")
    List<WebElement> orderedRows;


    WebDriver driver;
    public Orders(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderIds(List<String> yourOderIds){
        boolean verification = false;
        for(String id : yourOderIds) {
            verification = orderIds.stream().anyMatch(orderId -> orderId.getText().equalsIgnoreCase(id));
        }
        return verification;
    }

    public void deleteAllOrders(){
        for(int i = deleteButtons.size()-1; i>=0; i--){
            deleteButtons.get(i).click();
            defaultWait(1000);
        }
    }
    public void defaultWait(int value){
        try{
            Thread.sleep(value);
        }catch (InterruptedException ignored){

        }
    }

    public void deleteNonMatchingItems(List<String> itemsList){
        for(int i= orderedRows.size()-1;i>=0;i--)
        { boolean deletion = false;
            for(String item : itemsList) {
                deletion = orderedRows.get(i).findElement(By.cssSelector("td:nth-child(3)")).getText().equalsIgnoreCase(item);
                if (deletion)
                    break;
            }
            if(!deletion) orderedRows.get(i).findElement(By.xpath("//button[.='Delete']")).click();
        }
    }

    }



