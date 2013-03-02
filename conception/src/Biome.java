
public class Biome {
	/**
	 * TODO
	 * rewrite to just alter the acres rather than trying to claim them
	 */
	private boolean canGrow = false;
	private Acre center;
	private int id;
	
	public Biome(int i)
	{
		id = i;
	}
	
	public void name(int i)
	{
		id = i;
	}
	
	public boolean canGrow()
	{
		return canGrow;
	}
	
	public boolean grow()
	{
		System.out.println("Attemping to Grow");
		if(!canGrow)
			return false;
		Acre temp = center;
		int count = 1;
		int direction = 0;
		while(canGrow)
		{
			for(int i = 0; i<count; i++)
			{
				if(direction == 0)
				{
					temp = temp.getNorth();
					if(temp.biomeClaim(this))
						return true;
				}
				else
				{
					temp = temp.getSouth();
					if(temp.biomeClaim(this))
						return true;
				}
				if(temp.inBiome() && !temp.getBiome().equals(this))
					canGrow = false;
			}
			for(int i = 0; i<count; i++)
			{
				if(direction == 0)
				{
					temp = temp.getEast();
					if(temp.biomeClaim(this))
						return true;
				}
				else
				{
					temp = temp.getWest();
					if(temp.biomeClaim(this))
						return true;
				}
				if(temp.inBiome() && !temp.getBiome().equals(this))
					canGrow = false;
			}
			if(direction == 0)
				direction = 1;
			else
				direction = 0;
			count ++;
		}
		
		return false;
	}
	
	public void generate()
	{
		/**
		 * TODO
		 */
	}
	
	public boolean setCenter(Acre a)
	{
		if(center==null)
		{
			if(a.biomeClaim(this))
			{
				System.out.println("Biome " + id + " Centered on " + a.location());
				center = a;
				canGrow = true;
				return true;
			}
		}
		return false;
	}

	public int getID()
	{
		return id;
	}
	
	public boolean equals(Biome b)
	{
		return b.id == id;
			
	}
}
