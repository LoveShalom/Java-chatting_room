package GUIs;

import classentity.common.example.User;
import javax.swing.*;
import java.awt.*;

/**
 * 图形渲染器
 */

public class ListCellRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID =1L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof User){
            User u =(User)value;
            String name =u.getId();
            String motto=u.getMotto();
            String iconpath =u.getIconpath();

            ImageIcon icon =new ImageIcon(iconpath);
            icon.setImage(icon.getImage().getScaledInstance(55,55,Image.SCALE_DEFAULT));
            setIcon(icon);
            String text ="<html><body><span color='#00B8D8' style='font-size:16px;'>"+name+ "</span><br/>"+motto+"</body></html>";
            setText(text);

            //设置一下点击后如何处理
            if(isSelected){
                setBackground(Color.lightGray);
            }else{
                setForeground(Color.gray);
            }
            setVerticalTextPosition(SwingConstants.TOP);
            setHorizontalTextPosition(SwingConstants.RIGHT);
        }
        return this;
    }
}
