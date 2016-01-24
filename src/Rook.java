import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rook extends Piece {

	public static final String img_file_white = "whiterook.png";
	public static final String img_file_black = "blackrook.png";
	private BufferedImage img;
	

	public Rook(boolean white, int i, int j){
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
		} else if(nextI != i && nextJ !=j){
			return false;
		} else if(nextI == i){
			if(nextJ > j){
				for(int k = j+1; k < nextJ; k++){
					if(!(GameScreen.getPiece(i, k) instanceof EmptySpace)){
						return false;
					}
				}
			} else {
				for(int k = j-1; k > nextJ; k--){
					if(!(GameScreen.getPiece(i, k) instanceof EmptySpace)){
						return false;
					}
				}
			}
		} else if(nextJ == j){
			if(nextI > i){
				for(int k = i+1; k < nextI; k++){
					if(!(GameScreen.getPiece(k, j) instanceof EmptySpace)){
						return false;
					}
				}
			} else {
				for(int k = i-1; k > nextI; k--){
					if(!(GameScreen.getPiece(k, j) instanceof EmptySpace)){
						return false;
					}
				}
			}
		}
		
		
		return true;
	}
	
}
