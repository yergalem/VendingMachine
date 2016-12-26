package main.java.com.audition.states;

import main.java.com.audition.VendingMachineState;
import main.java.com.inventory.CoinInventory;
import main.java.com.inventory.ProductInventory;
import main.java.com.model.Coin;
import main.java.com.model.CoinType;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.ProductOutOfStockException;
import main.java.com.audition.VendingMachine;

public class ExactChangeOnlyState implements VendingMachineState {
	VendingMachine vendingMachine;

	public ExactChangeOnlyState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public void makeChange(Product product) throws CoinTypeNotAvailableException {
		System.out.println("EXACT CHANGE ONLY");
		returnCoin();
	}

	@Override
	public void acceptCoin(Coin coin) {
		vendingMachine.insertCoin(coin.getType());
	}

	@Override
	public void selectProduct(Product product) throws ProductOutOfStockException, CoinTypeNotAvailableException {
        CoinInventory coinInventory = vendingMachine.getCoinInventory();
		// Coins in Inventory
		final int NUM_QUARTERS = coinInventory.getCountByCoinType(CoinType.QUARTER);
		final int NUM_DIMES = coinInventory.getCountByCoinType(CoinType.DIME);
		final int NUM_NICKELS = coinInventory.getCountByCoinType(CoinType.NICKEL);
		
		int[] COLA_CHANGES = {4,0,0};  int[] CHIPS_CHANGES = {2,0,0};  
		int[] CANDY_CHANGES = {2,1,1}; 
		
		boolean isExactChange  = false;
		if( product.getName().equals("cola")) {
			isExactChange = isProductExactChange( COLA_CHANGES , 
					        new int[]{ NUM_QUARTERS, NUM_DIMES, NUM_NICKELS} );
		}
		else if( product.getName().equals("chips")) {
			isExactChange = isProductExactChange( CHIPS_CHANGES , 
					        new int[]{ NUM_QUARTERS, NUM_DIMES, NUM_NICKELS} );
		}
		else 
			isExactChange = isProductExactChange( CANDY_CHANGES , 
					        new int[]{ NUM_QUARTERS, NUM_DIMES, NUM_NICKELS} );

        if ( !isExactChange )
        	throw new CoinTypeNotAvailableException("EXACT CHANGE ONLY");
        
        vendingMachine.setCoinState( vendingMachine.getHasCoinState());
        vendingMachine.selectProduct(product);
	}

	private boolean isProductExactChange(int[] requiredChanges, int[] insertedChanges) {
		int numOccurences  = 0;
		   
		  for(int i=0; i< 3; i++)
			if(requiredChanges[i] == insertedChanges[i])
				numOccurences++;
		  
		return 3 == numOccurences;
	}

	public void returnCoin(Double amountPaid) throws CoinTypeNotAvailableException {
		CoinInventory coinInventory = vendingMachine.getCoinInventory();
		System.out.println(amountPaid + " returned");
		coinInventory.emptyCoinInventory();

		vendingMachine.setCoinState(vendingMachine.getNoCoinState());
	}

	@Override
	public void returnCoin() throws CoinTypeNotAvailableException {
		CoinInventory cinv = vendingMachine.getCoinInventory();

		returnCoin(cinv.getCountByOnesEquivalent() / 100.0);
	}

}
