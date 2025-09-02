package UtilityPackage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyUtility {
    public static void waitTill(long miliSec){
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void grabScreenshot(WebDriver driver,String folderName){
        String currentDataAndTime = new SimpleDateFormat("EEE-dd-MMM-yyyy_hh-mm-ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = "C:\\Users\\admin\\IdeaProjects\\MavenWithTestNG\\Screenshots\\"+folderName+"\\"+currentDataAndTime+".jpg";
//        String destinationFile = "C:\\Users\\admin\\IdeaProjects\\MavenWithTestNG\\Screenshots\\"+currentDataAndTime+".jpg";
        File destination = new File(destinationFile);
        try {
            FileHandler.copy(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
