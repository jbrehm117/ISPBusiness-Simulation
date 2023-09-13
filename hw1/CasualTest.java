package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CasualTest {
	Town t = new Town(4, 4);
	
	@Test
	void test() {
		t.randomInit(10);
		Casual casual = new Casual(t, 1, 1);
		t.grid[1][1] = casual;
		assertEquals(State.OUTAGE, casual.next(t).who());
	}

}
