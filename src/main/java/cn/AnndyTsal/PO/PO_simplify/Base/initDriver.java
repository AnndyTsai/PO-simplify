/**
 * 初始化APP 返回driver对象 
 * */
package cn.AnndyTsal.PO.PO_simplify.Base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import cn.AnndyTsal.PO.PO_simplify.Utlis.readProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class initDriver {

	private readProperties rw;
	private AndroidDriver<AndroidElement> driver;
	private static final Logger log = LogManager.getLogger(initDriver.class.getName());

	public initDriver() {

		rw = new readProperties("./config/initDriver.properties");
	}

	/**
	 * 返回AndroidDriver
	 * 
	 * @boolean:isAppInstalled false:表示app未安装 true：表示APP已经安装
	 */
	public AndroidDriver<AndroidElement> getDriver(String UUID, int AppiumPort) {
		
		String IP = rw.getValue("ServerIP");
		
		File app = null;
		boolean isAppInstalled = true;
		
		if(rw.getValue("isAPPInstalled").equalsIgnoreCase("true")){
			
			isAppInstalled = false;
			log.info("[Class-initDriver][Method-getDriver] 当前APP已安装");
			//log.info("[Class-initDriver][Method-getDriver] 当前APP已安装");
		}
		
		//log.info("[Class-initDriver][Method-getDriver] 调用‘initDriver’方法");
		log.info("[Class-initDriver][Method-getDriver] 调用‘initDriver’方法");
		if (isAppInstalled) {
			
			app = new File(rw.getValue("APPPath"));
			log.info("[Class-initDriver][Method-getDriver] 当前APP未安装,APP路径"+rw.getValue("APPPath"));
			//log.info("[Class-initDriver][Method-getDriver] 当前APP未安装,APP路径"+rw.getValue("APPPath"));
		}

		DesiredCapabilities caps = new DesiredCapabilities();
		//log.info("[Class-initDriver][Method-getDriver] 创建DesiredCapabilities对象");
		log.info("[Class-initDriver][Method-getDriver] 创建DesiredCapabilities对象");
		if (isAppInstalled) {
			
			caps.setCapability(MobileCapabilityType.APP, app.getAbsoluteFile());
			log.info("[Class-initDriver][Method-getDriver] 正在安装APP,请稍后...");
		}
		// IOS需要填写正确的DeviceName Andriod随便写 但是不能null
		log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-DeviceName："+rw.getValue("DeviceName"));
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, rw.getValue("DeviceName"));
		
		log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-appPackage"+rw.getValue("appPackage"));
		caps.setCapability("appPackage", rw.getValue("appPackage"));
		// 要启动的应用的起始activity
		log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-appActivity"+rw.getValue("appActivity"));
		caps.setCapability("appActivity", rw.getValue("appActivity"));
		// resetKeyBoard是执行完测试后将设备的输入法重置回原有的输入法
		caps.setCapability("unicodeKeyBoard", true);
		caps.setCapability("resetKeyBoard", true);
		// 不对app进行重签名，因为有的app在重签名之后无法使用
		caps.setCapability("noSign", true);
		// 设置session的超时时间
		log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-session超时时间600S");
		caps.setCapability("newCommandTimeout", 600);
		log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-udid超时时间-->"+UUID);
		caps.setCapability("udid", UUID);
		//log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-deviceReadyTimeout超时时间60S");
		//caps.setCapability("deviceReadyTimeout",60);

		if (rw.getValue("OSType").equalsIgnoreCase("Android")) {

			try {
				log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver,启动在【"+AppiumPort+"】端口,UUID是：【"+UUID+"】...");
				String URL = "http://"+IP+":" + AppiumPort + "/wd/hub";
				driver = new AndroidDriver<AndroidElement>(new URL(URL), caps);
				log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver成功...");
			} catch (MalformedURLException e) {
				log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver异常...");
				e.printStackTrace();
			}

		}
		
//		else {
//
//			try {
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver...");
//				driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:" + AppiumPort + "/wd/hub"), caps);
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver成功...");
//			} catch (MalformedURLException e) {
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver异常...");
//				e.printStackTrace();
//			}
//		}
		return driver;
	}
}
