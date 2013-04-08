import java.awt.Point;
import java.awt.event.ActionListener;


public abstract class Entity implements ActionListener{
	private int HP;
	private static int maxHP = 100;
	private Acre myAcre;
	private double durability;
	private static final double maxDurability = 1.0;
	private static final double minDurability = 0.0;
	private String name;
	
	/*
	 * Constructor Methods
	 */
	
	/**
	 * Default Constructor
	 * HP = 1; Coordinates = (0,0); Durability = 1.0;
	 */
	public Entity()
	{
		HP = 1;
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

	public Entity(int h, int maxH, double d)
	{
		HP = h;
		maxHP = maxH;
		durability = d;
	}

	public Entity(int h, int maxH, double d, Acre a)
	{
		HP = h;
		maxHP = maxH;
		durability = d;
		myAcre = a;
	}
	
	/*
	 * HP Management Methods
	 */
	
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
	
	/*
	 * Location Management Methods
	 */
	
	public void setLocation(Acre a)
	{
		myAcre = a;
	}
	
	public Acre getLocation()
	{
		return myAcre;
	}
	
	/*
	 * Durability Management Methods
	 */
	
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

	/*
	 * Name Management Methods
	 */
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	/*
	 * UI Methods
	 */
	
	/**
	 * Returns Entity Name
	 */
	public String toString()
	{
		return name;
	}

	
	/*
	 * Abstract Methods
	 */
	
	public abstract void noHP();
	public abstract void noDurability();
}
