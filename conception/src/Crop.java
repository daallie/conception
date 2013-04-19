import java.awt.event.ActionEvent;


public class Crop extends Item
{
	private String name;
	private byte minFertility;
	private byte optimalFertility;
	private byte maxFertility;
	private byte minElevation;
	private byte optimalElevation;
	private byte maxElevation;
	private byte minHumidity;
	private byte optimalHumidity;
	private byte maxHumidity;
	private boolean spread;
	
	// The Base Amount of Crop per Acre
	// Technology will Eventually Influence this amount
	private int density;
	
	// Status is an Indicator of Crop Growth
	// 0-100 status indicates growth
	private double status;
	
	// Daily Percent of Max (density variable) per Day (24*3 in a season)
	private double growthRate;
	
	// Daily Growth of 1.0 G
	private double plantRate;
	private double harvestRate;
	
	// Decay Rate
	// The rate that the crop decays after harvested
	private double decayRate;
	
	// The Seasons Associated with Crop Growth
	// These are the default settings
	// These can be used to override natural Seasons
	public static final byte PLANT = 3;
	public static final byte GROW = 6;
	public static final byte HARVEST = 9;

	public Crop()
	{
		super();
	}
	
	public Crop(byte a, byte b, byte c, byte d, byte e, byte f, int g, double h, double i, double j)
	{
		super();
		setMinFertility(a);
		setMaxFertility(b);
		setMinElevation(c);
		setMaxElevation(d);
		setMinHumidity(e);
		setMaxHumidity(f);
		density = g;
		growthRate = h;
		plantRate = i;
		harvestRate = j;
	}

	public int getCropMax()
	{
		return density;
	}

	
	public double getDensity()
	{
		// TODO Auto-generated method stub
		return density;
	}

	public double getStatus()
	{
		return status;
	}
	
	/**
	 * @return the minFertility
	 */
	public int getMinFertility()
	{
		return minFertility;
	}
	
	/**
	 * @param minFertility the minFertility to set
	 */
	public void setMinFertility(byte minFertility)
	{
		this.minFertility = minFertility;
	}

	/**
	 * @return the optimalFertility
	 */
	public byte getOptimalFertility()
	{
		return optimalFertility;
	}

	/**
	 * @param optimalFertility the optimalFertility to set
	 */
	public void setOptimalFertility(byte optimalFertility)
	{
		this.optimalFertility = optimalFertility;
	}

	/**
	 * @return the maxFertility
	 */
	public int getMaxFertility()
	{
		return maxFertility;
	}

	/**
	 * @param maxFertility the maxFertility to set
	 */
	public void setMaxFertility(byte maxFertility)
	{
		this.maxFertility = maxFertility;
	}

	/**
	 * @return the minElevation
	 */
	public int getMinElevation()
	{
		return minElevation;
	}

	/**
	 * @param minElevation the minElevation to set
	 */
	public void setMinElevation(byte minElevation)
	{
		this.minElevation = minElevation;
	}

	/**
	 * @return the optimalElevation
	 */
	public byte getOptimalElevation()
	{
		return optimalElevation;
	}

	/**
	 * @param optimalElevation the optimalElevation to set
	 */
	public void setOptimalElevation(byte optimalElevation)
	{
		this.optimalElevation = optimalElevation;
	}

	/**
	 * @return the maxElevation
	 */
	public int getMaxElevation()
	{
		return maxElevation;
	}

	/**
	 * @param maxElevation the maxElevation to set
	 */
	public void setMaxElevation(byte maxElevation)
	{
		this.maxElevation = maxElevation;
	}

	/**
	 * @return the minHumidity
	 */
	public int getMinHumidity()
	{
		return minHumidity;
	}

	/**
	 * @param minHumidity the minHumidity to set
	 */
	public void setMinHumidity(byte minHumidity)
	{
		this.minHumidity = minHumidity;
	}

	/**
	 * @return the optimalHumidity
	 */
	public byte getOptimalHumidity()
	{
		return optimalHumidity;
	}

	/**
	 * @param optimalHumidity the optimalHumidity to set
	 */
	public void setOptimalHumidity(byte optimalHumidity)
	{
		this.optimalHumidity = optimalHumidity;
	}

	/**
	 * @return the maxHumidity
	 */
	public int getMaxHumidity()
	{
		return maxHumidity;
	}

	/**
	 * @param maxHumidity the maxHumidity to set
	 */
	public void setMaxHumidity(byte maxHumidity)
	{
		this.maxHumidity = maxHumidity;
	}

	/**
	 * @return the spread
	 */
	public boolean isSpread()
	{
		return spread;
	}

	/**
	 * @param spread the spread to set
	 */
	public void setSpread(boolean spread)
	{
		this.spread = spread;
	}

	public double plantRate(byte b)
	{
		if(status != 0)
			return 0;
		switch (b)
		{
		/**
		 * TODO
		 * Remove Hard Code Here to allow for Crops to have variable seasonal rates
		 */
		case 0:
			return 0;
		case 3:
			return 0.028;
		case 6:
			return 0.018;
		default:
			return 0.008;
		}
	}
	
	public double growRate(byte b)
	{
		switch (b)
		{
		/**
		 * TODO
		 * Remove Hard Code Here to allow for Crops to have variable seasonal rates
		 */
		case 0:
			return 0;
		case 3:
			return 0.8;
		case 6:
			return 2.8;
		default:
			return 1.8;
		}
	}
	
	public void growingDay(byte b)
	{
		status += growRate(b);
	}

	public double harvestRate(byte b)
	{
		if(status < 100)
			return 0;
		switch (b)
		{
		/**
		 * TODO
		 * Remove Hard Code Here to allow for Crops to have variable seasonal rates
		 */
		case 0:
			return 0.008;
		case 3:
			return 0.018;
		case 6:
			return 0.008;
		default:
			return 0.028;
		}
	}

	public double decayRate(byte b)
	{
		switch (b)
		{
		/**
		 * TODO
		 * Remove Hard Code Here to allow for Crops to have variable seasonal rates
		 */
		case 0:
			return 0.0007;
		case 3:
			return 0.00093;
		case 6:
			return 0.00139;
		default:
			return 0;
		}
	}
	
	public void startPlanting()
	{
		status = 0;
	}
	
	public boolean hasGrown()
	{
		return status >= 100.0;
	}

	/*
	 * Override Methods
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
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
