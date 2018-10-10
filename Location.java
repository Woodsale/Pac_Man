/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    int r; 
    int c;
    public Location(int r, int c){
        this.r =r;
        this.c=c;
    }

    public void setRow(int r){
        this.r=r;
    }

    public void setCol(int c){
        this.c=c;
    }

    public int getRow(){
        return r;
    }

    public int getCol(){
        return c;
    }
    
    public String toString(){
        return "Location "+"row: "+r+" col: "+c;
    }
}

