package exception;

public class CyclicDependencyException extends Exception{
    public CyclicDependencyException() {
        super();
    }

    public CyclicDependencyException(String message) {
        super(message);
    }
}
