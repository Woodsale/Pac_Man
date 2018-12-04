import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.*;

/*****************************************************************
Game class contains all of the logic and entity (Pacman/Ghost) 
information needed to play the Pacman game 
@author Team 7
@version Fall 2018
*****************************************************************/
public class Game extends Canvas implements Runnable,KeyListener{
	/** Indicates whether the game is playing */
	private boolean isRunning = false;
	/** Final dimensions for the game board (in pixels) */
	public static final int WIDTH = 380, HEIGHT = 640;
	/** Title of the game */
	public static final String TITLE = "Pac Man";  
	/** Game Thread */
	private Thread thread;
	
	/**Player Object*/
	public static Player player;
	/**Player Object*/
	public static Player playerTwo;
	/**Player Object*/
	public static Player one;
	/**Player Object*/
	public static Player two;
	/**Ghost Object*/
	public static Ghost blinky;
	/**Ghost Object*/
	public static Ghost pinky;
	/**Ghost Object*/
	public static Ghost inky;
	/**Ghost Object*/
	public static Ghost clyde;
	/**Map Object*/
	public static Map map;
	
	/**Timer for timing everything*/
	public static int timer = 0;
	
	/**Used in a player death*/
	public static int invTime = 3;
	/**used in calc inv time*/
	public static int p1InvTimer = 0;
	/**used for player death*/
	public static boolean p1inv = false;
	/**lives remaining*/
	public static int p1LivesRemaining = 3;
	/**next life*/
	public static int p1NextLife = 1;
	/**is the game over*/
	public static boolean gameOver = false;
	/**is the game paused*/
	public static boolean isPaused = false;
	
	/**Difficulty, used for ghost speed*/
	public static int difficulty = 1;
	/**Level, used for ghost speed*/
	public static int level = 1;
	/**player 1 score*/
	public static int playerOneScore = 0;
	/**player 1 score*/
	public static int playerTwoScore = 0;
	
	/*For possible future updates*/
	public static boolean twoPlayer = false;
	
	/*****************************************************************
    Constructor starts a new game, intializes players, ghosts, and draws the game board
    *****************************************************************/
	public Game() {
		Sound.INTRO.play();
		Dimension dimension = new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		addKeyListener(this);
		player = new Player(9*20,19*20);//input is location
		playerTwo = new Player(19*20,19*20);// input is location
		one = new Player(16*20,1*20);
		two = new Player(17*20,1*20);
		blinky = new Ghost(10*20,16*20,1);
		pinky = new Ghost(10*20,17*20,2);
		inky = new Ghost(9*20,17*20,3);
		clyde = new Ghost(9*20,16*20,4);
		map = new Map(); 
	}
	
	/*****************************************************************
    Retuns the either the x or the y location of the requested entity
    @param p - entity (player or ghost)
    @param v - x or y location of the entity
    @return int
    *****************************************************************/
	public static int playerLoc(int p, int v) {
		/*0 for x and 1 for y*/
		if(p == 1) {
			if(v == 0) {
				return ((int)player.getX());
			}else if(v == 1){
				return ((int)player.getY());
			}
		}else if(p == 2) {
			if(v == 0) {
				return ((int)playerTwo.getX());
			}else if(v == 1){
				return ((int)playerTwo.getY());
			}
		}else if(p == 3) {
			if(v == 0) {
				return ((int)blinky.getX());
			}else if(v == 1){
				return ((int)blinky.getY());
			}
		}
		
		return 0;
	}
	
	public void pause() {
		if(isPaused) {
			isPaused = false;
		}
		else {
			isPaused = true;
		}
	}
	
	/*****************************************************************
    Sets the location of the Player based whether or not they've lost a life
    @param lost - entity (player or ghost)
    @param pm - Player 1 or Player 2
    @return none
    *****************************************************************/
	public static void lifeCounter(boolean lost, int pm) {
		if(lost == true && pm == 1) {
			one.setLocation(-60,-60);
		}else if(lost == true && pm == 2) {
			two.setLocation(-80,-80);
		}else if(lost == false && pm == 1){
			one.setLocation(16*20,1*20);
		}else if(lost == false && pm == 2) {
			two.setLocation(17*20,1*20);
		}
	}
	
