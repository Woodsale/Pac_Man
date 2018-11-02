import java.awt.*;
import java.util.ArrayList;

public class Player extends Rectangle{
	
	public boolean up, down, left, right;
	/*Speed is how many pixels every tick
	 * whcih should be at 60 fps*/
	private int speed = 1;
	private int xLocation,yLocation;
	
	/**/
	public Player(int x, int y) {
		setBounds(x,y,20,20);//locx,locy,sizex,sizey
		
	}
	
	/*Allows for movement*/
	public void move() {
		ArrayList<Rectangle> wallList = new ArrayList<Rectangle>();
		wallList = Map.getMap();
		
			Rectangle r = new Rectangle();
			r.setBounds(40,40,20,20);
		
			if(right) {
				for(int i = 0;i<wallList.size();i++) {
					if(collision(x+speed,y,wallList.get(i)) == false){
						x+=speed;
						break;
					}
				}
			}
			else if(left) {
				for(int i = 0;i<wallList.size();i++) {
					if(collision(x-speed,y,wallList.get(i)) == false){
						x-=speed;
						break;
					}
				}
			}
			else if(up) {
				for(int i = 0;i<wallList.size();i++) {
					if(collision(x,y-speed,wallList.get(i)) == false){
						y-=speed;
						break;
					}
				}
			}
			else if(down) {
				for(int i = 0;i<wallList.size();i++) {
					if(collision(x,y+speed,wallList.get(i)) == false){
						y+=speed;
						break;
					}
				}
			}
		
		//System.out.println(getLocation()+" " + x + " "+ y);
	}
	
	/*private boolean collision(int x,int y) {
		int[][] board = Map.getBoard();
		Rectangle r = new Rectangle();
		r.setBounds(40,40,20,20);
		
		if( this.contains(r.getLocation()) == true) {
			System.out.println("HI");
			return true;
		}
		return false;
	}*/
	
	/*Checks if the location is okay to go to*/
	private boolean collision(int xDir, int yDir, Rectangle entity2) {
		if ((xDir+20 > entity2.getX()) && 
				(xDir < entity2.getX() + entity2.getWidth()) && 
				(yDir+20 > entity2.getY()) && 
				(yDir < entity2.getY()+entity2.getHeight())) {
			return true;
		}
		return false;
	}
	
	/*Creates the pac man rectangle on the board*/
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
}
