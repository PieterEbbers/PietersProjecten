package Pieteraria;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player
{

	private double x,y,width,height;
	
	public Player(double x, double y, int r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(Color.MAGENTA);
		g.drawRect(x - r,y - r,width,height);
		g.setColor(Color.GREEN);
		g.fillRect(x - r, y -r, width, height);
	}
	
	private void moveX(float speed)
	{
		x += speed;	
	}
	
	private void moveY(float speed)
	{
		y -= speed;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getR()
	{
		return r;
	}
}
