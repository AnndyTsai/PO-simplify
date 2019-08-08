/**
 * 测试用例
 * */
package cn.AnndyTsal.PO.PO_simplify.Case;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;
import cn.AnndyTsal.PO.PO_simplify.Buss.HomeBuss1;
import cn.AnndyTsal.PO.PO_simplify.Listener.shotScreen.Annotations.screenshot;


@Listeners(cn.AnndyTsal.PO.PO_simplify.Listener.Assert.AssertListener.class)
public class HomeTestCase1 extends CaseBase{
	
	private DriverBase driver;
	private HomeBuss1 hb1;
	
	/**
	 * getDriver方法
	 * */
	public DriverBase getDriver() {
		return driver;
	}
	/**
	 * 设计模式为一个测试点一条用例 每次都需要关闭APP 启动APP
	 * @return 
	 * */
	@BeforeTest
	public void init(){
		
		driver = initDriver();		
		hb1 = new HomeBuss1(driver);	
	}
	
	@AfterTest
	public void closeAPP(){
		
		driver.close();
	}
	
	@Test
	public void loginCase(){
		
		Reporter.log("执行登录操作测试用例");
		hb1.loginBuss("11221514596", "1122334455");
		
	}	
	
}