import java.awt.event.ActionEvent;
import java.util.Observable;


public class FarmField extends Structure
{
	Farm myFarm;
	
	FarmField(Farm f)
	{
		super();
		setName("Field");
		myFarm = f;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noHP()
	{
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noDurability()
	{
		// TODO Auto-generated method stub
		
	}

	public Farm getFarm()
	{
		// TODO Auto-generated method stub
		return myFarm;
	}

	@Override
	public void gameTick()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void gameDay(int day)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void gameMonth(int month)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameYear(int year)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public double productivity()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public String status()
	{
		/**
		 * TODO Write Status
		 */
		return "";
	}

	
	@Override
	public void educate()
	{
		// TODO Auto-generated method stub
		
	}
}
