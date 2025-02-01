package chandraLearning.tests;

import chandraLearing.PageObjects.*;
import chandraLearning.TestComponents.BaseTest;
import chandraLearning.TestComponents.Listeners;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class SubmitOrderTest extends BaseTest {
    static final String loginName = "Sample@Test.com";
    static final String password = "S@s12345";
    List<String> cartItems = new ArrayList<>(Arrays.asList("IPHONE 13 PRO", "Banarsi Saree", "qwerty"));
    List<String> cartIValue = new ArrayList<>(Arrays.asList( "Banarsi Saree", "qwerty"));
    static final String myCountry = "India";
    static final String cvvTxtBox = "CVV Code ?";
    static final String cvvNum = "123";

    @Test(priority = 1)
    public  void submitOrder() {

        ProductCatLog productCatLog = landingPage.loginApplication(loginName,password);
        cartItems.forEach(productCatLog::addProductToCart);
        MyCart myCart =  productCatLog.gotoCartPage();
        Assert.assertTrue(myCart.verifyTheCartItemsAddedSuccessfully(cartItems));
        PaymentsPage paymentsPage = myCart.clickCheckOut();
        paymentsPage.getPersonalInfoTxtEleByName(cvvTxtBox).sendKeys(cvvNum);
        paymentsPage.enterCountryNameAndSelect(myCountry);
        ThanksForOrder thanksForOrder = paymentsPage.clickOnPlaceOrder();
        List<String> orderIds = thanksForOrder.getOrderNumbers();
        Orders orders = thanksForOrder.gotoOrdersPage();
        Assert.assertTrue(orders.verifyOrderIds(orderIds));

    }
    @Test(priority = 2,  dependsOnMethods = "submitOrder", retryAnalyzer = Listeners.class)
    public void deleteOtherItems(){
          landingPage.loginApplication(loginName,password);
           landingPage.gotoOrdersPage().deleteNonMatchingItems(cartIValue);
    }

    @Test(priority = 3, dependsOnMethods = "submitOrder")
    public void deleteAllRows(){
        landingPage.loginApplication(loginName,password);
        landingPage.gotoOrdersPage().deleteAllOrders();
    }


}
