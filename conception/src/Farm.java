import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;


public class Farm extends Structure
{
	ArrayList<FarmField> fields = new ArrayList<FarmField>();
	Crop cropType;
	int cropField = 0;
	int cropInventory = 0;
	
	/**
	 * 	0 = Not Active
	 *  1 = Planting
	 *  2 = Growing
	 *  3 = Harvesting
	 */
	byte cropStatus = 0;
	
	public Farm()
	{
		setName("Farm");
		/**
		 * TODO
		 * Remove this HardCode Is only for testing
		 */
		cropType = new Wheat();
	}
		
	/**
	 * Sets Structure Location to Acre a
	 * @param a Acre for Farm Location
	 */
	@Override
	public void setLocation(Acre a)
	{
		super.setLocation(a);
		a.setFarm(true);
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

	public double productivity()
	{
		/**
		 * TODO
		 * Need to Write Productivity Calculator
		 * Will Need to be done after Skill System
		 */
		return 1.0;
	}
	
	private int maxCrop()
	{
		/**
		 * TODO
		 * Incorporate Technology
		 */
		return (int) (( 0.5 + fields.size() ) * cropType.getDensity());
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		Calendar c = (Calendar) o;
		
		// Decay Current Inventory
		cropInventory *= 1-cropType.decayRate(c.getSeason());
		
		// Must Start Planting Crop
		if(cropField <= 0)
		{
			cropField = 0;
			cropType.startPlanting();
			cropField += maxCrop()*cropType.plantRate(c.getSeason()) * productivity();
		}
		// Still Need to Plant
		else if(cropField < maxCrop() && !cropType.hasGrown())
		{
			cropField += maxCrop()*cropType.plantRate(c.getSeason()) * productivity();
		}
		// Need to Grow
		else if(!cropType.hasGrown())
		{
			cropType.growingDay(c.getSeason());
		}
		// Need to Harvest
		else
		{
			double amount = (maxCrop()*cropType.harvestRate(c.getSeason()) * productivity() );
			System.out.println("Amount : " + amount);
			System.out.println("Harvest Rate: " + cropType.harvestRate(c.getSeason()) );
			System.out.println("Season : " + c.getSeason());
			cropField -= amount;
			cropInventory += amount;
		}
			
	}

	public String status()
	{
		return "Crop Type: " + cropType.getName() + "\nCrop in Fields: " + cropField + "\n Grow Status: " + cropType.getStatus() + "\nCrop in Storage: " + cropInventory;
	}

}
