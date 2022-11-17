package pageFactory.saucedemo.entity;

/**
 * Value Object  pattern
 */
public class Login_VObject {

    private String username;
    private String password;

    public Login_VObject() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
