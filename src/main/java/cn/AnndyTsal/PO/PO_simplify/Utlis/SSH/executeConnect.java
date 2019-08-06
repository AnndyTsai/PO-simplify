/**
 * 连接Mac系统
 * 
 * Mac需要开启SSH服务：系统偏好设置->共享->远程登录->所有用户
 * 
 * Appium运行在Linux版的Jenkins上 远程启动Mac上的Appium-Server
 * */
package cn.AnndyTsal.PO.PO_simplify.Utlis.SSH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Session;

import cn.AnndyTsal.PO.PO_simplify.Utlis.SSH.connect.SSHConnect;
import cn.AnndyTsal.PO.PO_simplify.Utlis.SSH.session.SSHSession;

public class executeConnect {

	private static final Logger log = LogManager.getLogger(executeConnect.class.getName());

	private String host;
	private String userName;
	private String Password;

	public executeConnect(String host, String userName, String Password) {

		this.host = host;
		this.Password = Password;
		this.userName = userName;
	}

	public void connectMAC(int port, String... cmd) {

		log.info("[Class-executeConnect][Method-connectMAC] 连接" + host + ",用户名" + userName + ",端口" + port);
		SSHSession sshSession = new SSHSession(host, userName, Password, port);
		SSHConnect connect = new SSHConnect();
		Session session = sshSession.getSession();
		try {
			// connect.shellConnect(session);
			for (int i = 0; i < cmd.length; i++) {
				connect.execConnect(session, cmd[i]);
				log.info("[Class-executeConnect][Method-connectMAC] 执行命令：" + cmd[i]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			sshSession.disConnectsession(session);
			log.info("[Class-executeConnect][Method-connectMAC] connect和shellConnect关闭，执行命令结束");
		}
	}
}
