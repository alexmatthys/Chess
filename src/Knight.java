import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Knight extends Piece {

	public static final String img_file_white = "whiteknight.png";
	public static final String img_file_black = "blackknight.png";
	private BufferedImage img;


	public Knight(boolean white, int i, int j){
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
		if(nextI == i-2 && nextJ == j+1){
			return true;
		} else if(nextI == i-1 && nextJ == j+2){
			return true;
		} else if(nextI == i+1 && nextJ == j+2){
			return true;
		} else if(nextI == i+2 && nextJ == j+1){
			return true;
		} else if(nextI == i+2 && nextJ == j-1){
			return true;
		} else if(nextI == i+1 && nextJ == j-2){
			return true;
		} else if(nextI == i-1 && nextJ == j-2){
			return true;
		} else if(nextI == i-2 && nextJ == j-1){
			return true;
		}
		
		return false;
	}
	
}
