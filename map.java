
public class map extends entity{
  String type; //the type of functionality the object will have. "wall" or "pellet" for now.
  
  static map[] allMaps = new map[0]; //List of all map objects. Later down the road when we have multiple maps ready, this will need to get modified.
  
  public map(int startX, int startY, int startWidth, int startHeight, String startType){
    super(startX, startY, startWidth, startHeight);
    type = startType;
    //add here: append the new object to all objects static list
  }
  //need getters and setters for new variables not in entity
  //need method for blitting map stuff
  //need method for pellets(check if player collides, if so remove itself)
  


}
