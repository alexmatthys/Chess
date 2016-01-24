import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EmptySpace extends Piece {

	public static final String img_file = "emptyspace.png";
	private BufferedImage img;
	

	public EmptySpace(boolean white, int i, int j){
		super(white, i, j);
		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}

	}
	
	@Override
	public void draw(Graphics g, int pos_x, int pos_y){
		g.drawImage(img,pos_y * 50, pos_x * 50, 50, 50, null);
	}
	
	public boolean isValidMove(int nextI, int nextJ){
		
		return true;
	}
	
}
