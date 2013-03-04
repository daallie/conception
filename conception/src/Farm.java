import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Farm extends Structure
{
	ArrayList<FarmField> fields;
	Crop cropType;
	int cropInventory = 0;
	
	public Farm()
	{
		setName("Farm");
	}
	
	public void addField(FarmField f)
	{
		if(isAttached(f))
			fields.add(f);
	}
	
	/**
	 * 
	 * @param f FarmField to Attach
	 * @return I
	 */
	public boolean isAttached(FarmField f)
	{
		if(f.getLocation().distance(getLocation()) == 1)
			return true;
		for(FarmField field: fields)
			if(f.getLocation().distance(field.getLocation()) == 1)
				return true;
		return false;
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

}
