package classentity.common.example;

import java.io.Serializable;

/**
 * 消息的类
 */
public class Message implements Serializable {
    private static final long serialVersionUID =11;
    private String user;
    private String password;
    private String sender;//发送者
    private String receiver;//接受者
    private String content;//消息内容
    private MessageType messageType;//消息类型
    private Boolean LoginSuccess;//登陆成功没
    private String note;//系统发送消息
    private String[] OnlineUserList;//在线用户列表
    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
    public String getUser(){return  user;}

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Boolean getLoginSuccess() {
        return LoginSuccess;
    }

    public void setLoginSuccess(Boolean loginSuccess) {
        LoginSuccess = loginSuccess;
    }

    public String[] getOnlineUserList() {
        return OnlineUserList;
    }

    public void setOnlineUserList(String[] onlineUserList) {
        OnlineUserList = onlineUserList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getsender(){
        return sender;
    }
    public void setSender(String sender){
        this.sender =sender;
    }

    public String getReceiver(){
        return receiver;
    }
    public void setReceiver(String receiver){
        this.receiver =receiver;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content =content ;
    }


}
