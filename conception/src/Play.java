

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Play extends BasicGameState
{
	private int ID;
	private int width;
	private int height;
	private Map currentMap;
	private boolean rightClick = false;
	private boolean leftClick = false;
	private int clickX;
	private int clickY;
	private int leftX;
	private int leftY;
	private int grid = 50;
	
 	public Play(int state)
	{
		ID = state;
	}
	
	// Initialize all values needed for the game
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		width = gc.getWidth();
		height = gc.getHeight();
		/**
		 * TODO write Map Making
		 */
	}
	
	// This method renders images, strings, shapes, etc.
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		if(!rightClick)
			g.clear();
		// Top Window
		g.drawRect(0, 0, width, 30);
		// Main Window
		g.drawRect(0, 30, width-200, height-230);
		// Right Window
		g.drawRect(width-200, 30, 200, height-30);
		// Bottom Window
		g.drawRect(0, height - 200, width-200, 200);
		// Display Map
		displayMap(g);
		// Display Right Click Menu
		if(rightClick)
		{
			int tempX = clickX;
			int tempY = clickY-30;
			tempX = clickX/grid - (width-200)/grid/2;
			tempY = (clickY-30)/grid - (height-230)/grid/2;
			String[] options = currentMap.rightClick(tempX, tempY);
			g.fillRect(clickX, clickY, 100, options.length*25 );
			g.setColor(Color.black);
			for(int i = 0; i<options.length; i++)
			{
				g.drawString(options[i], clickX+5, clickY+5+25*i);
			}
			g.setColor(Color.white);
			/**
			 * TODO Write Get rightClick in Map (to get Menu Options)
			 */
		}
		else if(leftClick)
		{
			// In Menu
			if(leftX > clickX &&  leftX < clickX+100)
				// Option 1
				if(leftY > clickY)
				{
					currentMap.option((leftY-clickY-5)/25);
				}
			leftClick = false;
		}
			
	}
	
	// This method updates the screen. It is how the animation works within the game.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = gc.getInput();
		width = gc.getWidth();
		height = gc.getHeight();
		if(input.isKeyPressed(input.KEY_UP))
			currentMap.move(0);
		if(input.isKeyPressed(input.KEY_DOWN))
			currentMap.move(1);
		if(input.isKeyPressed(input.KEY_LEFT))
			currentMap.move(2);
		if(input.isKeyPressed(input.KEY_RIGHT))
			currentMap.move(3);
		if(input.isKeyPressed(input.KEY_F1))
			currentMap = new Map(6);
		if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON))
		{
			clickX = input.getMouseX();
			clickY = input.getMouseY();
			rightClick = true;
		}
		else if(input.isMousePressed(input.MOUSE_LEFT_BUTTON))
		{
			leftX = input.getMouseX();
			leftY = input.getMouseY();
			rightClick = false;
			leftClick = true;
		}
	}
	
	public int getID()
	{
		return ID;
	}

	private void displayMap(Graphics g)
	{
		if(currentMap == null)
			return;
		// Temporary Minimum Grid Size
		int wide = width - 200;
		int high = height - 230;
		wide = wide/grid;
		high = high/grid;
		Acre[][] display = currentMap.getDisplayMap(wide, high);
		for(int i = 0; i<wide; i++)
		{
			for(int j = 0; j<high; j++)
			{
				g.drawRect(i*grid, 30+j*grid, grid, grid);
				g.drawString(display[i][j].toString(), i*grid+5, 30+j*grid);
			}
		}
	}
}
