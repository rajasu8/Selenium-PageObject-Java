package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePageClass {
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Conversations')]")
    WebElement conversationLink;

    @FindBy(xpath = "//span[contains(text(),'Contacts')]")
    WebElement contactsLink;

    public void clickConversations(){
        webActionHelperMethods.clickbutton(conversationLink,"Conversations");
    }

    public void clickContacts(){
        webActionHelperMethods.clickbutton(contactsLink,"Contacts");
    }
}
