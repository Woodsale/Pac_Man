import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*****************************************************************
Player within the Pacman game
@author Team 7
@version Fall 2018
*****************************************************************/
public class Player extends Rectangle{
	
	/**Indicates direction the player is moving*/
	public boolean up, down, left, right;
	/**How many pixels every tick, intitialized to 1*/
	private int speed = 1;
	
	private static int a = 0,b = 0,moveX = 0,moveY = 0;
	/**Size of the player in pixels*/
	private int size;
	/**If the player has won*/
	boolean winPrint;
	
    /*****************************************************************
    Constructor creates a player
    @param x x location of player
    @param y y location of player
    *****************************************************************/
	public Player(int x, int y) {
		size = 20;
		setBounds(x,y,size,size);//locx,locy,sizex,sizey
	}
	
   /*****************************************************************
    Allows for Player movement
    @return None
    *****************************************************************/
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
				Sound.DEATH.play();
			}
		}
		
		if(Game.timer > (Game.p1InvTimer + Game.invTime*180)) {
			Game.p1inv = false;
		}
		
		for(int ii = 0;ii < pellets.size(); ii++) {
			p = pellets.get(ii);
			if(collision(x,y,p)) {
				Game.playerOneScore = Game.playerOneScore + 35;
				Map.changeBoardValue((int)(p.getX()/20), (int)(p.getY()/20), 3);
				pellets.remove(p);
				Sound.MUNCH.play();
			}
		}
		if(pellets.size() == 0 && winPrint == true) {
			System.out.println("You Win!");
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
	
	/*****************************************************************
    Checks if the location is ok to go to
    @param xDir x direction of entity1
    @param yDir y direction of entity1
    @param entity2 the entity that the collision with entity1 is being compared
    @return boolean
    *****************************************************************/
	private boolean collision(int xDir, int yDir, Rectangle entity2) {
		if ((xDir+size > entity2.getX()) && (xDir < entity2.getX() + entity2.getWidth()) && 
			(yDir+size > entity2.getY()) &&	(yDir < entity2.getY() + entity2.getHeight())) {
			return true;
		}
		return false;
	}
	
	/*****************************************************************
    Creates the Pacman rectangle on the board
    @param g graphics context
    @return None
    *****************************************************************/
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
		
		if (direction == "left") {
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
		if (direction == "right") {
			if((a > 30 && a < 59) || (a > 150 && a < 180)) {
				g.fillRect(x+size-2, y+8, 2,4);
				g.fillRect(x+size-7, y+9, 7,2);
			}
			else if((a > 60 && a < 89) || (a > 120 && a < 149)) {
				g.fillRect(x+size-5, y+8, 5,4);
				g.fillRect(x+size-9, y+9, 9,2);
			}
			if(a>90&&a<119) {
				g.setColor(Color.BLACK);
				g.fillRect(x+size-5, y+7, 5,6);
				g.fillRect(x+size-10, y+8, 10,4);
				g.fillRect(x+size-12, y+9, 12,2);	
			}
		}
		if (direction == "up") {
			if((a > 30 && a < 59) || (a > 150 && a < 180)) {
				g.fillRect(x+8, y, 4,2);
				g.fillRect(x+9, y, 2,7);
			}
			else if((a > 60 && a < 89) || (a > 120 && a < 149)) {
				g.fillRect(x+8, y, 4,5);
				g.fillRect(x+9, y, 2,9);
			}
			if(a>90&&a<119) {
				g.setColor(Color.BLACK);
				g.fillRect(x+7, y, 6,5);
				g.fillRect(x+8, y, 4,10);
				g.fillRect(x+9, y, 2,12);
			}
		}
		if (direction == "down") {
			if((a > 30 && a < 59) || (a > 150 && a < 180)) {
				g.fillRect(x+8, y+size-2, 4,2);
				g.fillRect(x+9, y+size-7, 2,7);
			}
			else if((a > 60 && a < 89) || (a > 120 && a < 149)) {
				g.fillRect(x+8, y+size-5, 4,5);
				g.fillRect(x+9, y+size-9, 2,9);
			}
			if(a>90&&a<119) {
				g.setColor(Color.BLACK);
				g.fillRect(x+7, y+size-5, 6,5);
				g.fillRect(x+8, y+size-10, 4,10);
				g.fillRect(x+9, y+size-12, 2,12);
			}
		}
		
	}
		
}
