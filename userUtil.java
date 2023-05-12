package UserUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static java.lang.Class.forName;

/**
 * 连接数据库的工具
 */
public class userUtil {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream in = new FileInputStream("client/src/userdata.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            //  驱动只加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    //获取链接
    public static Connection getConnections() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
    //释放连接
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet!=null){
            try{
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement!=null){
            try{
                statement.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch(SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

}