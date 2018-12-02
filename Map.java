	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
/*****************************************************************
Map class contains graphical representation of the walls, pellets, and entities within the game board

@author Team 7
@version Fall 2018
*****************************************************************/
public class Map extends Rectangle{
	
	/** The board represented by an array*/
	/** 0 = Wall, 1 = Pellet, 2 = Text tile, 3 = no pellet tile, 4 = PM Lives tile, 5 = Ghost House */
	public static int[][] board = {
			
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
			{0,1,1,1,1,1,1,0,0,5,0,0,1,1,1,1,1,1,0},
			{0,1,0,0,0,0,1,0,5,5,5,0,1,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,0,5,5,5,0,1,0,0,0,0,1,0},
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
	
    /*****************************************************************
    Default constructor creates map, may include input of what level
    *****************************************************************/
	public void Map() {
	
	}
	
    /*****************************************************************
    Returns current board
    @return int[][]
    *****************************************************************/
	public static int[][] getBoard() {
		return board; 
	}
	
   /*****************************************************************
    Changes the board value for a given position of the board
    @param w - y value of a position on the board
    @param h - x value of a position on the board
    @param type - value that the given value should be changed to
    @return none
    *****************************************************************/
	public static void changeBoardValue(int w, int h,int type){
		int[][] b = getBoard(); 
		b[h][w] = type;
	}
	
    /*****************************************************************
    Returns the positions of the walls within a map
    @param type - entity being searched for within the map (walls)
    @return ArrayList<Rectangle>
    *****************************************************************/
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
	
    /*****************************************************************
    Draws the values for the score and reports if the game is over
    @param g - graphics context for the panel
    @return none
    *****************************************************************/
	public void render(Graphics g) {
		int[][] board = getBoard();
		for(int i=0;(i*20)<(board[0].length*20);i++) {
			for(int j=0;(j*20)<(board.length*20);j++) {
				if(board[j][i] == 0) {
					drawCube(g,i,j);
				}else if(board[j][i] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect((8+i*20), (8+j*20), 4, 4);
				}else if(board[j][i] == 2 && Game.timer > 180*5) {
					if(i == 1 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,'s');
						}
					}else if(i == 2 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,'c');
						}
					}else if(i == 3 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,'o');
						}
					}else if(i == 4 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,'r');
						}
					}else if(i == 5 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,'e');
						}else {
							drawChar(g,i,'g');
						}
					}else if(i == 6 && j == 1) {
						if(Game.gameOver == false) {
							drawChar(g,i,':');
						}else{
							drawChar(g,i,'a');
						}
					}else if(i == 7 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,7,Game.playerScoreDigit(1,7));
						}else {
							drawChar(g,i,'m');
						}
					}else if(i == 8 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,8,Game.playerScoreDigit(1,6));
						}else {
							drawChar(g,i,'e');
						}
					}else if(i == 9 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,9,Game.playerScoreDigit(1,5));
						}
					}else if(i == 10 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,10,Game.playerScoreDigit(1,4));
						}else {
							drawNumber(g,i,0);
						}
					}else if(i == 11 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,11,Game.playerScoreDigit(1,3));
						}else {
							drawChar(g,i,'v');
						}
					}else if(i == 12 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,12,Game.playerScoreDigit(1,2));
						}else {
							drawChar(g,i,'e');
						}
					}else if(i == 13 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,13,Game.playerScoreDigit(1,1));
						}else {
							drawChar(g,i,'r');
						}
					}else if(i == 14 && j == 1) {
						if(Game.gameOver == false) {
							drawNumber(g,14,Game.playerScoreDigit(1,0));
						}
					}
				}else if(board[j][i] == 2 && Game.timer < 5*180){ 
					if(Game.timer < 180) {
						drawNumber(g,9,3);
					}else if(Game.timer > 180 && Game.timer < 180*2) {
						drawNumber(g,9,2);
					}else if(Game.timer > 2*180 && Game.timer < 180*3) {
						drawNumber(g,9,1);
					}else if(Game.timer > 3*180 && Game.timer < 180*4) {
						drawNumber(g,9,0);
					}else if(Game.timer > 4*180 && Game.timer < 180*5) {
						drawNumber(g,9,0);
						drawChar(g,8,'g');
						drawChar(g,10,'!');
					}
				}else if(board[j][i] == 3) {
					g.setColor(Color.BLACK);
					g.fillRect((i*20), (j*20), 20, 20);
				}
			}
		}
	}
	
	private void drawChar(Graphics g, int x, char c){
		if(c == 'v') {
			g.setColor(Color.YELLOW);	
			g.fillRect((8+x*20), (16+20), 3, 2);
			g.fillRect((6+x*20), (11+20), 2, 5);
			g.fillRect((11+x*20), (11+20), 2, 5);
			g.fillRect((5+x*20), (4+20), 2, 7);
			g.fillRect((12+x*20), (4+20), 2, 7);
		}else if(c == 'm') {
			g.setColor(Color.YELLOW);	
			/*V*/
			g.fillRect((8+x*20), (16+20), 3, 2);
			g.fillRect((6+x*20), (11+20), 2, 5);
			g.fillRect((11+x*20), (11+20), 2, 5);
			g.fillRect((5+x*20), (4+20), 2, 7);
			g.fillRect((12+x*20), (4+20), 2, 7);
			/**/
			g.fillRect((3+x*20), (4+20), 2, 12);
			g.fillRect((14+x*20), (4+20), 2, 12);
		}else {
			drawEight(g,x);
		}
		if(c == 'a') {
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (16+20), 14, 2);
			g.setColor(Color.YELLOW);
		}else if(c == 'c') {
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (6+20), 16, 8);
			g.setColor(Color.YELLOW);	
			g.fillRect((1+x*20), (9+20), 2, 2);
		}else if(c == 'e'){
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (4+20), 16, 12);
			g.setColor(Color.YELLOW);	
			g.fillRect((1+x*20), (9+20), 2, 2);
			g.fillRect((3+x*20), (9+20), 14, 2);	
		}else if(c=='g'){
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (9+20), 7, 2);
			g.fillRect((17+x*20), (4+20), 2, 5);
			g.setColor(Color.YELLOW);
			g.fillRect((1+x*20), (9+20), 2, 2);
		}else if(c == 'o') {
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (6+20), 14, 8);
			g.setColor(Color.YELLOW);	
			g.fillRect((1+x*20), (9+20), 2, 2);
			g.fillRect((17+x*20), (9+20), 2, 2);
		}else if(c == 's') {
			g.setColor(Color.BLACK);
			g.fillRect((3+x*20), (6+20), 16, 3);
			g.fillRect((1+x*20), (11+20), 16, 3);
		}else if(c =='r') {
			g.setColor(Color.BLACK);
			g.fillRect((17+x*20), (4+20), 2, 5);
			g.fillRect((3+x*20), (16+20), 14, 2);
			g.fillRect((15+x*20), (2+20), 2, 2);
			g.setColor(Color.YELLOW);
			g.fillRect((15+x*20), (4+20), 2, 5);
			g.fillRect((1+x*20), (9+20), 2, 2);
		}else if(c == ':') { //Makes :
			g.setColor(Color.BLACK);
			g.fillRect((x*20), (20), 20, 20);
			g.setColor(Color.YELLOW);
			g.fillRect((1+x*20), (5+20), 2, 2);
			g.fillRect((1+x*20), (13+20), 2, 2);
		}else if(c == '!') {
			g.setColor(Color.BLACK);
			g.fillRect((x*20), (20), 20, 20);
			g.setColor(Color.YELLOW);
			g.fillRect((9+x*20), (16+20), 2, 2);
			g.fillRect((9+x*20), (4+20), 2, 8);
		}
	}
	
	private void drawNumber(Graphics g, int x, int num) {
		drawEight(g,x);
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
	
	private void drawEight(Graphics g, int x) {
		/*Prints an 8, and gets modified to the char below*/
		g.setColor(Color.YELLOW);
		/*Bars*/
		g.fillRect((3+x*20), (2+20), 14, 2);
		g.fillRect((3+x*20), (9+20), 14, 2);
		g.fillRect((3+x*20), (16+20), 14, 2);
		/*Vertical Bars*/
		g.fillRect((1+x*20), (4+20), 2, 5);
		g.fillRect((17+x*20), (4+20), 2, 5);
		g.fillRect((1+x*20), (11+20), 2, 5);
		g.fillRect((17+x*20), (11+20), 2, 5);
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
	
	private void drawCube(Graphics g,int i, int j) {
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
	}

	/*Checks if its a pellot or a wall*/
	public int getType(int x, int y){
		int[][] board = getBoard();
		return board[x][y];
	}
}
