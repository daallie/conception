import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observer;


public abstract class Structure extends Entity implements Observer {
	private int inhabitantLimit;
	protected Unit owner;
	protected ArrayList<Unit> workers;
	protected ArrayList<Unit> inhabitants;
	protected Acre location;
	
	/**
	 * Default Constructor
	 * Avoid Using
	 */
	public Structure()
	{
		super();
	}
	
	public Structure(Unit u)
	{
		this();
		owner = u;
	}
	
	public Structure(Unit u, Acre a)
	{
		super(1000, 1000, 1, a);
		owner = u;
	}
	
	/*
	 * Methods to Handle Worker Management
	 */
	
	/**
	 * Adds Worker to Structure
	 * This new Unit will be used in the calculation for productivity/pay/etc
	 * @param u
	 */
	public boolean addWorker(Unit u)
	{
		// Prevent Duplicate Workers
		if(workers.contains(u))
			return false;
		
		return workers.add(u);
	}
	
	public boolean removeWorker(Unit u)
	{
		return workers.remove(u);
	}
	
	/**
	 * 
	 * @return Full List of Workers for this Structure
	 */
	public ArrayList<Unit> getWorkers()
	{
		return workers;
	}
	
	/*
	 * Methods to Handle Inhabitant Management
	 */
	
	/**
	 * Add Unit to Inhabit structure (this does assign them as a worker as well)
	 * @param u Unit to add as Inhabitant
	 * @return If inhabitant was successfully added
	 */
	public boolean addInhabitant(Unit u)
	{
		// Check against Inhabitant Limit
		if(inhabitants.size()<inhabitantLimit)
		{
			// Prevent Duplicates
			if(inhabitants.contains(u))
				return false;
			
			// Add Inhabitant as Worker (Requirement)
			addWorker(u);
			inhabitants.add(u);
			return true;
		}
		return false;
	}
	
	/**
	 * Remove inhabitant from the structure
	 * @param u Unit to Remove from Inhabitant List (Does NOT remove them as a worker)
	 * @return true if unit was in list
	 */
	public boolean removeInhabitant(Unit u)
	{
		return inhabitants.remove(u);
	}
	
	/**
	 * 
	 * @return ArrayList of Inhabitants
	 */
	public ArrayList<Unit> getInhabitants()
	{
		return inhabitants;
	}

	/**
	 * Sets maximum limit of Inhabitants (Does NOT currently remove inhabitants if maximum is changed below number of inhabitants)
	 * @param i Amount to Set Inhabitant Limit
	 */
	public void setInhabitantLimit(int i)
	{
		inhabitantLimit = i;
	}
	
	/**
	 * 
	 * @return Inhabitant Limit Number
	 */
	public int getInhabitantLimit()
	{
		return inhabitantLimit;
	}

	
	/*
	 * Abstract Methods Section
	 */
	
	public abstract String status();
	public abstract double productivity();
	public abstract void educate();
	public abstract void gameTick();
	public abstract void gameDay(int day);
	public abstract void gameMonth(int month);
	public abstract void gameYear(int year);
}
