import java.io.IOException;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class MathVectorTest {
    static FileOperator operator;
    static int numberOfVectors;

    public static void main(String[] args){
        try{
            operator = new FileOperator("output.txt");
            numberOfVectors = Integer.parseInt(args[0]);
            operator.writeLine(vectorsProcessing().toString());
            operator.closeAll();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Provide a number of vectors as an argument!");
            return;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    static MathVector vectorsProcessing(){
        boolean isInvalidEnter = true;
        MathVector sumVector = null;
        ArrayList<MathVector> vectorsList;
        while(isInvalidEnter){
            System.out.println("Enter " + numberOfVectors + " vectors, please:");
            vectorsList = readVectors();
            if(numberOfVectors > 1){
                isInvalidEnter = false;
                try{
                    sumVector = vectorsAdding(vectorsList);
                }catch(DifferentVectorsLengthsException e){
                    isInvalidEnter = true;
                }
            }else{
                return vectorsList.get(0);
            }
        }
        return sumVector;
    }

    static MathVector vectorsAdding(ArrayList<MathVector> vectorsList) throws DifferentVectorsLengthsException{
        MathVector sum = new MathVector();
        try{
            sum = vectorsList.get(0).addVector(vectorsList.get(1));
            for(int i = 2; i < numberOfVectors; i++){
                sum = sum.addVector(vectorsList.get(i));
            }
        }catch(DifferentVectorsLengthsException e){
            vectorsCmpPrinting(e.getFirstVectorLength()-e.getSecondVectorLength(), 
                                e.getFirstVectorIndex(), e.getSecondVectorIndex());
            System.out.println("Try one more time");
            throw e;
        }
        return sum;
    }

    static void vectorsCmpPrinting(int cmp, ArrayList<Integer> v1, ArrayList<Integer> v2){
        if(cmp > 0){
            System.out.println(v1.toString() + " the vector length is bigger than " + 
            v2.toString() + " vector length");
        }else{
            System.out.println(v1.toString() + " the vector length is lower than " + 
            v2.toString() + " vector length");
        }
    }

    static ArrayList<MathVector> readVectors(){
        ArrayList<MathVector> list = new ArrayList<MathVector>();
        boolean isInvalidInput = true;
        for(int i = 0; i < numberOfVectors; i++){
            System.out.println("Enter vector #" + (i+1) + ":");
            while(isInvalidInput){
                try{
                    isInvalidInput = false;
                    list.add(new MathVector(operator.getLine(), i));
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
