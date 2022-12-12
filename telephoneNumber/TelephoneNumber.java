package telephoneNumber;

public class TelephoneNumber implements Comparable<TelephoneNumber> {
    private String countryCode, localNumber;

    public TelephoneNumber() {
        this("00", "000000000");
    }


    public TelephoneNumber(String countryCode, String localNumber) {
        setCountryCode(countryCode);
        setLocalNumber(localNumber);        
    }

    
    @Override
    public int compareTo(TelephoneNumber anotherNumber){
        if(countryCode.compareTo(anotherNumber.countryCode) != 0){
            return countryCode.compareTo(anotherNumber.countryCode);
        }else{
            return localNumber.compareTo(anotherNumber.localNumber);
        }
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }


    public void setCountryCode(String countryCode) {
        if(countryCode.matches("\\+[0-9]+")){
            this.countryCode = countryCode.substring(1);
        }else if(countryCode.matches("[0-9]+")){
            this.countryCode = countryCode;
        }else{
            this.countryCode = "00";
        }
    }


    public void setLocalNumber(String localNumber) {
        if(localNumber.matches("[0-9]{4,}?")){
            this.localNumber = localNumber;
        }else{
            this.localNumber = "000000000";
        }
    }


    @Override
    public String toString() {
        return "+" + countryCode + localNumber;
    }

}
