import javax.management.InvalidAttributeValueException;

public class MyDate {
    private int day, month, year;
    private String dayOfTheWeek;
    MyDate(String inputDate) throws InvalidAttributeValueException{
        try{
            convertDate(inputDate);
        }catch(InvalidAttributeValueException e){
            throw e;
        }
        
    }
    MyDate(MyDate oldDate){
        day = oldDate.getDay();
        month = oldDate.getMonth();
        year = oldDate.getYear();
        dayOfTheWeek = oldDate.getDayOfTheWeek();
    }

    private void convertDate(String inputDate) throws InvalidAttributeValueException{
        String[] separatedDateAndDay = inputDate.split(" ");
        if(separatedDateAndDay.length != 2){
            throw new InvalidAttributeValueException("Invalid separation od date and day of week");
        }

        String foundWeekDay = findWeekDay(separatedDateAndDay[0]);
        if(foundWeekDay == null){
            foundWeekDay = findWeekDay(separatedDateAndDay[1]);
            if(foundWeekDay == null){
                throw new InvalidAttributeValueException("Impossible to find the day of week");
            }else{
                try{
                    decomposeDate(separatedDateAndDay[0]);
                }catch(InvalidAttributeValueException e){
                    throw e;
                }
                dayOfTheWeek = foundWeekDay;
            }
        }else{
            try{
                decomposeDate(separatedDateAndDay[1]);
            }catch(InvalidAttributeValueException e){
                throw e;
            }
            dayOfTheWeek = foundWeekDay;
        }
    }

    private String findWeekDay(String peaceOfDate){
        switch (peaceOfDate.toLowerCase()) {
            case "monday":
                return "monday";
            case "tuesday":
                return "tuesday";
            case "wednesday":
                return "wednesday";
            case "thursday":
                return "thursday";
            case "friday":
                return "friday";
            case "saturday":
                return "saturday";
            case "sunday":
                return "sunday";
            default:
                return null;
        }
    }

    private void decomposeDate(String peaceOfDate) throws InvalidAttributeValueException{
        int dateArray[];
        try{
            dateArray = makeDateArray(peaceOfDate);
        }catch(InvalidAttributeValueException e){
            throw e;
        }


        if((dateArray[1] > 0) && (dateArray[1] < 13)){
            month = dateArray[1];
        }else{
            throw new InvalidAttributeValueException("Invalid month format");
        }

        try{
            if(dateArray[0] >= dateArray[2]){    
                validYearAndDaySet(dateArray[0], dateArray[2]);
            }else{
                validYearAndDaySet(dateArray[2], dateArray[0]);
            }
        }catch(InvalidAttributeValueException e){
            throw e;
        }

    }

    private void validYearAndDaySet(int year, int day) throws InvalidAttributeValueException{
        if((year > 0) && (year < 10000)){
            this.year = year;
            if((day > 0) && (day < 32)){
                this.day = day;
            }else{
                throw new InvalidAttributeValueException("Invalid day format");
            }
            
        }else{
            throw new InvalidAttributeValueException("Invalid year format");
        }
    }

    private int[] makeDateArray(String peaceOfDate) throws InvalidAttributeValueException{
        String separatedDateNumbersString[] = peaceOfDate.split("\\.|/|-");//TODO regex can be wrong
        if(separatedDateNumbersString.length != 3){
            throw new InvalidAttributeValueException("Invalid date numbers separating format");
        }

        int separatedDateNumbers[];
        try{
            separatedDateNumbers = new int[3];
            for(int i = 0; i < 3; i++){
                separatedDateNumbers[i] = Integer.valueOf(separatedDateNumbersString[i]);
            }
        }
        catch (NumberFormatException e){
            throw new InvalidAttributeValueException("Invalid date numbers");
        }

        return separatedDateNumbers;
    }

    
    @Override
    public String toString() {
        return "day=" + day + ", month=" + month + ", year=" + year + ", dayOfTheWeek=" + dayOfTheWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MyDate)) {
            return false;
        }

        MyDate secondDate = (MyDate)o;

        return (day == secondDate.getDay() &&
        month == secondDate.getMonth() &&
        year == secondDate.getYear() &&
        dayOfTheWeek == secondDate.getDayOfTheWeek());
    }


    public int getDay() {
        return day;
    }


    public int getMonth() {
        return month;
    }


    public int getYear() {
        return year;
    }


    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

}

/*
    in main program I should save previos date(+copy constractor) and compare(by overwriten method) if they are the same.
    If the same, than go forward, don`t save current in input file, but save it in current

    in constractor: evaluating and assigning date fields

    +*create toString() method(overwrite)
    +*create equel()

 */
