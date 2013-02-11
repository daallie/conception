/*TODO
 * Write Map Generation Code
 */


public class Map {

	private Acre[][] grid;
	private int size;
	
	public Map(int area)
	{
		area = area*640;
		area = (int) Math.sqrt(area);
		grid = new Acre[area][area];
		size = area;
	}
}
