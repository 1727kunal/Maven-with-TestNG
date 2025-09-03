/*
Perform the SendKeys and Click operation using the JavascriptExecutor interface...
*/

package interviewquestions;
import UtilityPackage.MyUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavascriptExecutorDemo {
    ChromeDriver driver;
    WebElement usernameTxt;
    WebElement passwordTxt;
    WebElement loginBtn;

    @BeforeTest
    void openBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        MyUtility.waitTill(2000);
    }
    @Test
    void performLogin(){

        usernameTxt= driver.findElement(By.name("username"));
//        usernameTxt.sendKeys("Admin");
//        driver.executeScript("arguments[0].value='Admin';",usernameTxt);   //1st way without using sendKeys()
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='Admin';",usernameTxt);  //2nd way without using sendKeys()
        MyUtility.waitTill(1000);

        passwordTxt= driver.findElement(By.name("password"));
//        passwordTxt.sendKeys("admin123");
//        driver.executeScript("arguments[0].value='admin123';",passwordTxt);
        js.executeScript("arguments[0].value='admin123';",passwordTxt);
        MyUtility.waitTill(1000);

        loginBtn= driver.findElement(By.xpath("//button[@type='submit']"));
//        loginBtn.click();
//        driver.executeScript("arguments[0].click();",loginBtn);
        js.executeScript("arguments[0].click();",loginBtn);
        MyUtility.waitTill(1000);
    }

    @AfterTest
    void tearDown(){
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
