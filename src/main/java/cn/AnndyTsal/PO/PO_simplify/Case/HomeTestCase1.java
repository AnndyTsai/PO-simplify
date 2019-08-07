/**
 * 测试用例
 * */
package cn.AnndyTsal.PO.PO_simplify.Case;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;
import cn.AnndyTsal.PO.PO_simplify.Buss.HomeBuss1;


@Listeners(cn.AnndyTsal.PO.PO_simplify.Listener.Assert.AssertListener.class)
public class HomeTestCase1 extends CaseBase{
	
	private DriverBase driver;
	private HomeBuss1 hb1;
	/**
	 * get set
	 * */
	public DriverBase getDriver() {
		return driver;
	}

	public void setDriver(DriverBase driver) {
		this.driver = driver;
	}

	public HomeBuss1 getHb1() {
		return hb1;
	}

	public void setHb1(HomeBuss1 hb1) {
		this.hb1 = hb1;
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
		
		hb1.loginBuss("11221514596", "1122334455");
		
		System.out.println("testCase--Driver"+driver);
	}	
	/**
	@Test
	public void Demo1(){
		
		String name = "+测试工程师+";
		System.out.println("===================");
		Assertion.verifyNulls(false, true);
	}
	*/
}