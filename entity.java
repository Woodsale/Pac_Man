public class entity{

	  private int x;

	  private int y;

	  private int width;

	  private int height;

	  



	  public entity(){

	    x = 0;

	    y = 0;

	    width = 40;

	    height = 40;

	  }

	  public entity(int startX, int startY, int startWidth, int startHeight){

	    x = startX;

	    y = startY;

	    width = startWidth;

	    height = startHeight;

	  }

	  

	  //to do: add getters and setters for all current attributes

	  

	  public void setX(int startX){

	    x = startX;

	  }

	  public void setY(int startY){

	    y = startY;

	  }

	  public void setWidth(int startWidth){

	    width = startWidth;

	  }

	  public void setHeight(int startHeight){

	    height = startHeight;

	  } 

	  public int getX(){

	    return x;

	  }

	  public int getY(){

	    return y;

	  }

	  public int getWidth(){

	    return width;

	  }

	  public int getHeigth(){

	    return height;

	  }

	  public boolean collides(entity entity1, entity entity2){

	    //all collision logic here. return true if they collide

	    //if((entity1.getX()+entity1.getWidth()>=entity2.getX())&&(...
		  if ((entity1.getX()+entity1.getWidth() >= entity2.getX()) && (entity1.getX() <= entity2.getX() + entity2.getWidth()) && (entity1.getY()+entity1.getHeigth() >= entity2.getY()) && (entity1.getY() <= entity2.getY()+entity2.getHeight())) {
			  return true;
		  }
		  return false;
		

	  }
	  public static void main(String[] args) {
		  entity box1 = new entity(50,100,50,50);
		  entity box2 = new entity(75,80,50,50);
		  System.out.println(box1.collides(box2));
	  }
}
