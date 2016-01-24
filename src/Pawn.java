import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pawn extends Piece {
	
	public static final String img_file_white = "whitepawn.png";
	public static final String img_file_black = "blackpawn.png";
	private BufferedImage img;


	public Pawn(boolean white, int i, int j){
		super(white, i, j);
		if(white){
			try {
				if (img == null) {
					img = ImageIO.read(new File(img_file_white));
				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		} else {
			try {
				if (img == null) {
					img = ImageIO.read(new File(img_file_black));
				}
			} catch (IOException e) {
				System.out.println("Internal Error:" + e.getMessage());
			}
		}

	}
	
	@Override
	public void draw(Graphics g, int pos_x, int pos_y){
		g.drawImage(img,pos_y * 50, pos_x * 50, 50, 50, null);
	}
	
	@Override
	public boolean isValidMove(int nextI, int nextJ, Piece p){
		if(!isWhite()){
			if(i == 1 && nextI-i == 2 && j == nextJ && 
					GameScreen.getPiece(i+1, j) instanceof EmptySpace && p instanceof EmptySpace){
				return true;
			} else if(!(p instanceof EmptySpace) && p.isWhite() && 
					nextI-i == 1 && (Math.abs(nextJ-j) == 1)){
				return true;
			} else if(nextJ != j || nextI-i > 1){
				return false;
			} else if(!(p instanceof EmptySpace) && p.isWhite() &&
					nextI-i == 1 && nextJ == j){
				return false;
			} else if(nextI-i < 1){
				return false;
			} else {
				return true;
			}
		} else {
			if(i == 6 && i-nextI == 2 && j == nextJ && 
					GameScreen.getPiece(i-1, j) instanceof EmptySpace && p instanceof EmptySpace){
				return true;
			} else if(!(p instanceof EmptySpace) && !p.isWhite() && 
					i-nextI == 1 && (Math.abs(nextJ-j) == 1)){
				return true;
			} else if(nextJ != j || i-nextI > 1){
				return false;
			} else if(!(p instanceof EmptySpace) && !p.isWhite() &&
					i-nextI == 1 && nextJ == j){
				return false;
			} else if(i-nextI < 1){
				return false;
			} else {
				return true;
			}
		}

	}
	
	
}
