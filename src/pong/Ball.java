package pong;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.Display;

public class Ball 
{
	private float x;
	private float y;
	private int width;
	private int height;
	
	private static float acceleration = 0.018f;
	
	public static float xSpeed;
	public static float ySpeed;
	
	public final float xSpeedMax = 1;
	public final float ySpeedMax = 1;
	

	private Player p = Main.player;
	
	
	public Ball(float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		xSpeed = 0.3f;
		ySpeed = 0f;
	}
	
	public void render()
	{
		glPushMatrix();
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
		glPopMatrix();
	}
	
	public void update()
	{
		x += xSpeed;
		y += ySpeed;
		
		/////////////////////////////////////
		
		if(x+width > Display.getWidth())
		{
			x = Display.getWidth() - width;
			xSpeed = -xSpeed;
			xSpeed -= acceleration;
		}
		if(x < 0)
		{
			x = 0;
			xSpeed = -xSpeed;
			xSpeed += acceleration;
		}
		
		if(y < 0)
		{
			y = 0;
			ySpeed = -ySpeed;
			ySpeed += acceleration;
		}
		if(y + height > Display.getHeight())
		{
			y = Display.getHeight() - height;
			ySpeed = -ySpeed; 
			ySpeed -= acceleration;
		}
		
		/////////////////////////////////////////
		if(y < p.getY() + p.getHeight() && y + height > p.getY())
		{
			if(x < p.getX() + p.getWidth())
			{
				xSpeed = -xSpeed;
				xSpeed += acceleration;
				
				float midPlayer = (p.getY() + p.getY() + p.getHeight()) / 2;
				float midBall = (y + y + height) / 2;
				float distance = midBall - midPlayer;
				
				ySpeed += distance * 0.005;
			}
		}
		////////////////////////////////////////
		if(y < Main.enemy.getY() + Main.enemy.getHeight() && y + height > Main.enemy.getY())
		{
			if(x+width > Main.enemy.getX())
			{
				xSpeed = -xSpeed;
				xSpeed -= acceleration;
				
				float midEnemy = (Main.enemy.getY() + Main.enemy.getY() + Main.enemy.getHeight()) / 2;
				float midBall = (y + y + height) / 2;
				float distance = midBall - midEnemy;
				
				ySpeed += distance * 0.005;
			}
		}		
		////////////////////////////////////////
		
		if(xSpeed < 0 && xSpeed < -xSpeedMax)
		{
			xSpeed = -xSpeedMax;
		}
		
		if(xSpeed > 0 && xSpeed > xSpeedMax)
		{
			xSpeed = xSpeedMax;
		}
		if(ySpeed < 0 && ySpeed < -ySpeedMax)
		{
			ySpeed = -ySpeedMax;
		}
		
		if(ySpeed > 0 && ySpeed > ySpeedMax)
		{
			ySpeed = ySpeedMax;
		}
		
		////////////////////////////////////////////
	}
	
	public float getX() 
	{
		return x;
	}

	public float getY() 
	{
		return y;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
	}

}
