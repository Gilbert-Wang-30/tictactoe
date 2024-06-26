package tictactoe;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class game extends JPanel implements MouseListener{
	
	// Class Variables
	// gameState Legend:
	// 0  <- menu Screen
	// 1  Transition Screen
	// 2  In game Screen
	// 3  O wins the Game
	// 4  X wins the game
	// 5  tie
	// 6  Transition State for Ending Screen
	public static int gameState = 0;
	public static int mouseX;
	public static int mouseY;
	public static BufferedImage oWinScreen;
	public static BufferedImage xWinScreen;	
	public static BufferedImage tieScreen;
	public static int turn = 2;
	public static int numberOfTurns= 0;
	
	
	BufferedImage menuScreen;
	
	// Game States
	// grid Legend;
	// 0 <- No X nor O entries
	// 1 <- O entry
	// 2 <- X entry
	
	public static int[][] grid = new int[3][3];
	public static int row;
	public static int col;
	
	
	public game()
	{
		// JPanel Settings
		setPreferredSize(new Dimension(300, 300));
		setBackground(new Color(255,255,255));
		
		addMouseListener(this);
		
		// Image Importation
		try
		{
		menuScreen = ImageIO.read(new File("menu.jpg"));
		oWinScreen = ImageIO.read(new File("oWin.jpg"));
		xWinScreen = ImageIO.read(new File("xWin.jpg"));
		tieScreen = ImageIO.read(new File("tie.jpg"));
		}
		catch(Exception e){}
	}
	
	
	public static boolean winner(int n)
	{
		for(int i = 0; i<3; i++)
		{
				if(grid[i][0] == n&& grid[i][1] == n && grid[i][2] ==n)
					return true;
		}
		for(int i = 0; i<3; i++)
		{
				if(grid[0][i] == n&& grid[1][i] == n && grid[2][i] ==n)
					return true;
		}
		if(grid[0][0] == n&& grid[1][1] == n && grid[2][2] ==n)
		{
			return true;
		}
		if(grid[2][0] == n&& grid[1][1] == n && grid[0][2] ==n)
		{
			return true;
		}
		return false;
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		if(gameState == 0)
		{
			g.drawImage(menuScreen, 0, 0, null);
		}
		else if(gameState == 1)
		{
			super.paintComponent(g);
			
			g.setColor(new Color(0,0,0));
			g.drawLine(100, 0, 100, 300);
			g.drawLine(200, 0, 200, 300);
			// Draw two horizontal line
			g.drawLine(0, 100, 300, 100);
			g.drawLine(0, 200, 300, 200);
			
			gameState =2;
			
		}
		else if(gameState == 2)
		{
			if(grid[row][col]==0&&turn == 2)
			{
				g.drawLine(100*col+10, 100*row+10, 100*col+90, 100*row+90);
				g.drawLine(100*col+10, 100*row+90, 100*col+90, 100*row+10);
				
				grid[row][col] = turn;
				numberOfTurns++;
				
				if(winner(turn))
				{
					System.out.println("X won the game");
					gameState = 4;
				}
				else if(numberOfTurns == 9)
				{
					System.out.println("Tie Game!");
					gameState = 5;
				}
				
				turn = 1;
			}
			if(grid[row][col]==0&&turn == 1)
			{
				g.drawOval(100*col+10, 100*row+10, 80, 80);
				g.drawOval(100*col+10, 100*row+10, 80, 80);
			
				grid[row][col] = turn;
				numberOfTurns++;
				
				if(winner(turn))
				{
					System.out.println("O won the game");
					gameState = 3;
				}
				
				turn = 2;
			}
		}
		else if(gameState == 3)
		{
			super.paintComponent(g);
			g.drawImage(oWinScreen, 0, 0, null);
			gameState =6;
		}
		else if(gameState == 4)
		{
			super.paintComponent(g);
			g.drawImage(xWinScreen, 0, 0, null);
			gameState = 6;
		}
		else if(gameState == 5)
		{
			super.paintComponent(g);
			g.drawImage(tieScreen, 0, 0, null);
			gameState = 6;
		}
	}
	
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Tic Tac Toe");
		game pannel = new game();
		frame.add(pannel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void mousePressed(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(gameState ==0)
		{
			if(mouseX>= 100 && mouseX <= 200 && mouseY >= 250 && mouseY <= 280)
			{
				gameState = 1;
				paintComponent(this.getGraphics());
			}
		}
		else if(gameState == 2)
		{
			row = mouseY/100;
			col = mouseX/100;
			paintComponent(this.getGraphics());
			
		}
		else if(gameState == 3 || gameState ==4 || gameState ==5)
		{
			// TODO
			paintComponent(this.getGraphics());
		}
		else if(gameState ==6)
		{
			gameState =0;
			paintComponent(this.getGraphics());
			for(int i = 0; i <3; i++)
			{
				for(int j =0; j<3; j++)
				{
					grid[i][j]=0;
				}
			}
			turn =2;
			numberOfTurns = 0;
			
		}
	}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	

}

