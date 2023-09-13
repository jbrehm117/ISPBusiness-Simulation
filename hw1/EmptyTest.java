package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyTest {
	Town t = new Town(4, 4);
	
	@Test
	void test() {
		t.randomInit(10);
		Empty empty = new Empty(t, 1, 1);
		t.grid[1][1] = empty;
		assertEquals(State.CASUAL, empty.next(t).who());
	}

}
