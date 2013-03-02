import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Menu extends BasicGameState
{
	private int ID;
	private int width;
	private int height;
	private int menu = -1;
	
	public Menu(int state)
	{
		ID = state;
	}
	
	// Initialize all values needed for the game
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		width = gc.getWidth();
		height = gc.getHeight();
	}
	
	// This method renders images, strings, shapes, etc.
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.clear();
		// Mouse Input Test
		Input input = gc.getInput();
		g.drawString("X: " + input.getMouseX() + " Y: " + input.getMouseY(), 0, 0);
		
		switch(menu)
		{
			// Set Resolution Menu
			case 0: g.drawString("800 x 600", width/2 - 43, height/2 - 30);
					g.drawString("1024 x 768", width/2 - 47, height/2 - 10);
					g.drawString("1600 x 1024", width/2 - 51, height/2 + 10);
					g.drawString("Back", width/2 - 19, height/2 + 30);
					break;
			default:g.drawString("Play", width/2 - 19, height/2 - 30);
					g.drawString("Resolution", width/2 - 43, height/2 - 10);
					g.drawString("Logout", width/2 - 27, height/2 + 10);
					break;
		}
		
	}
	
	// This method updates the screen. It is how the animation works within the game.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = gc.getInput();
		switch(menu)
		{
			// Handle Resolution Menu
			case 0:
				if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON))
				{
					int mouseX = input.getMouseX();
					int mouseY = input.getMouseY();
					// Change Resolution to 800 x 600
					if(mouseX > width/2-43 && mouseX < width/2+43 && mouseY > height/2 - 30 && mouseY < height/2 - 10)
					{
						Game.appgc.setDisplayMode(800, 600, false);
						mouseWait();
					}
					// Change Resolution to 1024 x 768
					if(mouseX > width/2-47 && mouseX < width/2+47 && mouseY > height/2 - 10 && mouseY < height/2 + 10)
					{
						Game.appgc.setDisplayMode(1024, 768, false);
						mouseWait();
					}
					// Change Resolution to 1600 x 1024
					if(mouseX > width/2-51 && mouseX < width/2+51 && mouseY > height/2 + 10 && mouseY < height/2 + 30)
					{
						Game.appgc.setDisplayMode(1600, 1024, false);
						mouseWait();
					}
					if(mouseX > width/2-19 && mouseX < width/2+19 && mouseY > height/2 + 30 && mouseY < height/2 + 50)
					{
						menu = -1;
						mouseWait();
					}
				}
				break;
			// Default Menu
			default:
				if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON))
				{
					int mouseX = input.getMouseX();
					int mouseY = input.getMouseY();
					// Logout
					if(mouseX > width/2-27 && mouseX < width/2+27 && mouseY > height/2+10 && mouseY < height/2+30)
					{
						sbg.enterState(Game.LOGIN);
						mouseWait();
					}
					// Play
					if(mouseX > width/2-19 && mouseX < width/2+19 && mouseY > height/2-30 && mouseY < height/2-10)
					{
						sbg.enterState(Game.PLAY);
						mouseWait();
					}
					else if(mouseX > width/2-43 && mouseX < width/2+43 && mouseY > height/2-10 && mouseY < height/2+10)
					{
						menu = 0;
						mouseWait();
					}
				}
		}
		
		// Update Window Size
		width = gc.getWidth();
		height = gc.getHeight();
	}
	
	/**
	 * Prevent Ghost Mouse Clicks
	 */
	private void mouseWait()
	{
		try
		{
			Thread.sleep(150);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getID()
	{
		return ID;
	}
	
}
