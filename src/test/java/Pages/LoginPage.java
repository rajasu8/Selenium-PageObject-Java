package Pages;

import actionHelper.WebActionHelperMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePageClass {

    public LoginPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
    }

    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    WebElement loginBtn;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailPath;

    @FindBy(xpath = " //input[@id='password']")
    WebElement passwordTxtBox;

    public void enterlogindetailsAndLogin(String email, String password){
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void enterEmail(String email){
        emailPath.sendKeys(email);
    }
    public void enterPassword(String password){
        passwordTxtBox.sendKeys(password);
    }

    public void clickLogin(){
        webActionHelperMethods.clickbutton(loginBtn,"Login Button");
    }




}
