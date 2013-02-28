import java.util.ArrayList;


public class Acre {
	/**
	 * TODO Fix Null Biome Pointer
	 * Remove the biomeID variable here
	 */
	public int biomeID;
	private Biome myBiome;
	private boolean inBiome = false;
	private boolean checked = false;
	private int elevation;
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
		this(0,50,50,0,0,0,0,0);
		myBiome = null;
	}
	
	public boolean isChecked()
	{
		return checked;
	}
	
	public void resetCheck()
	{
		checked = false;
	}
	
	public boolean inBiome()
	{
		return myBiome!=null;
	}

	public Biome getBiome()
	{
		return myBiome;
	}
	
	public boolean biomeClaim(Biome a)
	{
		checked = true;
		if(inBiome)
			return false;
		biomeID = a.getID();
		myBiome = a;
		inBiome = true;
		return true;
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
	public Acre(int a, int b, int c, int d, int e, int f, int g, int h)
	{
		elevation = a;
		humidity = b;
		fertility = c;
		oreType = d;
		oreQuantity = e;
		oreDepth = f;
		lumberType = g;
		lumberQuantity = h;
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
		a.setLocation(x,y);
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
		return true;
	}
	
	
	public String location()
	{
		return x + " " + y;
	}
	
	
	
	/**
	 * Return Location in format (x,y)
	 */
	public String toString()
	{
		return "" + biomeID + "\n" + x + " " +y;
	}
	
	
}
