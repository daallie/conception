

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
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
		currentMap = new Map(8);
	}
	
	// This method renders images, strings, shapes, etc.
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
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
	}
	
	public int getID()
	{
		return ID;
	}

	private void displayMap(Graphics g)
	{
		// Temporary Minimum Grid Size
		int GRID = 50;
		int wide = width - 200;
		int high = height - 230;
		wide = wide/GRID;
		high = high/GRID;
		Acre[][] display = currentMap.getDisplayMap(wide, high);
		for(int i = 0; i<wide; i++)
		{
			for(int j = 0; j<high; j++)
			{
				g.drawRect(i*GRID, 30+j*GRID, GRID, GRID);
				g.drawString(display[i][j].toString(), i*GRID+5, 30+j*GRID);
			}
		}
	}
}
