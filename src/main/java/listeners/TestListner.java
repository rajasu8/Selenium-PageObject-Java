package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import reportManager.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class TestListner implements ITestListener {
    private ConcurrentHashMap<String, ExtentTest> allTests = new ConcurrentHashMap<>();
    String reportFolderPath = System.getProperty("user.dir") + "//AutomationReports//";
    String reportName = "AutomationReport.html";
    protected static ExtentTest extentTest;
    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.getInstance().flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentTest = ExtentManager.createInstance(reportFolderPath, reportName).createTest(iTestResult.getMethod().getRealClass().getSimpleName());
        allTests.put(iTestResult.getClass().getSimpleName(), extentTest);
        ExtentManager.setTest(extentTest);
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentManager.getTest().get().assignCategory(iTestResult.getClass().getSimpleName());
        ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN).getMarkup());
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test Failed", ExtentColor.RED).getMarkup())
                    .fail(iTestResult.getThrowable());
            ExtentManager.getTest().get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(reportFolderPath+iTestResult.getClass().getSimpleName()+".png").build());

            ExtentManager.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }


}