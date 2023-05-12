package classentity.common.example;

/**
 * 消息类型与状态
 */
public enum MessageType {
    login(1,"LOGIN"),
    note(2,"News"),
    chatting(3,"chatting"),
    userOnlineList(4,"OnlineUsers"),
    exist(5,"Exist");
    private Integer statue;
    private String oder;
    private MessageType(int statue,String oder){
        this.statue =statue;
        this.oder =oder;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getOder() {
        return oder;
    }

    public void setOder(String oder) {
        this.oder = oder;
    }
}
