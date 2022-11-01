import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class MathVectorTest {
    static FileOperator operator;
    static int numberOfVectors;
    static ArrayList<MathVector> vectorsList;

    public static void main(String[] args){
        args = new String[1]; args[0] = "3"; //TODO to run: 1)delete this line, 2)change FileOutputPath in FileOperator into "output.txt", 3)open folder 3 as a project folder, 4)run "javac *.java", 5) run "java MathVectorTest [number of vectors]"
        MathVector sumVector = new MathVector();
        boolean isInvalidEnter = true;
        operator = new FileOperator("output.txt");
        try{
            numberOfVectors = Integer.parseInt(args[0]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Provide a number of vectors as an argument!");
            return;
        }
        

        while(isInvalidEnter){
            System.out.println("Enter " + numberOfVectors + " vectors, please:");
            vectorsList = readVectors();
            if(numberOfVectors > 1){
                isInvalidEnter = false;
                try{
                    sumVector = vectorsList.get(0).addVector(vectorsList.get(1));
                    for(int i = 2; i < numberOfVectors; i++){
                        sumVector = sumVector.addVector(vectorsList.get(i));
                    }
                }catch(DifferentVectorsLengthsException e){
                    System.out.println(e.getFirstVector().stringCompareToLength(e.getSecondVector()));
                    System.out.println("Try one more time");
                    isInvalidEnter = true;
                }
            }else{
                System.out.println("There are not enough vectors to add them");
                return;
            }
        }

        operator.writeLine(sumVector.toString());
        operator.closeAll();
    }

    static ArrayList<MathVector> readVectors(){
        ArrayList<MathVector> list = new ArrayList<MathVector>();
        boolean isInvalidInput = true;
        for(int i = 0; i < numberOfVectors; i++){
            System.out.println("Enter vector #" + (i+1) + ":");
            while(isInvalidInput){
                try{
                    isInvalidInput = false;
                    list.add(new MathVector(operator.getLine()));
                }catch(InvalidAttributeValueException e){
                    isInvalidInput = true;
                    System.out.println(e.getMessage());
                    System.out.println("Reenter this vector:");
                }
            }
            isInvalidInput = true;
        }
        return list;
    }
}
