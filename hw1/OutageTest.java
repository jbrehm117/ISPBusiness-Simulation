package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OutageTest {
	Town t = new Town(4, 4);
	
	@Test
	void test() {
		t.randomInit(10);
		Outage outage = new Outage(t, 1, 1);
		t.grid[1][1] = outage;
		assertEquals(State.EMPTY, outage.next(t).who());
	}

}
