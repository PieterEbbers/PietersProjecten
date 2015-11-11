package pong;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Player 

{
	private float x;
	private float y;
	private int width;
	private int height;
	private float speed = 0.85f;
	
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isMovingUp;
	private boolean isMovingDown;
	
	public Player(float x, float y, int width, int height) 
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
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
		while (Keyboard.next()) 
		{
	        if (Keyboard.getEventKeyState()) 
	        {
	            if (Keyboard.getEventKey() == Keyboard.KEY_A) 
	            {
	            	//isMovingLeft = true;
	            }
	            if (Keyboard.getEventKey() == Keyboard.KEY_S) 
	            {
	            	isMovingDown = true;
	            }
	            if (Keyboard.getEventKey() == Keyboard.KEY_D) 
	            {
	            	//isMovingRight = true;
	            }
	            if(Keyboard.getEventKey() == Keyboard.KEY_W)
	            {
	            	isMovingUp = true;
	            }
	        } else {
	        	if (Keyboard.getEventKey() == Keyboard.KEY_A) 
	            {
	            	isMovingLeft = false;
	            }
	            if (Keyboard.getEventKey() == Keyboard.KEY_S) 
	            {
	            	isMovingDown = false;
	            }
	            if (Keyboard.getEventKey() == Keyboard.KEY_D) 
	            {
	            	isMovingRight = false;
	            }
	            if(Keyboard.getEventKey() == Keyboard.KEY_W)
	            {
	            	isMovingUp = false;
	            }

	        }
	    }
		
		if(isMovingLeft)
		{
			moveX(-speed);
		}
		if(isMovingDown)
		{
			moveY(speed);
		}
		if(isMovingUp)
		{
			moveY(-speed);
		}
		if(isMovingRight)
		{
			moveX(speed);
		}
		
		if(y<0)
		{
			y=0;
		}
		if(y+height > Display.getHeight())
		{
			y = Display.getHeight() - height;
		}
			
	}
	
	public float getX() 
	{
		return x;
	}

	public void moveX(float speed)
	{
		x+=speed;
	}

	public float getY() {
		return y;
	}

	public void moveY(float speed)
	{
		y+=speed;
	}

	public int getWidth() 
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}
}
