/**
 * 功能：判断端口是否被占用 可以判断远程机器或者本机的
 * 
 * 1：Appium-server与脚本在本机的情况
 * 
 * 2：Appium-server与脚本不在同一台机器上
 * */
package cn.AnndyTsal.PO.PO_simplify.Server;

import java.util.List;

public class isPortBeUsed {
	/**
	 * 判断本机端口是否被占用
	 * 
	 * @return: false:表示端口没被占用 true：端口被占用
	 * */
	public static boolean isLocatePortUsed(int port){
		
		boolean flag = false;
		//获取OS名称
		String osName = System.getProperty("os.name");
		
		if(osName.contains("indow")){
			
			String commonds = "netstat -ano|findstr"+" \""+port+"\"";
			
			try {
				List<String> executed = executeCommand.executed(commonds, true, 0);

				if(executed.size() > 0){					
					flag = true;				
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			String commonds = "lsof -i:"+port;
			
			try {
				List<String> executed = executeCommand.executed(commonds, true, 0);
				if(executed.size() > 0){
					
					flag = true;				
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		return flag;
	}
}
