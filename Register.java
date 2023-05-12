package GUIs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import UserUtil.userUtil;

public class Register extends JFrame {
    private static Integer width =600;
    private static Integer height =400;
    public Register() {
        setTitle("Register");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel lb =new JLabel();
        ImageIcon icon =new ImageIcon("client/src/R-C.jpg");
        lb.setIcon(icon);
        lb.setBounds(0,0,width,height);
        lb.setLayout(null);
        this.add(lb);

        //文本和按钮
        //用户ID
        JLabel name =new JLabel("name:");
        JTextField tf_name =new JTextField();
        //设置用户id区域的位置和大小
        name.setBounds(170,120,50,30);
        tf_name.setBounds(240,120,160,30);

        //用户密码
        JLabel Password =new JLabel("password:");
        JTextField tf_password =new JTextField();
        //设置用户密码区域的位置和大小
        Password.setBounds(150,180,80,30);
        tf_password.setBounds(240,180,160,30);
        //设置按钮的区域和大小
        JButton register =new JButton("submit");
        register.setBounds(240,250,100,30);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Inputname = tf_name.getText();
                String InputPassword =tf_password.getText();
                JDialog d =new JDialog();
                d.setLocationRelativeTo(null);
                d.setSize(200,200);
                String t;
                if(CheckRegister(Inputname,InputPassword)){
                    t ="Success!";

                }else{
                    t ="Fail!";
                }
                d.add(new JLabel(t));
                d.setVisible(true);
            }
        });
        lb.add(register);
        lb.add(name);
        lb.add(tf_name);
        lb.add(Password);
        lb.add(tf_password);
        setVisible(true);

    }
    //验证
    public boolean CheckRegister(String Inputname,String InputPassword){
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        try {
            connection= userUtil.getConnections();
            String sql ="INSERT INTO use_information(username,userpassword)VALUES(?,?)";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,Inputname);
            preparedStatement.setString(2,InputPassword);
            int i =preparedStatement.executeUpdate();
            if(i >0){
                return true;
            }
        } catch (SQLException throwables) {
            System.out.println("duplicate");
        }finally {
            userUtil.release(connection,preparedStatement,resultSet);
        }
        System.out.println("Done");
        return false;
    }
}
