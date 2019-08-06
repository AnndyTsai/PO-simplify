/**
 * 返回连接上Appium-server机器的设备有多少台
 * */
package cn.AnndyTsal.PO.PO_simplify.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class deviceNumbers {
	
	/**
	 * 本机执行
	 * @return:连接上的设备数量
	 * */
	public static List<String> locateNumbers(){
		
		List<String> executed = null;
		
		try {
			executed = executeCommand.executed("adb devices", true, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//对这个数据移除首位的两个数据
		executed.remove(0);
		executed.remove(executed.size()-1);
		
		//值保留attache状态为"device"的设备
		for (int i = 0 ; i < executed.size() ; i++) {
			
			if(executed.get(i).contains("device")){
				
				continue;
				
			}else{
				
				executed.remove(i);
			}
		}
		
		return executed;
	}
	
	/**
	 * Appium-server在远程机器上 判断该机器attach的设备的数量
	 * 
	 * windows Linux Mac通用的方法都可以使用socket
	 * 
	 * socket：客户端 接受服务端发送过来的device状态的连接数（attach状态为device的）
	 * */
	@Deprecated
	public static int hostNumbers(String host,int socketServerPort){
		
		int numbers = 0 ;
		
		try {
            //创建Socket对象
            Socket socket=new Socket(host,socketServerPort);
            
            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
            String info="";
            String temp=null;//临时变量
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                numbers = Integer.parseInt(info);
            }
            
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return numbers;
	}
	
	
	public static void main(String[] args) {
		
		int numbers = hostNumbers("127.0.0.1",9601);
		System.out.println(numbers);
	}

}
