package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TownTest {
	Town t = new Town(5, 4);

	@Test
	void widthTest() {
		assertEquals(4, t.getWidth());
	}
	
	@Test
	void lengthTest() {
		assertEquals(5, t.getLength());
	}
	
	@Test
	void toStringTest() {
		// Also tests randomInit()
		Town g = new Town(4, 4);
		g.randomInit(10);
		assertEquals("O R O R\n" + "E E C O\n" + "E S O S\n" + "E O R R", g.toString());
	}
	
	@Test
	void fillFromFileTest() throws FileNotFoundException{
		// Change to new file path to test on a different computer
		Town s = new Town("test.txt");
		assertEquals("O R O R\n" + "E E C O\n" + "E S O S\n" + "E O R R", s.toString());
		
	}

}
