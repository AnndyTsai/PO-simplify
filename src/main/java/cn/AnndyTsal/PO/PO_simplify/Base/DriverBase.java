/**
 * 基类 封装基础操作方法
 * */
package cn.AnndyTsal.PO.PO_simplify.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import cn.AnndyTsal.PO.PO_simplify.Server.deviceNumbers;
import cn.AnndyTsal.PO.PO_simplify.Server.startAppiumServer;
import cn.AnndyTsal.PO.PO_simplify.Utlis.readProperties;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class DriverBase {

	private AndroidDriver<?> driver;
	private List<String> UUIDList;
	private initDriver init;
	private readProperties rw;

	private static final Logger log = LogManager.getLogger(DriverBase.class.getName());

	public DriverBase() {
		rw = new readProperties("./config/initDriver.properties");
		// 启动Appium-Server
		Map<Integer, Integer> portMap = startAppiumServer.start();

		/**
		 * 重构UUIDList 去掉"device"
		 */
		UUIDList = deviceNumbers.locateNumbers();
		for (int i = 0; i < UUIDList.size(); i++) {

			String str = UUIDList.get(i);
			String UUID = str.split("\t")[0];
			UUIDList.remove(i);
			UUIDList.add(UUID);
		}
		log.info("[Class-DriverBase] 当前连接上Appium-server的设备UUID是：" + UUIDList.toString());

		init = new initDriver();
		/**
		 * 初始化driver
		 */
		Set<Entry<Integer, Integer>> entrySet = portMap.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {

			int a = 0;

			Integer AppiumPort = entry.getKey();
			String UUID = UUIDList.get(a);			
			log.info("[Class-DriverBase] 启动的UUID是:["+UUID+"],启动的Appium端口是：["+AppiumPort+"]");
			driver = init.getDriver(UUID, AppiumPort);

			a++;
		}
	}

	/**
	 * 封装element方法
	 */
	public MobileElement findMobileElement(By by) {

		// 隐式等待
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
		
		return (MobileElement)driver.findElement(by);
	}
	
	/**
	 * 封装返回element集合的方法
	 * */
	@SuppressWarnings("unchecked")
	public List<MobileElement> findMobileElements(By by) {

		// 隐式等待
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
		
		return (List<MobileElement>) driver.findElements(by);
	}

	/**
	 * 封装closed方法
	 */
	public void close() {

		driver.closeApp();
		log.info("[Class-DriverBase][Method-close] 关闭当前APP");
	}

	/**
	 * 获取APP的宽和高 (获取APP的最大点坐标)
	 */
	public List<Integer> getXY() {

		List<Integer> WidthAndHeight = new ArrayList<>();

		int X = driver.manage().window().getSize().getWidth();
		int Y = driver.manage().window().getSize().getHeight();

		WidthAndHeight.add(X);
		WidthAndHeight.add(Y);

		return WidthAndHeight;
	}

	/**
	 * drivr的滑动 全屏滑动
	 */
	public void driverSwipe(int startx, int starty, int endx, int endy) {

		driver.swipe(startx, starty, endx, endy, 1000);
	}

	/**
	 * 对坐标点位进行长按操作
	 */
	public void LongPressPoint(int x, int y, int duration) {

		TouchAction touchAction = new TouchAction(driver);
		touchAction.longPress(x, y, duration);
	}

	/**
	 * 对元素进行长按操作
	 */
	public void LongPressElement(MobileElement element, int duration) {

		TouchAction touchAction = new TouchAction(driver);
		touchAction.longPress(element, duration);
	}

	/**
	 * 封装tap 对坐标进行点击
	 */
	public void driverTap(int fingers, int x, int y, int duration) {

		driver.tap(fingers, x, y, duration);
	}

	/**
	 * 对元素进行点击
	 */
	public void driverTap(int fingers, MobileElement element, int duration) {

		driver.tap(fingers, element, duration);
	}
	
	public static void main(String[] args) {
		
		new DriverBase();
	}
}
