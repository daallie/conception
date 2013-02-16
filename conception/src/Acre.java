import java.util.ArrayList;


public class Acre {
	private int biome;
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
		
	
	/**
	 * @param a Index of Biome Type
	 * @param b Elevation Value (-100,100)
	 * @param c Humidity Value (0,100)
	 * @param d Fertility Value (0,100)
	 * @param e Index of Ore Type
	 * @param f Quantity of Ore
	 * @param g Depth of Ore (-100,100) < Elevation
	 * @param h Index of Lumber Type
	 * @param i Quantity of Lumber
	 */
	public Acre(int a, int b, int c, int d, int e, int f, int g, int h, int i)
	{
		biome = a;
		elevation = b;
		humidity = c;
		fertility = d;
		oreType = e;
		oreQuantity = f;
		oreDepth = g;
		lumberType = h;
		lumberQuantity = i;
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
	
	
	
}
