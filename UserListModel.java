package GUIs;

import classentity.common.example.User;

import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * 用于处理列表框的列表模型
 */

public class UserListModel extends AbstractListModel<User> {
    private static long serialVersionUID =1L;
    private ArrayList<User> OnlineUsers =new ArrayList<User>();
    @Override
    public int getSize() {
        return OnlineUsers.size();
    }

    @Override
    public User getElementAt(int index) {
        return OnlineUsers.get(index);
    }
    public void addElement(User user){
        OnlineUsers.add(user);
    }
}
