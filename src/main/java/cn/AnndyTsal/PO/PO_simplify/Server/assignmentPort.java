/**
 * 分配端口
 * */
package cn.AnndyTsal.PO.PO_simplify.Server;

import java.util.HashMap;
import java.util.Map;

public class assignmentPort {	
	/**
	 * 分配端口：只做本机分配  Appium-server在远程机器存在日志收集的问题
	 * */
	public static Map<Integer,Integer> port(){
		
		Map<Integer,Integer> map = new HashMap<>();
		int numbers = deviceNumbers.locateNumbers().size();
		//随机产生端口 根据Device状态的设备
		if(numbers>0){
			while(map.size() < numbers){
				
				int a = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
				
				//检测端口是否被占用
				if(!isPortBeUsed.isLocatePortUsed(a) && !isPortBeUsed.isLocatePortUsed(a+1)){
					
					map.put(a, a+1);
				}
			}		
		}
		return map;
	}
}
