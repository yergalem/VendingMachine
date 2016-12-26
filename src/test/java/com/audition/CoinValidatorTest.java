package test.java.com.audition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.model.Coin;
import main.java.com.model.CoinType;
import main.java.vending.services.CoinValidator;

public class CoinValidatorTest {
	
		private CoinValidator coinValidator;
		
		@Before
		public void setUp(){
			coinValidator = new CoinValidator();
		}
		
		@Test
		public void shouldAcceptNickel(){
			final Coin coin = new Coin(CoinType.NICKEL);
			assertTrue(coinValidator.validate(coin));
		}
		
		@Test
		public void shouldAcceptDime(){
			final Coin coin = new Coin(CoinType.DIME);
			assertTrue(coinValidator.validate(coin));
		}
		
		@Test
		public void shouldAcceptQuarter(){
			final Coin coin = new Coin(CoinType.QUARTER);
			assertTrue(coinValidator.validate(coin));
		}
		
		@Test
		public void shouldRejectPenny(){
			final Coin coin = new Coin(CoinType.PENNEY);
			assertFalse(coinValidator.validate(coin));
		}

	}
