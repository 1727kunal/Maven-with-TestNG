package UtilityPackage;

public class MyUtility {
    public static void waitTill(long miliSec){
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
