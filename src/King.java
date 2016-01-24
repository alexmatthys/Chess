import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece {
	
	public static final String img_file_white = "whiteking.png";
	public static final String img_file_black = "blackking.png";
	private BufferedImage img;


	public King(boolean white, int i, int j){
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
		if(nextI >= i+2 || nextI <= i-2 || nextJ >= j+2 || nextJ <= j-2 
				|| (nextI == i && nextJ == j)){
			return false;
		}
		
		return true;
	}
	

	
	
	
}
