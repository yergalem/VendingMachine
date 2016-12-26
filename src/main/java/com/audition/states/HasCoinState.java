package main.java.com.audition.states;

import main.java.com.audition.VendingMachineState;
import main.java.com.inventory.CoinInventory;
import main.java.com.inventory.ProductInventory;
import main.java.com.model.Coin;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.ProductOutOfStockException;

import main.java.com.audition.VendingMachine;

public class HasCoinState implements VendingMachineState {
	VendingMachine vendingMachine;

	public HasCoinState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public void makeChange( Product product ) throws CoinTypeNotAvailableException {
		CoinInventory coinInventory = vendingMachine.getCoinInventory();
		Double productPrice = product.getPrice();
		
		int coinsInserted = coinInventory.getCountByOnesEquivalent();
		Double productPriceOnesEquivalent =  productPrice * 100;
		int val = productPriceOnesEquivalent.intValue();
		if( val == coinsInserted ){
			coinInventory.emptyCoinInventory();
			return;
		}
		else if( val < coinsInserted ){
			 if ( hasCoinChange(product) )
			    returnCoin( (coinsInserted - val) / 100.0 );
			 else {
				vendingMachine.setCoinState( vendingMachine.getExactChangeCoinState());
				vendingMachine.makeChange(product);
			 }
		}
		throw new CoinTypeNotAvailableException(" ADD COIN ");
	}	
	
	private boolean hasCoinChange( Product product) throws CoinTypeNotAvailableException {
        
        return vendingMachine.getCoinInventory().hasCoinChange(product);
	}

	public void returnCoin( Double amountPaid ) throws CoinTypeNotAvailableException {
	  CoinInventory coinInventory = vendingMachine.getCoinInventory();
	  System.out.println( amountPaid+ " returned");
	  coinInventory.emptyCoinInventory();
	  
	  vendingMachine.setCoinState( vendingMachine.getNoCoinState());
	}
	
	@Override
    public void returnCoin() throws CoinTypeNotAvailableException{
    	CoinInventory cinv = vendingMachine.getCoinInventory();
    	
    	returnCoin( cinv.getCountByOnesEquivalent() / 100.0 );
    }

	@Override
	public void acceptCoin(Coin coin) {
		vendingMachine.insertCoin(coin.getType());
	}

	@Override
	public void selectProduct(Product product)  throws ProductOutOfStockException, CoinTypeNotAvailableException {
		
		ProductInventory productInventory = vendingMachine.getProductInventory();		
		Product item = null;
		
		try {
			item = productInventory.getProduct(product.getName());
			
		} catch (ProductOutOfStockException e) {
			vendingMachine.setCoinState( vendingMachine.getSoldOutState());
			vendingMachine.selectProduct(product);
		}
			
		makeChange( item );
		System.out.println( item.getName().toUpperCase()+" released!");
		
		vendingMachine.setCoinState( vendingMachine.getNoCoinState());
		
	}


}
