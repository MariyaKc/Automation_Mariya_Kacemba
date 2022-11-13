package pageObjects.saucedemo.lombok;

import lombok.Builder;
import lombok.Data;

@Builder(builderClassName = "CheckoutBuilder", setterPrefix = "with", buildMethodName = "create")
@Data
public class Checkout {
    private String firstName;
    private String lastName;
    private String zipCode;

    public static class CheckoutBuilder {
        public CheckoutBuilder() {
        }
    }
}
