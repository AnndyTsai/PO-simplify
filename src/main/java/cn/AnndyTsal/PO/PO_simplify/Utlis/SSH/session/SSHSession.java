/**
 * function:返回Session
 * 
 * */
package cn.AnndyTsal.PO.PO_simplify.Utlis.SSH.session;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHSession {

	private String ipAddress;
	private String username;
	private String password;
	public int port;

	public SSHSession(final String ipAddress, final String username, final String password, final int port) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	/**
	 * 获取session
	 * */

	public Session getSession(){

		JSch jsch = new JSch();
		MyUserInfo userInfo = new MyUserInfo();

		// Create and connect session.
		Session session = null;
		try {
			session = jsch.getSession(username, ipAddress, port);
			
			session.setPassword(password);
			session.setUserInfo(userInfo);
			session.connect();
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	}
	
	/**
	 * 关闭session
	 * */
	public void disConnectsession(Session session){
		
		session.disconnect();
		
	}
}
