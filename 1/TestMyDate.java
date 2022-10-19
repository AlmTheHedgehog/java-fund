import java.io.FileNotFoundException;

import javax.management.InvalidAttributeValueException;

public class TestMyDate {
    public static void main(String[] args){

        FileOperator operator;
        try{
            operator = new FileOperator("1/input.txt", "1/output.txt");
        }catch(FileNotFoundException e){
            return;
        }


        String inpuString = operator.getLine();
        MyDate curDate, previosDate = null; 
        while(inpuString != null){
            try{
                curDate = new MyDate(inpuString);
                if((curDate != null) && !(curDate.equals(previosDate))){
                    operator.writeLine(curDate.toString());
                    previosDate = new MyDate(curDate);
                }
            }catch(InvalidAttributeValueException e){
                curDate = null;
            }
            inpuString = operator.getLine();
        }

        operator.closeAll();
    }
}
