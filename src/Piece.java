import java.awt.Graphics;

public class Piece {

	private boolean isWhite;
	public int i;
	public int j;
	
	public Piece(boolean white, int i, int j){
		this.isWhite = white;
		this.i = i;
		this.j = j;
	}
	
	public void draw(Graphics g, int pos_x, int pos_y) {
		
	}
	
	public boolean isWhite(){
		return isWhite;
	}
	
	public void updateIJ(int i, int j){
		this.i = i;
		this.j = j;
	}
	
	public boolean isValidMove(int nextI, int nextJ, Piece p){
		
		return true;
	}
	
}
