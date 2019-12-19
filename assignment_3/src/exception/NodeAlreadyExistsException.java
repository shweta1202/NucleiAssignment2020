package exception;

public class NodeAlreadyExistsException extends Exception{
    public NodeAlreadyExistsException(){
        super();
    }
    public NodeAlreadyExistsException(String message){
        super(message);
    }
}
