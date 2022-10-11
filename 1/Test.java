import javax.management.InvalidAttributeValueException;

public class Test {
    public static void main(String[] args){

        try{
            System.out.println(new MyDate("21-21-2001 Thrsday"));
        }catch(InvalidAttributeValueException e){
            e.printStackTrace();
        }
        
    }
}
