import java.awt.event.ActionEvent;


public abstract class Tree extends Item
{
	private String name;
	private int minFertility;
	private int maxFertility;
	private int minElevation;
	private int maxElevation;
	private int minHumidity;
	private int maxHumidity;
	
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

	public Tree()
	{
		super();
	}
	
	public Tree(int a, int b, int c, int d, int e, int f, int g, double h, double i, double j)
	{
		super();
		minFertility = a;
		maxFertility = b;
		minElevation = c;
		maxElevation = d;
		minHumidity = e;
		maxHumidity = f;
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
	
}
