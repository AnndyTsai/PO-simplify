/**
 * Shell方式he Exec方法运行
 * */
package cn.AnndyTsal.PO.PO_simplify.Utlis.SSH.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import cn.AnndyTsal.PO.PO_simplify.Utlis.SSH.session.SSHSession;


public class SSHConnect {
	
	private static final Logger log = LogManager.getLogger(SSHConnect.class.getName());
	public void shellConnect(Session session ,String cmd) {

		ChannelShell channelShell = null;

		try {

			// Create and connect channel.
			log.info("[Class-SSHConnect][Method-shellConnect] 创建和连接shell通道");
			channelShell = (ChannelShell) session.openChannel("shell");
			channelShell.connect();

			OutputStream outputStream = channelShell.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			/**
			 * 改用printWriter.println方法可以不用写 \n\r
			 */
			// String cmd = "cd /mvsp/ \n\r";
			/*
			 * String cmd = "cd /mvsp/mysql/ \n\r";
			 * outputStream.write(cmd.getBytes()); String cmd2 =
			 * "./bin/mysql -uroot -p123456789 \n\r";
			 * outputStream.write(cmd2.getBytes()); String cmd3 =
			 * "show databases; \n\r"; outputStream.write(cmd3.getBytes());
			 * 
			 * String cmd4 = "exit \n\r"; outputStream.write(cmd4.getBytes());
			 * outputStream.write(cmd4.getBytes()); outputStream.flush();
			 */

			// printWriter.println("ls");
			printWriter.println(cmd);
			printWriter.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(channelShell.getInputStream()));

			String buf = null;
			StringBuffer sb = new StringBuffer();
			while ((buf = reader.readLine()) != null) {
				sb.append(buf);
				System.out.println(buf);// 打印控制台输出
			}
			reader.close();

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Disconnect the channel and session.
			channelShell.disconnect();
		}
	}

	/**
	 * exec方式执行linux命令
	 */
	public List<String> execConnect(Session session, String cmd) {

		ChannelExec channelExec = null;

		List<String> list = new ArrayList<>();

		try {

			// Create and connect channel.
			channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(cmd);
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			channelExec.connect();

			// InputStream inputStream = channelExec.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));

			String buf = null;
			StringBuffer sb = new StringBuffer();
			while ((buf = reader.readLine()) != null) {
				sb.append(buf);
				System.out.println(buf);// 打印控制台输出
				list.add(buf);

			}
			reader.close();

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Disconnect the channel and session.
			channelExec.disconnect();
		}

		// System.out.println(list.toString());

		return list;
	}

	/**
	 * Test
	 */
	public static void main(String[] args) throws JSchException {

		SSHSession sshSession = new SSHSession("192.168.1.6", "yangbin", "123456", 22);

		SSHConnect connect = new SSHConnect();

		Session session = sshSession.getSession();
		try {
			//connect.shellConnect(session);
			connect.execConnect(session, "mkdir appium");
			connect.execConnect(session, "touch /Users/yangbin/appium/log.txt");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			sshSession.disConnectsession(session);
		}
	}

}
