
/**
 * Write a description of class GHOST here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GHOST extends ENTITY
{
    public color myColor;
    
    public static enum color{
        RED,PINK,ORANGE,CYAN;
    }

    /**
     * Constructor for objects of class GHOST
     */
    public GHOST(Location loc, color c)
    {
        // initialise instance variables
        super(loc);
        myColor = c;
    }
    
    public final color getColor(){
     return myColor;   
    }
    
    public final void steColor(color c){
      myColor = c;  
    }

   }
