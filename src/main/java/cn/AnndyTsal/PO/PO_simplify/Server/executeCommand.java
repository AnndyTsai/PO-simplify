/**
 * 功能：根据不同的系统 执行不同的命令启动Appium-server
 * */
package cn.AnndyTsal.PO.PO_simplify.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 执行DOS命令
 */
public class executeCommand {

	private static Process process;

	 private static final Logger log = LogManager.getLogger(executeCommand.class.getName());

	@Deprecated
	public static void executed(String command, int seconds) {

		String osName = System.getProperty("os.name");

		try {

			if (osName.contains("indow")) { // windows系统执行的命令如下

				log.info("[executeCommand] 当前脚本执行在Windows系统上");

				Runtime.getRuntime().exec("cmd /c " + command);

			} else {

				log.info("[executeCommand] 当前脚本执行在Mac&Linux系统上");

				String[] cmd = new String[] { "/bin/sh", "-c", command };

				Runtime.getRuntime().exec(cmd);
			}

			Thread.sleep(seconds * 1000);// 注意 这里一定要延时 不然启动不了Appium

		} catch (Exception e) {

			RuntimeException exception = new RuntimeException();
			exception.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 * @override executed
	 * 
	 */
	public static List<String> executed(String Command, boolean isgetInfo, int seconds) throws Exception {

		List<String> context = null;

		String osName = System.getProperty("os.name");

		try {
			if (osName.contains("indow")) {

				// log.info("[Class-executeCommand][Method-executed]
				// 当前脚本执行在Windows系统上");
				log.info("[Class-executeCommand][Method-executed] 当前脚本执行在Windows系统上");
				process = Runtime.getRuntime().exec("cmd /c " + Command);

			} else {

				// log.info("[executeCommand] 当前脚本执行在Mac&Linux系统上");
				log.info("[Class-executeCommand][Method-executed] 当前脚本执行在Mac&Linux系统上");
				String[] cmd = new String[] { "/bin/sh", "-c", Command };
				process = Runtime.getRuntime().exec(cmd);
			}
			if (seconds >= 0) {

				log.info("[Class-executeCommand][Method-executed] 当前Appium-Server开始延时启动中....");
			}

			Thread.sleep(seconds * 1000);// 注意 这里一定要延时 不然启动不了Appium

			// log.info("[executeCommand] 当前Appium-Server启动延时已经结束");
			log.info("[Class-executeCommand][Method-executed] 当前Appium-Server启动延时已经结束");

		} catch (Exception e) {

			RuntimeException exception = new RuntimeException();
			exception.printStackTrace();
		}

		if (isgetInfo) {

			context = new ArrayList<>();

			InputStream in = process.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line = null;

			while ((line = reader.readLine()) != null) {
				context.add(line);
			}
			process.waitFor();

			process.destroy();
		}
		log.info("[Class-executeCommand][Method-executed] 返回执行后的日志集合");

		return context;
	}
}