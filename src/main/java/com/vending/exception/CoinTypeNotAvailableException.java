package main.java.com.vending.exception;

/**
 * 
 * @author Yergalem
 *
 */
public class CoinTypeNotAvailableException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CoinTypeNotAvailableException(final String message){
		super(message);
	}
	
	public CoinTypeNotAvailableException(){
		this(" Coin isn't available in the Stock!");
	}

}
