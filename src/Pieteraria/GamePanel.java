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
	private Thread thread;
	
	private boolean running;
	private boolean left, right, up, down;
	private boolean canFire = true, isFiring = false;
	
	private long timer, fireTimer = 0;
	
	private Graphics2D g;

	private BufferedImage image;
	private int screenWidth = 680, screenHeight = 640;
	
	private float playerSpeed = 1.3f;
	
	private  Player p;
			
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
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//INITIALIZE OBJECTS
		running = true;
		image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
	
		p = new Player(screenWidth / 2, screenHeight / 6 * 5, 7);
	
		while(running)
		{
			drawScreen();
			render();
			update();
		}
	}

	private void update() 
	{
		// TODO Auto-generated method stub
		timer = System.currentTimeMillis();
		movement();
		p.update();
	}

	private void render() 
	{
		//Achtergrond
		g.setColor(new Color(50,60,200));
		g.fillRect(0, 0, screenWidth, screenHeight);
		
		p.render(g);
	}

	private void drawScreen() 
	{
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	private void movement()
	{
		if(left)
		{
			moveX(-playerSpeed);
		}
		if(right)
		{
			moveX(playerSpeed);
		}
		if(up)
		{
			moveY(playerSpeed)
		}
		if(down)
		{
			moveY(-playerSpeed)
		}
		if(isFiring && !canFire && fireTimer == 0)
		{
			fireTimer = System.currentTimeMillis();
		}
		int reloadTime = timer - fireTimer;
		if(reloadTime >= 400 && isFiring && !canFire && fireTimer != 0)
		{
			canFire = true;
		}
		if(isFiring && canFire)
		{
			//add bullet
			
			fireTimer = 0;
		}
	}

	
	@Override public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;	
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			isFiring = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = false;	
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			isFiring = false;
		}
	}
}
