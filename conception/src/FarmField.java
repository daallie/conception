import java.awt.event.ActionEvent;


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

	@Override
	public void gameTick()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameDay()
	{
		// TODO Auto-generated method stub
		
	}

	
	public Farm getFarm()
	{
		// TODO Auto-generated method stub
		return myFarm;
	}
}
