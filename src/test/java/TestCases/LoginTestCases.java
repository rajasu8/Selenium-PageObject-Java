package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTest {


    @Test
    public void TEST_CASE_ONE() {
        driver.get(URL);
        loginPage.enterlogindetailsAndLogin(userName,password);
    }

//    @Test
//    public void TEST_CASE_TWO() throws Exception {
//        driver.get(URL);
//        homePage.searchForProducts();
//
//    }


}
