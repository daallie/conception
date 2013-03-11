import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


public class Calendar extends Observable
{
	Map myMap;
	private int time;
	private int day;
	private int month;
	private int year;
	private long interval = 6250;
	
	private static byte DAY = 24;
	private static byte MONTH = 24;
	private static byte YEAR = 12;
	
	public static final byte SPRING = 3;
	public static final byte SUMMER = 6;
	public static final byte FALL = 9;
	public static final byte WINTER = 12;
	
	
	public Calendar(Map map)
	{
		myMap = map;
		time = 1;
		day = 1;
		month = 1;
		year = 1;
		Timer tim = new Timer();
		tim.scheduleAtFixedRate(new TimeHandle(),0,10);
	}
	
	private void gameTick()
	{
		time ++;
		if(time>DAY)
		{
			time = 1;
			dayTick();
		}
	}
		
	private void dayTick()
	{
		setChanged();
		myMap.gameDay();
		day++;
		if(day>MONTH)
		{
			monthTick();
			day = 1;
		}
		notifyObservers();
		clearChanged();
	}
	
	private void monthTick()
	{
		myMap.gameMonth();
		month++;
		if(month>YEAR)
		{
			yearTick();
			month = 1;
		}
	}
	
	private void yearTick()
	{
		year++;
		myMap.gameYear();
	}

	public byte getSeason()
	{
		switch(month)
		{
		case 3:
		case 4:
		case 5:
			return SPRING;
		case 6:
		case 7:
		case 8:
			return SUMMER;
		case 9:
		case 10:
		case 11:
			return FALL;
		default:
			return WINTER;
		}
	}
	
	public void setDay(int i)
	{
		day = i;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public void setMonth(int i)
	{
		month = i;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setYear(int i)
	{
		year = i;
	}
	
	public int getYear()
	{
		return year;
	}

	private class TimeHandle extends TimerTask
	{		
		public TimeHandle()
		{
		}
		
		@Override
		public void run()
		{
			gameTick();
		}
		
	}
	
}


