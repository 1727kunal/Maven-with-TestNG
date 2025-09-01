package testclasses;
import UtilityPackage.MyUtility;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class LoginTestOrangeHRM {
    WebDriver driver;
    WebElement usernameTxt;
    WebElement passwordTxt;
    WebElement loginBtn;
    WebElement dashboardHeader;

    @DataProvider
    public Object[][] testDataForLogin(){
        File f = new File("C:\\Users\\admin\\IdeaProjects\\MavenWithTestNG\\src\\test\\resources\\LoginData.xlsx");
        try {
            Workbook wb = WorkbookFactory.create(f);
            Sheet s1 = wb.getSheet("Sheet1");
            int numberOfRows = s1.getPhysicalNumberOfRows()-1;
            int numberOfColumns = s1.getRow(0).getLastCellNum();
            Object[][] testData = new Object[numberOfRows][numberOfColumns];
            for(int r=1; r<=numberOfRows;r++){
                for (int c=0;c<numberOfColumns;c++) {
                    testData[r-1][c] = s1.getRow(r).getCell(c).toString();
                }
            }
            return testData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    void openBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        MyUtility.waitTill(5000);
        Reporter.log("Login page displayed...",true);
    }

    @Test(dataProvider="testDataForLogin")
    public void testLoginScenario(String username, String password){
        usernameTxt= driver.findElement(By.name("username"));
        usernameTxt.sendKeys(username);
        Reporter.log("Username entered...",true);
        MyUtility.waitTill(1000);
        passwordTxt= driver.findElement(By.name("password"));
        passwordTxt.sendKeys(password);
        Reporter.log("Password entered...",true);
        MyUtility.waitTill(1000);
        loginBtn= driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        Reporter.log("Login button clicked...",true);
        MyUtility.waitTill(5000);
        dashboardHeader= driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertEquals(dashboardHeader.isDisplayed(),true);
        Reporter.log("Login done successfully...",true);
    }

    @AfterMethod
    void tearDown(){
        MyUtility.waitTill(1000);
        driver.quit();
        Reporter.log("Browser session terminated...",true);
    }
}
