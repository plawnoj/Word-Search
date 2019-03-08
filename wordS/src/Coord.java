public class Coord {

  /*
  * Class to store coordinates of words found in word WordSearch
  * Stores start X and Y and end X and Y
  */

  private int xStart;
  private int yStart;
  private int xEnd;
  private int yEnd;

  public Coord(int xS, int yS, int xE, int yE){
    this.xStart = xS;
    this.yStart = yS;
    this.xEnd = xE;
    this.yEnd = yE;
  }

  public String toString(){
    String xS = Integer.toString(this.xStart);
    String yS = Integer.toString(this.yStart);
    String xE = Integer.toString(this.xEnd);
    String yE = Integer.toString(this.yEnd);

    return ("Start [" + xS + ", " + yS + "]" + " => End [" + xE + " ," + yE + "]");

  }
}
