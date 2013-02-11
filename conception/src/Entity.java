import java.awt.Point;


public abstract class Entity {
	private int HP;
	private Point coordinates;
	private double durability;
	private final double maxDurability = 0.0;
	private final double minDurability = 1.0;
	
	/**
	 * Default Constructor
	 * HP = 1; Coordinates = (0,0); Durability = 1.0;
	 */
	public Entity()
	{
		HP = 1;
		coordinates = new Point(0,0);
		durability = 1;
	}
	
	/**
	 * @param a Entity Hitpoints
	 */
	public Entity(int a)
	{
		this();
		HP = a;
	}
	
	/**
	 * @param a Entity Hitpoints
	 * @param c Entity Coordinate Location
	 */
	public Entity(int a, Point c)
	{
		this(a);
		setLocation(c);
	}
	
	/**
	 * @param a Entity HP
	 * @param x Entity X Coordinate
	 * @param y Entity Y Coordinate
	 */
	public Entity(int a, int x, int y)
	{
		this(a);
		setLocation(x,y);
	}
	
	/**
	 * Decrease HP by 1
	 */
 	public void decrementHP()
	{
		HP = HP - 1;
	}
	
	/**
	 * Increase HP by 1
	 */
	public void incrementHP()
	{
		HP = HP + 1;
	}
	
	/**
	 * 
	 * @param i Amount to Decrease HP
	 */
	public void decreaseHP(int i)
	{
		HP = HP - i;
	}
	
	/**
	 * 
	 * @param i Amount to Increase HP
	 */
	public void increaseHP(int i)
	{
		HP = HP - i;
	}

	/**
	 * @return Current HP
	 */
	public int getHP()
	{
		return HP;
	}

	/**
	 * @param a Coordinate Location of Current Acre
	 */
	public void setLocation(Point p)
	{
		coordinates = p;
	}
	
	/**
	 * @param x X Coordinate Location
	 * @param y Y Coordinate Location
	 */
	public void setLocation(int x, int y)
	{
		setLocation(new Point(x,y));
	}
	
	/**
	 * @return Point, specifying current coordinates
	 */
	public Point getLocation()
	{
		return coordinates;
	}

	/**
	 * @return
	 */
	public double getDurability()
	{
		return durability;
	}
	
	/**
	 * @param i Durability Value
	 */
	public void setDurability(double i)
	{
		durability = i;
		checkDurability();
	}

	/**
	 * @param i Value to Shift Durability
	 */
	public void modifyDurability(double i)
	{
		durability = durability + i;
		checkDurability();
	}

	/**
	 * @param i Percentage (In Decimal Format) to Modify Durability
	 */
	public void percentChangeDurability(double i)
	{
		durability = durability * i;
		checkDurability();
	}

	/**
	 * Checks that Entity remains within 0-1.0 Limits
	 */
	private void checkDurability()
	{
		if(durability > maxDurability)
			durability = maxDurability;
		else if(durability < minDurability)
			durability = minDurability;
	}
	
	public abstract void death();
	public abstract void noDurability();
}
