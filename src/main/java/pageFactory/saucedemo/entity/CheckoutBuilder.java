package pageFactory.saucedemo.entity;

public class CheckoutBuilder {
    private String firstName;
    private String lastName;
    private String zipCode;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public static class Builder {
        private CheckoutBuilder checkoutBuilder;

        public Builder() {
            this.checkoutBuilder = new CheckoutBuilder();
        }

        public CheckoutBuilder build() {
            return checkoutBuilder;
        }

        public Builder withFirstName(String firstName) {
            checkoutBuilder.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            checkoutBuilder.lastName = lastName;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            checkoutBuilder.zipCode = zipCode;
            return this;
        }
    }
}
