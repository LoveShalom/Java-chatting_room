package Client;

import GUIs.ChattingWindow;
import GUIs.ListCellRenderer;
import GUIs.UserListModel;
import GUIs.login;
import IOstream.common.example.IOstream;
import classentity.common.example.Message;
import classentity.common.example.MessageType;
import classentity.common.example.User;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

/**
 * 客户端的线程，将处理登陆，在线用户列表，无异常退出等
 */

public class ClientThread extends Thread {
    Socket socket;
    login l;
    ChattingWindow c;
    class Dialog extends JDialog{
        public Dialog(){
            //加图片
            ImageIcon imageIcon =new ImageIcon("client/src/R-C.jpg");
            JLabel background =new JLabel(imageIcon);
            add(background);
            JLabel label =new JLabel("You Fail TO Login!");
            label.setBounds(200,230,200,50);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
            setBounds(0,0,300,300);
            setLocationRelativeTo(null);
            setResizable(false);
            this.setModal(true);
            setVisible(true);
        }
    }
    public ClientThread(Socket socket,login l){
        this.socket =socket;
        this.l =l;
    }
    @Override
    public void run(){
        while (true) {
            Object o = IOstream.readMessage(socket);
            System.out.println(o);
            //处理读到的消息

                Message mes = (Message) o;
                if (mes.getMessageType() == MessageType.login) {//处理登陆信息
                    login_result(mes);

                } else if (mes.getMessageType() == MessageType.chatting) {//处理私聊消息
                    chatting(mes);
                } else if (mes.getMessageType() == MessageType.note) {//处理系统消息
                    note(mes);
                } else if (mes.getMessageType() == MessageType.userOnlineList) {//获取在线用户列表
                    onLineUserList(mes);
                }

        }
    }

    //处理登陆信息
    public void login_result(Message message){
        if(message.getLoginSuccess()){
            String user =message.getUser();
            c =new ChattingWindow(user,socket);//可能要改，稍后看看
            //关闭login
            l.dispose();
        }else{
            System.out.println("You fail to login.");
            System.out.println(message.getNote());//获得系统消息
            new Dialog();
        }
    }

    //处理聊天消息
    public void chatting(Message message){
        String sender =message.getsender();
        String text =c.accept.getText();
        c.accept.setText(text+"\n"+">>"+sender+":"+message.getContent());
    }
    //系统消息
    public void note(Message message){
        //显示系统消息
        String text =c.accept.getText();
        if(!text.isEmpty()){
            c.accept.setText(text+"\n"+message.getNote());
        }else {
            c.accept.setText(message.getNote());
        }
    }

    //获取在线用户列表
    public void onLineUserList(Message message){
        String[] onlineuserlist =message.getOnlineUserList();
        UserListModel model =new UserListModel();
        for(String name : onlineuserlist){
            User user =new User();
            user.setId(name);
            user.setIconpath("client/src/emo.jpeg");
            System.out.println(user.getIconpath());
            user.setMotto("csc1004");
            model.addElement(user);
        }
        ChattingWindow.OnlineUser.setModel(model);
        ChattingWindow.OnlineUser.setCellRenderer(new ListCellRenderer());

    }


}