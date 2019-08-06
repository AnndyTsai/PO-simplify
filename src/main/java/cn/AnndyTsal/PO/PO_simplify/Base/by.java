package cn.AnndyTsal.PO.PO_simplify.Base;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import cn.AnndyTsal.PO.PO_simplify.Utlis.readProperties;

/**
 * 封装By方法
 * */
 
public class by {
	
	private readProperties properties;
	private static final Logger log = LogManager.getLogger(by.class.getName());
 
	public by(String filePath){
		
		this.properties = new readProperties(filePath);
	}
	/**
	 * 静态by方法
	 * */
	public By by(String Key){
		
		String value = properties.getValue(Key);
		
		String LocateMethon = value.split(">")[0];
		String LocateEle = value.split(">")[1];
		
		if(LocateMethon.equalsIgnoreCase("id")){
			log.info("[Class-by][Method-by] 当前定位方式是id,定位元素"+LocateEle);
			
			return By.id(LocateEle);
			
		}else if(LocateMethon.contentEquals("name")){
			
			log.info("[Class-by][Method-by] 当前定位方式是name,定位元素"+LocateEle);
			return By.name(LocateEle);
			
		}else if(LocateMethon.equalsIgnoreCase("className")){
			
			log.info("[Class-by][Method-by] 当前定位方式是className,定位元素"+LocateEle);
			return By.className(LocateEle);
			
		}else{
			
			log.info("[Class-by][Method-by] 当前定位方式是xpath,定位元素"+LocateEle);
			return By.xpath(LocateEle);
		}
		
	}
	
}
