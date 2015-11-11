package pong;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.Display;

public class Enemy 
{
	private float x;
	private float y;
	private int width;
	private int height;
	
	private float speed = 0.45f;
	
	
	
	public Enemy(float x, float y, int width, int height)
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
		Ball b = Main.ball;
		float midBall = (b.getY() + b.getY() + b.getHeight()) / 2;
		float midEnemy = (y + y + height) / 2;
		
		if(midBall > midEnemy)
		{
			moveY(speed);
		}
		if(midBall < midEnemy)
		{
			moveY(-speed);
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
	
	public int getWidth() {
		return width;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void moveY(float speed) {
		y += speed;
	}

	public int getHeight() {
		return height;
	}
}
