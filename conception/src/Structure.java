import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observer;


public abstract class Structure extends Entity implements Observer {
	private Unit owner;
	private ArrayList<Unit> workers;
	private Acre location;
	
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
	
	public void addWorker(Unit u)
	{
		workers.add(u);
	}
	
	public ArrayList<Unit> getWorkers()
	{
		return workers;
	}
	
	public abstract String status();
	public abstract double productivity();
	public abstract void gameTick();
	public abstract void gameDay(int day);
	public abstract void gameMonth(int month);
	public abstract void gameYear(int year);
}
