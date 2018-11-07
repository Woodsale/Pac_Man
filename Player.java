import java.awt.*;
import java.util.ArrayList;

public class Player extends Rectangle{
	
	public boolean up, down, left, right;
	/*Speed is how many pixels every tick
	 * whcih should be at 60 fps*/
	private int speed = 1;
	
	private int size;
	
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
		
		boolean cx = false, cy = false;
		if(right) {
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x+speed,y,r) == true){
					cx = true;
				}
			}
			if(cx == false){
				x+=speed;
			}/*
			if(collision(x+speed,y,r) == false){
				x+=speed;
			}*/
		}
		if(left) {
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x-speed,y,r) == true){
					cx = true;
				}
			}
			if(cx == false){
				x-=speed;
			}/*
			if(collision(x-speed,y,r) == false){
				x-=speed;
			}*/
		}
		if(up) {
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x,y-speed,r) == true){
					cy = true;
				}
			}
			if(cy == false){
				y-=speed;
			}/*
			if(collision(x,y-speed,r) == false){
				y-=speed;
			}*/
		}
		if(down) {
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x,y+speed,r) == true){
					cy = true;
				}
			}
			if(cy == false){
				y+=speed;
			}/*
			if(collision(x,y+speed,r) == false){
				y+=speed;
			}*/
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
		/*g.setColor(Color.BLACK);
		g.fillRect(x, y, 8,1);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 1,8);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 2,5);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 5,2);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 3, 3);*/
	}
}