	/*****************************************************************
    Returns a list of ghosts (represented by rectangles)
    @param none
    @return ArrayList<Rectangle>
    *****************************************************************/
	public static ArrayList<Rectangle> getGhost() {
		ArrayList<Rectangle> ghost = new ArrayList<Rectangle>();
			ghost.add(blinky);
			ghost.add(pinky);
			ghost.add(inky);
			ghost.add(clyde);
		return ghost;
	}
	/*****************************************************************
    Starts the game play
    @param none
    @return none
    *****************************************************************/
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	/*****************************************************************
    Returns the given player's score
    @param player - Player 1 or Player 2 
    @param d
    @return int
    *****************************************************************/
	public static int playerScoreDigit(int player,int d) {
		int a,b;
		a = (int)Math.pow((double)10,(double)(d+1));
		b = (int)Math.pow((double)10,(double)(d));
	    
		if(player == 1) {
			return (playerOneScore%a)/b;
		}else if(player == 2) {
			
		}
		return 0;
	}
	/*****************************************************************
    Moves the player and the Ghosts on the board
    @return none
    *****************************************************************/
	private void move() {
		if(gameOver == false && timer > 180*5) {
			player.move();
			playerTwo.move();	
			/*Makes them move slower*/
			if(timer % (difficulty*5*level) > 1) {
				blinky.move();
				pinky.move();
				inky.move();
				clyde.move();
			}
		}
		timer++;
	}
	/*****************************************************************
    Renders the board, player, and ghosts
    @return none
    *****************************************************************/
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		map.render(g);
		one.render(g);
		two.render(g);
		player.render(g);
		playerTwo.render(g);
		blinky.render(g);
		pinky.render(g);
		inky.render(g);
		clyde.render(g);
		g.dispose();
		bs.show();
	}
	
	/*****************************************************************
    Runs the game at a steady pace
    @return none
    *****************************************************************/
	public void run() {
		requestFocus();//allows movement without clicking
		int fps = 0;//current time
		//double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 180.0;//target fps
		double x = 0;//var that will modify fps to become targeted fps
		double nanosec = 1000000000/targetTick;
		
		while(isRunning==true) {
			//checks current time
			long now = System.nanoTime();
			x+=(now - lastTime)/nanosec;
			lastTime = now;
			//System.out.println("X is: "+x);
			while(x>=1) {
				if(!isPaused)
					move();
				render();
				fps++;
				x--;
			}
			if(Map.gameOver()) {
				//goto next level
				Map.reset();
				level++;
				player.setLocation(9*20, 19*20);
				blinky.setLocation(10*20,16*20);
				pinky.setLocation(10*20,17*20);
				clyde.setLocation(9*20,16*20);
			}
			if(p1LivesRemaining < 1) {
				gameOver = true;
			}
		}
	}
	/*****************************************************************
    Manages the player's keypresses, and translates them to the GUI movement
    @param e - KeyEvent
    @return none
    *****************************************************************/
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
	
		if(e.getKeyCode() == KeyEvent.VK_D) playerTwo.right = true;
		if(e.getKeyCode() == KeyEvent.VK_A) playerTwo.left = true;
		if(e.getKeyCode() == KeyEvent.VK_W) playerTwo.up = true;
		if(e.getKeyCode() == KeyEvent.VK_S) playerTwo.down = true;
		if(e.getKeyCode() == KeyEvent.VK_P) pause();
	}
	/*****************************************************************
    Manages when the player releases a directional key, 
    and translates them to the GUI movement
    @param e - KeyEvent
    @return none
    *****************************************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
		
		if(e.getKeyCode() == KeyEvent.VK_D) playerTwo.right = false;
		if(e.getKeyCode() == KeyEvent.VK_A) playerTwo.left = false;
		if(e.getKeyCode() == KeyEvent.VK_W) playerTwo.up = false;
		if(e.getKeyCode() == KeyEvent.VK_S) playerTwo.down = false;		
	}
	@Override
	public void keyTyped(KeyEvent e) {}

}
