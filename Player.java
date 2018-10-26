import java.awt.*;

public class Player extends Rectangle{
	
	public boolean up, down, left, right;
	/*Speed is how many pixels every tick
	 * whcih should be at 60 fps*/
	private int speed = 3;
	private int xLocation,yLocation;
	
	/**/
	public Player(int x, int y) {
		setBounds(x,y,20,20);//locx,locy,sizex,sizey
		xLocation = x;
		yLocation = y;
	}
	/*Allows for movement*/
	public void tick() {
		if(right && collision(xLocation,yLocation) == true) {
			x+=speed;
		}
		if(left && collision(xLocation,yLocation) == true) {
			x-=speed;
		}
		if(up && collision(xLocation,yLocation) == true) {
			y-=speed;
		}
		if(down && collision(xLocation,yLocation) == true) {
			y+=speed;
		}
		//System.out.println(getLocation());
	}
	
	/*Checks if the location is okay to go to*/
	private boolean collision(int xMove, int yMove) {
		return true;
	}
	
	/*Creates the pac man rectangle on the board*/
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
}
