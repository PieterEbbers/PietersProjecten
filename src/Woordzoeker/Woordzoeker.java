package Woordzoeker;


import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
// import java.util.Random;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

@SuppressWarnings("deprecation")
public class Woordzoeker {
	
	static String alphabet = "aaabbcddeeefghiijkkllmmnooppqrrsttuuvwxyz";
	static Random randomNumber;
	
	static String[][] grid = new String[17][20];
	
	static String check;
	static String checkReverse;
	static String currentWord;

	static String formedLine = "";
	
	static Color color = Color.white;

	static int wordLength;
	private static int maxWordLength = 10;

	static int hoverRow;
	static int hoverCol;
	
	static int clickedRow;
	static int clickedCol;
	
	static int wordCoordsTaken = 0;
	static int lineCoordsTaken = 0;
	static int correctWordsCoordsTaken = 0;
	
	static final String[] words = {
		"regen", "hagel", "sneeuw", "onweer", "bliksem", "winter", "zomer", "lente", "herfst"
		};
	
	static String[] formedWords = new String[words.length];
		
	static final Color[] colors = {
		Color.red, Color.yellow, Color.cyan, Color.blue, Color.orange, Color.pink, Color.magenta, Color.gray, Color.darkGray
	};
		

	private static int[][] formedLinesCoords = new int[words.length * maxWordLength][3];
	private static int[][] wordCoords = new int[words.length * maxWordLength][2];
	private static int[][] correctWordsCoords = new int[words.length * maxWordLength][2];
	
	private static TrueTypeFont font;

	static int wordsFormed = 1;
	static int correctWordsFormed = 0;
	static int clickedCol2;
	static int clickedRow2;

	static boolean hasClicked = false;
	static boolean hasClicked2 = false;

	static int coordWordsFormed = 1;


	static boolean checked = false;
	
	static int randomCol;
	static int randomRow;
	static Random r;
	static int randomDirection;

	static String curWord;
	
	public static void main(String[] argv) {
    	initDisplay();
    	initGL();
    	gameLoop();
    }

	private static void initDisplay() {
		try {
		    Display.setDisplayMode(new DisplayMode(600,600));
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		}
	}

