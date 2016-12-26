package main.java.com.audition;

import main.java.com.model.Coin;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.InvalidCoinException;
import main.java.com.vending.exception.ProductOutOfStockException;
/**
 * 
 * @author Yergalem
 *
 */
public interface VendingMachineState {
		public void selectProduct(Product product) throws ProductOutOfStockException, CoinTypeNotAvailableException;
		public void makeChange(Product product) throws CoinTypeNotAvailableException;
		public void acceptCoin(Coin coin) throws InvalidCoinException;
		public void returnCoin() throws CoinTypeNotAvailableException;
		
}
