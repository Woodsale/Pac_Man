import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Rectangle{
	
	public boolean up, down, left, right;
	/*Speed is how many pixels every tick
	 * whcih should be at 180 fps*/
	private int speed = 1;
	private static int a = 0,b = 0,moveX = 0,moveY = 0;
	private int size;
	boolean winPrint;
	
	/**/
	public Player(int x, int y) {
		size = 20;
		setBounds(x,y,size,size);//locx,locy,sizex,sizey
	}
	
	/*Allows for movement*/
	public void move() {
		winPrint = true;
		boolean cx = false, cy = false;
		Rectangle r = new Rectangle();
		r.setBounds(40,40,20,20);
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		list = Map.getMap(0);
		
		Rectangle p = new Rectangle();
		ArrayList<Rectangle> pellets = new ArrayList<Rectangle>();
		pellets = Map.getMap(1);
		
		Rectangle gs = new Rectangle();
		ArrayList<Rectangle> ghosts = new ArrayList<Rectangle>();
		ghosts = Game.getGhost();
		
		Rectangle gh = new Rectangle();
		ArrayList<Rectangle> ghostHouse = new ArrayList<Rectangle>();
		ghostHouse = Map.getMap(5);
		
		for(int jj = 0;jj < ghosts.size(); jj++) {
			gs = ghosts.get(jj);
			if(collision(x,y,gs) && Game.p1inv == false) {
				Game.p1LivesRemaining = Game.p1LivesRemaining - 1;
				Game.lifeCounter(true,Game.p1NextLife);
				Game.p1NextLife++;
				Game.p1inv = true;
				Game.p1InvTimer = Game.timer;
				if(Game.p1LivesRemaining <= 0) {
					Game.gameOver = true;
				}
			}
		}
		
		if(Game.timer > (Game.p1InvTimer + Game.invTime*180)) {
			Game.p1inv = false;
		}
		
		for(int ii = 0;ii < pellets.size(); ii++) {
			p = pellets.get(ii);
			if(collision(x,y,p)) {
				Game.playerOneScore = Game.playerOneScore + 5;
				Map.changeBoardValue((int)(p.getX()/20), (int)(p.getY()/20), 3);
				pellets.remove(p);
			}
		}
		if(pellets.size() == 0 && winPrint == true) {
			//System.out.println("You Win!");
			winPrint = false;
		}
		/*Checks for collision against wall*/
		if(right) {//moveX=0
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x+speed,y,r) == true){
					cx = true;
				}
			}
			if(cx == false){
				x+=speed;
			}
		}
		if(left) {//left//moveX==1
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x-speed,y,r) == true){
					cx = true;
				}
			}
			if(cx == false){
				x-=speed;
			}
		}
		if(up) {//up//moveY==0
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x,y-speed,r) == true){
					cy = true;
				}
			}
			if(cy == false){
				y-=speed;
			}
		}
		if(down) {//down//moveY==0
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x,y+speed,r) == true){
					cy = true;
				}
			}
			for(int i = 0;i<ghostHouse.size();i++) {
				gh = ghostHouse.get(i);
				if(collision(x,y+speed,gh) == true){
					cy = true;
				}
			}
			if(cy == false){
				y+=speed;
			}
		}
	}
	
	/*Checks if the location is okay to go to*/
	private boolean collision(int xDir, int yDir, Rectangle entity2) {
		if ((xDir+size > entity2.getX()) && (xDir < entity2.getX() + entity2.getWidth()) && 
			(yDir+size > entity2.getY()) &&	(yDir < entity2.getY() + entity2.getHeight())) {
			return true;
		}
		return false;
	}
	
	/*Creates the pac man rectangle on the board*/
	public void render(Graphics g) {
		if(((Game.timer % 90) > 45) && Game.p1inv == true) {
			g.setColor(Color.BLACK);
		}else {
			g.setColor(Color.yellow);
		}
		g.fillRect(x, y, width, height);

		/*Upper Left corner*/
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 7,1);
		g.fillRect(x, y, 1,7);
		g.fillRect(x, y, 3, 3);
		g.fillRect(x, y, 5,2);
		g.fillRect(x, y, 2,5);

		/*Lower Right corner*/
		g.fillRect(width+x-7, height+y-1, 7,1);
		g.fillRect(width+x-1, height+y-7, 1,7);
		g.fillRect(width+x-3, height+y-3, 3,3);
		g.fillRect(width+x-5, height+y-2, 5,2);
		g.fillRect(width+x-2, height+y-5, 2,5);

		/*Upper Right corner*/
		g.fillRect(width+x-7, y, 7,1);
		g.fillRect(width+x-1, y, 1,7);
		g.fillRect(width+x-3, y, 3,3);
		g.fillRect(width+x-5, y, 5,2);
		g.fillRect(width+x-2, y, 2,5);

		/*Lower Left corner*/
		g.fillRect(x, height+y-1, 7,1);
		g.fillRect(x, height+y-7, 1,7);
		g.fillRect(x, height+y-3, 3,3);
		g.fillRect(x, height+y-2, 5,2);
		g.fillRect(x, height+y-5, 2,5);

		/*Draws the mouth*/
		a++;
		if(a > 180) {
			a = 0;
		}
		if((a > 30 && a < 59) || (a > 150 && a < 180)) {
			g.fillRect(x, y+8, 2,4);
			g.fillRect(x, y+9, 7,2);
		}
		else if((a > 60 && a < 89) || (a > 120 && a < 149)) {
			g.fillRect(x, y+8, 5,4);
			g.fillRect(x, y+9, 9,2);
		}
		if(a>90&&a<119) {
			g.setColor(Color.BLACK);
			g.fillRect(x, y+7, 5,6);
			g.fillRect(x, y+8, 10,4);
			g.fillRect(x, y+9, 12,2);	
		}
	}
}