	private static void initGL() {
		glEnable(GL_BLEND);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),Display.getHeight(), 0,-1,1);
		glMatrixMode(GL_MODELVIEW);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);	
		glDisable(GL_DEPTH_TEST);
		
	}

	private static void gameLoop() {
		
		Font awtFont = new Font("Comic Sans", Font.ITALIC, 17);
		font = new TrueTypeFont(awtFont, false);		
		
		generateGrid();
		generateWords();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			checkInput();
			
			if(wordsFormed > words.length && !checked)
			{
				checked  = true;
			}

			render();
			
			Display.update();
		}
	 
		Display.destroy();
	}

	private static void generateWords() {
		for(int i = 0; i < words.length; i++)
		{
			curWord = words[i];
			r = new Random();
			randomDirection = (int) (Math.random() * 3) + 1;
			
			//  1 = left-right
			if(randomDirection == 1)
			{
				randomCol = r.nextInt(grid.length);	
				randomRow = r.nextInt(grid[0].length - words[i].length());
				
				checkIsTaken(randomCol, randomRow);	
				
				for(int w = 0; w < words[i].length(); w++)
				{
					grid[randomCol][randomRow + w] = words[i].substring(w, w+1);
					
					wordCoords[wordCoordsTaken][0] = randomCol;
					wordCoords[wordCoordsTaken][1] = randomRow + w;
					
					wordCoordsTaken++;
				}
			}
			//  2 = right-left
			else if(randomDirection == 2)
			{
				randomCol = r.nextInt(grid.length);	
				randomRow = r.nextInt(grid[0].length - words[i].length()) + words[i].length();
				
				checkIsTaken(randomCol, randomRow);
				
				for(int w = 0; w < words[i].length(); w++)
				{
					grid[randomCol][randomRow - w] = words[i].substring(w, w+1);
					
					wordCoords[wordCoordsTaken][0] = randomCol;
					wordCoords[wordCoordsTaken][1] = randomRow - w;
					
					wordCoordsTaken++;				
				}
			}
			
			//  3 = up-down
			else if(randomDirection == 3)
			{
				randomCol = r.nextInt(grid.length - words[i].length());	
				randomRow = r.nextInt(grid[0].length);	

				checkIsTaken(randomCol, randomRow);
				
				for(int w = 0; w < words[i].length(); w++)
				{
					grid[randomCol + w][randomRow] = words[i].substring(w, w+1);
					
					wordCoords[wordCoordsTaken][0] = randomCol + w;
					wordCoords[wordCoordsTaken][1] = randomRow;
					
					wordCoordsTaken++;				
				}
			}
			
			//  4 = down-up
			else if(randomDirection == 4)
			{
				randomCol = r.nextInt(grid.length - words[i].length()) + words[i].length();	
				randomRow = r.nextInt(grid[0].length);	

				checkIsTaken(randomCol, randomRow);
				
				for(int w = 0; w < words[i].length(); w++)
				{
					grid[randomCol - w][randomRow] = words[i].substring(w, w+1);
					
					wordCoords[wordCoordsTaken][0] = randomCol - w;
					wordCoords[wordCoordsTaken][1] = randomRow;
					
					wordCoordsTaken++;					
				}
			}
			
			//  5 = topleft downright
			else if(randomDirection == 5)
			{
				
			}
			
			//  6 = downright topleft
			else if(randomDirection == 6)
			{
				
			}
			
			//  7 = topright downleft
			else if(randomDirection == 7)
			{
				
			}
			
			//  8 = downleft topright
			else if(randomDirection == 8)
			{
				
			}
		}
	}

	private static void checkIsTaken(int col, int row)
	{
		for(int i = 0; i < wordCoordsTaken; i++)
		{
			if(randomDirection == 1)
			{
				for(int w = 0; w < curWord.length(); w++)
				{
					if(wordCoords[i][0] == col && wordCoords[i][1] == row + w)
					{
						r = new Random();

						randomCol = r.nextInt(grid.length);	
						randomRow = r.nextInt(grid[0].length - curWord.length());
						checkIsTaken(randomCol, randomRow);
					}
				}
			}
			else if(randomDirection == 2)
			{
				
				for(int w = 0; w < curWord.length(); w++)
				{
					if(wordCoords[i][0] == col && wordCoords[i][1] == row - w)
					{		
						r = new Random();
						
						randomCol = r.nextInt(grid.length);	
						randomRow = r.nextInt(grid[0].length - curWord.length()) + curWord.length();
						checkIsTaken(randomCol, randomRow);
					}	
				}
			}
			else if(randomDirection == 3)
			{
				for(int w = 0; w < curWord.length(); w++)
				{
					if(wordCoords[i][0] == col + w && wordCoords[i][1] == row)
					{
						r = new Random();

						randomCol = r.nextInt(grid.length - curWord.length());	
						randomRow = r.nextInt(grid[0].length);	
						checkIsTaken(randomCol, randomRow);
					}
				}
			}
			else if(randomDirection == 4)
			{
				for(int w = 0; w < curWord.length(); w++)
				{
					if(wordCoords[i][0] == col - w && wordCoords[i][1] == row)
					{
						r = new Random();

						randomCol = r.nextInt(grid.length - curWord.length()) + curWord.length();	
						randomRow = r.nextInt(grid[0].length);
						checkIsTaken(randomCol, randomRow);
					}
				}	
			}			
		}
	}

	
	private static void generateGrid() {
		for(int col = 0; col < grid.length; col++)
		{
			for(int row = 0; row < grid[0].length; row++)
			{
				randomNumber = new Random();
				String r = String.valueOf(alphabet.charAt(randomNumber.nextInt(alphabet.length() - 1)));

				grid[col][row] =  r;
			}
		}
	}
	
	private static void render() {
		
		for(int col = 0; col < grid.length; col++)
		{
			for(int row = 0; row < grid[0].length; row++)
			{
				
				if(col == hoverCol && row == hoverRow)
				{
					color = Color.lightGray;
				}
				
				if(col == clickedCol && row == clickedRow)
				{
					color = Color.cyan;
				}
				
				for(int i = 0; i < lineCoordsTaken; i++)
				{
					if(formedLinesCoords[i][0] == col && formedLinesCoords[i][1] == row)
					{
						color = colors[formedLinesCoords[i][2] - 1];
					}
				}

				for(int i = 0; i < correctWordsCoordsTaken; i++)
					{
					if(correctWordsCoords[i][0] == col && correctWordsCoords[i][1] == row)
					{
						color = Color.green;
					}
				}
				
//				for(int i = 0; i < wordCoordsTaken; i++)
//				{
//					if(wordCoords[i][0] == col && wordCoords[i][1] == row)
//					{
//						color = Color.magenta;
//					}
//				}
				font.drawString(60 + (row*25), 20 + (col*25), grid[col][row], color);
				color = Color.white;
				
			}
		}	
		

		for(int i = 0; i < words.length; i++)
		{
			for(int j = 0; j < correctWordsFormed; j++)
			{
				String revWord = new StringBuffer(words[i]).reverse().toString();
				
				if(formedWords[j].equals(words[i]) || formedWords[j].equals(revWord))
				{
					color = Color.green;
				}
			}
				if(i < 3)
				{
					font.drawString((float) (60 + (Math.floor(i/3)) * 210), (Display.getHeight()- 125) + (i*34), i + 1 + ". " + words[i], color);
				}
				if(i < 6 && i >= 3)
				{
					font.drawString((float) (60 + (Math.floor(i/3)) * 210), (Display.getHeight()- 125) + ((i-3)*34), i + 1 + ". " + words[i], color);
				}
				if(i < 9 && i >= 6)
				{
					font.drawString((float) (60 + (Math.floor(i/3)) * 210), (Display.getHeight()- 125) + ((i-6)*34), i + 1 + ". " + words[i], color);		
				}
				
				color = Color.white;
		}
	}

	
	
	public static void checkInput()
	{
		for(int col = 0; col < grid.length; col++)
		{
			for(int row = 0; row < grid[0].length; row++)
			{
				int mouseY = -(Mouse.getY() - Display.getHeight());
				int mouseX = Mouse.getX();
				if((mouseY >= 20 + (col*25) && mouseY <= (20 + (col*25)) + font.getHeight(grid[col][row])) 
						&& (mouseX >= 60 + (row*25) && mouseX <= (60 + (row*25)) + font.getWidth(grid[col][row])))
				{
					hoverCol = col;
					hoverRow = row;			
					
					if(Mouse.isButtonDown(0) && wordsFormed <= words.length + 1)
					{
						clickedCol = col;
						clickedRow = row;
						hasClicked  = true;
					}
					
					if(hasClicked && Mouse.isButtonDown(1))
					{
						clickedCol2 = col;
						clickedRow2 = row;
						hasClicked2 = true;
					}
				}
			}
		}
		

		if(canFormLine(clickedCol, clickedRow, clickedCol2, clickedRow2) && hasClicked && hasClicked2)
		{
			currentWord = "";
			
			//////LEFT RIGHT
			if(formedLine.equals("left-right"))
			{

				if(clickedRow2 > clickedRow)
				{
					for(int rows = clickedRow; rows <= clickedRow2; rows++)
					{
						formedLinesCoords[lineCoordsTaken][1] = rows;
						formedLinesCoords[lineCoordsTaken][0] = clickedCol;
						formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
						
						currentWord += grid[clickedCol][rows];
						
						lineCoordsTaken++;
					}	
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int rows = clickedRow; rows <= clickedRow2; rows++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = rows;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						}
					}
					
					wordsFormed++;
				}
				
				else if(clickedRow2 < clickedRow)
				{
					for(int rows = clickedRow2; rows <= clickedRow; rows++)
					{
						formedLinesCoords[lineCoordsTaken][1] = rows;
						formedLinesCoords[lineCoordsTaken][0] = clickedCol;
						formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
						
						currentWord += grid[clickedCol][rows];
						
						lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int rows = clickedRow2; rows <= clickedRow; rows++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = rows;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						}
					}
					
					wordsFormed++;
				}
			}
			
			////UP DOWN
			if(formedLine.equals("up-down"))
			{

				if(clickedCol2 > clickedCol)
				{
					for(int cols = clickedCol; cols <= clickedCol2; cols++)
					{
						formedLinesCoords[lineCoordsTaken][1] = clickedRow;
						formedLinesCoords[lineCoordsTaken][0] = cols;
						formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
						
						currentWord += grid[cols][clickedRow];
						
						lineCoordsTaken++;
					}	
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int cols = clickedCol; cols <= clickedCol2; cols++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow;
								correctWordsCoords[correctWordsCoordsTaken][0] = cols;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						} 
					}

					wordsFormed++;
				}
				else if(clickedCol2 < clickedCol)
				{
					for(int cols = clickedCol2; cols <= clickedCol; cols++)
					{
						formedLinesCoords[lineCoordsTaken][1] = clickedRow;
						formedLinesCoords[lineCoordsTaken][0] = cols;
						formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
						
						currentWord += grid[cols][clickedRow];
						
						lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int cols = clickedCol2; cols <= clickedCol; cols++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow;
								correctWordsCoords[correctWordsCoordsTaken][0] = cols;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						}
					}
					
					wordsFormed++;
				}
			}
			
			///////////TOPLEFT TO BOTRIGHT
			if(formedLine.equals("diagonal1"))
			{
				if(clickedCol < clickedCol2)
				{
					for(int i = 0; i <= Math.abs(clickedCol - clickedCol2); i++)
					{
							formedLinesCoords[lineCoordsTaken][1] = clickedRow + i;
							formedLinesCoords[lineCoordsTaken][0] = clickedCol + i;
							formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
								
							currentWord += grid[clickedCol + i][clickedRow + i];
								
							lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int j = 0; j <= Math.abs(clickedCol - clickedCol2); j++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow + j;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol + j;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						} 
					}
					
					wordsFormed++;
				}
				else if(clickedCol > clickedCol2)
				{
					for(int i = 0; i <= Math.abs(clickedCol - clickedCol2); i++)
					{
							formedLinesCoords[lineCoordsTaken][1] = clickedRow2 + i;
							formedLinesCoords[lineCoordsTaken][0] = clickedCol2 + i;
							formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
								
							currentWord += grid[clickedCol2 + i][clickedRow2 + i];
								
							lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int j = 0; j <= Math.abs(clickedCol - clickedCol2); j++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow2 + j;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol2 + j;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						} 
					}
					
					wordsFormed++;
				}
			}
			
			/////////TOPRIGHT TO BOTLEFT
			if(formedLine.equals("diagonal2"))
			{
				if(clickedCol < clickedCol2)
				{
					for(int i = 0; i <= Math.abs(clickedCol - clickedCol2); i++)
					{
							formedLinesCoords[lineCoordsTaken][1] = clickedRow - i;
							formedLinesCoords[lineCoordsTaken][0] = clickedCol + i;
							formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
								
							currentWord += grid[clickedCol + i][clickedRow - i];
								
							lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int j = 0; j <= Math.abs(clickedCol - clickedCol2); j++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow - j;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol + j;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						} 
					}
					
					wordsFormed++;
				}
				else if(clickedCol > clickedCol2)
				{
					for(int i = 0; i <= Math.abs(clickedCol - clickedCol2); i++)
					{
							formedLinesCoords[lineCoordsTaken][1] = clickedRow2 - i;
							formedLinesCoords[lineCoordsTaken][0] = clickedCol2 + i;
							formedLinesCoords[lineCoordsTaken][2] = wordsFormed;
								
							currentWord += grid[clickedCol2 + i][clickedRow2 - i];
								
							lineCoordsTaken++;
					}
					
					for(int i = 0; i < words.length; i++)
					{
						String reverse = new StringBuffer(words[i]).reverse().toString();
						if(currentWord.equals(words[i]) || currentWord.equals(reverse))
						{
							for(int j = 0; j <= Math.abs(clickedCol - clickedCol2); j++)
							{
								correctWordsCoords[correctWordsCoordsTaken][1] = clickedRow2 - j;
								correctWordsCoords[correctWordsCoordsTaken][0] = clickedCol2 + j;
								
								correctWordsCoordsTaken++;
							}
							
							formedWords[correctWordsFormed] = currentWord;
							correctWordsFormed++;
						} 
					}
					
					wordsFormed++;
				}
			}
			
			hasClicked = false;
			hasClicked2 = false;
			
			
		}	
	}
	
	private static boolean canFormLine(int startCol, int startRow, int endCol, int endRow)
	{
//		for(int col = 0; col < grid.length; col++)
//		{
//			for(int row = 0; row < grid[0].length; row++)
//			{
				//Left-Right
				 
				if(startCol == endCol && startRow != endRow)
				{
					formedLine = "left-right";
					return true;
				}
				if(startCol != endCol && startRow == endRow)
				{
					formedLine = "up-down";
					return true;
				}
				for(int i = 0; i < Math.sqrt((grid.length * grid.length) + (grid[0].length  * grid[0].length)); i++)
				{
					if((endCol == startCol + i && endRow == startRow + i) || (endCol == startCol - i && endRow == startRow - i))
					{
						formedLine = "diagonal1";
						return true;
					}
					if((endCol == startCol + i && endRow == startRow - i) || (endCol == startCol - i && endRow == startRow + i))
					{
						formedLine = "diagonal2";
						return true;
					}
				}
				formedLine = "";
				return false; 
				
//			}
//		}
	}
}
