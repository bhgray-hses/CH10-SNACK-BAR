import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VendorTest {

	Vendor v1, v2;
	final int v1Stock = 5;
	final int v1NewStock = 10;
	final int v1Price = 10;
	final int v1DepositExact = 10;
	final int v1DepositForChange = 30;
	final int v2Stock = 0;
	final int v2Price = 100;
	final int v2Deposit = 100;
	final int initialDeposit = 0;
	
	@Before
	public void setUp() throws Exception {
		v1 = new Vendor(v1Price, v1Stock);
		v2 = new Vendor(v2Price, v2Stock);		
	}

	@Test
	public void testSetStock() {
		assertEquals("v1 should start with " + v1Stock + "  units of stock, but had " + v1.getStock(), v1Stock, v1.getStock());
		v1.setStock(v1NewStock);
		assertEquals("v1 should now have " + v1NewStock + " units of stock, but had " + v1.getStock(), v1NewStock, v1.getStock());
	}

	@Test
	public void testGetStock() {
		assertEquals("v1 should start with " + v1Stock + "  units of stock, but had " + v1.getStock(), v1Stock, v1.getStock());
	}

	@Test
	public void testAddMoney() {
		assertEquals("v1 should start with " + initialDeposit + " deposit, but had " + v1.getDeposit(), initialDeposit, v1.getDeposit());
		v1.addMoney(v1DepositExact);
		assertEquals("v1 should now have " + v1DepositExact + " cents deposited, but had " + v1.getDeposit(), v1DepositExact, v1.getDeposit());
		
	}

	@Test
	public void testGetDeposit() {
		assertEquals("v1 should start with " + initialDeposit + " deposit, but had " + v1.getDeposit(), initialDeposit, v1.getDeposit());
	}

	@Test
	public void testDontMakeSaleWithNoStock() {
		// try to get sale from machine with no stock (v2)
		assertEquals("v2 should start with " + v2Stock + " in stock, but had " + v2.getStock(), v2Stock, v2.getStock());
		assertEquals("v2 should start with " + initialDeposit + " deposit, but had " + v2.getDeposit(), initialDeposit, v2.getDeposit());
		v2.addMoney(v2Deposit);
		assertFalse("v2 should not make a sale, given no stock", v2.makeSale());
		
		// try to get sale from machine w/out appropriate amount of deposit (v1)
		assertEquals("v1 should start with "+ v1Stock +" in stock, but had " + v1.getStock(), v1Stock, v1.getStock());
		assertEquals("v1 should start with " + initialDeposit + " deposited, but had " + v1.getDeposit(), initialDeposit, v1.getDeposit());
		v1.addMoney(v1DepositExact);
		assertTrue("v1 should make a sale", v1.makeSale());	
	}
	
	@Test
	public void testMakeSale() {
		// try to get sale from machine w/out appropriate amount of deposit (v1)
		assertEquals("v1 should start with 5 in stock, but had " + v1.getStock(), 5, v1.getStock());
		assertEquals("v1 should start with 0 deposited, but had " + v1.getDeposit(), 0, v1.getDeposit());
		v1.addMoney(10);
		assertTrue("v1 should make a sale", v1.makeSale());		
	}

	@Test
	public void testGetChange() {
		// try to get sale from machine w/out appropriate amount of deposit (v1)
		assertEquals("v1 should start with 5 in stock, but had " + v1.getStock(), 5, v1.getStock());
		assertEquals("v1 should start with 0 deposited, but had " + v1.getDeposit(), 0, v1.getDeposit());
		v1.addMoney(20);
		assertTrue("v1 should make a sale", v1.makeSale());
		int change = v1.getChange();
		assertEquals("v1 should make change in the amount of 10 cents, but made " + change, 10, change);
		assertEquals("v1 should have zeroed out the change, but has " + v1.getChange(), 0, v1.getChange());
		
	}

}

