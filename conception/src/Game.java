/**
 * 
 * @author Daallie
 *
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame
{
	public static final String NAME = "Conception";
	public static AppGameContainer appgc;
	
	// State IDs
	public static final int LOGIN = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	
	//Constructor takes NAME as a param
	public Game(String NAME)
	{
		super(NAME);
		this.addState(new Login(LOGIN));
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
	}

	
	// Initialize all States
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(LOGIN).init(gc, this);
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		this.enterState(LOGIN);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Makes Game Window
		
		try{
			// Sets Up Window and Puts Tital at the top of screen
			appgc = new AppGameContainer(new Game(NAME));
			// This set the Size of the screen (x, y, fullscreen)
			appgc.setDisplayMode(800, 600, false);
			
			// Starts the Game
			appgc.start();
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
