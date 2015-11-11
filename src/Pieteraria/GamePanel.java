package Pieteraria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
@author Pieter
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private int worldWidth = 20, worldHeight = 30;
	private Thread thread;
	private boolean running;
	private Graphics2D g;

	private BufferedImage image;
	private int screenWidth = 680, screenHeight = 640;
	
	private  Block[][] block = new Block[worldWidth][worldHeight];
			
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(screenWidth ,screenHeight));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		super.addNotify();
		if(thread == null)
		{
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//INITIALIZE OBJECTS
		running = true;
		image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		for(int col = 0; col < block.length; col++)
		{
			for(int row = 0; row < block[0].length; row++)
			{
				block[col][row] = new Block(col * 10,row * 10, 10, 10);
			}
		}
		
		while(running)
		{
		//GAMELOOP
			//render
			drawScreen();
			render();
			update();
			//update
		}
	}

	private void update() 
	{
		// TODO Auto-generated method stub
		
	}

	private void render() 
	{
		//Achtergrond
		g.setColor(new Color(50,60,200));
		g.fillRect(0, 0, screenWidth, screenHeight);
		
		//Grid jwz
		for(int col = 0; col < block.length; col++)
		{
			for(int row = 0; row < block[0].length; row++)
			{
				block[col][row].render(g);
			}
		}
		
		
		//Objects
	}

	private void drawScreen() 
	{
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

}
