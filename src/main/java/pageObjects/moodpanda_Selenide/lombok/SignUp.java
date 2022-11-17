package pageObjects.moodpanda_Selenide.lombok;

import lombok.Builder;
import lombok.Data;

@Builder(builderClassName = "SignUpBuilder", setterPrefix = "with", buildMethodName = "create")
@Data
public class SignUp {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean checkbox;

    public static class SignUpBuilder {
        public SignUpBuilder() {
        }
    }
}
