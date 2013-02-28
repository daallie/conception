/*TODO
 * Write Map Generation Code
 */


public class Map {

	private Acre[][] grid;
	private int size;
	private int x;
	private int y;
	private int numBiomes = 4;
	private Biome[] biomes;
	
	public Map()
	{
		this(1);
	}
	
	public Map(int area)
	{
		area = area*640;
		area = (int) Math.sqrt(area);
		grid = new Acre[area][area];
		size = area;
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
		System.out.println("Unclaimed: " + numUnclaimed());
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
		biomes = new Biome[numBiomes];
		// Set Poles
		biomes[0] = new Biome(1);
		biomes[0].setCenter(grid[0][0]);
		biomes[1] = new Biome(2);
		biomes[1].setCenter(grid[size/2][size/2]);
		// Get Centers
		for(int i = 2; i<numBiomes; i++)
		{
			// Generate New Biome
			biomes[i] = new Biome(i+1);
			// Get Center
			while(!biomes[i].canGrow())
			{
				// Generate Random Numbers
				int x = (int) (Math.random() * size);
				int y = (int) (Math.random() * size);
				biomes[i].setCenter(grid[x][y]);
			}
		}
		boolean growth = true;
		while(growth)
		{
			resetChecks();
			boolean temp = false;
			for(int i = 0; i<numBiomes && !temp; i++)
			{
				if(biomes[i].canGrow())
					temp = true;
			}
			if(temp)
			{
				// Get Random Biome
				int b = (int) (Math.random() * numBiomes);
				System.out.println("Growing Biome " + (b+1) + " ");
				System.out.println(biomes[b].grow());
			}
			growth = temp;
		}
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

	private int numUnclaimed()
	{
		int temp = 0;
		for(int i = 0; i<size; i++)
			for(int j = 0; j<size; j++)
				if(grid[i][j].biomeID == 0)
					temp ++;
		return temp;
	}
}
