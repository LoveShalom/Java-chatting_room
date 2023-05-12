package Server.example;

import IOstream.common.example.IOstream;
import ServerGUIs.common.example.ServerWindow;
import classentity.common.example.Message;
import classentity.common.example.MessageType;
import UserUtil.userUtil;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 服务器端的线程
 */

public class ServerThread extends Thread{
    Socket socket;
    ServerWindow serverWindow;
    public ServerThread(Socket socket,ServerWindow serverWindow){
        this.serverWindow = serverWindow;
        this.socket = socket;
    }

    static ArrayList<Socket> OnlineUserSocket =new ArrayList<>();
    static ArrayList<String> OnlineUserList =new ArrayList<>();
    @Override
    public void run(){//读消息
        while (true) {
            Object object = IOstream.readMessage(socket);
            System.out.println("Server receive:" + object);//read message
            Message mes = (Message) object;
            //处理消息
            //处理登陆消息
            if (mes.getMessageType() == MessageType.login) {
                Login(mes);

            } else if(mes.getMessageType() == MessageType.chatting){
                //处理聊天
                chatting_choice(mes);
            }else if(mes.getMessageType() ==MessageType.exist){
                logout(mes);
                try{
                    Thread.sleep(1000);
                    socket.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.interrupt();//关闭线程
                break;
            }
        }

    }

    //验证登陆消息的方法
    public boolean checkUser(Message mes){
            String name =mes.getUser();
            String password =mes.getPassword();
            //此处咱与数据库链接
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try{
                connection= userUtil.getConnections();
                String sql ="SELECT * FROM use_information WHERE username =? AND userpassword =?";
                preparedStatement =connection.prepareStatement(sql);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,password);
                resultSet =preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.println("You login successfully!Welcome!");
                    return true;
                }else{
                    System.out.println("You failed to login!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                userUtil.release(connection,preparedStatement,resultSet);
            }
       return false;
    }

    //处理登陆消息
    public void Login(Message mes){
        boolean a =checkUser(mes);
        mes.setLoginSuccess(false);
        if(a){
            //返回客户端一个登陆成功的消息
            mes.setLoginSuccess(true);
            mes.setMessageType(MessageType.login);
            IOstream.writeMessage(socket,mes);
            String username =mes.getUser();

            //添加到集合进行管理
            OnlineUserList.add(username);
            OnlineUserSocket.add(socket);
            Server.userlist.put(username,socket);

            //在服务端发送最新客户列表给客户端
            mes =new Message();
            mes.setOnlineUserList(OnlineUserList.toArray(new String[OnlineUserList.size()]));
            mes.setMessageType(MessageType.userOnlineList);
            multi_chatting(mes);

            //发送最新的上线用户信息
            mes =new Message();
            mes.setMessageType(MessageType.note);
            String text ="Welcome"+username +"!";
            mes.setNote(text);
            multi_chatting(mes);

            //刷新在线用户列表
            flushOnlineUserList();

            //发送日志消息
            ServerNews(text);
        }else{
            IOstream.writeMessage(socket,mes);
            ServerNews(mes.getUser()+ "fails to login!");

        }
    }

    //聊天的部分
    //处理私聊
    public void chatting(Message message){
        String receiver =message.getReceiver();
        String sender =message.getsender();
        Socket socket1 =Server.userlist.get(sender);
        Socket socket2 =Server.userlist.get(receiver);
        IOstream.writeMessage(socket1,message);
        IOstream.writeMessage(socket2,message);

    }

    //处理群聊
    public void multi_chatting(Message message){
        for(int a =0;a <OnlineUserSocket.size();a++){
            Socket socket1 =OnlineUserSocket.get(a);
            IOstream.writeMessage(socket1,message);
        }
    }

    //处理聊天请求
    public void chatting_choice(Message message){
        String receiver =message.getReceiver();
        if("ALL".equals(receiver)){
            multi_chatting(message);
        }else{
            chatting(message);
        }
    }

    //处理在线用户列表
    public void flushOnlineUserList(){
        JList UserList =serverWindow.pane2.OnlineUser;
        String[] users =OnlineUserList.toArray(new String[OnlineUserList.size()]);
        UserList.setListData(users);
        serverWindow.pane1.number.setText(users.length+"");
    }

    //服务器消息
    public void ServerNews(String a){
        Date date =new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String d =simpleDateFormat.format(date);
        JTextPane log =serverWindow.pane1.log;
        String log2 =log.getText();
        log.setText(log2+"\n"+d+":"+a);

    }

    //无异常退出
    public void logout(Message message){
        String username =message.getUser();
        //移除该用户
        Iterator<String> USER = OnlineUserList.iterator();
        while (USER.hasNext()){
            if(USER.next().equals(username)){
                USER.remove();
            }
        }

        //移除该socket
        Iterator<Socket> socketIterator=OnlineUserSocket.iterator();
        while (socketIterator.hasNext()){
            Socket so =socketIterator.next();
            if(socket == so){
                socketIterator.remove();
            }
        }

        //从hashmap中移除
        Server.userlist.remove(username);

        //刷新
        flushOnlineUserList();

        //下线了
        message.setMessageType(MessageType.note);
        multi_chatting(message);

        //让其他人刷新
        message.setOnlineUserList(OnlineUserList.toArray(new String[OnlineUserList.size()]));
        message.setMessageType(MessageType.userOnlineList);
        multi_chatting(message);
    }


}
