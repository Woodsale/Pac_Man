import java.awt.*;

public class Player extends Rectangle{
	
	public boolean up, down, left, right;
	/*Speed is how many pixels every tick
	 * whcih should be at 60 fps*/
	private int speed = 3;
	private int xLocation,yLocation;
	//public static Map map = new Map();
	
	/**/
	public Player(int x, int y) {
		setBounds(x,y,20,20);//locx,locy,sizex,sizey
		xLocation = x;//checking collison for x
		yLocation = y;//checking collison for y
	}
	
	/*Allows for movement*/
	public void move() {
		if(right) {
			x+=speed;
		}
		if(left) {
			x-=speed;
		}
		if(up) {
			y-=speed;
		}
		if(down) {
			y+=speed;
		}
		//System.out.println(getLocation());
	}
	
	//private boolean collision(Player p, int[][] board) {
	//	for(int i = 0;i<board.length;i++) {
	//		for(int j = 0;j<board[0].length;j++) {
				/*if(p.intersects(map)) {	
					return true;
				}
	//		}
	//	}	
		return false;
	}
	
	/*Checks if the location is okay to go to*/
	/*private boolean collision(Player entity1, Map entity2) {
		if ((entity1.getX()+entity1.getWidth() >= entity2.getX()) && 
				(entity1.getX() <= entity2.getX() + entity2.getWidth()) && 
				(entity1.getY()+20 >= entity2.getY()) && 
				(entity1.getY() <= entity2.getY()+entity2.getHeight())) {
			return true;
		}
		return false;
	}*/
	
	/*Creates the pac man rectangle on the board*/
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
}
