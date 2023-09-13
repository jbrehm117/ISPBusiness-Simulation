package edu.iastate.cs228.hw1;
import java.util.Arrays;

/**
 * 
 * @author Joseph Brehm
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0;

		int row = this.row, col = this.col;
		int numRows = plain.grid.length, numCols = plain.grid[0].length;
		
		// list of coordinates that (could) be surrounding the current cell.
		int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
		int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

		for (int i = 0; i < 8; i++) {
			int newRow = row + dr[i];
			int newCol = col + dc[i];

			// Check if the new coordinates are within bounds
			if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
				switch(plain.grid[newRow][newCol].who()) {
					case EMPTY -> nCensus[EMPTY] += 1;
					case OUTAGE -> nCensus[OUTAGE] += 1;
					case RESELLER -> nCensus[RESELLER] += 1;
					case CASUAL -> nCensus[CASUAL] += 1;
					case STREAMER -> nCensus[STREAMER] += 1;
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
