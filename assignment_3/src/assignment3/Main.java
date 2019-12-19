package assignment3;

import exception.InvalidDataTypeException;
import exception.NoSuchNodeException;

public class Main {
    public static void main(String []args) throws InvalidDataTypeException, NoSuchNodeException {
        DependencyGraph obj = new DependencyGraph() ;
        obj.run();
    }
}
