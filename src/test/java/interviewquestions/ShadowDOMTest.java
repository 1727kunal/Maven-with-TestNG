/*
* Normal findElement method will not be able to find the elements inside the SHADOW DOM,
* So in this case we need to use, JavascriptExecutor interface to perform actions on it...
*/
package interviewquestions;

import UtilityPackage.MyUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShadowDOMTest {
    WebDriver driver;

    @BeforeTest
    void openBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.htmlelements.com/demos/menu/shadow-dom/?utm_source=chatgpt.com");
        MyUtility.waitTill(2000);
    }
    @Test
    void performActions(){
        WebElement frame = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(frame);
        MyUtility.waitTill(2000);

        WebElement shadowHost = driver.findElement(By.cssSelector(".smart-ui-component"));
        MyUtility.waitTill(1000);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        //WebElement fileTab = (WebElement) js.executeScript("return arguments[0].shadowRoot.querySelector('//span[contains(text(),File)]')",shadowHost);
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement fileTab = shadowRoot.findElement(By.cssSelector("smart-menu-items-group[label='File']"));
        fileTab.click();
        driver.switchTo().defaultContent();

    }

    @AfterTest
    void tearDown(){
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
