
public class Calendar
{
	private int time;
	private int day;
	private int month;
	private int year;
	
	private static int DAY = 50;
	private static int MONTH = 24;
	private static int YEAR = 12;
	
	public Calendar()
	{
		time = 0;
		day = 0;
		month = 0;
		year = 0;
	}
	
	public void gameTick()
	{
		time++;
		if(time>DAY)
		{
			dayTick();
			time = 0;
		}
	}
	
	private void dayTick()
	{
		day++;
		if(day>MONTH)
		{
			monthTick();
			day = 0;
		}
	}
	
	private void monthTick()
	{
		month++;
		if(month>YEAR)
		{
			yearTick();
			month = 0;
		}
	}
	
	private void yearTick()
	{
		
	}
}
