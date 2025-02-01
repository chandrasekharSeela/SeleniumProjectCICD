package chandraLearning.tests;

import Util.DataCaptureFromJsonFiles;
import chandraLearing.PageObjects.*;
import chandraLearning.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Hello world!
 */
public class StandAloneTest extends BaseTest {
    static final String loginName = "Sample@Test.com";
    static final String password = "S@s12345";

    static List<String> cartItems = new ArrayList<>();
    @Test( dataProvider = "cartItemsProvider", dataProviderClass = DataCaptureFromJsonFiles.class)
    public  void AddToCart(Object[][] myVal) {
            Arrays.stream(myVal)
                    .forEach(arr -> Arrays.stream(arr).forEach(StandAloneTest::addToList));
        ProductCatLog productCatLog = landingPage.loginApplication(loginName,password);
        cartItems.forEach(productCatLog::addProductToCart);
        MyCart myCart =  productCatLog.gotoCartPage();
        Assert.assertTrue(myCart.verifyTheCartItemsAddedSuccessfully(cartItems));
        cartItems.clear();
    }



    private static void addToList(Object o) {
        cartItems.add(o.toString());
    }





}
