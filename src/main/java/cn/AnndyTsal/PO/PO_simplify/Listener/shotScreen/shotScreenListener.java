package cn.AnndyTsal.PO.PO_simplify.Listener.shotScreen;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;


public class shotScreenListener implements ITestListener {
	
	private static final Logger log = LogManager.getLogger(shotScreenListener.class.getName());
	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// 类加载获取BaseDriver的实例化driver对象
		DriverBase driver = null;
		try {
			// Listener添加的class中一定需要get方法
			Method method = result.getInstance().getClass().getMethod("getDriver");
			driver = (DriverBase) method.invoke(result.getInstance());

		} catch (Exception e) {
			
			log.error("[Class-shotScreenListener][Method-onTestFailure] 获取AndroidDriver实例对象失败");
			e.printStackTrace();
		}
		File path = new File("screenshots");
		// 截图位置及文件名 名称为 类名+方法名称
		
		String fullClassName = result.getInstanceName();
		String[] split = fullClassName.split("\\."); //以"."等特殊符号 需要转义
		String className = split[split.length-1];

		String name = path.getAbsolutePath() + File.separator + className+"-"+result.getMethod().getMethodName() + ".png";
		// 执行截图操作
		log.info("[Class-shotScreenListener][Method-onTestFailure] 截图存放的位置为："+name);
		File shotScreen = driver.shotScreen();
		try {
			FileUtils.copyFile(shotScreen, new File(name));
			log.info("[Class-shotScreenListener][Method-onTestFailure] 复制图片成功,图片复制到-->"+name);
		} catch (IOException e) {
			log.error("[Class-shotScreenListener][Method-onTestFailure] 复制图片失败-->"+className+"-"+result.getMethod().getMethodName());
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}
}
