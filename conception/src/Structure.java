import java.awt.event.ActionEvent;
import java.util.ArrayList;


public abstract class Structure extends Entity {
	private Unit owner;
	private ArrayList<Unit> workers;
	
	
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
	
	public Structure(Unit u, int x, int y)
	{
		super(1000, 1000, x, y, 1);
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
	
	public abstract void gameTick();
	public abstract void gameDay();
}
