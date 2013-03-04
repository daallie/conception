/*TODO
 * Write Map Generation Code
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable 
{
	private Acre[][] grid;
	private int size;
	private int power;
	private int x;
	private int y;
	private int focusX;
	private int focusY;
	
	public Map()
	{
		this(1);
	}
	
	public Map(int area)
	{
		power = area;
		size = (int) (Math.pow(2,area) + 1);
		System.out.println("Generating " + (size*size) + " Acres");
		grid = new Acre[size][size];
		/**
		 * TODO Custom Map Generation
		 */
		baseMap();
		x = 0;
		y = 0;
	}

	public void baseMap()
	{
		for(int i = 0; i<size; i++)
			for(int j = 0; j<size; j++)
			{
				grid[i][j] = new Acre();
				grid[i][j].setLocation(i, j);
			}
		linkAcres();
		generateBiomes();
		generateOceans();
		generateLakes();
		generateMountains();
	}
	
	/**
	 * 
	 * @param x Number of Horizontal Acres
	 * @param y Number of Vertical Acres
	 * @return Array of Acres around Current Focus Point
	 */
	public Acre[][] getDisplayMap(int x, int y)
	{
		Acre[][] temp = new Acre[x][y];
		int left = this.x - x/2;
		int theTop = this.y - y/2;
		for(int i = 0; i<x; i++)
		{
			int xCoord;
			// If wraps around to the Left
			if(left<0)
			{
				xCoord = size+left;
			}
			// If wraps around to the Right
			else if(left>=size)
			{
				xCoord = left-size;
			}
			else
			{
				xCoord = left;
			}
			int top = theTop;
			for(int j = 0; j<y; j++)
			{
				
				int yCoord;
				// If wraps around the Top
				if(top<0)
				{
					yCoord = size+top;
				}
				// If wraps around the Bottom
				else if(top>=size)
				{
					yCoord = top-size;
				}
				else
				{
					yCoord = top;
				}
				temp[i][j] = grid[xCoord][yCoord];
				top++;
			}
			left++;
		}
		return temp;
	}
	
	/**
	 * Links Acres Used by all Generation Methods
	 */
	private void linkAcres()
	{
		for(int i = 0; i<size; i++)
			for(int j = 0; j<size; j++)
			{
				Acre temp = grid[i][j];
				// Checks if Left Side
				if(i == 0)
				{
					/**
					 * REMOVE
					 */
					// Checks if Top Left
					if(j == 0)
					{
						temp.setLinks(grid[i][size -1], grid[i][j+1], grid[i+1][j], grid[size-1][j]);
					}
					// Checks if Bottom Left
					else if(j == size-1)
					{
						temp.setLinks(grid[i][j - 1], grid[i][size - 1], grid[0][j], grid[size-1][j]);
					}
					else
						temp.setLinks(grid[i][j-1], grid[i][j+1], grid[i+1][j], grid[size-1][j]);
				}
				// Checks if Right Side
				else if(i == size-1)
				{
					// Checks if Top Right
					if(j == 0)
						temp.setLinks(grid[i][size-1], grid[i][j+1], grid[0][j], grid[i-1][j]);
					// Checks if Bottom Right
					else if(j == size-1)
						temp.setLinks(grid[i][j-1], grid[i][0], grid[0][j], grid[i-1][j]);
					else
						temp.setLinks(grid[i][j-1], grid[i][j+1], grid[0][j], grid[i-1][j]);
				}
				// Checks if Top
				else if(j == 0)
					temp.setLinks(grid[i][size-1], grid[i][j+1], grid[i+1][j], grid[i-1][j]);
				// Checks if Bottom
				else if(j == size-1)
					temp.setLinks(grid[i][j-1], grid[i][0], grid[i+1][j], grid[i-1][j]);
				// Not on the Side
				else
					temp.setLinks(grid[i][j-1], grid[i][j+1], grid[i+1][j], grid[i-1][j]);
			}
	}

	/**
	 * TODO
	 * @param i Direction to move UP-0 , DOWN-1, LEFT-2, RIGHT-3
	 */
	public void move(int i)
	{
		switch(i)
		{
			// Move Up
			case 0:
				y--;
				if(y<0)
					y = size-1;
				break;
			// Move Down
			case 1:
				y++;
				if(y>=size)
					y = 0;
				break;
			// Move Left
			case 2:
				x--;
				if(x<0)
					x = size -1;
				break;
			// Move Right
			default:
				x++;
				if(x>=size)
					x = 0;
				break;
		}
	}

	/**
	 * Generates Biomes Using Midpoint Displacement Diamond-Square Algorithm
	 * Processes Initial Corners and then calls helper method
	 */
	private void generateBiomes()
	{
		// Random Generator
		Random generator = new Random();
		// Set the Corners to the same random height
		int temp = (int) (generator.nextGaussian() * 30);
		grid[0][0].setElevation(temp);
		grid[0][size-1].setElevation(temp);
		grid[size-1][0].setElevation(temp);
		grid[size-1][size-1].setElevation(temp);
		generateBiomesHelper(grid[(size-1)/2][(size-1)/2], (size-1)/2, 1, generator);
	}
	
	/**
	 * Biome Generation Helper Method
	 * Initiates Diamond Then Prepares New Corners before recursively calling on new quadrants
	 * @param center Center point of current square
	 * @param length 1/2 the Length of the Current Square
	 * @param spread Variable difference possible from the average
	 * @param generator Java Random Object (Is reused to conserve memory
	 */
	private void generateBiomesHelper(Acre center, int length, double spread, Random generator)
	{
		// Base Case
		if(length<1)
			return;
		
		// Square Step
		// Get Corner Values
		int x = center.getX();
		int y = center.getY();
		// Bottom Right Corner
		int average = grid[x+length][y+length].getElevation();
		// Bottom Left Corner
		average += grid[x-length][y+length].getElevation();
		// Top Right Corner
		average += grid[x+length][y-length].getElevation();
		// Top Left Corner
		average += grid[x-length][y-length].getElevation();
		average = average/4;
		// Set New Elevation
		center.setElevation((int) (average + (generator.nextGaussian()) * (50 * spread)));
		
		// Diamond Step
		average = (center.getElevation() + average)/4;
		
		// Set New Elevation South Point
		if(!grid[x][y+length].isChecked())
		{
			grid[x][y+length].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		}

		// Set New Elevation North Point
		if(!grid[x][y-length].isChecked())
		{
			grid[x][y-length].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		}
		
		// Set New Elevation East Point
		if(!grid[x+length][y].isChecked())
		{
			grid[x+length][y].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		}
		
		// Set New Elevation West Point
		if(!grid[x-length][y].isChecked())
		{
			grid[x-length][y].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		}
		
		// Recursive Call
		length = length/2;
		double newSpread = spread/(1 + 1/(power/5.0));
		// Bottom Right
		generateBiomesHelper(grid[x+length][y+length], length, newSpread, generator);
		// Bottom Left
		generateBiomesHelper(grid[x-length][y+length], length, newSpread, generator);
		// Top Right
		generateBiomesHelper(grid[x+length][y-length], length, newSpread, generator);
		// Top Left
		generateBiomesHelper(grid[x-length][y-length], length, newSpread, generator);
	}

	/**
	 * Generates Oceans
	 * Essentially Determines what qualifies as salt water.
	 * Current default permiates all from all <0 elevation acres 
	 * 	connected directly to an acre <-10 elevation
	 */
	private void generateOceans()
	{
		// Find all Acres with Elevation <-10
		ArrayList<Acre> deepWater = new ArrayList<Acre>();
		for(int i = 0; i<size; i++)
			for(int j = 0; j<size; j++)
			{
				if(grid[i][j].getElevation()<-10)
					deepWater.add(grid[i][j]);
			}
		
		// Parse All Connected Acres
		for(Acre ocean : deepWater)
		{
			generateOceansHelper(ocean);
		}
	}
	
	/**
	 * Recursively Parses through all attached Acres and sets as Ocean
	 * Also Depletes the first land Acre Fertility to 0
	 * @param ocean Acre to Start
	 */
	private void generateOceansHelper(Acre ocean)
	{
		try
		{
			// Base Case
			if(ocean.getElevation()>=0)
			{
				ocean.alterFertility(0);
				ocean.alterHumidity(1.5);
				ocean.getEast().alterFertility(0.5);
				ocean.getEast().alterHumidity(1.25);
				ocean.getWest().alterFertility(0.5);
				ocean.getWest().alterHumidity(1.25);
				ocean.getNorth().alterFertility(0.5);
				ocean.getNorth().alterHumidity(1.25);
				ocean.getSouth().alterFertility(0.5);
				ocean.getSouth().alterHumidity(1.25);
				return;
			}
			// Already Seen
			if(ocean.isOcean())
				return;
			// Set as Ocean
			ocean.setOcean(true);
			// Call Recursively
			generateOceansHelper(ocean.getEast());
			generateOceansHelper(ocean.getNorth());
			generateOceansHelper(ocean.getSouth());
			generateOceansHelper(ocean.getWest());
		}
		catch(StackOverflowError e)
		{
			System.out.println("All Ocean");
			return;
		}
	}
	
	/**
	 * Generates Lakes
	 * Grabs All Land <0 that is Not Ocean
	 * Also Augments the Fertility of the Surrounding Land
	 */
	private void generateLakes()
	{
		// Get all Acres <0 and Not Ocean
		ArrayList<Acre> lakes = new ArrayList<Acre>();
		for(int i = 0; i<size; i++)
			for(int j = 0; j<size; j++)
			{
				if(!grid[i][j].isOcean() && grid[i][j].getElevation()<0)
					lakes.add(grid[i][j]);
			}
		// Augment all Fertility
		for(Acre lake: lakes)
		{
			generateLakesHelper(lake,1.1);
		}
	}
	
	/**
	 * Recursive Method to improve the fertility of land surrounding fresh water
	 * @param lake Lake Origin
	 * @param i Amount to Modify Fertility
	 */
	private void generateLakesHelper(Acre lake, double i)
	{
		// Base Case
		if(i<=1)
			return;
		// Modify Fertility
		lake.alterFertility(i);
		lake.alterHumidity(1+(i-1)/2);
		// Shift Fertility Change
		i *= .985;
		// Recursive Call
		generateLakesHelper(lake.getEast(),i);
		generateLakesHelper(lake.getNorth(),i);
		generateLakesHelper(lake.getSouth(),i);
		generateLakesHelper(lake.getWest(),i);
	}

	/**
	 * Method to generate Mountains and effect fertility and Temperature on those areas
	 */
	private void generateMountains()
	{
		// Get Max Height
		int max = 0;
		int maxX = 0;
		int maxY = 0;
		for(int i = 0; i<size; i++)
		{
			for(int j = 0; j<size; j++)
			{
				if(grid[i][j].getElevation()>max)
				{
					max = grid[i][j].getElevation();
					maxX = i;
					maxY = j;
				}
			}
		}
		System.out.println("Highest Elevation: " + max + "(" +  maxX + ", " + maxY + ")");
		// If there are high mountains
		if(max > 10)
		{
			ArrayList<Acre> mountains = new ArrayList<Acre>();
			for(int i = 0; i<size; i++)
			{
				for(int j = 0; j<size; j++)
				{
					if(grid[i][j].getElevation()>10)
						mountains.add(grid[i][j]);
				}
			}
			for(Acre mountain: mountains)
			{
				mountain.alterFertility(Math.pow(0.95,(mountain.getElevation()-10)));
				mountain.alterTemperature(Math.pow(0.95,(mountain.getElevation()-10)));
				mountain.alterHumidity(Math.pow(0.9,(mountain.getElevation()-10)));
			}
		}
	}
	
	/**
	 * 
	 * @param x X Coord
	 * @param y Y Coord
	 */
	public String[] rightClick(int x, int y)
	{
		int tempX = this.x+x;
		int tempY = this.y+y;
		if(tempX<0)
			tempX = size+tempX;
		else if(tempX>=size)
			tempX = tempX-size;
		if(tempY<0)
			tempY = size+tempY;
		else if(tempY>=size)
			tempY = tempY-size;
		focusX = tempX;
		focusY = tempY;
		return grid[tempX][tempY].getMenu();
	}

	public void option(int i)
	{
		// TODO Auto-generated method stub
		switch(i)
		{
		case 0:
			grid[focusX][focusY].setStructure(new Farm());
		case 1:
			grid[focusX][focusY].setStructure(new FarmField(grid[focusX][focusY].getFarm()));
		}
	}
	
	public String getAcreDetails(int x, int y)
	{
		int tempX = this.x+x;
		int tempY = this.y+y;
		if(tempX<0)
			tempX = size+tempX;
		else if(tempX>=size)
			tempX = tempX-size;
		if(tempY<0)
			tempY = size+tempY;
		else if(tempY>=size)
			tempY = tempY-size;
		return grid[tempX][tempY].getDetails();
	}
}
