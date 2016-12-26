package main.java.com.vending.exception;

/**
 * 
 * @author Yergalem
 *
 */
public class InvalidCoinException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidCoinException(final String message){
		super(message);
	}

	public InvalidCoinException(){
		this("Invalid Coin - Insert nickels, dimes or quarters only ");
	}
}
