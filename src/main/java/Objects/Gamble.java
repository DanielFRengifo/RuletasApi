package Objects;

import java.util.ArrayList;

public class Gamble 
{
	private static final String RED = "RED";
	private static final String BLACK = "BLACK";
	
	private int id;
	private boolean state; 
	private ArrayList<String[]> bets;
	
	public Gamble(int id)
	{
		this.id = id; 
		this.state = false;
		this.bets = new ArrayList<String[]>(); 
	}	
	
	public int getId()
	{
		return id;
	}	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public boolean getState()
	{
		return state;
	}	
	public void setState(boolean state)
	{
		this.state = state;
	}
	
	public ArrayList<String[]> getBets()
	{
		return bets;
	}	
	public void setBets(ArrayList<String[]> bets)
	{
		this.bets = bets;
	}
	
}
