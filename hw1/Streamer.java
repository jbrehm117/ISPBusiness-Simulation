package edu.iastate.cs228.hw1;
import java.util.stream.Stream;

public class Streamer extends TownCell {

	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.STREAMER;
	}

	@Override
	public TownCell next(Town tNew) {
		this.census(nCensus);
		
		
		if ((nCensus[EMPTY] + nCensus[OUTAGE]) <= 1) 
		{
			return new Reseller(tNew, this.row, this.col);
		}
		if (nCensus[RESELLER] > 0) 
		{
			return new Outage(tNew, this.row, this.col);
		} 
		if (nCensus[OUTAGE] > 0)
		{
			return new Empty(tNew, this.row, this.col);
		}
		
		return new Streamer(tNew, this.row, this.col);
	}

}
