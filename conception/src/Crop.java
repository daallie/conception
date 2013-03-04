import java.awt.event.ActionEvent;


public abstract class Crop extends Item
{
	private String name;
	private int minFertility;
	private int maxFertility;
	private int minElevation;
	private int maxElevation;
	private int minHumidity;
	private int maxHumidity;
	private double growthRate;	
	
	public Crop()
	{
		super();
	}
	
	public Crop(int a, int b, int c, int d, int e, int f)
	{
		minFertility = a;
		maxFertility = b;
		minElevation = c;
		maxElevation = d;
		minHumidity = e;
		maxHumidity = f;
	}
}
