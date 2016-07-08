package app.bai.com.foodpai.bean;

/**
 * Created by 86724 on 2016/7/8 0008.
 */
public class User {
    private String userName;
    private String userIcon;
    public User (String name,String iconUrl){
        this.userIcon = iconUrl;
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
