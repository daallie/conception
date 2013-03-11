import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Acre{
	private boolean checked = false;
	private boolean isOcean = false;
	private boolean hasFarm = false;
	private int elevation;
	private int temperature;
	private int humidity;
	private int fertility;
	private int oreType;
	private int oreQuantity;
	private int oreDepth;
	private int lumberType;
	private int lumberQuantity;
	private int x;
	private int y;
	private Acre north;
	private Acre south;
	private Acre east;
	private Acre west;
	private Structure structure;
	private ArrayList<Unit> units;
	
	public Acre()
	{
		this(-1,30,30,0,0,0,0,0,50);
	}
	
	/**
	 * @param a Elevation Value (-100,100)
	 * @param b Humidity Value (0,100)
	 * @param c Fertility Value (0,100)
	 * @param d Index of Ore Type
	 * @param e Quantity of Ore
	 * @param f Depth of Ore (-100,100) <= Elevation
	 * @param g Index of Lumber Type
	 * @param h Quantity of Lumber
	 */
	public Acre(int a, int b, int c, int d, int e, int f, int g, int h, int i)
	{
		elevation = a;
		humidity = b;
		fertility = c;
		oreType = d;
		oreQuantity = e;
		oreDepth = f;
		lumberType = g;
		lumberQuantity = h;
		temperature = i;
	}
	
	public boolean isChecked()
	{
		return checked;
	}
	
	public void resetCheck()
	{
		checked = false;
	}
	
	/**
	 * @param n Acre in the North Position
	 * @param s Acre in the South Position
	 * @param e Acre in the East Position
	 * @param w Acre in the West Position
	 */
	public void setLinks(Acre n, Acre s, Acre e, Acre w)
	{
		north = n;
		south = s;
		east = e;
		west = w;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return Acre to North
	 */
	public Acre getNorth()
	{
		return north;
	}
	
	/**
	 * @return Acre to South
	 */
	public Acre getSouth()
	{
		return south;
	}
	
	/**
	 * @return Acre to East
	 */
	public Acre getEast()
	{
		return east;
	}
	
	/**
	 * @return Acre to West
	 */
	public Acre getWest()
	{
		return west;
	}

	/**
	 * @param a Unit to Add to Acre
	 */
	public void addUnit(Unit a)
	{
		units.add(a);
		a.setLocation(this);
	}
	
	/**
	 * @param a Unit to Remove from Acre
	 * @return
	 */
	public boolean removeUnit(Unit a)
	{
		return units.remove(a);
	}

	/**
	 * @return the structure
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * @param structure the structure to set
	 */
	public boolean setStructure(Structure structure) {
		if(this.structure != null)
			return false;
		this.structure = structure;
		structure.setLocation(this);
		
		return true;
	}
	
	/**
	 * @return Location in Format 'x y'
	 */
	public String location()
	{
		return x + " " + y;
	}
	
	/**
	 * Return Location in format
	 * x y
	 * elevation
	 */
	public String toString()
	{
		if(isOcean)
			return "s";
		if(elevation<0)
			return " ";
		if(structure!=null)
			return x + " " + y + "\n" + structure.toString();
		return x + " " + y + "\n" + elevation + " " + humidity;
	}

	/**
	 * Changes the Acre Elevation
	 * @param temp Elevation to be Set in range (-100,100)
	 */
	public void setElevation(int temp) {
		if(temp > 100)
			elevation = 100;
		else if(temp < -100)
			elevation = -100;
		else
			elevation = temp;
		checked = true;
	}

	/**
	 * 
	 * @return X value of location
	 */
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	/**
	 * 
	 * @return Y value of location
	 */
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	/**
	 * 
	 * @return Elevation of Acre
	 */
	public int getElevation() {
		// TODO Auto-generated method stub
		return elevation;
	}
	
	/**
	 * 
	 * @param i New Fertility Value
	 * @return
	 */
	public void alterFertility(double i)
	{
		fertility = (int) (fertility*i);
		if(fertility<0)
			fertility = 0;
		else if(fertility>100)
			fertility = 100;
	}
	
	/**
	 * 
	 * @return Current Fertility Value
	 */
	public int getFertility()
	{
		return fertility;
	}
	
	/**
	 * 
	 * @return If Acre is Ocean
	 */
	public boolean isOcean()
	{
		// TODO Auto-generated method stub
		return isOcean;
	}
	
	/**
	 * Sets if Acre is Ocean
	 */
	public void setOcean(boolean b)
	{
		isOcean = b;
	}

	/**
	 * 
	 * @return Acre Temperature (Not current just average)
	 */
	public int getTemperature()
	{
		return temperature;
	}
	
	/**
	 * 
	 * @param i Multiplier to modify the Temperature
	 */
	public void alterTemperature(double i)
	{
		temperature *= i;
		if(temperature>100)
			temperature = 100;
		else if(temperature<0)
			temperature = 0;
	}
	
	/**
	 * 
	 * @param d Amount to Modify Humidity
	 */
	public void alterHumidity(double d)
	{
		humidity *= d;
		if(humidity > 100)
			humidity = 100;
		else if(humidity < 0)
			humidity = 0;
	}

	/**
	 * 
	 * return Acre Base Humidity
	 */
	public int getHumidity()
	{
		return humidity;
	}

	/**
	 * TODO
	 * @return Array of Menu Options that can be performed on this acre
	 */
	public String[] getMenu()
	{
		// TODO Auto-generated method stub
		if(isOcean)
			return new String[]{"I am Ocean","(" + x + ", " + y + ")"};
		if(elevation<0)
			return new String[]{"I am Lake","(" + x + ", " + y + ")"};
		if(neighborIsFarm())
			return new String[]{"Build Farm", "Build Field", "(" + x + ", " + y + ")"};
		return new String[]{"Build Farm", "(" + x + ", " + y + ")"};
	}

	/**
	 * @return True if a Neighboring Acre has a Farm or Farm Field
	 */
	private boolean neighborIsFarm()
	{
		if(north.hasFarm())
			return true;
		if(south.hasFarm())
			return true;
		if(east.hasFarm())
			return true;
		if(west.hasFarm())
			return true;
		return false;
	}
	
	/**
	 * North -> South -> East -> West Priority
	 * @return One of the Farms from one of its neighbors
	 */
	public Farm getFarm()
	{
		if(neighborIsFarm())
		{
			Acre farm = null;
			if(north.hasFarm())
				farm = north;
			else if(south.hasFarm())
				farm = south;
			else if(east.hasFarm())
				farm = east;
			else
				farm = west;
			if(farm.structure.getName().equals("Farm"))
				return (Farm) farm.structure;
			else
				return ((FarmField) farm.structure).getFarm();
		}
		return null;
	}

	/**
	 * 
	 * @return If Acre has Farm/Field Structure Built on it
	 */
	public boolean hasFarm()
	{
		return hasFarm;
	}
	
	public void setFarm(boolean b)
	{
		hasFarm = b;
	}
	
	public String getDetails()
	{
		// TODO Auto-generated method stub
		String rString = "Location: (" + x + ", " + y + ")";
		rString += "\n";
		rString += "Elevation: " + elevation;
		rString += "\n";
		rString += "Temperature: " + temperature;
		rString += "\n";
		rString += "Humidity: " + humidity;
		rString += "\n";
		rString += "Fertility: " + fertility;
		rString += "\n";
		rString += "Ore: " + oreType;
		rString += "\n";
		rString += "Quantity: " + oreQuantity;
		rString += "\n";
		rString += "Depth: " + oreDepth;
		rString += "\n";
		rString += "Lumber: " + lumberType;
		rString += "\n";
		rString += "Quantity: " + lumberQuantity;
		return rString;
	}
	
	public double distance(Acre location)
	{
		return (new Point(x,y)).distance(location.x, location.y);
	}


}
