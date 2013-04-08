import java.util.Random;


public abstract class Unit extends Entity
{
	/*Alpha can be tuned to what ever value we want between 1-100.
	 * The higher the alpha value, the more likely the npc is to 
	 * perform the desired action. The lower the alpha, the less
	 * likely the npc is to perform the action.
	 */
	private byte alpha=80;
	
	// Description Stats
	private boolean male;
	
	// Attributes
	protected byte intelligence;
	protected byte cunning;
	protected byte strength;
	protected byte agility;
	protected byte perception;
	protected byte honor;
	protected byte speed;
	
	// Skills
	protected byte farming;
	
	// Public Skill Reference
	public static final byte FARMING = 0; 
	
	// TODO Update for Each new Skill
	// This keeps track of the last number so other methods can easily recreate or access all skills
	public static final byte lastSkill = 0;
	
	/*
	 * Constructors
	 */
	
	public Unit()
	{
		super();
		male = true;
		resetAttributes();
		resetSkills();
	}
	
	/*
	 * Constructor Helpers
	 */
	
	/**
	 * Sets all Attributes to 0
	 */
	public void resetAttributes()
	{
		// TODO Keep Attribute List upto date
		intelligence = 0;
		cunning = 0;
		strength = 0;
		agility = 0;
		perception = 0;
		honor = 0;
		speed = 0;
	}
	
	/**
	 * Sets all Skills to 0
	 */
	public void resetSkills()
	{
		// TODO Keep Skill List upto date
		farming = 0;
	}
	
	/*
	 * Gender Methods
	 */
	
	/**
	 * Get Gender
	 * @return Male: 0, Female: 1
	 */
	public int gender()
	{
		if(male)
			return 0;
		return 1;
	}
	
	/**
	 * Set Gender
	 * @param i Male: 0, Female: All other ints
	 */
	public void setGender(int i)
	{
		if(i == 0)
			male = true;
		else
			male = false;
	}
	
	/**
	 * Is Unit Male
	 * @return True if unit is male
	 */
	public boolean isMale()
	{
		return male;
	}
	
	/**
	 * Is Unit Female
	 * @return True if unit is female
	 */
	public boolean isFemale()
	{
		return !male;
	}
	
	/**
	 * Sets Unit Male
	 */
	public void setMale()
	{
		male = true;
	}
	
	/**
	 * Sets Unit Female
	 */
	public void setFemale()
	{
		male = false;
	}
	
	/*
	 * Skill Methods
	 */
	
	/**
	 * Get Number of Skills
	 * @return Last index value of Skill, starts at 0
	 */
	public byte getNumSkills()
	{
		return lastSkill;
	}
	
	/**
	 * Get Skill Level
	 * @param i Index of Skill
	 * @return Skill Value or -1 if i is not a valid index
	 */
	public byte getSkillLevel(byte i)
	{
		/**
		 * TODO
		 */
		switch(i)
		{
		case FARMING:
				return farming;
		}
		return -1;
	}
	
	/**
	 * 
	 * @param i The Skill to be Developed
	 */
	public void developSkill(byte i)
	{
		/**
		 * TODO
		 */
		if(!learning())
			return;
		switch(i)
		{
		case FARMING:
				farming++;
			break;
		}
	}
	
	/**
	 * Random Method to calculate learning
	 * @return
	 */
	private boolean learning()
	{
		int temp = (int) (Math.random() * 100);		//generate a random int between 0-100
		if(temp<=intelligence)
			return true;
		return false;
	}

	
	/*
	 * Abstract Methods
	 */
	
	public abstract boolean move();
}
