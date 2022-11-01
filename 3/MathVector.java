import java.util.ArrayList;
import java.util.Iterator;

import javax.management.InvalidAttributeValueException;

public class MathVector implements Cloneable{

    private ArrayList<Integer> vector; 
    private final int SEPARATED_INPUT_ARR_SIZE = 2;

    MathVector(){
        vector = new ArrayList<Integer>();
    }
    MathVector(String inputString) throws InvalidAttributeValueException{
        String[] separatedInput = inputString.split(" - length is ");
        if(separatedInput.length != SEPARATED_INPUT_ARR_SIZE){
            throw new InvalidAttributeValueException("Invalid vector structure format");
        }

        int vectorLength;
        try{
            vectorLength = Integer.parseInt(separatedInput[1]);
        }catch(NumberFormatException e){
            throw new InvalidAttributeValueException("impossible to read length in vector string");
        }
        
        vector = convertVectorStringValues(separatedInput[0]);
        if(vector.size() != vectorLength){
            throw new InvalidAttributeValueException("Invalid vector length");
        }
    }

    MathVector addVector(MathVector anotherVector) throws DifferentVectorsLengthsException{
        if(compareLengthTo(anotherVector) != 0){
            throw new DifferentVectorsLengthsException("Impossible to add vectors with different length", this, anotherVector);
        }

        MathVector sumVector = null;
        try {
            sumVector = (MathVector) clone();
            for(int i = 0; i < sumVector.length(); i++){
                sumVector.vector.set(i, anotherVector.vector.get(i) + sumVector.vector.get(i));
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sumVector;
    }

    ArrayList<Integer> convertVectorStringValues(String inpuString){
        ArrayList<Integer> vectorBuilder = new ArrayList<Integer>(); 
        String[] separatedValues = inpuString.split(",");

        for(String eachValue:separatedValues){
            try{
                vectorBuilder.add(Integer.parseInt(eachValue));
            }catch(NumberFormatException e){
            }
        }

        return vectorBuilder;
    }

    public String stringCompareToLength(MathVector anotherVector){
        int cmp = compareLengthTo(anotherVector);
        String result;
        if(cmp > 0){
            result = toStringValues() + " the vector length is bigger than " + 
                    anotherVector.toStringValues() + " vector length";
        }else if(cmp == 0){
            result = "Vectors are equal";
        }else{
            result = toStringValues() + " the vector length is lower than " + 
                    anotherVector.toStringValues() + " vector length";
        }
        return result;
    }

    public int compareLengthTo(MathVector anotherVector){
        return length() - anotherVector.length();

    }

    public int length(){
        return vector.size();
    }

    public void addValue(int value){
        vector.add(value);
    }

    public void deleteLastValue(){
        vector.remove(vector.size()-1);
    }

    public String toStringValues(){
        if(vector.isEmpty()){
            return "";
        }

        StringBuilder valuesString = new StringBuilder();
        Iterator<Integer> valuesIterator = vector.iterator();
        valuesString.append(valuesIterator.next());
        while(valuesIterator.hasNext()){
            valuesString.append(", "+valuesIterator.next());
        }
        return valuesString.toString();
    }

    @Override
    public String toString() {
        return toStringValues() + " the vector length is " + vector.size();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
	    MathVector newVector = new MathVector();

        Iterator<Integer> valuesIterator = vector.iterator();
        while(valuesIterator.hasNext()){
            newVector.vector.add(valuesIterator.next());
        }
        return newVector;
    }

}