package pong;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main 
{
	public static Player player;
	public static Ball ball;
	public static Enemy enemy;
	
	private static int width = 20;
	private static int height = 75;
	private static int playerGoal = 0;
	private static int enemyGoal = 0;
	
    public static void main(String[] args)
    {
    	  try {
    		    Display.setDisplayMode(new DisplayMode(800,600));
    		    Display.create();
    		} catch (LWJGLException e) 
    	  {
    		    e.printStackTrace();
    		    System.exit(0);
    		}
    	 
    	  	glMatrixMode(GL_PROJECTION);

  			glLoadIdentity();
  			glOrtho(0,800,600,0,-1,1);
  			
  			glMatrixMode(GL_MODELVIEW);
    	 
  			player = new Player(10,10, width, height);
  			ball = new Ball(Display.getWidth()/2, Display.getHeight()/2, 10, 10);
  			enemy = new Enemy(Display.getWidth()-10-width, 10, width, height);
    		while (!Display.isCloseRequested()) 
    		{
    			//Game$Loop :D:D:D:
      			glClear(GL_COLOR_BUFFER_BIT);
    			
      			player.render();
      			enemy.update();
      			ball.render();
      			player.update();
      			enemy.render();
      			ball.update();
      			
      			if(ball.getX()<player.getX())
      			{
      				enemyGoal++;
      				ball = new Ball(Display.getWidth()/2, Display.getHeight()/2, 10, 10);
      			}
      			if(ball.getX() + ball.getWidth() > enemy.getX() + enemy.getWidth())
      			{
      				playerGoal++;
      				ball = new Ball(Display.getWidth()/2, Display.getHeight()/2, 10, 10);
      			}
      			Display.setTitle(playerGoal + "    -    " + enemyGoal);
      			Display.update();
    		}
    	 
    		Display.destroy();
    }
}