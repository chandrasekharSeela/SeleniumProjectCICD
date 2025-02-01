package chandraLearning.tests;

import chandraLearning.TestComponents.BaseTest;
import chandraLearning.TestComponents.Listeners;
import org.testng.annotations.Test;

public class ErrorValidations  extends BaseTest {

    private final String loginName = "Sample@Test.com";
    private final String password = "S@12345";

    @Test(retryAnalyzer = Listeners.class)
    public void loginPageErrorValidation(){
        landingPage.loginApplication(loginName,password);
        landingPage.loginPageError();
    }
}
