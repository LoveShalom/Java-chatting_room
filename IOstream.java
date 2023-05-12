package IOstream.common.example;

import java.io.*;
import java.net.Socket;

/**
 * 此类用于读写消息
 */

public class IOstream {
    //read message
    public static Object readMessage(Socket socket) {
        Object o = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            o = in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;//返回读到的对象

    }

    //write message
    public static void writeMessage(Socket socket, Object mes){
        try {
            ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
