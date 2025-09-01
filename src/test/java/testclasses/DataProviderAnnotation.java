/*
1. In this program, we are performing test on Amazon's search text field.
2. We are using Data Provider annotation to perform the search for the different set of inputs.
*/

package testclasses;
import UtilityPackage.MyUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class DataProviderAnnotation {

    WebDriver driver;
    WebElement searchTxt;

    @DataProvider(name="testDataForSearch")
    public Object[][] searchDataProvider(){
        Object[][] testData = new Object[4][1];
        testData[0][0]="Mobile";
        testData[1][0]="Shoe";
        testData[2][0]="Charger";
        testData[3][0]="Toys";
        return testData;
    }
    @BeforeMethod
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.in/");
        MyUtility.waitTill(1000);
    }

    @Test(dataProvider = "testDataForSearch")
    public void searchProduct(String searchKey){
        searchTxt= driver.findElement(By.id("twotabsearchtextbox"));
        searchTxt.sendKeys(searchKey+Keys.ENTER);
    }

    @AfterMethod
    public void tearDown(){
        MyUtility.waitTill(1000);
        driver.quit();
    }
}
