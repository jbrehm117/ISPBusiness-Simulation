package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {
	Town t = new Town(4, 4);
	
	@Test
	void getProfitTest() {
		t.randomInit(10);
		assertEquals(1, ISPBusiness.getProfit(t));
	}
	
	@Test
	void updatePlainTest() {
		t.randomInit(10);
		t = ISPBusiness.updatePlain(t);
		assertEquals("E E E E\n" + "C C O E\n" + "C O E O\n" + "C E E E", t.toString());
	}

}
