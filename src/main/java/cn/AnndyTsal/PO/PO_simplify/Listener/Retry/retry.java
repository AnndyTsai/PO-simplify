/**
 * case运行失败后再次运行
 * */
package cn.AnndyTsal.PO.PO_simplify.Listener.Retry;
 
import org.testng.IRetryAnalyzer; 
import org.testng.ITestResult; 
   
public class retry implements IRetryAnalyzer{ 
   
    private int retryCount = 1;  
    private static int maxRetryCount = 3;
   
    public boolean retry(ITestResult result) {   
        if (retryCount < maxRetryCount) { 
            retryCount++; 
            return true; 
        } 
        return false; 
    }
}