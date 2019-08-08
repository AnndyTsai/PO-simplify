package cn.AnndyTsal.PO.PO_simplify.Listener.Report.ExtentXReport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;

public class ExtentTestNGITestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance("test-output/report.html");
	private static ThreadLocal test = new ThreadLocal();
	DriverBase driver = null;

	@Override
	public synchronized void onStart(ITestContext context) {
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getMethod().getMethodName()));
		
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		/**
		 * boolean isScreenshot = true; String pictureName = null; String
		 * pictureFile = null; // 获取与运行时class method上的注解 try {
		 * 
		 * screenshot annotation =
		 * result.getMethod().getConstructorOrMethod().getMethod()
		 * .getAnnotation(screenshot.class);
		 * 
		 * // 获取直接的内容 isScreenshot = annotation.isScreenshot(); pictureName =
		 * annotation.name(); pictureFile = annotation.file();
		 * 
		 * } catch (Exception e) { System.out.println(
		 * "[Class-shotScreenListener][Method-onTestFailure] 未获取到任何注解信息 获取注解异常"
		 * ); }
		 */
		((ExtentTest) test.get()).fail(result.getThrowable());
		File directory = new File("test-output");
		try {
			String screenPath = directory.getCanonicalPath() + "/";
			File file = new File(screenPath);
			if (!file.exists()) {
				file.mkdirs();
			}

			// 失败截图的操作
			/**
			 * String fileName = result.getMethod().getMethodName() + ".png";
			 * driver.saveScreenshot(screenPath + fileName);
			 * ((ExtentTest)test.get()).addScreenCaptureFromPath(screenPath +
			 * fileName);
			 */

			// Listener添加的class中一定需要get方法
			Method method = result.getInstance().getClass().getMethod("getDriver");
			driver = (DriverBase) method.invoke(result.getInstance());

			File path = new File("screenshots");

			// 截图位置及文件名 名称为 类名+方法名称

			String fullClassName = result.getInstanceName();
			String[] split = fullClassName.split("\\."); // "."等特殊符号 需要转义
			String className = split[split.length - 1];

			String name = path.getAbsolutePath() + File.separator + className + "-" + result.getMethod().getMethodName()
					+ ".png";

			System.out.println("[Class-shotScreenListener][Method-onTestFailure] 截图存放的位置为：" + name);
			File shotScreen = driver.shotScreen();
			try {
				FileUtils.copyFile(shotScreen, new File(name));
				System.out.println("[Class-shotScreenListener][Method-onTestFailure] 复制图片成功,图片复制到-->" + name);
				((ExtentTest) test.get()).fail("报错截图如下,点击放大").addScreenCaptureFromPath(name);
				
				
			} catch (IOException e) {
				System.out.println("[Class-shotScreenListener][Method-onTestFailure] 复制图片失败-->");
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		((ExtentTest) test.get()).skip(result.getThrowable());
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}