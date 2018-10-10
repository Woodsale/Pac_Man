import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class GAMELOGIC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GAMELOGIC extends JPanel
{
    // instance variables - replace the example below with your own
    private final int ROWS=50,COLUMNS=50;
    private int[][] theBoard; //we need to build a board
    private boolean powerPellet;
    private PACMAN pm;
    private GHOST g1;
    private GHOST g2;
    private GHOST g3;
    private GHOST g4;
    private int points=0;
    private int lives=3;
    private Location myLocation;
    
    public GAMELOGIC(){
        theBoard = new int [ROWS][COLUMNS];
        pm = new PACMAN(myLocation,points,lives);
        
    }
    public boolean collide(ENTITY pm, ENTITY g){
       
        while(pm.getLocation() == g.getLocation()){
        if(pm.getType()==g.getType())
            return false; 
        else if(powerPellet){
            //return ghosts to starting point
        }
        else if(powerPellet ==false){
            //reduce pacman life by 1
    }
}
return true;
}
}
