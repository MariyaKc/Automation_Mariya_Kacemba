package pageObjects.moodpanda.entity;

/** реализауия Value Object*/
public class SignUp {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean checkbox;

    public SignUp() {

    }

    public SignUp(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public SignUp(String firstName, String lastName, String email, String password, Boolean checkbox) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.checkbox = checkbox;
    }

    public SignUp(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }
}
