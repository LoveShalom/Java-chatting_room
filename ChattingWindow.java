package GUIs;

import IOstream.common.example.IOstream;
import classentity.common.example.Message;
import classentity.common.example.MessageType;
import classentity.common.example.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

/**
 * 聊天的GUI界面设计
 */

public class ChattingWindow extends JFrame{
    //窗口的宽高
    private static final long serialVersionUID =1L;
    public static final int width =800;
    public static final int height =700;

    //消息接收框和当前在线用户列表
    public JTextPane accept;
    public static JList OnlineUser;

    //其他需要的参数
    String name;
    Socket socket;
    JTextPane send;
    JComboBox<String> receiver;
    ChattingWindow chattingWindow;


    public ChattingWindow(String name,Socket socket){
        this.name=name;
        this.socket =socket;
        //窗体的一些属性
        setTitle("CSC1004 ChattingRoom");
        chattingWindow=this;
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //加背景
        JLabel Background = new JLabel();
        ImageIcon icon = new ImageIcon("client/src/R-C.jpg");
        Background.setIcon(icon);
        Background.setBounds(0, 0, width, height);
        Background.setLayout(null);
        add(Background);

        //接收部分
        //框
        accept=new JTextPane();
        accept.setOpaque(true);
        accept.setFont(new Font("PingFang SC",Font.BOLD,16));
        accept.setEditable(false);
        //滚动条
        JScrollPane scrollPane =new JScrollPane(accept);
        scrollPane.setOpaque(false);
        scrollPane.setBounds(225,20,500,332);
        scrollPane.getViewport().setOpaque(false);
        Background.add(scrollPane);

        //在线用户列表
        OnlineUser =new JList();
        OnlineUser.setVisibleRowCount(17);
        OnlineUser.setFixedCellWidth(180);
        OnlineUser.setFixedCellHeight(60);
        OnlineUser.setFont(new Font("PingFang SC",Font.BOLD,14));
        JPopupMenu menu =new JPopupMenu();
        JMenuItem pchat =new JMenuItem("Private");
        pchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object receiver1 =OnlineUser.getSelectedValue();
                if(receiver1 instanceof User u){
                    String receiver2 =u.getId();
                    receiver.removeAllItems();
                    receiver.addItem("ALL");
                    receiver.addItem(receiver2);
                    receiver.setSelectedItem(receiver2);

                }
            }
        });
        menu.add(pchat);

        //确认是右键
        OnlineUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //监听左右键
                if(e.isMetaDown() && OnlineUser.getSelectedIndex()>=0){
                    //弹出菜单
                    menu.show(OnlineUser,e.getX(),e.getY());
                }
            }
        });
        JScrollPane spuser =new JScrollPane(OnlineUser);
        spuser.setFont(new Font("PingFang SC",Font.BOLD,15));
        spuser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spuser.setBounds(15,17,200,507);
        Background.add(spuser);

        //输入框
        send =new JTextPane();
        send.setOpaque(true);
        send.setFont(new Font("PingFang SC",Font.BOLD,16));
        //输入滚动条
        JScrollPane scrollPane1 =new JScrollPane(send);
        scrollPane1.setOpaque(false);
        scrollPane1.setBounds(225,400,500,122);
        scrollPane1.getViewport().setOpaque(false);
        Background.add(scrollPane1);

        //私聊
        JLabel l = new JLabel("Chat to:");
        l.setBounds(505,362,80,25);
        l.setFont(new Font("PingFang SC",Font.BOLD,13));
        Background.add(l);
        //选择
        receiver=new JComboBox<>();
        receiver.addItem("All");
        receiver.setBounds(580,362,150,25);
        receiver.setSelectedItem("All");
        Background.add(receiver);

        //发送按钮
        JButton SEND =new JButton("SEND");
        SEND.setBounds(605,545,125,30);
        SEND.setForeground(Color.BLACK);
        SEND.setBackground(Color.YELLOW);
        SEND.setFont(new Font("PingFang SC",Font.BOLD,16));
        SEND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text =send.getText();
                Message mes =new Message();
                mes.setContent(text);
                mes.setSender(name);
                String re ="ALL";
                Object RE = receiver.getSelectedItem();
                if(RE !=null){
                    re =String.valueOf(RE);
                }
                System.out.println("Message:"+re);

                //处理接受者
                mes.setReceiver(re);
                mes.setMessageType(MessageType.chatting);
                IOstream.writeMessage(socket,mes);
                send.setText("");
            }
        });
        Background.add(SEND);
        //注意因为要实现无异常退出，所以GUI部分也要有处理无异常退出的部分
        //客户端关闭窗体也退出
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    System.out.println(name+"Close");
                    Message message =new Message();
                    message.setUser(name);
                    message.setMessageType(MessageType.exist);
                    message.setNote(name+" leave");
                    IOstream.writeMessage(socket,message);
                }catch (Exception E){
                    E.printStackTrace();
                }
                super.windowClosing(e);
            }
        });
        setVisible(true);


    }

}
