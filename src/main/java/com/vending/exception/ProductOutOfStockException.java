package main.java.com.vending.exception;

/**
 * 
 * @author Yergalem
 *
 */
public class ProductOutOfStockException extends Exception {
	
	public ProductOutOfStockException(final String message){
		super(message);
	}
	
	public ProductOutOfStockException(){
		this("Product is out of stock!, Select another product");
	}
}
