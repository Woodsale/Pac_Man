
/**
 * Write a description of class PACMAN here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PACMAN extends ENTITY
{
    // instance variables - replace the example below with your own
    private boolean powerPellet;
    private int points;
    private int lives;
    

    /**
     * Constructor for objects of class PACMAN
     */
    public PACMAN(Location loc, int p, int l)
    {
        // initialise instance variables
        super(loc);
        points = p;
        lives = l;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void setPoints(int p){
       points =p; 
    }
    
    public int getLives(){
     return lives;   
    }
    
    public void setLives(int l){
     lives =l;   
    }
    

}

