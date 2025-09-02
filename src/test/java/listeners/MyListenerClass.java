package listeners;

import UtilityPackage.MyUtility;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListenerClass implements IRetryAnalyzer, ITestListener {
    int initialCount=0;
    int retryCount=2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(initialCount<retryCount){
            initialCount++;
            System.out.println("Test case retried: "+initialCount);
            return true;
        }
        return false;
    }

    /*@Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }*/

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        MyUtility.grabScreenshot(driver,"Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        MyUtility.grabScreenshot(driver,"Fail");
    }

    /*@Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);

    }*/

   /* @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);

    }*/

    /*@Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);

    }*/

   /* @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);

    }*/

   /* @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }*/
}
