import java.awt.event.ActionEvent;
import java.util.Random;

public abstract class NPC extends Unit
{
	/*Alpha can be tuned to what ever value we want between 1-100.
	 * The higher the alpha value, the more likely the npc is to 
	 * perform the desired action. The lower the alpha, the less
	 * likely the npc is to perform the action.
	 */
	private byte alpha=80;
	private Random r=new Random();
	
	private boolean learning()
	{
		int temp=(r.nextInt(100)+1);		//generate a random int between 1-100
		if(temp<=intelligence)
			return true;
		else
			return false;
	}
	
}