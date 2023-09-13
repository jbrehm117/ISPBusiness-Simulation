package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TownCellTest {
	Town t = new Town(4, 4);
	
	@Test
	void censusTest() {
		t.randomInit(10);
		Casual c = new Casual(t, 1, 1);
		t.grid[1][1] = c;
		int[] nCensus = new int[5];
		c.census(nCensus);
		assertEquals("[1, 2, 1, 3, 1]",Arrays.toString(nCensus));
		
	}

}