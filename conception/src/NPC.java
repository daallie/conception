import java.awt.event.ActionEvent;
import java.util.Random;

public abstract class NPC extends Unit
{
	private Unit ruler;
	
	/*
	 * Constructor Methods
	 */
	
	public NPC()
	{
		super();
	}
	
	public NPC(Unit u)
	{
		this();
		setRuler(u);
	}
	
	/*
	 * Ruler Management Methods
	 */
	
	/**
	 * 
	 * @param u NPC new Ruler
	 */
	public void setRuler(Unit u)
	{
		ruler = u;
	}
	
	/**
	 * 
	 * @return NPC Ruler
	 */
	public Unit getRuler()
	{
		return ruler;
	}
}