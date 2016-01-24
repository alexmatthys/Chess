import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bishop extends Piece {
	
	public static final String img_file_white = "whitebishop.png";
	public static final String img_file_black = "blackbishop.png";
	private BufferedImage img;
	

	public Bishop(boolean white, int i, int j){
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
	
	public boolean isValidMove(int nextI, int nextJ, Piece p){
		if(nextI == i && nextJ == j){
			return false;
		} else if(nextI-nextJ != i-j && !(nextI == i-1 && nextJ == j+1) &&
				!(nextI == i-2 && nextJ == j+2) && !(nextI == i-3 && nextJ == j+3) &&
				!(nextI == i-4 && nextJ == j+4) && !(nextI == i-5 && nextJ == j+5) &&
				!(nextI == i-6 && nextJ == j+6) && !(nextI == i-7 && nextJ == j+7) &&
				!(nextI == i+1 && nextJ == j-1) && !(nextI == i+2 && nextJ == j-2) &&
				!(nextI == i+3 && nextJ == j-3) && !(nextI == i+4 && nextJ == j-4) &&
				!(nextI == i+5 && nextJ == j-5) && !(nextI == i+6 && nextJ == j-6) &&
				!(nextI == i+7 && nextJ == j-7)){
			return false;
		} else if(nextI-nextJ == i-j){
			if(nextJ > j){
				for(int k = 1; k < nextJ-j; k++){
					if(!(GameScreen.getPiece(i+k, j+k) instanceof EmptySpace)){
						return false;
					}
				}
			} else {
				for(int k = 1; k < j-nextJ; k++){
					if(!(GameScreen.getPiece(i-k, j-k) instanceof EmptySpace)){
						return false;
					}
				}
			}
		} else {
			if(nextJ > j){
				for(int k = 1; k < nextJ-j; k++){
					if(!(GameScreen.getPiece(i-k, j+k) instanceof EmptySpace)){
						return false;
					}
				}
			} else {
				for(int k = 1; k < j-nextJ; k++){
					if(!(GameScreen.getPiece(i+k, j-k) instanceof EmptySpace)){
						return false;
					}
				}
			}
		}
		
		
		
		return true;
	}
	
	

}
