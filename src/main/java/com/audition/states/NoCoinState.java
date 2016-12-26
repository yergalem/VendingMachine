package main.java.com.audition.states;

import main.java.com.audition.VendingMachineState;
import main.java.com.inventory.CoinInventory;
import main.java.com.model.Coin;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.InvalidCoinException;
import main.java.com.audition.VendingMachine;

public class NoCoinState implements VendingMachineState {
    VendingMachine vendingMachine;
	public NoCoinState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
    @Override
	public void selectProduct(Product product) {
		System.out.println("INSERT COIN");		
	}
    @Override
	public void makeChange(Product product) {
		System.out.println(" INSERT COIN");
	}

	public void returnCoin(Double amountPaid) {
		System.out.println(" INSERT COIN ");
	}
	
	@Override
    public void returnCoin() throws CoinTypeNotAvailableException{
    	CoinInventory cinv = vendingMachine.getCoinInventory();
    	
    	returnCoin( cinv.getCountByOnesEquivalent() / 100.0 );
    }

	@Override
	public void acceptCoin(Coin coin) throws InvalidCoinException {
        System.out.println(" INSERT COIN ");
        vendingMachine.setCoinState( vendingMachine.getHasCoinState());
        vendingMachine.acceptCoin(coin);
	}

}
