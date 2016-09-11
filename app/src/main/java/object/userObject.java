package object;

/**
 * Created by Nguyen Van Tinh on 07/09/2016.
 */
public class userObject {
    private String userId;
    private String userPassword;
    private String name;
    private String birthday;
    private String right;

    public userObject(String userId, String userPassword, String name, String birthday, String right) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.birthday = birthday;
        this.right = right;
    }

    public userObject(String userId, String userPassword, String name) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.birthday = "";
        this.right = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
