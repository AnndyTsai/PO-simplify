/**
 * 测试用例
 * */
package cn.AnndyTsal.PO.PO_simplify.Case;

import java.util.Map;

import org.testng.annotations.Test;

import cn.AnndyTsal.PO.PO_simplify.Base.DriverBase;
import cn.AnndyTsal.PO.PO_simplify.Buss.HomeBuss1;

public class HomeTestCase1 extends CaseBase{
	
	private DriverBase driver;
	private HomeBuss1 hb1;
	
	public HomeTestCase1(){
		
		this.driver = initDriver();		
		hb1 = new HomeBuss1(driver);	
	}
	
	@Test
	public void case1(){
		
		Map<Integer, String> map = hb1.Buss_HomePage_top10Topics();
		System.out.println(map.toString());
	}
}