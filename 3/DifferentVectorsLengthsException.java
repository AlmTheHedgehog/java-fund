import java.util.ArrayList;

public class DifferentVectorsLengthsException extends Exception {
	private int firstVectorLength, secondVectorLength;
	private ArrayList<Integer> firstVectorIndex, secondVectorIndex;

	public DifferentVectorsLengthsException(String message, int firstVectorLength, int secondVectorLength) {
		super(message);
		this.firstVectorLength = firstVectorLength;
		this.secondVectorLength = secondVectorLength;
	}

	public DifferentVectorsLengthsException(String message, int firstVectorLength, int secondVectorLength,
											ArrayList<Integer> firstVectorIndex, ArrayList<Integer> secondVectorIndex) {
		this(message, firstVectorLength, secondVectorLength);
		this.firstVectorIndex = new ArrayList<Integer>();
		this.secondVectorIndex = new ArrayList<Integer>();
		this.firstVectorIndex = firstVectorIndex;
		this.secondVectorIndex = secondVectorIndex;
	}

	public int getFirstVectorLength() {
		return firstVectorLength;
	}

	public int getSecondVectorLength() {
		return secondVectorLength;
	}

	public ArrayList<Integer> getFirstVectorIndex() {
		return firstVectorIndex;
	}

	public ArrayList<Integer> getSecondVectorIndex() {
		return secondVectorIndex;
	}

}