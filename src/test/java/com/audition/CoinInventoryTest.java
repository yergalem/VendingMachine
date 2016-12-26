package test.java.com.audition;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.com.inventory.CoinInventory;
import main.java.com.model.Coin;
import main.java.com.model.CoinType;
import main.java.com.model.Product;
import main.java.com.vending.exception.CoinTypeNotAvailableException;

public class CoinInventoryTest {
	
		private CoinInventory coinInventory;
		private Coin quarter , dime, nickel;
		
		@Before
		public void setUp(){
			coinInventory = new CoinInventory();
			quarter = new Coin(CoinType.QUARTER);
			dime = new Coin(CoinType.DIME);
			nickel = new Coin(CoinType.NICKEL);
		}
		
		@Test
		public void shouldLoadCoins() throws CoinTypeNotAvailableException{
			final List<CoinType> coinTypes = new ArrayList<>();
			for(int i=1; i< 5; i++){
				coinTypes.add( CoinType.QUARTER );
			}
			coinInventory.load(coinTypes);
			assertEquals(4, coinInventory.getCountByCoinType(CoinType.QUARTER));
		}
		
		@Test(expected = CoinTypeNotAvailableException.class)
		public void shouldThrowCoinTypeNotAvailableException() throws CoinTypeNotAvailableException{
			coinInventory.getCountByCoinType(CoinType.DIME);
		}
		
		/*@Test
		public void shouldCountCoinChangeWays() {
			//List<CoinType> allowedCoinTypes = CoinType.getValidCoinTypes();
			//CoinType[] coinTypes = (CoinType[]) allowedCoinTypes.toArray();
			//int[] vals = new int[coinTypes.length];
			int i =0;
			//for( CoinType c : coinTypes ) vals[i++] = (c.getWorth().intValue()) *100;
			int counts = coinInventory.countCoinChangeWays( new int[]{5,25} , 2, 45 );
			
			assertEquals(4, counts);
		}*/
		@Test
		public void shouldCheckForCoinChange() throws CoinTypeNotAvailableException {
			Product product = new Product("coin");
			coinInventory.load( Arrays.asList(CoinType.QUARTER, CoinType.DIME, CoinType.QUARTER ));
		    coinInventory.hasCoinChange(product);
		}
		@Test
		public void shouldConvertToOnesAndReturnSumOfCoins() throws CoinTypeNotAvailableException {
			final List<CoinType> coinTypes = new ArrayList<>();
			for(int i=1; i< 5; i++){
				coinTypes.add( CoinType.QUARTER );
			}
			
			for(int i=1; i<23; i++){
				coinTypes.add( CoinType.NICKEL );
			}
			for(int i=1; i< 12; i++){
				coinTypes.add( CoinType.DIME );
			}
		    coinInventory.load(coinTypes);	
			
		    assertEquals( 320,  coinInventory.getCountByOnesEquivalent());
		}

}
