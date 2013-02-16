import java.awt.Point;
import java.awt.event.ActionListener;


public abstract class Entity implements ActionListener{
	private int HP;
	private static final int maxHP = 100;
	private Point coordinates;
	private double durability;
	private static final double maxDurability = 0.0;
	private static final double minDurability = 1.0;
	
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
	 * @param a Entity HP
	 */
	public Entity(int a)
	{
		this();
		HP = a;
	}
	
	/**
	 * @param a Entity HP
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
	 * 
	 * @param i Amount to Change HP
	 */
	public void modifyHP(int i)
	{
		HP = HP + i;
		checkHP();
	}
	
	/**
	 * @return Current HP
	 */
	public int getHP()
	{
		return HP;
	}

	/**
	 * TODO
	 * Check HP <= 0
	 */
	private void checkHP()
	{
		if(HP <= 0)
			noHP();
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
			noDurability();
	}
	
	public abstract void noHP();
	public abstract void noDurability();
}
