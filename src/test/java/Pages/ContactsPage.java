
package Pages;

        import org.openqa.selenium.By;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindAll;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
        import org.testng.Assert;

        import java.util.List;

public class ContactsPage extends BasePageClass {
    public ContactsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15), this);
        webActionHelperMethods.waitTillPageIsLoaded(15);
    }

    @FindBy(xpath = "//button[span/span[text()='Send']]/parent::div/parent::div/preceding-sibling::div/div[4]")
    WebElement insertTemplateBtn;

    @FindBy(xpath = "//button/span/span[text()='Send']")
    WebElement sendBtn;

    @FindBy(xpath = "//section[@id]//div[@aria-label='Search for option']//input")
    WebElement inputTemplate;

    @FindBy(xpath = "//button[contains(text(),'Use Template')]")
    WebElement useTemplateBtn;

    @FindBy(xpath ="//textarea[@id='text-message']")
    WebElement textArea;

    @FindBy(xpath ="//button[contains(text(),'Save')]")
    WebElement saveBtn;

    @FindAll({
            @FindBy(xpath ="//div[@class='message-wrapper']//p/span[@class='grow']")
    })
    List<WebElement> listOfAllMessages;


    public void sendSMS(String contactName, String templateName, String description){
        clickContact(contactName);
        clickInsertTemplate();
        selectTemplate(templateName);
        compareTextAndSEnd(description);
        System.out.println("Template last = "+ listOfAllMessages.get(listOfAllMessages.size()-1).getText());
        Assert.assertEquals( listOfAllMessages.get(listOfAllMessages.size()-1).getText(),description,"Message send is same as in template");
    }

    public void clickContact(String contactName){
        webActionHelperMethods.implicitlyWait(5);
        webActionHelperMethods.clickbutton(driver.findElement(By.xpath("//tbody/tr/td[@data-title='Name']//a[contains(text(),'"+contactName+"')]")),"Contact"+contactName);
    }

    public void clickInsertTemplate(){
        webActionHelperMethods.clickbutton(insertTemplateBtn,"Insert Template Btn");
    }

    public void selectTemplate(String templateName){
        webActionHelperMethods.flentWait(inputTemplate);
        webActionHelperMethods.clickbutton( inputTemplate, "Input Template Btn");
        inputTemplate.sendKeys(templateName);
        webActionHelperMethods.implicitlyWait(10);
        webActionHelperMethods.selectdropdown("//section[@id]//ul[@role='listbox']",templateName);
        System.out.println(webActionHelperMethods.getObjectText(inputTemplate));
        webActionHelperMethods.clickbutton(useTemplateBtn, "Use Template");
    }

    public void compareTextAndSEnd(String description){
        webActionHelperMethods.flentWait(textArea);
        webActionHelperMethods.clickbutton(sendBtn,"Send Btn");
        try {
            Thread.sleep(5000); //Hard stop to wait untill message is sent
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webActionHelperMethods.implicitlyWait(5);
    }


    public void enterTemplateDescription(String description){
        webActionHelperMethods.switchToIframe("editsmseditor_ifr");
        driver.findElement(By.xpath("//html/body")).sendKeys(description);
        webActionHelperMethods.switchToDefaultContent();
    }

    public void clickSaveBtn(){
        webActionHelperMethods.clickbutton(saveBtn,"Save Btn");
    }

    public boolean isTemplateCreated(String name){
        webActionHelperMethods.flentWait(driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+name+"')]")));
        return driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+name+"')]")).isDisplayed();
    }


}
