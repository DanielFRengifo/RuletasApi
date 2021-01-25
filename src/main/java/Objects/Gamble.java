package Objects;

import java.util.ArrayList;

public class Gamble 
{
	private static final String RED = "RED";
	private static final String BLACK = "BLACK";
	
	private String id;
	private boolean state; 
	private ArrayList<String[]> bets;
	
	public Gamble()
	{
		this.state = false;
		this.bets = new ArrayList<String[]>(); 
	}	
	
	public String getId()
	{
		return id;
	}	
	public void setId(String id)
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
