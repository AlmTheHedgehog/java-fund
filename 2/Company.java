public class Company extends TelephoneEntry {

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
        this.phoneNumber = new TelephoneNumber();
    }

    public Company(String name, String address, String countryCode, String localNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    @Override
    public String description() {
        return "Company record of " + name + " " + ".\n"
                + phoneNumber.toString() + ", " + address;
    }
    
}
