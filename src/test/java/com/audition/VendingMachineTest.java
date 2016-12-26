package test.java.com.audition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.java.com.audition.VendingMachine;
import main.java.com.audition.VendingMachineState;
import main.java.com.model.Coin;
import main.java.com.model.CoinType;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.InvalidCoinException;
import main.java.com.vending.exception.ProductOutOfStockException;

public class VendingMachineTest {

	private VendingMachine machine;
	private List<Product> products;
	private List<CoinType> coins;
	private List<Product> colas, chipses, candies;
	private Product cola, chips, candy;
	private Map<String, List<Product> > productMap;

	@Before
	public void setUp() {
		cola = new Product("cola");
		chips = new Product("chips");
		candy = new Product("candy");
		products = new ArrayList<>();
		coins = new ArrayList<>();
		chipses = new ArrayList<>();
		colas = new ArrayList<>();
		machine = new VendingMachine();
				
	}

	@Test(expected = InvalidCoinException.class)
	public void shouldRejectPenny() throws InvalidCoinException  {
		final Coin coin = new Coin(CoinType.PENNEY);
		machine.acceptCoin(coin);
	}
	
	@Test(expected = ProductOutOfStockException.class)
	public void shouldThrowProductOutOfStockException() throws ProductOutOfStockException,
	       CoinTypeNotAvailableException {
		machine.selectProduct(cola);
	}

	@Test
	public void shouldDisposeCola() throws InvalidCoinException, 
	       ProductOutOfStockException, CoinTypeNotAvailableException  {
		fillCola();
		machine.acceptCoin(new Coin(CoinType.QUARTER));
		machine.acceptCoin(new Coin(CoinType.QUARTER));
		machine.acceptCoin(new Coin(CoinType.QUARTER));
		machine.acceptCoin(new Coin(CoinType.QUARTER));
		machine.selectProduct( cola );
		
	}

	@Test
	public void shouldCancelProductSelection() throws ProductOutOfStockException, 
	       InvalidCoinException, CoinTypeNotAvailableException {
		fillCola();
		machine.selectProduct(cola);
		final Coin coin = new Coin(CoinType.QUARTER);
		machine.acceptCoin(coin);
		
		final Coin dime = new Coin(CoinType.DIME);
		machine.acceptCoin(dime);
		machine.returnCoin();
	}
	
	@Test(expected = CoinTypeNotAvailableException.class)
	public void shouldEjectCoinWhenNoExactChange() throws ProductOutOfStockException, 
	       InvalidCoinException, CoinTypeNotAvailableException {
		fillChips();
		machine.setCoinState( machine.getExactChangeCoinState());
		machine.selectProduct(chips);
		machine.acceptCoin(new Coin(CoinType.QUARTER));
		machine.acceptCoin(new Coin(CoinType.DIME));
		
	}

	/**
	 * Populate Chips
	 */
	private void fillChips() {
		
		for (int i = 0; i < 4; i++) {
			products.add(chips);
		}
		
	    machine.loadProducts(products);
	}

	/**
	 * Populate Cola
	 */
	private void fillCola() {
        
		for (int i = 0; i < 8; i++) {
			products.add(cola);
		}
		machine.loadProducts(products);
		
	}

	/**
	 * Populate VendingMachine
	 */
	private void fillProductMap() {
		
		productMap.put( "cola" ,  colas);
		productMap.put( "chips", chipses);
				
	}
  
}
