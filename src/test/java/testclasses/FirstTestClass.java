package testclasses;

import UtilityPackage.MyUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTestClass {
    WebDriver driver;
    @BeforeTest
    public void launchBrowser(){
        driver= new ChromeDriver();
        driver.navigate().to("https://www.google.com/");
        driver.manage().window().maximize();
        MyUtility.waitTill(1000);
    }
    @Test
    public void login(){
        System.out.println(driver.getTitle());
    }
    @AfterTest
    public void tearDown(){
        MyUtility.waitTill(1000);
        driver.quit();
    }
}
