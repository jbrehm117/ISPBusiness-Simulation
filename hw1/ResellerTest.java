package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResellerTest {
	Town t = new Town(4, 4);
	
	@Test
	void test() {
		t.randomInit(10);
		Reseller reseller = new Reseller(t, 1, 1);
		t.grid[1][1] = reseller;
		assertEquals(State.EMPTY, reseller.next(t).who());
	}

}
