package Pieteraria;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet
{

	private double x,y,width,height;
	private int rad;
	
	private double xSpeed, ySpeed;
	
	public Bullet(double x, double y, int r, int angle)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		rad = Math.toRadians(angle);
	  xSpeed = Math.cos(rad);
	  ySpeed = Math.sin(rad);
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(Color.MAGENTA);
		g.drawRect(x - r,y - r,width,height);
		g.setColor(Color.GREEN);
		g.fillRect(x - r, y -r, width, height);
	}
