/**
 * Home页面的业务层
 * */
package cn.AnndyTsal.PO.PO_simplify.Buss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;
import cn.AnndyTsal.PO.PO_simplify.Page.pageBase1;
import cn.AnndyTsal.PO.PO_simplify.Utlis.readProperties;
import io.appium.java_client.MobileElement;
import cn.AnndyTsal.PO.PO_simplify.Base.by;

public class HomeBuss1 {

	private pageBase1 pb1;
	private readProperties rp;
	private by by;

	private static final Logger log = LogManager.getLogger(HomeBuss1.class.getName());

	public HomeBuss1(DriverBase driver) {

		pb1 = new pageBase1(driver);
		by = new by("./config/homePage.properties");
	}

	/**
	 * 业务操作--获取top10热门话题
	 */
	public Map<Integer, String> Buss_HomePage_top10Topics() {

		Map<Integer, String> map = new HashMap<>();
		int i = 0;

		/**
		 * 执行顺序以"知乎"APP为例 
		 * 1：启动APP initDriver时启动Driver 
		 * 2：点击"热榜" 
		 * 3：点击"全站" 
		 * 4：获取话题编号 获取话题title 存入Map集合 key=编号 value=话题title 
		 * 5:屏幕向上滑动 获取话题编号 获取话题title 存入Map集合 key=编号 value=话题title(当key大于10时 结束滑动)
		 */
		pb1.click(pb1.element(by.by("isAllowed")));
		// 点击"热榜"
		pb1.click(pb1.element(by.by("hotTopics")));
		// 点击"全站"
		pb1.click(pb1.element(by.by("allTopics")));
		//获取屏幕分辨率
		List<Integer> widthAndHeight = pb1.getWidthAndHeight();
		int X = widthAndHeight.get(0);
		int Y = widthAndHeight.get(1);
		log.info("[Class-HomeBuss1][Method-Map] 获取到的屏幕分辨率为：["+X+","+Y+"]");

		while (i < 1000) { // 循环 1000是鼠标填写的 实际肯定不可能循环1000次		
			// 获取话题编号 获取话题title 存入Map集合 key=编号 value=话题title
			List<MobileElement> top10TopicsNo = pb1.elements(by.by("top10TopicsNo"));
			List<MobileElement> top10TopicsTitle = pb1.elements(by.by("top10TopicsTitle"));

			if (top10TopicsNo.size() == 0 && top10TopicsTitle.size() == 0) {

				log.info("[Class-HomeBuss1][Method-Map] 未获取到任何top10Topics");

			} else {

				for (int j = 0; j < top10TopicsNo.size(); j++) {

					map.put(Integer.parseInt(top10TopicsNo.get(j).getText()), top10TopicsTitle.get(j).getText());
					log.info("[Class-HomeBuss1][Method-Map] 获取到的top10TopicsNo为：" + top10TopicsNo.get(j).getText()
							+ "，获取到的top10TopicsTitle为：" + top10TopicsTitle.get(j).getText());
				}
				//滑动操作 X轴不变 Y轴变化 表示上下滑动  
				pb1.driverSwipe(X/2, 5*Y/10, X/2, 4*Y/10);
				
				if(map.size()>=10){ //如果map集合长度大于10 退出循环
					
					break;
				}
			}
		}		
		return map;
	}
}
