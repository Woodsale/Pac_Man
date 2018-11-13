	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Map extends Rectangle{
	public static int[][] board = {
			/**
			 * 0 = Wall
			 * 1 = pellot tile
			 * 2 = text tile
			 * 3 = no pellet tile
			 * 4 = PM Live tile
			 * */
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,4,4,4,0},
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
			{0,1,1,1,1,1,1,0,0,3,0,0,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,3,3,3,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,3,3,3,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,1,1,3,1,1,1,0,0,0,0,1,0},
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
	public void Map() {
	/*Might include input for what map level*/
	}
	
	public static int[][] getBoard() {
		return board; 
	}
	
	public static void changeBoardValue(int w, int h,int type){
		int[][] b = getBoard(); 
		b[h][w] = type;
	}
	
	public static ArrayList<Rectangle> getMap(int type) {
		int[][] board = getBoard();
		ArrayList<Rectangle> ghost = new ArrayList<Rectangle>();
		for(int i=0;(i*20)<(board[0].length*20);i++) {
			for(int j=0;(j*20)<(board.length*20);j++) {
				///loop through the map
				if (getBoard()[j][i]==type) {
					//if space is wall
					Rectangle r = new Rectangle();
					r.setBounds(i*20,j*20,20,20);
					ghost.add(r);
				}
			}
		}
		return ghost;
	}
	
	
	public void render(Graphics g) {
		int[][] board = getBoard();
		for(int i=0;(i*20)<(board[0].length*20);i++) {
			for(int j=0;(j*20)<(board.length*20);j++) {
				if(board[j][i] == 0) {
					Color blueOne = new Color(0,0,255); 
					Color blueTwo = new Color(0,100,164); 
					
					g.setColor(blueOne);
					g.fillRect((i*20), (j*20), 20, 20);
					
					g.setColor(blueTwo);
					g.fillRect((1+i*20), (1+j*20), 18, 18);
					
					g.setColor(blueOne);
					/*upper left corner*/
					g.fillRect((1+i*20), (1+j*20), 1, 1);
					g.fillRect((2+i*20), (2+j*20), 1, 1);
					g.fillRect((3+i*20), (3+j*20), 1, 1);
					/*Upper Right corner*/
					g.fillRect((18+i*20), (1+j*20), 1, 1);
					g.fillRect((17+i*20), (2+j*20), 1, 1);
					g.fillRect((16+i*20), (3+j*20), 1, 1);
					/*Lower Left*/
					g.fillRect((1+i*20), (18+j*20), 1, 1);
					g.fillRect((2+i*20), (17+j*20), 1, 1);
					g.fillRect((3+i*20), (16+j*20), 1, 1);
					/*Lower Right*/
					g.fillRect((18+i*20), (18+j*20), 1, 1);
					g.fillRect((17+i*20), (17+j*20), 1, 1);
					g.fillRect((16+i*20), (16+j*20), 1, 1);
					
					g.fillRect((4+i*20), (4+j*20), 12, 12);
					
					g.setColor(blueTwo);
					g.fillRect((5+i*20), (5+j*20), 10, 10);
				}else if(board[j][i] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect((8+i*20), (8+j*20), 4, 4);
				}else if(board[j][i] == 2) {
					/*Prints an 8, and gets modified to the char below*/
					g.setColor(Color.YELLOW);
					/*Bars*/
					g.fillRect((3+i*20), (2+j*20), 14, 2);
					g.fillRect((3+i*20), (9+j*20), 14, 2);
					g.fillRect((3+i*20), (16+j*20), 14, 2);
					/*Vertical Bars*/
					g.fillRect((1+i*20), (4+j*20), 2, 5);
					g.fillRect((17+i*20), (4+j*20), 2, 5);
					g.fillRect((1+i*20), (11+j*20), 2, 5);
					g.fillRect((17+i*20), (11+j*20), 2, 5);
					
					if(i == 1 && j == 1) {
						/*Makes an S*/
						g.setColor(Color.BLACK);
						g.fillRect((3+i*20), (6+j*20), 16, 3);
						g.fillRect((1+i*20), (11+j*20), 16, 3);
					}
					else if(i == 2 && j == 1) {
						/*Makes a C*/
						g.setColor(Color.BLACK);
						g.fillRect((3+i*20), (6+j*20), 16, 8);
						g.setColor(Color.YELLOW);	
						g.fillRect((1+i*20), (9+j*20), 2, 2);
					}
					else if(i == 3 && j == 1) {
						/*Makes an O*/
						g.setColor(Color.BLACK);
						g.fillRect((3+i*20), (6+j*20), 14, 8);
						g.setColor(Color.YELLOW);	
						g.fillRect((1+i*20), (9+j*20), 2, 2);
						g.fillRect((17+i*20), (9+j*20), 2, 2);
					}
					else if(i == 4 && j == 1) {
						/*Makes an R*/
						g.setColor(Color.BLACK);
						g.fillRect((17+i*20), (4+j*20), 2, 5);
						g.fillRect((3+i*20), (16+j*20), 14, 2);
						g.fillRect((15+i*20), (2+j*20), 2, 2);
						g.setColor(Color.YELLOW);
						g.fillRect((15+i*20), (4+j*20), 2, 5);
						g.fillRect((1+i*20), (9+j*20), 2, 2);
					}
					else if(i == 5 && j == 1) {
						/*Makes an E*/
						g.setColor(Color.BLACK);
						g.fillRect((3+i*20), (4+j*20), 16, 12);
						g.setColor(Color.YELLOW);	
						g.fillRect((1+i*20), (9+j*20), 2, 2);
						g.fillRect((3+i*20), (9+j*20), 14, 2);
					}
					else if(i == 6 && j == 1) {
						/*Makes :*/
						g.setColor(Color.BLACK);
						g.fillRect((i*20), (j*20), 20, 20);
						g.setColor(Color.YELLOW);
						g.fillRect((1+i*20), (5+j*20), 2, 2);
						g.fillRect((1+i*20), (13+j*20), 2, 2);
					}else if(i == 7 && j == 1) {
						drawNumber(g,7,Game.playerScoreDigit(1,7));
					}else if(i == 8 && j == 1) {
						drawNumber(g,8,Game.playerScoreDigit(1,6));
					}else if(i == 9 && j == 1) {
						drawNumber(g,9,Game.playerScoreDigit(1,5));
					}else if(i == 10 && j == 1) {
						drawNumber(g,10,Game.playerScoreDigit(1,4));
					}else if(i == 11 && j == 1) {
						drawNumber(g,11,Game.playerScoreDigit(1,3));
					}else if(i == 12 && j == 1) {
						drawNumber(g,12,Game.playerScoreDigit(1,2));
					}else if(i == 13 && j == 1) {
						drawNumber(g,13,Game.playerScoreDigit(1,1));
					}else if(i == 14 && j == 1) {
						drawNumber(g,14,Game.playerScoreDigit(1,0));
					}
				}else if(board[j][i] == 3) {
					//drawBLANK(g,j,i);
					g.setColor(Color.BLACK);
					g.fillRect((i*20), (j*20), 20, 20);
				}
			}
		}
	}
	
	/*//Doesn't Work, removes blocks unitentually
	 * private void drawBLANK(Graphics g, int j, int i) {
		g.setColor(Color.BLACK);
		g.fillRect((j*20), (i*20), 20, 20);
	}*/
	
	private void drawNumber(Graphics g, int x, int num) {
		if(num == 0) {drawZero(g,x);}
		else if(num == 1) {drawOne(g,x);}
		else if(num == 2) {drawTwo(g,x);}
		else if(num == 3) {drawThree(g,x);}
		else if(num == 4) {drawFour(g,x);}
		else if(num == 5) {drawFive(g,x);}
		else if(num == 6) {drawSix(g,x);}
		else if(num == 7) {drawSeven(g,x);}
		else if(num == 9) {drawNine(g,x);}
	}
	
	private void drawZero(Graphics g,int x) {
		g.setColor(Color.BLACK);
		g.fillRect((3+x*20), (6+20), 14, 8);
		g.setColor(Color.YELLOW);	
		g.fillRect((1+x*20), (9+20), 2, 2);
		g.fillRect((17+x*20), (9+20), 2, 2);
	}
	
	private void drawOne(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((3+x*20), (20), 20, 20);
		g.setColor(Color.YELLOW);	
		g.fillRect((1+x*20), (9+20), 2, 2);
	}
	
	private void drawTwo(Graphics g,int x) {
		g.setColor(Color.BLACK);
		g.fillRect((1+x*20), (4+20), 2, 5);
		g.fillRect((17+x*20), (11+20), 2, 5);
	}
	
	private void drawThree(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((1+x*20), (4+20), 2, 5);
		g.fillRect((1+x*20), (11+20), 2, 5);
	}
	
	private void drawFour(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((1+x*20), (11+20), 2, 5);
		g.fillRect((3+x*20), (2+20), 14, 2);
		g.fillRect((3+x*20), (16+20), 14, 2);
	} 
	
	private void drawFive(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((17+x*20), (4+20), 2, 5);
		g.fillRect((1+x*20), (11+20), 2, 5);
	}
	
	private void drawSix(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((17+x*20), (4+20), 2, 5);
	}
	
	private void drawSeven(Graphics g, int x) {
		g.setColor(Color.BLACK);
		/*Bars*/
		g.fillRect((3+x*20), (9+20), 14, 2);
		g.fillRect((3+x*20), (16+20), 14, 2);
		/*Vertical Bars*/
		g.fillRect((1+x*20), (4+20), 2, 5);
		g.fillRect((1+x*20), (11+20), 2, 5);
	}
	
	private void drawNine(Graphics g, int x) {
		g.setColor(Color.BLACK);
		g.fillRect((1+x*20), (11+20), 2, 5);
	}
	

	/*Checks if its a pellot or a wall*/
	public int getType(int x, int y){
		int[][] board = getBoard();
		return board[x][y];
	}
}