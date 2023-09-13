package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StreamerTest {
	Town t = new Town(4, 4);
	
	@Test
	void test() {
		t.randomInit(10);
		Streamer streamer = new Streamer(t, 1, 1);
		t.grid[1][1] = streamer;
		assertEquals(State.OUTAGE, streamer.next(t).who());
	}

}
