	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Map extends Rectangle{
	public void Map() {
	/*Might include input for what map level*/
	}
	
	private type tile;
	
	public static enum type{WALL,PELLOT};
	
	private int[][] getBoard() {
		int[][] board = {
				/**
				 * 0 = Wall
				 * 1 = pellot tile
				 * 2 = no pellot tile
				 * */
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0},
				{0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0},
				{0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0},
				{0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0},
				{0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0},
				{0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0},
				{0,1,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,1,0},
				{0,1,1,1,1,1,1,0,0,2,0,0,1,1,1,1,1,1,0},
				{0,1,0,0,0,0,1,0,2,2,2,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,0,2,2,2,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0},
				{0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,0},
				{0,1,0,1,0,0,1,1,1,0,1,1,1,0,0,1,0,1,0},
				{0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0},
				{0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0},
				{0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0},
				{0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
				{0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0},
				{0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0},
				{0,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,0},
				{0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		return board; 
	}
	
	/*public int[][] getBoadWalls(int x){
		int[][] board = getBoard();
		int[][] b = new int [board.length][board[0].length];
		for(int i = 0;i<board.length;i++) {
			for(int j= 0;j<board[0].length;j++) {
				if(x == 0) {
					board[i][j] = 0;
				}
			}
		}
		return board;
	}*/
	
	public void render(Graphics g) {
		int[][] board = getBoard();
		for(int i=0;(i*20)<(board[0].length*20);i++) {
			for(int j=0;(j*20)<(board.length*20);j++) {
				if(board[j][i] == 0) {
					g.setColor(Color.BLUE);
					g.fillRect((i*20), (j*20), 20, 20);
				}else if(board[j][i] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect((8+i*20), (8+j*20), 4, 4);
				}
			}
		}
		int tpMove = board[0].length*20;
		/*if(Game.twoPlayer == true) {
			for(int m=0;(m*20)<(board[0].length*20);m++) {
				for(int n=0;(n*20)<(board.length*20);n++) {
					if(board[n][m] == 0) {
						g.setColor(Color.BLUE);
						g.fillRect((tpMove+m*20), (n*20), 20, 20);
					}else if(board[n][m] == 1) {
						g.setColor(Color.WHITE);
						g.fillRect((tpMove + 8+m*20), (8+n*20), 4, 4);
					}
				}
			}
		}*/
	}
	/*Checks if its a pellot or a wall*/
	public int getType(int x, int y){
		int[][] board = getBoard();
		return board[x][y];
	}
}