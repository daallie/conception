


import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Login extends BasicGameState
{
	private int ID;
	private int width;
	private int height;
	
	public Login(int state)
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
		g.drawString("Login", width-115, height-70);
		g.drawRect(width-125, height-75, 75, 25);
	}
	
	// This method updates the screen. It is how the animation works within the game.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = gc.getInput();
		if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON))
		{
			int mouseX = input.getMouseX();
			int mouseY = input.getMouseY();
			if(mouseX > width-125 && mouseX < width-125+75 && mouseY > height-75 && mouseY < height-75+25)
			{
				sbg.enterState(Game.MENU);
			}
		}
		
		// Update Window Size
		width = gc.getWidth();
		height = gc.getHeight();
	}
	
	public int getID()
	{
		return ID;
	}
	
}
