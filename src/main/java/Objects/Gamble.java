package Objects;

import java.util.ArrayList;

public class Gamble 
{
	private static final String RED = "RED";
	private static final String BLACK = "BLACK";
	
	private String id;
	private boolean state; 
	private ArrayList<Bet> bets;
	
	public Gamble()
	{
		this.state = false;
		this.bets = new ArrayList<Bet>(); 
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
	
	public ArrayList<Bet> getBets()
	{
		return bets;
	}	
	public void setBets(ArrayList<Bet> bets)
	{
		this.bets = bets;
	}
	
	public void setBetsString(String bets)
	{
		if (bets.length()>3)
		{
			String[] arr = bets.split(";");
			String[] arr2;
			for (String item : arr) 
		    {
				arr2 = item.split(":");
				Bet resp = new Bet(arr2[0], Integer.parseInt(arr2[1]), arr2[2]);
				this.bets.add(resp);
		    }	
		}			
	}
	
	public boolean bet(Bet bet)
	{
		String betString = bet.getBet();
		if ((betString.equals(RED) || betString.equals(BLACK) || betString.matches("[0-9]+")))
		{
			bets.add(bet);
			
			return true;
		}
		else 
		{
			return false;
		}
	}	
	
	public String close()
	{
		int numero = (int) (Math.random( )*37);
		String resp = "";
		for (Bet bet : bets) 
        {
	        resp += "Usuario: " + bet.getUser() + " ";
	        String betString = bet.getBet();
	        if (betString.matches("[0-9]+"))
	        {
	        	if (betString.equals(String.valueOf(numero)))
	        	{
	        		resp += "Monto: " + String.valueOf((int) (bet.getAmount() * 5));
	        	}
	        	else
	        	{
	        		resp += "Monto: -" + String.valueOf(bet.getAmount());
	        	}
	        }
	        else
	        {
	        	if (numero % 2 == 0)
	        	{
	        		if (betString.equals(RED))
	        		{
	        			resp += "Monto: " + String.valueOf((int) (bet.getAmount() * 1.8));
	        		}
	        		else
	        		{
	        			resp += "Monto: -" + String.valueOf(bet.getAmount());
	        		}
	        	}
	        	else
	        	{
	        		if (betString.equals(BLACK))
	        		{
	        			resp += "Monto: " + String.valueOf((int) (bet.getAmount() * 1.8));
	        		}
	        		else
	        		{
	        			resp += "Monto: -" + String.valueOf(bet.getAmount());
	        		}
	        	}
	        }
	        resp += " || ";
        }	
		
		return resp.substring(0, resp.length() - 4);
	}
	
}
