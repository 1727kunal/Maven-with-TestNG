package testclasses;

import UtilityPackage.MyUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonLogin {
    WebDriver driver;
    Actions act;
    WebElement accountAndList;
    WebElement signinBtn1;
    WebElement mobileTxt;
    WebElement continueBtn;
    WebElement passwordTxt;
    WebElement signinBtn2;
    WebElement searchTxt;
    WebElement shoeProduct;
    WebElement addCartBtn;
    WebElement cartIcon;

    @BeforeTest
    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.amazon.in/");
        driver.manage().window().maximize();
        MyUtility.waitTill(1000);
    }

    @Test(priority = 1)
    void performLogin(){
        MyUtility.waitTill(1000);
        accountAndList= driver.findElement(By.xpath("//a[@data-csa-c-content-id='nav_ya_signin']"));
        act= new Actions(driver);
        act.moveToElement(accountAndList).perform();
        MyUtility.waitTill(1000);
        signinBtn1= driver.findElement(By.xpath("//span[text()='Sign in'][@class='nav-action-inner']"));
        signinBtn1.click();
        MyUtility.waitTill(1000);
        mobileTxt= driver.findElement(By.id("ap_email_login"));
        mobileTxt.sendKeys("7264811720");
        MyUtility.waitTill(1000);
        continueBtn= driver.findElement(By.xpath("//input[@type='submit']"));
        continueBtn.click();
        MyUtility.waitTill(1000);
        passwordTxt= driver.findElement(By.id("ap_password"));
        passwordTxt.sendKeys("1163kunal1727");
        MyUtility.waitTill(1000);
        signinBtn2= driver.findElement(By.xpath("//input[@type='submit']"));
        signinBtn2.click();
        MyUtility.waitTill(2000);
        Assert.assertEquals(driver.getTitle(),"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
    }

    @Test(priority = 2)
    void searchAndAddToCart(){
        MyUtility.waitTill(1000);
        searchTxt= driver.findElement(By.id("twotabsearchtextbox"));
        searchTxt.sendKeys("Shoes"+ Keys.ENTER);
        MyUtility.waitTill(1000);
        act.scrollByAmount(0,500).perform();
        MyUtility.waitTill(1000);
        shoeProduct= driver.findElement(By.xpath("//img[@alt='Sponsored Ad - Bacca Bucci Men Lace Up Basketball Shoe']"));
        shoeProduct.click();
        MyUtility.waitTill(1000);
        act.scrollByAmount(0,500).perform();
        addCartBtn= driver.findElement(By.id("add-to-cart-button"));
        addCartBtn.click();
        MyUtility.waitTill(2000);
        cartIcon = driver.findElement(By.id("nav-cart-count"));
        int numberOfProducts = Integer.parseInt(cartIcon.getText());
        Assert.assertEquals(numberOfProducts>0,true);
    }

    @AfterTest
    public void tearDown() {
        MyUtility.waitTill(2000);
        driver.quit();
    }
}
