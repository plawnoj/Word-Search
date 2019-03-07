public class WordFound {

    private int xPos;
    private int yPos;
    private int xEnd;
    private int yEnd;
    private String word;

    public WordFound(int xS, int yS, int xE, int yE, String w){
        this.xPos = xS;
        this.yPos = yS;
        this.xEnd = xE;
        this.yEnd = yE;
        this.word = w;
    }
}
