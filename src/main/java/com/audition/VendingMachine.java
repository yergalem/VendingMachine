package main.java.com.audition;

import java.util.List;
import java.util.Map;

import main.java.com.audition.states.ExactChangeOnlyState;
import main.java.com.audition.states.HasCoinState;
import main.java.com.audition.states.NoCoinState;
import main.java.com.audition.states.SoldOutState;
import main.java.com.inventory.CoinInventory;
import main.java.com.inventory.ProductInventory;
import main.java.com.model.Coin;
import main.java.com.model.CoinType;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.InvalidCoinException;
import main.java.com.vending.exception.ProductOutOfStockException;
/**
 * 
 * @author Yergalem
 *
 */
public class VendingMachine {
	private VendingMachineState soldOut;
	private VendingMachineState noCoin;
	private VendingMachineState hasCoin;
	private VendingMachineState exactChangeCoin;
	private VendingMachineState sold;
	private VendingMachineState coinState = soldOut; // current CoinState
	//private Map<String, List<Product>> productMap;
	private int productsCount;
	private ProductInventory productInventory;
	private CoinInventory coinInventory;

	public VendingMachine() {

		soldOut = new SoldOutState(this);
		noCoin = new NoCoinState(this);
		hasCoin = new HasCoinState(this);
		exactChangeCoin = new ExactChangeOnlyState(this);
		
		productInventory = new ProductInventory();
		coinInventory    = new CoinInventory();
		//sold = new SoldState(this);
		
	}

	public void selectProduct(Product product) throws ProductOutOfStockException, CoinTypeNotAvailableException {
		coinState.selectProduct(product);
	}

	public void makeChange(Product product) throws CoinTypeNotAvailableException {
		coinState.makeChange(product);
	}

	public void acceptCoin( Coin coin) throws InvalidCoinException  {
		if( coin.getType().equals(CoinType.PENNEY))
			throw new InvalidCoinException("Invalid Coin");
		
		coinState.acceptCoin(coin);
	}

	public void returnCoin() throws CoinTypeNotAvailableException {
		coinState.returnCoin();

	}

	public void setCoinState(VendingMachineState coinState) {
		this.coinState = coinState;
	}

		
	public void loadProducts(final List<Product> products){
		productInventory.load(products);
		
		productsCount = productInventory.getCountOfInventoryProducts();
		
        if( coinInventory.getCountOfInventoryCoins() > 0 
        	&& productsCount > 0  )
              coinState = hasCoin;
        else  coinState = noCoin;
        
	}
	public void insertCoin(CoinType coinType) {
		coinInventory.load(coinType);
	}
 
	public ProductInventory getProductInventory() {
		return productInventory;
	}

	public CoinInventory getCoinInventory( ) {
        return coinInventory;
	}

	public VendingMachineState getCoinState() {
		return coinState;
	}

	public VendingMachineState getSoldOutState() {
		return soldOut;
	}

	public VendingMachineState getNoCoinState() {
		return noCoin;
	}

	public VendingMachineState getHasCoinState() {
		return hasCoin;
	}

	public VendingMachineState getSoldCoinState() {
		return sold;
	}

	public VendingMachineState getExactChangeCoinState() {
		return exactChangeCoin;
	}

	public void setExactChangeCoin(VendingMachineState exactChangeCoin) {
		this.exactChangeCoin = exactChangeCoin;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Vending Machine .");
		result.append("\nInventory: " + productsCount + " gumball");
		if ( productsCount != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + coinState + "\n");
		return result.toString();
	}

}
