package ServerGUIs.common.example;

import javax.swing.*;
import java.awt.*;

public class OnlineUserInformation {
    public JList OnlineUser;
    public JLabel getOnlineUser(){
        JPanel puser =new JPanel();
        //属性
        puser.setLayout(null);
        puser.setBackground(Color.YELLOW);
        puser.setBounds(30,5,480,400);
        puser.setOpaque(false);

        JLabel luser =new JLabel("Online User List");
        luser.setFont(new Font("PingFang SC",Font.BOLD,20));
        luser.setBounds(20,10,200,30);
        luser.setForeground(Color.YELLOW);
        puser.add(luser);

        //处理用户列表
        OnlineUser =new JList();
        //设置列表行数，宽度，高度
        OnlineUser.setVisibleRowCount(17);
        OnlineUser.setFixedCellHeight(35);
        OnlineUser.setFixedCellWidth(180);
        //其他属性
        OnlineUser.setFont(new Font("PingFang SC",Font.BOLD,16));
        OnlineUser.setOpaque(false);

        JScrollPane suser =new JScrollPane(OnlineUser);
        suser.setBounds(30,45,440,360);
        suser.setOpaque(false);
        suser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        suser.setFont(new Font("PingFang SC",Font.BOLD,14));
        puser.add(suser);

        //设置背景
        JLabel Background = new JLabel();
        ImageIcon icon = new ImageIcon("client/src/R-C.jpg");
        Background.setIcon(icon);
        Background.setBounds(0, 200, 300, 300);
        Background.setLayout(null);
        Background.add(puser);

        return Background;
    }
}
