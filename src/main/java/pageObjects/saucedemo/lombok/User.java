package pageObjects.saucedemo.lombok;

import lombok.Builder;
import lombok.Data;

@Builder (builderClassName = "UserBuilder", setterPrefix = "with", buildMethodName = "create")
@Data
public class User {
    private String username;

    private String password;

    public static class UserBuilder {
        public UserBuilder(){
        }
    }
}
