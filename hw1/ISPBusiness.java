package edu.iastate.cs228.hw1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Joseph Brehm
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		// Variables for length and width from tOld
		int l = tOld.getLength();
		int w = tOld.getWidth();
		
		Town tNew = new Town(l, w);
		
		int i, j;
		for (i = 0; i < w; ++i) 
		{
			for (j = 0; j < l; ++j) 
			{
				// Sets new grid equal to the new rendition of the corresponding cell in tOld.
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		
		int l = town.getLength(), w = town.getWidth();
		int i, j, m = 0;
		
		for (i = 0; i < w; ++i ) 
		{
			for (j = 0; j < l; ++j) 
			{
				if (town.grid[i][j].who() == State.CASUAL) {
					m++;
				}
			}
		}
		
		return m;
	}
	/**
	 * Returns the profit utilization in the form of a percentage at the end of 12 billing cycles.
	 * @param profit: total profit from 12 billing cycles
	 * @param town
	 * @return integer
	 */
	
	private static double getProfitUtil(int profit, Town town) {
		double profitUtil = 0;
		final int ONE_YEAR_CONSTANT = 12;
		double maxProfit;
		
		maxProfit = (town.getLength() * town.getWidth()) * ONE_YEAR_CONSTANT;
		
		profitUtil = profit/maxProfit;
		
		return profitUtil;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed. ");

		int userInput = scnr.nextInt();
		System.out.println();
		Town town = null;
		if (userInput == 1) 
		{
			System.out.println("Please enter file path: ");
			String filePath = scnr.next();
			try {
				town = new Town(filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
		else if (userInput == 2) 
		{
			BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
			int randomGrid[] = new int[3];
			String[] storedValues;
			System.out.println("Provide rows, cols, and seed integer seperated by spaces: ");
			try {
				storedValues = bi.readLine().split(" ");
				for (int i = 0; i < storedValues.length; ++i) {
					randomGrid[i] = Integer.parseInt(storedValues[i]);
				}
				town = new Town(randomGrid[0], randomGrid[1]); town.randomInit(randomGrid[2]);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		} else {
			System.out.println("Error, terminating JVM...");
			System.exit(0);
		}
		scnr.close();
		
		System.out.println("Start: ");
		System.out.println(town.toString());
		System.out.println();
		System.out.println("Profit: " + ISPBusiness.getProfit(town));

		int totalProfit = 0;
		for (int i = 1; i < 12; ++i) {
			town = ISPBusiness.updatePlain(town);
			System.out.println();
			System.out.println("After itr: " + i);
			System.out.println(town.toString());
			System.out.println();
			System.out.println("Profit: " + ISPBusiness.getProfit(town));
			totalProfit += ISPBusiness.getProfit(town);
		}

		System.out.println(Math.round((getProfitUtil(totalProfit, town) * 100.0) * 100.0)/100.0);
	}
}
