package edu.iastate.cs228.hw1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Joseph Brehm
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		
		File f = new File(inputFileName);
		Scanner scnr = new Scanner(f);
		
		length = scnr.nextInt();
		width = scnr.nextInt();
		scnr.nextLine();
		
		grid = new TownCell[length][width];
		
		
		int n = 0, k = 0;
		String s;
		while (scnr.hasNextLine()) 
		{
			while (scnr.hasNext() && k < width) 
			{
				s = scnr.next();
				if (s.equals("R") || s.equals("r")) 
				{
					grid[n][k] = new Reseller(this, n, k);
				}
				else if (s.equals("E") || s.equals("e"))
				{
					grid[n][k] = new Empty(this, n, k);
				}
				else if (s.equals("C") || s.equals("c"))
				{
					grid[n][k] = new Casual(this, n, k);
				}
				else if (s.equals("O") || s.equals("o"))
				{
					grid[n][k] = new Outage(this, n, k);
				}
				else if (s.equals("S") || s.equals("s"))
				{
					grid[n][k] = new Streamer(this, n, k);
				}
				k++;
			}
			
			k = 0;
			n++;		
		}
		
		scnr.close();
		
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) 
	{
		Random rand = new Random(seed);
		int r;
		for (int i = 0; i < length; ++i) 
		{
			for (int j = 0; j < width; ++j) 
			{
				// Initialize "r" as a random integer between 0 and 4 (inclusive).
				r = rand.nextInt(5);
				
				/*
				Depending on the value of "r", the cell on the grid corresponding to the position
				of the current height and width will be filled with either: OUTAGE, RESELLER, CASUAL, EMPTY, or STREAMER.
				*/
				
				if (r == 0) 
				{
					grid[i][j] = new Reseller(this, i, j);
				} else if (r == 1) 
				{
					grid[i][j] = new Empty(this, i, j);
				} else if (r == 2) 
				{
					grid[i][j] = new Casual(this,i, j);
				} else if (r == 3) 
				{
					grid[i][j] = new Outage(this, i, j);
				} else if (r == 4) 
				{
					grid[i][j] = new Streamer(this, i, j);
				}
				
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() 
	{
		String s = "";
		int n = 0, k = 0;
		while (n != grid.length) 
		{
			while (k != grid[n].length) 
			{
				if (k != grid[n].length - 1) 
				{
					s += grid[n][k].who().toString().charAt(0) + " ";
				}
				else {
					s += grid[n][k].who().toString().charAt(0);
				}
				k++;
			}
			
			k = 0;
			n++;
			
			if (n != grid.length) 
			{
				s += "\n";
			}
		}
		return s;
	}
}
