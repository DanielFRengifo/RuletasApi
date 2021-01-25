package Objects;

import java.util.ArrayList;

public class Ruleta 
{
	private static final String RED = "RED";
	private static final String BLACK = "BLACK";
	
	private int id;
	private boolean open; 
	private ArrayList<String[]> bets;
	
	public Ruleta(int id)
	{
		this.id = id; 
		this.open = false;
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
	
	public boolean getOpen()
	{
		return open;
	}	
	public void setOpen(boolean open)
	{
		this.open = open;
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
