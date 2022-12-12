package vectors;

import java.util.ArrayList;
import java.util.Iterator;

import javax.management.InvalidAttributeValueException;

public class MathVector implements Cloneable{

    private ArrayList<Integer> vector;
    private ArrayList<Integer> vectorIndexs;
    private final int SEPARATED_INPUT_ARR_SIZE = 2;

    MathVector(){
        vector = new ArrayList<Integer>();
    }
    MathVector(String inputString, int vectorIndex) throws InvalidAttributeValueException{
        vectorIndexs = new ArrayList<Integer>();
        vectorIndexs.add(vectorIndex);
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
            throw new DifferentVectorsLengthsException("Impossible to add vectors with different length", 
                                                        this.length(), anotherVector.length(), 
                                                        this.vectorIndexs, anotherVector.vectorIndexs);
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
        sumVector.vectorIndexs.add(anotherVector.vectorIndexs.get(0));
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

        newVector.vectorIndexs = new ArrayList<Integer>();
        Iterator<Integer> indexesIterator = vectorIndexs.iterator();
        while(indexesIterator.hasNext()){
            newVector.vectorIndexs.add(indexesIterator.next());
        }

        Iterator<Integer> valuesIterator = vector.iterator();
        while(valuesIterator.hasNext()){
            newVector.vector.add(valuesIterator.next());
        }
        return newVector;
    }

}