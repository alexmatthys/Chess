/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * GameScreen
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameScreen extends JPanel {
	
	public static Piece[][] grid = new Piece[8][8];
	private boolean whiteToMove = true;
	private boolean firstClick = false;
	private int firstClickI;
	private int firstClickJ;
	private int secondClickI;
	private int secondClickJ;
	private Piece firstPiece;


	
	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)


	// Game constants
	public static final int SCREEN_WIDTH = 400;
	public static final int SCREEN_HEIGHT = 400;

	

	public GameScreen(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);
		
		addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(e.getID() == MouseEvent.MOUSE_CLICKED){
		        	int i = e.getY() / 50;
		        	int j = e.getX() / 50;
		        	
		        	Piece p = grid[i][j];

		        	if(!firstClick && (whiteToMove != p.isWhite() 
		        			|| p instanceof EmptySpace)){
		        		// do nothing
		        	} else {
		        		if(!firstClick){
		        			firstClick = true;
		        			firstClickI = i;
		        			firstClickJ = j;
		        			firstPiece = p;
		        			System.out.println("first click success");
		        		} else {
		        			if(!(p instanceof EmptySpace) && 
		        					p.isWhite() == firstPiece.isWhite()){
		        				firstClickI = i;
		        				firstClickJ = j;
		        				firstPiece = p;
		        			} else if(firstPiece.isValidMove(i, j, p)){
		        				firstClick = false;
		        				secondClickI = i;
		        				secondClickJ = j;

		        				if(p instanceof EmptySpace){
		        					p.updateIJ(firstClickI, firstClickJ);
		        					firstPiece.updateIJ(secondClickI, secondClickJ);
		        					grid[firstClickI][firstClickJ] = p;
		        					grid[secondClickI][secondClickJ] = firstPiece;
		        				} else {
		        					firstPiece.updateIJ(secondClickI, secondClickJ);
		        					grid[firstClickI][firstClickJ] = 
		        							new EmptySpace(true, firstClickI, firstClickJ);
		        					grid[secondClickI][secondClickJ] = firstPiece;
		        				}
		        				whiteToMove = !whiteToMove;
		        				repaint();
		        				System.out.println("second click success");
		        			} else {
		        				//do nothing
		        			}
		        		}
		        	}
		        	
		        	
		        	
		        }
		    }
		});
		

		this.status = status;
		
		
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		playing = true;
		
		status.setText("White to move");
		
		whiteToMove = true;
		firstClick = false;
		
		for(int i = 0; i < 8; i++){
			grid[1][i] = new Pawn(false, 1, i);
		}
		
		for(int i = 0; i < 8; i++){
			grid[6][i] = new Pawn(true, 6, i);
		}
		
		for(int i = 2; i < 6; i++){
			for(int j = 0; j < 8; j++){
				grid[i][j] = new EmptySpace(true, i, j);
			}
		}
		
		grid[0][0] = new Rook(false, 0, 0);
		grid[0][1] = new Knight(false, 0, 1);
		grid[0][2] = new Bishop(false, 0, 2);
		grid[0][3] = new Queen(false, 0, 3);
		grid[0][4] = new King(false, 0, 4);
		grid[0][5] = new Bishop(false, 0, 5);
		grid[0][6] = new Knight(false, 0, 6);
		grid[0][7] = new Rook(false, 0, 7);
		
		grid[7][0] = new Rook(true, 7, 0);
		grid[7][1] = new Knight(true, 7, 1);
		grid[7][2] = new Bishop(true, 7, 2);
		grid[7][3] = new Queen(true, 7, 3);
		grid[7][4] = new King(true, 7, 4);
		grid[7][5] = new Bishop(true, 7, 5);
		grid[7][6] = new Knight(true, 7, 6);
		grid[7][7] = new Rook(true, 7, 7);
		
		repaint();
		
		
		
		
		
		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				grid[i][j].draw(g, i, j);
			}
		}
		
		if(whiteToMove){
			status.setText("White to move");
		} else {
			status.setText("Black to move");
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public static Piece getPiece(int i, int j){
		return grid[i][j];
	}
	
}
