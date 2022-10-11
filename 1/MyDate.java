import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class MyDate {
    private int day, month, year;
    private String dayOfTheWeek;
    MyDate(String inputDate){
        try{
            convertDate(inputDate);
        }catch(InvalidAttributeValueException e){
            //TODO
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

        String weekDay = findWeekDay(separatedDateAndDay[0]);
        if(weekDay == null){
            weekDay = findWeekDay(separatedDateAndDay[1]);
        }
        if(weekDay == null){
            throw new InvalidAttributeValueException("Impossible to find the day of week");
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
