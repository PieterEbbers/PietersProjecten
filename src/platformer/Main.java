package platformer;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main
{


	

	private static int WIDTH = 480;
	private static int HEIGHT = 640;
	
	private static int space = 2;
	private static int blockWidth = 10, blockHeight = 10;
	
	private static int gridWidth = WIDTH / (blockWidth);
	private static int gridHeight = HEIGHT / (blockHeight);
	static int grid[][] = new int[gridWidth][gridHeight];
	
	public static void main(String[] args)
	{
		try 
		{
 		   Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
 		   Display.create();
		} 
		catch (LWJGLException e) 
		{
 		   e.printStackTrace();
 		   System.exit(0);
		}
		
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0, 800, 600, 0, 1, -1);
	    glMatrixMode(GL_MODELVIEW);
		
		while (!Display.isCloseRequested()) 
		{
  			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  			render();
  			
  			Display.update();
		}
		
		Display.destroy();
	}
	
	public static void render() {
	   for(int row = 0; row < grid.length; row++)
	   {
		   for(int col = 0; col < grid[0].length; col++)
		   {
			   glPushMatrix();
			   glBegin(GL_QUADS);
			   Random r = new Random();
			   glColor3f(r.nextFloat(),r.nextFloat(), r.nextFloat());
			   glVertex2f(row * blockWidth + space, col * blockHeight + space);
			   glVertex2f(row * blockWidth + blockWidth, col * blockHeight + space);
			   glVertex2f(row * blockWidth + blockWidth, col * blockHeight + blockHeight);
			   glVertex2f(row * blockWidth + space, col * blockHeight + blockHeight);
			   glEnd();
			   glPopMatrix();
		   }
	   }
	
	}
}
