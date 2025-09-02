package listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({MyListenerClass.class})
public class TestClass1 {
    @Test(retryAnalyzer = MyListenerClass.class)
    void firstMethod(){
        Assert.assertEquals(true,true);
    }
}
