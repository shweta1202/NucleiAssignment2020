package exception;

/**
 * todo
 * 1. Read through checked, unchecked exceptions, throwable				-done
 * 2. When to extend Exception and when to extend RuntimeException		-done
 * @author shwetasolanki
 *
 */
public class InvalidItemTypeException extends Exception{
	public InvalidItemTypeException() {
		super(); 
	}
	public InvalidItemTypeException(String e) {
		System.out.println(e);
	}
}
