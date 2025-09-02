package listeners;

import UtilityPackage.MyUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListenerClass.class)
public class ListenersDemo {
    WebElement usernameTxt;
    WebElement passwordTxt;
    WebElement loginBtn;
    WebElement dashboardHeader;
    public WebDriver driver;

    @BeforeTest
    public void openBrowser(ITestContext context){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        context.setAttribute("driver",driver);
        MyUtility.waitTill(5000);
    }

    @Test
    public void performLogin(){
        usernameTxt= driver.findElement(By.name("username"));
        usernameTxt.sendKeys("Admin");
        MyUtility.waitTill(2000);
        passwordTxt= driver.findElement(By.name("password"));
        passwordTxt.sendKeys("admin123");
        MyUtility.waitTill(2000);
        loginBtn= driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        MyUtility.waitTill(5000);
        dashboardHeader= driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed());
    }
    @AfterTest
    public void tearDown(){
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
