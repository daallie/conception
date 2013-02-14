import javax.swing.JFrame;


public class Driver
{
	public static void main(String[] args)
	{
		JFrame main = createStartFrame();
	}
	
	private static JFrame createStartFrame()
	{
		JFrame temp = new JFrame();		
		temp.setSize(800, 600);
		temp.setResizable(true);
		temp.setVisible(true);
		return temp;
	}
}

