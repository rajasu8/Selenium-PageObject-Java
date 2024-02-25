
package TestCases;

import Pages.ContactsPage;
import Pages.ConversationsPage;
import Pages.HomePage;
import Pages.LoginPage;
import actionHelper.WebActionHelperMethods;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SendSmsTestCases extends BaseTest {

    @BeforeMethod
    public void initialiseClasses(){
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        conversationsPage = new ConversationsPage(driver);
        contactsPage = new ContactsPage(driver);
    }

    @Test
    public void TEST_CASE_ONE() {
        driver.get(URL);
        loginPage.enterlogindetailsAndLogin(userName,password);
        homePage.clickConversations();
        String name = "Test Template "+ System.currentTimeMillis() ;
        String content = "Here is the template description " + System.currentTimeMillis() ;
        conversationsPage.addTemplate(name, content);
        homePage.clickContacts();
        String contactName = "Sudharsana Lakshmi";
        contactsPage.sendSMS(contactName,name,content);
    }


//    @Test
//    public void TEST_CASE_TWO() throws Exception {
//        driver.get(URL);
//        homePage.searchForProducts();
//
//    }


}

