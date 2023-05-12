package classentity.common.example;

import java.io.Serializable;

/**
 * 用户的信息
 */

public class User implements Serializable {
    private String id;
    private String Password;
    private String motto;
    private String iconpath;
    private static final long serialVersionUID =1L;//兼容性

    public static long getSerialVersionUID(){
        return serialVersionUID;
    }

    public void setId(String id){
        this.id =id;
    }
    public String getId(){
        return id;
    }

    public String getPassword(){
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public void setMotto(String motto){
       this.motto =motto;
    }
    public String getMotto(){
        return  motto;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }
}

