package cn.AnndyTsal.PO.PO_simplify.Listener.RetryWithPO.Listener;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestngListener extends TestListenerAdapter {

	@Override
	public void onFinish(ITestContext testContext) {

		super.onFinish(testContext);

		Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = (ITestResult) listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();

			if (testContext.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (testContext.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}
		}
	}
}
