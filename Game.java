package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.*;


public class Game extends Canvas implements Runnable,KeyListener{
	private boolean isRunning = false;
	public static final int WIDTH = 380, HEIGHT = 640;//480,640 
	public static final String TITLE = "Pac Man";  
	
	private Thread thread;
	
	public static Player player;
	public static Player playerTwo;
	public static Player one;
	public static Player two;
	public static Ghost blinky;
	public static Ghost pinky;
	public static Ghost inky;
	public static Ghost clyde;
	public static Map map;
	
	public static int timer = 0;
	public static int invTime = 3;
	public static int p1InvTimer = 0;
	public static boolean p1inv = false;
	
	public static int level = 1;
	public static int p1LivesRemaining = 3;
	public static int p1NextLife = 1;
	
	public static int playerOneScore = 0;
	public static int playerTwoScore = 0;
	
	public static boolean twoPlayer = false;
	
	public Game() {
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
	
	public static ArrayList<Rectangle> getGhost() {
		ArrayList<Rectangle> ghost = new ArrayList<Rectangle>();
			ghost.add(blinky);
			ghost.add(pinky);
			ghost.add(inky);
			ghost.add(clyde);
		return ghost;
	}
	
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if(!isRunning) return;
		isRunning = false;
		try{
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private void move() {
		player.move();
		playerTwo.move();	
		/*Makes them move slower*/
		if(timer % (10*level) > 1) {
			blinky.move();
			pinky.move();
			inky.move();
			clyde.move();
		}
		timer++;
	}
	
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
	
	/*The parts of this that were 	
	 * found on stackOverflow		
	 * are used to keep the program at 60fps*/
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
				move();
				render();
				fps++;
				x--;
			}
		}
		stop();
	}
	/*Manages Key presses from the Player(s)*/
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			player.direction = "right";
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
			player.direction = "left";
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
			player.direction = "up";
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
			player.direction = "down";
		}
	
		if(e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.right = true;
			playerTwo.direction = "right";
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.left = true;
			playerTwo.direction = "left";
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			playerTwo.up = true;
			playerTwo.direction = "up";
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			playerTwo.down = true;
			playerTwo.direction = "down";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			playerTwo.right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			playerTwo.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			playerTwo.up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			playerTwo.down = false;		
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}

}
