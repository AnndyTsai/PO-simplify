/**
 * Page页面的基类 所有页面都需要继承
 * */
package cn.AnndyTsal.PO.PO_simplify.Page;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;
import io.appium.java_client.MobileElement;

public class pageBase1 {

	private static final Logger log = LogManager.getLogger(pageBase1.class.getName());
	private DriverBase driver;
	private MobileElement element;
	private List<MobileElement> list;

	/**
	 * 构造方法 初始化DriverBase对象
	 */
	public pageBase1(DriverBase driver) {

		this.driver = driver;
	}
	
	/**
	 * element方法
	 */
	public MobileElement element(By by) {

		try {

			element = driver.findMobileElement(by);

			log.info("[Class-pageBase1][Method-element] 元素为" + element.toString());

		} catch (Exception e) {

			log.info("[Class-pageBase1][Method-element] 未定位到元素");
		}

		return element;
	}

	/**
	 * element集合的方法
	 */
	public List<MobileElement> elements(By by) {

		try {

			list = driver.findMobileElements(by);

			log.info("[Class-pageBase1][Method-elements] 元素为" + list.toString());

		} catch (Exception e) {

			log.info("[Class-pageBase1][Method-elements] 未定位到元素");
		}

		return list;
	}

	/**
	 * 封装click方法
	 */
	public void click(MobileElement element) {

		if (element == null) {

			log.info("[Class-pageBase1][Method-click] 元素为空 无法点击操作");

		} else {

			element.click();
			log.info("[Class-pageBase1][Method-click] 点击元素-->" + element.toString());
		}
	}

	/**
	 * 封装sendkeys方法
	 */
	public void sendKeys(MobileElement element, String value) {

		if (element == null) {

			log.info("[Class-pageBase1][Method-click] 元素为空 无法输入-->" + value);

		} else {

			element.clear();
			element.sendKeys(value);
			log.info("[Class-pageBase1][Method-sendKeys] " + element.toString() + "元素输入" + value);
		}

	}
	/**
	 * 封装滑动的操作
	 */
	public void driverSwipe(int startx, int starty, int endx, int endy) {

		driver.driverSwipe(startx, starty, endx, endy);

		log.info("[Class-pageBase1][Method-driverSwipe] 滑动坐标从[" + startx + " " + starty + "]-->[" + endx + " " + endy
				+ "]");
	}
	/**
	 * 封装获取APP高度 宽度的操作
	 * 
	 * @return List集合长度为2*n 基数表示X轴宽度； 偶数表示Y轴 高度
	 */
	public List<Integer> getWidthAndHeight() {

		log.info("[Class-pageBase1][Method-getWidthAndHeight] 获取到分辨率为：" + driver.getXY().toString());

		return driver.getXY();
	}

	/**
	 * 关闭APP
	 */
	public void closeAPP() {

		driver.close();
		log.info("[Class-pageBase1][Method-closeAPP] 关闭APP，结束当前执行...");
	}

	/**
	 * 对APP坐标的长按操作
	 */
	public void LongPressPoint(int x, int y, int duration) {

		driver.LongPressPoint(x, y, duration);
		log.info("[Class-pageBase1][Method-LongPressPoint] 对坐标[" + x + "," + y + "]进行长按操作，延时" + duration + "毫秒");
	}

	/**
	 * 对元素进行长按操作
	 */
	public void LongPressElement(MobileElement element, int duration) {

		if (element == null) {

			log.info("[Class-pageBase1][Method-LongPressPoint] 元素为空 无法进行长按操作");

		} else {

			driver.LongPressElement(element, duration);
			log.info("[Class-pageBase1][Method-LongPressPoint] 对元素" + element.toString() + "进行长按操作，延时" + duration
					+ "毫秒");
		}
	}

	/**
	 * 对坐标进行点击
	 */
	public void tapPoint(int fingers, int x, int y, int duration) {

		driver.driverTap(fingers, x, y, duration);
		log.info("[Class-pageBase][Method-LongPressPoint] " + fingers + "根手指对坐标[" + x + "," + y + "]进行点击操作，延时"
				+ duration + "毫秒");
	}

	/**
	 * 对元素进行点击操作
	 */
	public void tapElement(int fingers, MobileElement element, int duration) {

		if (element == null) {

			log.info("[Class-pageBase][Method-LongPressPoint] 元素为空 无法进行点击操作");

		} else {
			driver.driverTap(fingers, element, duration);
			log.info("[Class-pageBase][Method-LongPressPoint] " + fingers + "根手指对元素" + element.toString() + "进行点击操作，延时"
					+ duration + "毫秒");
		}
	}
}
