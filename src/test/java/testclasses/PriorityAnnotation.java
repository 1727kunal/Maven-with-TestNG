package testclasses;

import org.testng.annotations.Test;

public class PriorityAnnotation {
    @Test(priority = 1)
    void abc(){
        System.out.println("abc...");
    }
    @Test(priority = 1)
    void jkl(){
        System.out.println("jkl...");
    }
    @Test(priority = -1)
    void pqr(){
        System.out.println("pqr...");
    }
    @Test
    void xyz(){
        System.out.println("xyz...");
    }
}
