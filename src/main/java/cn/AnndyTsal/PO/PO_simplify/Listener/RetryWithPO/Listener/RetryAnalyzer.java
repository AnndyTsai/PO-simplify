package cn.AnndyTsal.PO.PO_simplify.Listener.RetryWithPO.Listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import cn.AnndyTsal.PO.PO_simplify.Listener.RetryWithPO.Annotations.RetryCountIfFailed;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will
	 * call this method every time a test fails. So we can put some code in here
	 * to decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and
	 * false it not.
	 *
	 */

	@Override
	public boolean retry(ITestResult result) {

		// check if the test method had RetryCountIfFailed annotation
		RetryCountIfFailed annotation = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(RetryCountIfFailed.class);
		
		annotation.value();

		System.out.println("获取到注解====annotation"+annotation.toString());
		// All tests that are retried after failures will appear in the skipped
		// tests
		// list. Which causes TestNg to report those retry attempts as skipped
		// tests.
		// Here we will explicitly remove the retry test from the skipped tests
		// list so that
		// TestNg doesn't report retry attempts as skipped attempts.
		// The line below simply does that.
		result.getTestContext().getSkippedTests().removeResult(result.getMethod());

		// based on the value of annotation see if test needs to be rerun
		/**
		 * if((annotation != null) && (counter < annotation.value())) {
		 * counter++; return true; }
		 * 
		 * System.out.println("获取到注解====return false"); return false;
		 */
		if (!result.isSuccess()) {

			if ((annotation != null) && (counter < annotation.value())) {

				counter++;
				result.setStatus(ITestResult.FAILURE);
			} else {
				result.setStatus(ITestResult.FAILURE);
			}

		}else {
			result.setStatus(ITestResult.FAILURE);
        }
		
		return false;
	}
}
