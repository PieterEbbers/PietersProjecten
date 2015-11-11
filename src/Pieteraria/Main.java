package Pieteraria;

import javax.swing.JFrame;

public class Main 
{
	public static JFrame frame;
	public static void main(String[] args)
	{
		frame = new JFrame("Gijs");		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new GamePanel());
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}
}
