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
	
	Random ran = new Random();
	
	/**/
	public Player(int x, int y) {
		size = 20;
		setBounds(x,y,size,size);//locx,locy,sizex,sizey
	}
	
	/*Allows for movement*/
	public void move() {
		Rectangle r = new Rectangle();
		r.setBounds(40,40,20,20);
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		list = Map.getMap();
		
		/*b++;
		if(b > 180) {
			moveX = ran.nextInt(2);
			moveY = ran.nextInt(2);
			b = 0;
		}*/
		
		boolean cx = false, cy = false;
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
			if(cy == false){
				y+=speed;
			}
		}

		//System.out.println(getLocation()+" " + x + " "+ y);
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
		g.setColor(Color.yellow);
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
