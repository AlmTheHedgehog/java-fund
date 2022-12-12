package telephoneNumber;

public abstract class TelephoneEntry{
    protected TelephoneNumber phoneNumber;
    protected String name, address;

    public abstract String description();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TelephoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(TelephoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
