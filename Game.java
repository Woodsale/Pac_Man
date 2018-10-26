import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.*;


public class Game extends Canvas implements Runnable,KeyListener{
	private boolean isRunning = false;
	public static final int WIDTH = 380, HEIGHT = 600;//480,640 
	public static final String TITLE = "Pac Man";  
	
	private Thread thread;
	
	public static Player player;
	public static Player playerTwo;
	public static Map map;
	//used for increasing map size for two players
	/*private static int w;*/
	
	public static boolean twoPlayer = false;
	
	public Game() {
		Dimension dimension = new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		addKeyListener(this);
		player = new Player(9*20,17*20);//input is location
		playerTwo = new Player(10*20,17*20);// input is location
		map = new Map(); 
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
	
	private void move() {
		player.move();
		playerTwo.move();
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
		player.render(g);
		playerTwo.render(g);
		map.render(g);
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
		double targetTick = 60.0;//target fps
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
			//the If below is for printing fps
			/*if(System.currentTimeMillis() - timer > 1000) {
				System.out.println(fps+" "+x);
				fps = 0;
				timer+=1000;
			}*/
		}
		stop();
	}
	/*Manages Key presses from the Player(s)*/
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
	}

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
