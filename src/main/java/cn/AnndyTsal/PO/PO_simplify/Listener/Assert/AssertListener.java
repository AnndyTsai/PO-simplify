/**
 * 断言失败后不中断执行的监听器
 * 
 * 使用方法有两种：
 * 1：testng.xml配置文件中放置监听器
 * 2：类上放置监听器(@Listener)注解的方式引入监听器
 * */
package cn.AnndyTsal.PO.PO_simplify.Listener.Assert;

import java.util.ArrayList;
import java.util.List;
 
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import cn.AnndyTsal.PO.PO_simplify.Assert.Assertion;
 
public class AssertListener extends TestListenerAdapter {
 
    @Override
    public void onTestStart(ITestResult result) {
        Assertion.flag = true;       
        Assertion.errors.clear();
    }
     
    @Override
    public void onTestFailure(ITestResult tr) {
        this.handleAssertion(tr);
    }
     
    @Override
    public void onTestSkipped(ITestResult tr) {
        this.handleAssertion(tr);
    }
     
    @Override
    public void onTestSuccess(ITestResult tr) {
        this.handleAssertion(tr);
    }
     
    private int index = 0;
     
    private void handleAssertion(ITestResult tr){
        if(!Assertion.flag){
            Throwable throwable = tr.getThrowable();           
            if(throwable==null){
                throwable = new Throwable();
            }           
            StackTraceElement[] traces = throwable.getStackTrace();
            StackTraceElement[] alltrace = new StackTraceElement[0];           
            for (Error e : Assertion.errors) {
                StackTraceElement[] errorTraces = e.getStackTrace();
                StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
                StackTraceElement[] message = new StackTraceElement[]{new StackTraceElement("message : "+e.getMessage()+" in method : ", tr.getMethod().getMethodName(), tr.getTestClass().getRealClass().getSimpleName(), index)};
                index = 0;
                alltrace = this.merge(alltrace, message);
                alltrace = this.merge(alltrace, et);
            }
            if(traces!=null){
                traces = this.getKeyStackTrace(tr, traces);
                alltrace = this.merge(alltrace, traces);
            }           
            throwable.setStackTrace(alltrace);
            tr.setThrowable(throwable);
            Assertion.flag = true;   
            Assertion.errors.clear();
            tr.setStatus(ITestResult.FAILURE);           
        }
    }
     
    private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
        List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : stackTraceElements) {           
            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){               
                ets.add(stackTraceElement);
                index = stackTraceElement.getLineNumber();
            }
        }
        StackTraceElement[] et = new StackTraceElement[ets.size()];
        for (int i = 0; i < et.length; i++) {
            et[i] = ets.get(i);
        }
        return et;
    }
     
    private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
        StackTraceElement[] ste = new StackTraceElement[traces1.length+traces2.length];
        for (int i = 0; i < traces1.length; i++) {
            ste[i] = traces1[i];
        }
        for (int i = 0; i < traces2.length; i++) {
            ste[traces1.length+i] = traces2[i];
        }
        return ste;
    }
}
