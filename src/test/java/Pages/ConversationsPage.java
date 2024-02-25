package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ConversationsPage extends BasePageClass {
    public ConversationsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15), this);
        webActionHelperMethods.waitTillPageIsLoaded(15);
    }

    @FindBy(xpath = "//a[@id =\"tb_conversations-templates\"]/span")
    WebElement templatesSection;

    @FindBy(xpath = "//a[@id =\"tb_conversations-templates\" and contains(@class,'active')]")
    WebElement templatesSectionSelected;

    @FindBy(xpath = "//button[@data-toggle=\"dropdown\"]")
    WebElement addSnippetBtn;

    @FindBy(xpath = "//a[contains(text(),'Add Text Template (Snippet)')]")
    WebElement addTextTemplate;

    @FindBy(xpath = "//div[@class='form-group']//input[@name ='msgsndr2' and @placeholder='Name'and following::div/i]")
    WebElement nameTextBox;

    @FindBy(xpath ="//html/head/body")
    WebElement templateDescription;

    @FindBy(xpath ="//button[contains(text(),'Save')]")
    WebElement saveBtn;




    public void addTemplate(String name, String content){
        clicktemplatesSection();
        clickaddSnippetBtn();
        clickaddTextTemplate();
        enterTemplateName(name);
        enterTemplateDescription(content);
        clickSaveBtn();
        isTemplateCreated(name);
    }

    public void clicktemplatesSection() {
        try {
            Thread.sleep(5000); //Hard stop to wait till page load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // webActionHelperMethods.RefreshBrowser();
       // webActionHelperMethods.explicityWaitUntilElementClickable(templatesSection,10);
        webActionHelperMethods.clickbutton(templatesSection, "Template Section ");
    }

    public void clickaddSnippetBtn(){
        webActionHelperMethods.clickbutton(addSnippetBtn,"Add snippet Dropdown");
    }

    public void clickaddTextTemplate(){
        webActionHelperMethods.clickbutton(addTextTemplate,"Text Template");
    }

    public void enterTemplateName(String name){
        webActionHelperMethods.flentWait(nameTextBox);
        nameTextBox.sendKeys(name);
    }

    public void enterTemplateDescription(String description){
        webActionHelperMethods.switchToIframe("editsmseditor_ifr");
        driver.findElement(By.xpath("//html/body")).sendKeys(description);
        webActionHelperMethods.switchToDefaultContent();
    }

    public void clickSaveBtn(){
        webActionHelperMethods.clickbutton(saveBtn,"Save Btn");
        webActionHelperMethods.implicitlyWait(5);
    }

    public boolean isTemplateCreated(String name){
        webActionHelperMethods.flentWait(driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+name+"')]")));
        if( driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+name+"')]")).isDisplayed())
        {
            webActionHelperMethods.logInfo(name +" Template is created");
            return true;
        }else
            return false;
    }


}
