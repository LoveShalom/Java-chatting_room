package ServerGUIs.common.example;

import javax.swing.*;
import java.awt.*;

/**
 * 这一块是服务器的有关信息
 */

public class ServerInformationPane {
    //服务器信息
    public JTextField number;
    public JTextField server_name;
    public JTextField ip;
    public JTextField port;
    public JTextPane log;
    //窗体宽高
    public static final int width = 600;
    public static final int height = 600;

    public JLabel get_pane(){
        //第一个服务选项卡面板，主要包括日志区域
        JPanel Server= new JPanel();
        Server.setOpaque(false);
        Server.setLayout(null);
        Server.setBounds(0,0,width,height);

        //log
        log=new JTextPane();
        log.setForeground(Color.yellow);
        log.setBackground(Color.BLACK);
        log.setOpaque(true);
        log.setFont(new Font("PingFang SC",Font.BOLD,18));

        //log显示屏
        JScrollPane scrollPane =new JScrollPane(log);
        scrollPane.setOpaque(false);
        scrollPane.setBounds(155,40,390,390);
        scrollPane.getViewport().setOpaque(false);
        Server.add(scrollPane);//待完善

        //按钮和参数
        Server.add(close());
        Server.add(save());
        Server.add(getServerInformation());

        //加背景
        JLabel Background = new JLabel();
        ImageIcon icon = new ImageIcon("client/src/R-C.jpg");
        Background.setIcon(icon);
        Background.setBounds(0, 0, 300, 300);
        Background.setLayout(null);
        Background.add(Server);

        return Background;

    }

    public JPanel getServerInformation(){
        //服务器参数
        JPanel server=new JPanel();
        server.setOpaque(false);
        server.setBounds(12,35,120,360);
        server.setFont(new Font("PingFang SC",Font.BOLD,15));

        /**
         * 标签和文本框的处理
         */
        //UserOnline
        JLabel lnumber =new JLabel("Online:");
        lnumber.setForeground(Color.black);
        lnumber.setFont(new Font("PingFang SC",Font.BOLD,15));

        number =new JTextField("0",14);
        number.setFont(new Font("PingFang SC",Font.BOLD,15));
        number.setEditable(false);
        number.setHorizontalAlignment(JTextField.CENTER);

        server.add(lnumber);
        server.add(number);

        //server_name
        JLabel lserver_number =new JLabel("Name:");
        lserver_number.setForeground(Color.black);
        lserver_number.setFont(new Font("PingFang SC",Font.BOLD,15));

        server_name =new JTextField("0",14);
        server_name.setFont(new Font("PingFang SC",Font.BOLD,15));
        server_name.setEditable(false);
        server_name.setHorizontalAlignment(JTextField.CENTER);

        server.add(lserver_number);
        server.add(server_name);

        //ip
        JLabel lip =new JLabel("IP:");
        lip.setForeground(Color.black);
        lip.setFont(new Font("PingFang SC",Font.BOLD,15));

        ip =new JTextField("0",14);
        ip.setFont(new Font("PingFang SC",Font.BOLD,15));
        ip.setEditable(false);
        ip.setHorizontalAlignment(JTextField.CENTER);

        server.add(lip);
        server.add(ip);

        //port
        JLabel lport =new JLabel("Port:");
        lport.setForeground(Color.black);
        lport.setFont(new Font("PingFang SC",Font.BOLD,15));

        port =new JTextField("0",14);
        port.setFont(new Font("PingFang SC",Font.BOLD,15));
        port.setEditable(false);
        port.setHorizontalAlignment(JTextField.CENTER);

        server.add(lport);
        server.add(port);

        return server;

    }

    /**
     * 关闭和保存日志按钮
     */

    public JButton close(){
        JButton close = new JButton("Close");
        close.setFont(new Font("PingFang SC",Font.BOLD,15));
        close.setBackground(Color.YELLOW);
        close.setForeground(Color.black);
        close.setBounds(180,450,120,35);
        return close;
    }

    public JButton save(){
        JButton close = new JButton("Save");
        close.setFont(new Font("PingFang SC",Font.BOLD,15));
        close.setBackground(Color.YELLOW);
        close.setForeground(Color.black);
        close.setBounds(390,450,120,35);
        return close;

    }
}
