package assignment3;
import exception.InvalidDataTypeException;
public class InputValidation extends Exception{
    public int isValidID(String nodeID) throws InvalidDataTypeException {
        try{
            return Integer.parseInt(nodeID);
        }catch(Exception e){
            throw new InvalidDataTypeException("An Integer is expected.");
        }
    }
}
