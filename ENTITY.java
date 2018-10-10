
/**
 * Write a description of class ENTITY here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class ENTITY
{
    protected Location myLocation;
    protected Location startLocation;
    private type myType;
    
    public static enum direction{
        LEFT,RIGHT,UP,DOWN;
    }
    public static enum type{
        PACMAN,GHOST;
    }
    // instance variables - replace the example below with your own
   
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
    

}
