package Objects;

public class Bet 
{
	private String user;
	private int amount;
	private String bet;
	
	public Bet(String user, int amount, String bet)
	{
		this.user = user;
		this.amount = amount;
		this.bet = bet;
	}
	
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public String getBet()
	{
		return bet;
	}
	public void setBet(String bet)
	{
		this.bet = bet;
	}	
}
