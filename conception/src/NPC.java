import java.awt.event.ActionEvent;
import java.util.Random;

public class NPC extends Unit
{
	/*Alpha can be tuned to what ever value we want between 1-100.
	 * The higher the alpha value, the more likely the npc is to 
	 * perform the desired action. The lower the alpha, the less
	 * likely the npc is to perform the action.
	 */
	private int alpha=80;
	private Random r=new Random();
	
	private boolean learning()
	{
		int temp=(r.nextInt(100)+1);		//generate a random int between 1-100
		if(temp<=alpha)
			return true;
		else
			return false;
	}
	
	/*public method to allow the alpha value to be set
	 * @param newAlpha needs to be an int between 1-100
	 */
	public void setAlpha(int newAlpha)
	{
		alpha=newAlpha;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noHP() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noDurability() {
		// TODO Auto-generated method stub
		
	}

}