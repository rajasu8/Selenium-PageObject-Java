package TestCases;

import Pages.ContactsPage;
import Pages.ConversationsPage;
import Pages.LoginPage;
import Pages.HomePage;
import actionHelper.WebActionHelperMethods;
import driverManager.DriverManagerType;
import driverManager.WebDrivers;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.utility;

import java.util.Properties;

public class BaseTest  {

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ConversationsPage conversationsPage;
    protected ContactsPage contactsPage;
    WebDriver driver;
    Properties properties;
    static String URL;
    static String userName;
    static String password;

    @BeforeSuite
    public void setUp() {
        String propertyPath = System.getProperty("user.dir") + "//src//test//resources//Env.properties";
        try {
            driver = WebDrivers.getDriver(DriverManagerType.CHROME);
            driver.manage().window().maximize();
            properties = utility.loadProperties(propertyPath);
            URL = properties.getProperty("URL");
            userName = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @AfterMethod
    public void getscreenshot(ITestResult result){
      //  webActionHelperMethods.getScreenshot(driver, result.getClass().getSimpleName());
    }


    @AfterSuite
    public void destroyDriver() {
        driver.quit();
    }
}