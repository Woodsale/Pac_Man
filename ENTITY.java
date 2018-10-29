
/**
 * Write a description of class ENTITY here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class ENTITY extends Rectangle 
{
    protected Location myLocation;
    private type myType;
    

    public static enum type{
        PACMAN,GHOST, WALL, PELLET;
    }
   
    /**
     * Constructor for objects of class ENTITY
     */
    public ENTITY(Location loc)
    {
        // initialise instance variables
        startLocation = loc;
        
    }

    public final void setLocation(Location loc){
        myLocation = loc;
    }
    
    public final Location getLocation(){
        return myLocation;
    }
    
    public final type getType(){
     return myType;   
    }
    
    public final void setType(type t){
      myType = t;  
    }
    
    public boolean noMoveCollision(ENTITY 1, ENTITY 2){
        while(1.getLocation() == 2.getLocation(){
     if(1.getType() == 2.getType())
         return false;
     else if(1.getType() == PELLET || 2.getType() == PELLET)
         return false;
     else if(1.getType() == WALL || 2.getType() == WALL)
         return true;  
        }
    }
    
    public boolean loseLifeCollision(ENTITY 1, ENTITY 2){
             while(1.getLocation() == 2.getLocation(){
     if(1.getType() == 2.getType())
         return false;
     else if(1.getType() == PELLET || 2.getType() == PELLET)
         return false;
     else if(1.getType() == GHOST || 2.getType() == GHOST)
         return true;  
        }   
    }
}
