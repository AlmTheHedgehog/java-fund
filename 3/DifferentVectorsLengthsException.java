public class DifferentVectorsLengthsException extends Throwable {
	private MathVector firstVector, secondVector;

    public DifferentVectorsLengthsException() {
		super();
	}

	public DifferentVectorsLengthsException(String message, Throwable cause) {
		super(message, cause);
	}

	public DifferentVectorsLengthsException(String message) {
		super(message);
	}

	public DifferentVectorsLengthsException(String message, MathVector firstVector, MathVector secondVector) {
		super(message);
		this.firstVector = firstVector;
		this.secondVector = secondVector;
	}

	public DifferentVectorsLengthsException(Throwable cause) {
		super(cause);
	}

	public MathVector getFirstVector() {
		return firstVector;
	}

	public MathVector getSecondVector() {
		return secondVector;
	}
}