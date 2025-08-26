package testclasses;

import UtilityPackage.MyUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterAnnotation {
    WebDriver driver;

    @Parameters({"browser","condition"})
    @BeforeTest
    void beforeTest(String browserName,String conditionValue){

        boolean flag = Boolean.parseBoolean(conditionValue);

        if(browserName.equals("Chrome") && flag)
            driver=new ChromeDriver();
        if(browserName.equals("Firefox") && flag)
            driver=new FirefoxDriver();
        if(browserName.equals("Edge") && flag)
            driver=new EdgeDriver();

        driver.navigate().to("https://www.google.com/");
        driver.manage().window().maximize();
        MyUtility.waitTill(1000);
    }
    @Test
    void testMethod1(){
        MyUtility.waitTill(1000);
        System.out.println(driver.getTitle());
    }
    @AfterTest
    void afterTest() {
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
