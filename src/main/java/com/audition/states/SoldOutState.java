package main.java.com.audition.states;

import main.java.com.audition.VendingMachineState;
import main.java.com.inventory.CoinInventory;
import main.java.com.model.Coin;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;
import main.java.com.vending.exception.ProductOutOfStockException;
import main.java.com.audition.VendingMachine;

public class SoldOutState implements VendingMachineState {
	private VendingMachine vendingMachine;

	public SoldOutState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	@Override
	public void selectProduct(Product product) throws ProductOutOfStockException, CoinTypeNotAvailableException {
		System.out.println("SOLD OUT");

		CoinInventory cinv = vendingMachine.getCoinInventory();
		if (cinv.getCountOfInventoryCoins() > 0)
			vendingMachine.setCoinState(vendingMachine.getHasCoinState());
		else
			vendingMachine.setCoinState(vendingMachine.getNoCoinState());

	}

	@Override
	public void makeChange(Product product) {
		System.out.println("Product is Out of Stock");
	}

	public void returnCoin(Double amountPaid) throws CoinTypeNotAvailableException {
		CoinInventory cinv = vendingMachine.getCoinInventory();
		if (cinv.getCountOfInventoryCoins() > 0)
			vendingMachine.setCoinState(vendingMachine.getHasCoinState());
		else
			vendingMachine.setCoinState(vendingMachine.getNoCoinState());

	}

	@Override
	public void returnCoin() throws CoinTypeNotAvailableException {
		CoinInventory cinv = vendingMachine.getCoinInventory();

		returnCoin(cinv.getCountByOnesEquivalent() / 100.0);
	}

	@Override
	public void acceptCoin(Coin coin) {
		System.out.println("SOLD OUT");
	}

}
