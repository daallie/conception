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
	protected byte loyalty;
	
	// Public Attribute Reference
	public static final byte INTELLIGENCE = 0;
	public static final byte CUNNING = 1;
	public static final byte STRENGTH = 2;
	public static final byte AGILITY = 3;
	public static final byte PERCEPTION = 4;
	public static final byte HONOR = 5;
	public static final byte SPEED = 6;
	public static final byte LOYALTY = 7;
	
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
	
	public Unit(byte intel, byte cunning, byte str, byte agi, byte perc, byte honor, byte speed, byte loyal)
	{
		this();
		this.intelligence = intel;
		
	}
	
	/*
	 * Attribute Methods
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
		loyalty = 0;
	}
	
	/**
	 * Get Attribute Level of Unit
	 * @param i Index of Attribute
	 * @return Value of Attribute or -1 if invalid index
	 */
	public byte getAttributeLevel(byte i)
	{
		switch(i)
		{
		case INTELLIGENCE:
			return intelligence;
		case CUNNING:
			return cunning;
		case STRENGTH:
			return strength;
		case AGILITY:
			return agility;
		case PERCEPTION:
			return perception;
		case HONOR:
			return honor;
		case SPEED:
			return speed;
		case LOYALTY:
			return loyalty;
		}
		return -1;
	}
	
	/**
	 * Set Attribute Value
	 * @param a Attribute Index (Ignores Invalid Attributes)
	 * @param b Value to set Attribute
	 */
	public void setAttribute(byte a, byte b)
	{
		if(b > 100)
			b = 100;
		else if(b < 0)
			b = 0;
		
		switch(a)
		{
		case INTELLIGENCE:
			intelligence = b;
			break;
		case CUNNING:
			cunning = b;
			break;
		case STRENGTH:
			strength = b;
			break;
		case AGILITY:
			agility = b;
			break;
		case PERCEPTION:
			perception = b;
			break;
		case HONOR:
			honor = b;
			break;
		case SPEED:
			speed = b;
			break;
		case LOYALTY:
			loyalty = b;
			break;
		}
	}
	
	/*
	 * Skill Methods
	 */
	
	/**
	 * Sets all Skills to 0
	 */
	public void resetSkills()
	{
		// TODO Keep Skill List upto date
		farming = 0;
	}
	
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
	 * Abstract Methods
	 */
	
	public abstract boolean move();
}
