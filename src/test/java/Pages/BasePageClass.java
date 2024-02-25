package Pages;

import actionHelper.WebActionHelperMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePageClass {
    protected WebDriver driver;
    static WebActionHelperMethods webActionHelperMethods;

    public BasePageClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
        webActionHelperMethods= new WebActionHelperMethods(driver);
    }

}
