/**
 * 启动Appium-Server 
 * 
 * 需要两个端口 Client->Server  Server->Client
 * */
package cn.AnndyTsal.PO.PO_simplify.Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class startAppiumServer {
	
	private static final Logger log = LogManager.getLogger(startAppiumServer.class.getName());
	
	public static Map<Integer, Integer> start() {	
		int i = 0 ;
		
		try {
			if(System.getProperty("os.name").contains("indow")){
				
				//executeCommand.executed("taskkill /f /t /im node.exe", false, 0);
				//log.info("[Class-startAppiumServer][Method-Map] 清除node进程成功");
				log.info("[Class-startAppiumServer][Method-Map] 清除node进程成功");
			}else{
				
				executeCommand.executed("killall -9 node", false, 0);
			}
		} catch (Exception e1) {
			//log.info("[startAppiumServer] 清除node进程失败");
			log.info("[Class-startAppiumServer][Method-Map] 清除node进程失败");
			e1.printStackTrace();
		}

		Map<Integer, Integer> port = assignmentPort.port();
		
		List<String> list = deviceNumbers.locateNumbers();
		
		//log.info("[startAppiumServer] 开始启动Appium-Server");
		log.info("[Class-startAppiumServer][Method-Map] 开始启动Appium-Server");
		
		//System.out.println(System.getProperty("user.dir"));
		
		Set set = port.entrySet();

		if (port.size() == 0) { // map集合长度为0 防护衣启动失败

			//log.info("[startAppiumServer] 启动Appium-Server失败：未获取到分配的端口");
			log.info("[Class-startAppiumServer][Method-Map] 启动Appium-Server失败：未获取到分配的端口");

		} else {

			for (Iterator iter = set.iterator(); iter.hasNext();) {
											
				Map.Entry entry = (Map.Entry) iter.next();
				Integer key = (Integer) entry.getKey();
				Integer value = (Integer) entry.getValue();
				
				String udid = list.get(i).split("\t")[0];
				i = i++;  //没循环一次 i自加1
				//启动Appium-Server appium -p 5000-bp 6000-U udid
				try {
					Date dNow = new Date( );
				    SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
					String logStr = null;
					if(System.getProperty("os.name").contains("indow")){						
						logStr = System.getProperty("user.dir")+"\\logs\\"+ft.format(dNow)+"-"+udid+".log";
					}else{						
						logStr = System.getProperty("user.dir")+"/logs/"+ft.format(dNow)+"-"+udid+".log";
					}
					String cmd = "appium -p "+key+" -bp "+value+" -U "+udid +">"+logStr;
					//启动Appium-Server 并且延时15S
					//log.info("[Class-startAppiumServer][Method-Map] Appium Server启动命令："+cmd);
					//log.info("[Class-startAppiumServer][Method-Map] Appium Server启动在【"+key+"】端口");
					//log.info("[startAppiumServer] Appium Server启动在【"+key+"】端口");
					executeCommand.executed(cmd, false, 10);
					//log.info("[Class-startAppiumServer][Method-Map] Appium Server启动延时结束");
				} catch (Exception e) {
					//log.info("[startAppiumServer] Appium Server启动异常");
					log.info("[Class-startAppiumServer][Method-Map] Appium Server启动异常");
					RuntimeException exception = new RuntimeException();
					exception.printStackTrace();
				}
			}
		}		
		//log.info("[startAppiumServer] Appium-Server启动完成");
		log.info("[Class-startAppiumServer][Method-Map] Appium-Server启动完成");
		return port;
	}
}
