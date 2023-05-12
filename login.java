package GUIs;

import Client.ClientThread;
import IOstream.common.example.IOstream;
import classentity.common.example.Message;
import classentity.common.example.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 登陆的窗口
 */
public class login extends JFrame{
    private static Integer width = 600;
    private static Integer height = 400;

    //首先写窗口
    public login() {
        //窗口
        JFrame window = new JFrame("CSC1004 Chatting Room");
        //窗体大小
        window.setSize(width, height);

        //窗体背景，太菜了我设置不明白

        JLabel Background = new JLabel();
        ImageIcon icon = new ImageIcon("client/src/R-C.jpg");
        Background.setIcon(icon);
        Background.setBounds(0, 0, width, height);
        Background.setLayout(null);
        this.add(Background);


        //用户ID
        JLabel name = new JLabel("name:");
        JTextField tf_name = new JTextField();
        //设置用户id区域的位置和大小
        name.setBounds(170, 120, 50, 30);
        tf_name.setBounds(240, 120, 160, 30);

        //用户密码
        JLabel Password = new JLabel("password:");
        JTextField tf_password = new JTextField();
        //设置用户密码区域的位置和大小
        Password.setBounds(150, 180, 80, 30);
        tf_password.setBounds(240, 180, 160, 30);

        //登陆和注册按钮
        JButton login = new JButton("login");
        JButton register = new JButton("register");
        //设置按钮的区域和大小
        login.setBounds(150, 250, 100, 30);
        register.setBounds(300, 250, 100, 30);

        //登陆事件
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //首先得到输入的账号密码
                String InputName = tf_name.getText();
                String InputPassword = tf_password.getText();
                //设置登陆信息
                Message mes =new Message();
                mes.setUser(InputName);
                mes.setPassword(InputPassword);
                mes.setMessageType(MessageType.login);
                connection(mes);
            }
        });

        //注册的部分
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });

        //添加控件
        window.add(name);
        window.add(tf_name);
        window.add(Password);
        window.add(tf_password);
        window.add(login);
        window.add(register);
        window.add(Background);

        //显示窗口
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

    }
    //链接服务器
    public void connection(Message mes){
        try{
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
            IOstream.writeMessage(socket,mes);
            //开启客户端的线程
            ClientThread clientThread =new ClientThread(socket,this);
            clientThread.start();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        new login();
    }

}
