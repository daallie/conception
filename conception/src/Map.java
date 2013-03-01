/*TODO
 * Write Map Generation Code
 */

import java.util.Random;

public class Map {

	private Acre[][] grid;
	private int size;
	private int x;
	private int y;
	private int acresTouched = 0;
	
	public Map()
	{
		this(1);
	}
	
	public Map(int area)
	{
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
		acresTouched +=4;
		generateBiomesHelper(grid[(size-1)/2][(size-1)/2], (size-1)/2, 1, generator);
		System.out.println(size*size);
		System.out.println(acresTouched);
	}
	
	private void generateBiomesHelper(Acre center, int length, double spread, Random generator)
	{
		// Base Case
		if(length<1)
			return;
		
		// Square Step
		// Get Corner Values
		int x = center.getX();
		int y = center.getY();
		System.out.println("Centered " + x + " " + y);
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
		center.setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		System.out.println("Set Elevation: " + center.getElevation() + " Using: " + average + " Spread: " + spread);
		
		// Diamond Step
		average = (center.getElevation() + average)/4;
		
		// Set New Elevation South Point
		grid[x][y+length].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		System.out.println("Corner " + x + " " + (y+length));
		System.out.println("Set Elevation: " + grid[x][y+length].getElevation() + " Using: " + average + " Spread: " + spread);

		// Set New Elevation North Point
		grid[x][y-length].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		System.out.println("Corner " + x + " " + (y-length));
		System.out.println("Set Elevation: " + grid[x][y-length].getElevation() + " Using: " + average + " Spread: " + spread);
		
		// Set New Elevation East Point
		grid[x+length][y].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		System.out.println("Corner " + (x+length) + " " + (y));
		System.out.println("Set Elevation: " + grid[x+length][y].getElevation() + " Using: " + average + " Spread: " + spread);
		
		// Set New Elevation West Point
		grid[x-length][y].setElevation((int) (average + (generator.nextGaussian()) * (30 * spread)));
		System.out.println("Corner " + (x-length) + " " + (y));
		System.out.println("Set Elevation: " + grid[x-length][y].getElevation() + " Using: " + average + " Spread: " + spread);
		
		acresTouched += 5;
		
		// Recursive Call
		length = length/2;
		// Bottom Right
		generateBiomesHelper(grid[x+length][y+length], length, spread/3, generator);
		// Bottom Left
		generateBiomesHelper(grid[x-length][y+length], length, spread/3, generator);
		// Top Right
		generateBiomesHelper(grid[x+length][y-length], length, spread/3, generator);
		// Top Left
		generateBiomesHelper(grid[x-length][y-length], length, spread/3, generator);
	}

	private void resetChecks()
	{
		System.out.println("Resetting Checks");
		for(int i = 0; i< size; i++)
		{
			for(int j = 0; j< size; j++)
			{
				grid[i][j].resetCheck();
			}
		}
	}
}
