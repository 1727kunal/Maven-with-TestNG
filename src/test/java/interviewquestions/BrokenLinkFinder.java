package interviewquestions;

import UtilityPackage.MyUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinkFinder {
    WebDriver driver;

    @BeforeTest
    void openBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        MyUtility.waitTill(2000);
    }
    @Test
    void getAllLinks(){
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for(WebElement e: allLinks){
            String linkURL = e.getAttribute("href");
            if(linkURL!=null || !linkURL.isEmpty() || !linkURL.startsWith("javascript")){
                checkForStatusCode(linkURL);
            }
        }
    }

    void checkForStatusCode(String linkURL){
        try {
            URL currentURL = new URL(linkURL);
            HttpURLConnection connection = null;
            try {
                connection= (HttpURLConnection)currentURL.openConnection();
                int statusCode= connection.getResponseCode();
                if(statusCode>=200 && statusCode<400){
                    System.out.println(currentURL +" ---> Valid Link...");
                }
                else{
                    System.out.println(currentURL +" ---> Broken Link...");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
    @AfterTest
    void tearDown(){
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
