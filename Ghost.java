import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*****************************************************************
Ghost class is made up of rectangles which represent ghost entities in the Pacman game

@author Team 7
@version Fall 2018
*****************************************************************/
public class Ghost extends Rectangle{
	
	/** pixels per tick, initialized to 1 */
	private int speed = 1;
	
	/**Total seconds*/
	private int a = 0; 
	
	/**Frames in that second*/
	private int b = 0; 
	
	/**First few seconds*/
	private int c = 0; 
	
	/**movement in the X direction*/
	private int moveX = 0
	
	/**movement in the y direction*/
	private int moveY = 0
	
	/*represtents ghost's color/type*/
	private int ghost = 0;
	
	/**size of the ghost in pixels*/
	private int size;
	
	/**random value*/
	Random ran = new Random();
	
	
	static int asdf;
	
	/**x location*/
	int locx;
	
	/**y location*/
	int locy;
	
    /*****************************************************************
    Constructor creates a ghost
    @param x - x location
    @param y - y location
    @param ghost
    *****************************************************************/
	public Ghost(int x, int y,int ghost) {
		size = 20;
		setBounds(x,y,size,size);//locx,locy,sizex,sizey
		ran.setSeed(System.currentTimeMillis()+ghost);
		setGhost(ghost);
	}
	
    /*****************************************************************
    Set the ghost.
    @param g - ghost
    @return none
    *****************************************************************/
	void setGhost(int g) {
		ghost = g;
	}
	
    /*****************************************************************
    Returns the value associated with a ghost.
    @return int
    *****************************************************************/
	int getGhost() {
		return ghost;
	}
	
    /*****************************************************************
    Allows for and calculates ghost movement based on ghost color/type
    @return none
    *****************************************************************/
	public void move() {
		boolean cx = false, cy = false;
		Rectangle r = new Rectangle();
		r.setBounds(40,40,20,20);
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		list = Map.getMap(0);
		
		Rectangle gh = new Rectangle();
		ArrayList<Rectangle> ghostHouse = new ArrayList<Rectangle>();
		ghostHouse = Map.getMap(5);
		
		/*used for calc movement*/
		a++;
		b++;
		c++;
		if(c < 180 * 2) {
			moveY = 0;//Goes up for the first 3 seconds
			if((a/180)%2==0) {
				moveX=1;
			}else {
				moveX=0;
			}
		}
		else if(b > 180) {
			if(getGhost() == 1) {
				if(Game.playerLoc(1,0)>this.getX()) {
					moveX=0;
				}else{
					moveX=1;
				}
				if(Game.playerLoc(1,1)>this.getY()){
					moveY=1;
				}else {
					moveY=0;
				}
			}else if(getGhost() == 2 && c%2==0) {
				if(Game.playerLoc(3,0)>this.getX()) {
					moveX=0;
				}else{
					moveX=1;
				}
				if(Game.playerLoc(3,1)>this.getY()){
					moveY=1;
				}else {
					moveY=0;
				}
			}else if(getGhost() == 4 && c%3==0) {
				if(Game.playerLoc(1,0)>this.getX()) {
					moveX=1;
				}else{
					moveX=0;
				}
				if(Game.playerLoc(1,1)>this.getY()){
					moveY=0;
				}else {
					moveY=1;
				}
			}else{
				moveX = ran.nextInt(2);
				moveY = ran.nextInt(2);
			}
			b = 0;
		}
		
		if(moveX==0) {//moveX=0
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
		if(moveX==1) {//left//moveX==1
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
		if(moveY==0) {//up//moveY==0
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
		if(moveY==1) {//down//moveY==0
			for(int i = 0;i<list.size();i++) {
				r = list.get(i);
				if(collision(x,y+speed,r) == true){
					cy = true;
				}
			}
			for(int i = 0;i<ghostHouse.size();i++) {
				gh = ghostHouse.get(i);
				if(collision(x,y+speed,gh) == true){
					y-=speed;
				}
			}
			if(cy == false){
				y+=speed;
			}
		}
	}
	
    /*****************************************************************
    Checks if location is ok to move to 
    @param xDir - x direction of entity1
    @param yDir - y direction of entity1
    @param entity2 - object that is possibly colliding with entity1
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
    Creates ghost rectangle on the board
    @param g - graphics context
    @return none
    *****************************************************************/
	public void render(Graphics g) {
		if(getGhost() == 1) {
			g.setColor(Color.red);
		}
		else if(getGhost() == 2) {
			g.setColor(new Color(255,51,153));
		}
		else if(getGhost() == 3) {
			g.setColor(new Color(51,255,255));
		}
		else if(getGhost() == 4) {
			g.setColor(Color.ORANGE);
		}else {
			g.setColor(Color.BLUE);
		}
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		/*Upper Left Corner*/
		g.fillRect(x, y, 1, 8);
		g.fillRect(x, y, 2, 4);
		g.fillRect(x, y, 3, 3);
		g.fillRect(x, y, 4, 2);
		g.fillRect(x, y, 6, 1);
		/*Upper right corner*/
		g.fillRect(width+x-1, y, 1, 8);
		g.fillRect(width+x-2, y, 2, 4);
		g.fillRect(width+x-3, y, 3, 3);
		g.fillRect(width+x-4, y, 4, 2);
		g.fillRect(width+x-6, y, 6, 1);
		/*Square on bottem*/
		g.fillRect(x+8,height+y-3, 4, 3);
		/*Left Triangle*/
		g.fillRect(x+1,height+y-1, 5, 1);
		g.fillRect(x+2,height+y-2, 3, 2);
		g.fillRect(x+3,height+y-3, 1, 3);
		/*Right Triangle*/
		g.fillRect(width+x-6,height+y-1, 5, 1);
		g.fillRect(width+x-5,height+y-2, 3, 2);
		g.fillRect(width+x-4,height+y-3, 1, 3);
		/*Left Eye*/
		g.setColor(Color.WHITE);
		g.fillRect(x+5,y+5,5,5);
		g.fillRect(x+6,y+4,3,7);
		g.setColor(Color.BLACK);
		g.fillRect(x+7,y+8,3,2);
		g.fillRect(x+8,y+7,1,4);
		/*Right Eye*/
		g.setColor(Color.WHITE);
		g.fillRect(x+13,y+5,5,5);
		g.fillRect(x+14,y+4,3,7);
		g.setColor(Color.BLACK);
		g.fillRect(x+15,y+8,3,2);
		g.fillRect(x+16,y+7,1,4);
	}
}
