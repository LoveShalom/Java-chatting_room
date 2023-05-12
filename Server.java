package Server.example;

import ServerGUIs.common.example.ServerWindow;
import Serverentity.common.example.ServerInformation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * 服务器的启动
 */
public class Server {
    static HashMap<String, Socket> userlist =new HashMap<>();
    public ServerWindow serverWindow;
    public Server(){
        try{
            //建立服务器链接
            ServerSocket serverSocket =new ServerSocket(9999);
            serverWindow =new ServerWindow();
            ServerInformation serverInformation =getSeverInfo();
            load(serverInformation);
            //处理多客户链接
            while (true){
                Socket socket =serverSocket.accept();
                //启动线程
                ServerThread serverThread =new ServerThread(socket,serverWindow);
                serverThread.start();
                System.out.println("Get Connected:"+socket);
            }

        }catch (IOException E){
            E.printStackTrace();
        }
    }
    //获取服务器各种信息的方法
    public ServerInformation getSeverInfo(){
        ServerInformation serverInformation =new ServerInformation();
        try{
            InetAddress server =InetAddress.getLocalHost();
            byte[] ip =server.getAddress();
            serverInformation.setIp((server.getHostAddress()));
            serverInformation.setHost(server.getHostName());
            serverInformation.setPort(9999);

            System.out.println("IP is:"+(ip[0] & 0xff)+"."+(ip[1] & 0xff)+"."+(ip[2] & 0xff)+"."+(ip[3] & 0xff));

        } catch (Exception e) {
            System.out.println("We could not get IP:"+e);
        }
        return serverInformation;
    }

    public void load(ServerInformation serverInformation){
        serverWindow.pane1.ip.setText(serverInformation.getIp());
        serverWindow.pane1.server_name.setText(serverInformation.getHost());
        serverWindow.pane1.log.setText("Server Success");
        serverWindow.pane1.port.setText("9999");
    }
    public static void main(String[] args){
        new Server();
    }


}