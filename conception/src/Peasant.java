import java.awt.event.ActionEvent;


public class Peasant extends NPC
{
	private Structure home;
	private Structure work;
	
	/*
	 * Constructor Methods
	 */
	public Peasant()
	{
		super();
	}

	/*
	* Home Methods
	*/
	
	/**
	 * This will only work if no home is currently set use move() to change home
	 * @param s New Home Structure
	 */
	public void setHome(Structure s)
	{
		if(home == null)
		{
			s.addInhabitant(this);
			home = s;
		}
	}
	
	/**
	 * Get Home
	 * @return Current Structure that is home
	 */
	public Structure getHome()
	{
		return home;
	}
	
	/**
	 * Change current Home 
	 * @param s New Home Structure
	 */
	public void move(Structure s)
	{
		evict();
		setHome(s);
	}
	
	/*
	 * TODO Add potential side effects for evictions
	 */
	/**
	 * Remove from home
	 */
	public void evict()
	{
		if(home != null)
		{
			home.removeInhabitant(this);
			home = null;
		}
	}
	
	
	/*
	 * Work Methods
	 */
	
	/**
	 * Sets work location will only work if no location is set, to change work location use transfer()
	 * @param s New work location
	 */
	public void setWork(Structure s)
	{
		if(work == null)
		{
			s.addWorker(this);
			work = s;
		}
	}
	
	/**
	 * Get Work
	 * @return Current work Structure
	 */
	public Structure getWork()
	{
		return work;
	}
	
	/**
	 * Change Work Location
	 * @param s New Work Structure
	 */
	public void transfer(Structure s)
	{
		fire();
		setWork(s);
	}
	
	/**
	 * Remove current work location
	 */
	public void fire()
	{
		if(work != null)
		{
			work.removeWorker(this);
		}
	}
	
	
	/*
	 * TODO
	 * Write Override Methods
	 */
	
	/*
	 * Abstract Implementation Methods
	 */
	
	@Override
 	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean move()
	{
		// TODO Auto-generated method stub
		return false;
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

}
