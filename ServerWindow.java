package ServerGUIs.common.example;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    //窗体宽高
    public static final int width = 600;
    public static final int height = 600;
    //选项卡
    public ServerInformationPane pane1;
    public OnlineUserInformation pane2;
    public ServerWindow(){
        setTitle("Server");
        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体属性

        //选项卡
        JTabbedPane tServer = new JTabbedPane(JTabbedPane.TOP);
        tServer.setBackground(Color.WHITE);
        tServer.setFont(new Font("PingFang SC",Font.BOLD,20));

        pane1 =new ServerInformationPane();
        pane2 =new OnlineUserInformation();
        tServer.add("Server Info",pane1.get_pane());
        tServer.add("User List",pane2.getOnlineUser());

        add(tServer);
        setVisible(true);

    }

    public static void main(String[] args){
        new ServerWindow();
    }
}
