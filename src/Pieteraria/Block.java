package Pieteraria;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block
{

	private int x,y,width,height;
	
	public Block(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(Color.MAGENTA);
		g.drawRect(x,y,width,height);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
