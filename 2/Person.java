public class Person extends TelephoneEntry{
    private String lastName;

    public Person(String name, String lastName, String address) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = new TelephoneNumber();
    }

    public Person(String name, String lastName, String address, String countryCode, String localNumber) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    @Override
    public String description() {
        return "Personal record of " + name + " " + lastName + ".\n"
                + phoneNumber.toString() + ", " + address;
    }
    
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
