
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// @Author Jon C. Walp
// 2019 AP-CS

public class WordSearch {

    public ArrayList<String> boardTemp = new ArrayList<>(); // to put text file into
    public ArrayList<Character> boardCharTemp = new ArrayList<>(); //separate string into chars
    public char[][] board = new char[15][15]; //actual board
    public ArrayList<String> wordList = new ArrayList<>(); //To hold word list from text file

    /*
    * init()
    * Finds text file containing the WordSearch
    * Catches the FileNotFoundException
    * Adds everything from text file into respective arrays
    */
    public void init(){
        try {
            File f = new File("C:\\Users\\Jon\\IdeaProjects\\WordS\\src\\WORDSEARCH.txt");
            Scanner fileRead = new Scanner(f);
            int count = 0;
            while (count <= 15){
                boardTemp.add(fileRead.nextLine());
                count++;
            }
            while (fileRead.hasNext()){
                wordList.add(fileRead.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
            System.exit(0);
        }
    }


    /*
    * format()
    * The text coming from file is formatted weird so I formatted it to fit specs
    */
    public void format(){
      //first for loop is to just add board into a temp ArrayList
        for (int i = 0; i < boardTemp.size(); i++){
            for (int j = 0; j < boardTemp.get(i).length(); j++){
                boardCharTemp.add(boardTemp.get(i).charAt(j));
            }
        }

        //next for loop is for removing spaces between chars
        for (int i = 0; i < boardCharTemp.size(); i++){
            if (boardCharTemp.get(i).equals(' ')){
                boardCharTemp.remove(i);
            }
        }

        //Adds chars into final board
        int count = 0;
        while (count < boardCharTemp.size()){
            for (int i = 0; i < 15; i++){
                for (int j = 0; j < 15; j++){
                    board[i][j] = boardCharTemp.get(count);
                    count++;
                }
            }
        }

        //removes the possible words line
        wordList.remove(0);

    }

    //Testing method that just prints out all boards and arrays
    public void printBoard(){
        System.out.println(boardTemp);
        System.out.println(boardCharTemp);
        System.out.println();
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                System.out.print(board[i][j] + "   ");
            }
            System.out.println();
            System.out.println();
        }

        System.out.println(wordList);
    }

    /*
    * findWords()
    * driving method for finding WordS
    * combines boolean methods to improve efficincy
    */
    public void findWords(){
        for (String word : wordList){
          //for each word in wordList
            char firstLetter = word.charAt(0); //get the first letter of that word
            for (int i = 0; i < 15; i++){
                for (int j = 0; j < 15; j++){
                    if (board[i][j] == firstLetter){ //if that letter is found
                      //checking the distances in all four directions to see if it should bother checking
                        if (checkUp(i, j, word.length())){
                            lookUp(i, j, word.length(), word);
                        }
                        if (checkRight(i, j, word.length())){
                            lookRight(i, j, word.length(), word);
                        }
                        if (checkDown(i, j, word.length())){
                            lookDown(i, j, word.length(), word);
                        }
                        if (checkLeft(i, j, word.length())){
                            lookLeft(i, j, word.length(), word);
                        }
                        loodDiagonalLeft(i, j, word.length(), word);
                        loodDiagonalRight(i, j, word.length(), word);
                    }
                }
            }
        }
    }
    //checking up
    /*
    * All check methods just look at the length of
    * current word and see if its possible to go in that direction
    */
    public boolean checkUp(int x, int y, int len){
        boolean possible = false;
        if (x - len >= 0){
            possible = true;
        }
        else {
            possible = false;
        }

        return possible;
    }

    public boolean checkRight(int x, int y, int len){
        boolean possible = false;
        if (y + len < 15){
            possible = true;
        }
        else {
            possible = false;
        }
        return possible;
    }

    public boolean checkDown(int x, int y, int len){
        boolean possible = false;
        if (x + len < 15){
            possible = true;
        }
        else {
            possible = false;
        }
        return possible;
    }

    public boolean checkLeft(int x, int y, int len){
        boolean possible = false;
        if (y - len >= 0){
            possible = true;
        }
        else {
            possible = false;
        }
        return possible;
    }


    /*
    * look() methods
    * if the check direction methods come back true
    * it will run the corrisponding look methods
    * @param x and y corrdinates of letter Found
    * @param length of current word
    * @param current word
    * if the word is found it will create a Coord for the start and end positions of current word
    */

    public void lookUp(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList<>();
        String wordTemp = "";
        for (int i = x; i >= 0; i--){
            temp.add(board[i][y]);
        }

        for (int i = 0; i < len; i++){
            wordTemp += temp.get(i);
        }
//        System.out.println(temp);
//        System.out.println(wordTemp);

        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x-len + 1, y);
            System.out.println(w + "   " + a.toString());
        }
    }

    public void lookRight(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList<>();
        String wordTemp = "";

        for (int i = y; i < 15; i++){
            temp.add(board[x][i]);
        }

        for (int i = 0; i < len; i++){
            wordTemp += temp.get(i);
        }

        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x, y + len + 1);
            System.out.println(w + "    " + a.toString());
        }
    }

    public void lookDown(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList<>();
        String wordTemp = "";

        for (int i = x; i < 15; i++){
            temp.add(board[i][y]);
        }

        for (int i = 0; i < len; i++){
            wordTemp += temp.get(i);
        }

        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x + len + 1, y);
            System.out.println(w + "     " + a.toString());
        }
    }

    public void lookLeft(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList<>();
        String wordTemp = "";

        for (int i = y; i >= 0; i--){
            temp.add(board[x][i]);
        }

        for (int i = 0; i < len; i++){
            wordTemp += temp.get(i);
        }

        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x, y - len + 1);
            System.out.println(w + "    " + a.toString());
        }
    }

    public void loodDiagonalRight(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList();
        String wordTemp = "";

        for (int i = x; i >= 0; i--) {
            for (int j = y; j < 15; j++) {
                temp.add(board[i][j]);
            }
        }
        if (temp.size() > len) {
            for (int i = 0; i < len; i++) {
                wordTemp += temp.get(i);
            }
        }
        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x-len + 1, y + len -1);
            System.out.println(w + "    " + a.toString());
        }

    }

    public void loodDiagonalLeft(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList();
        String wordTemp = "";

        for (int i = x; i < 15; i++) {
            for (int j = y; j >= 0; j--) {
                temp.add(board[i][j]);
            }
        }
        if (temp.size() > len) {
            for (int i = 0; i < len; i++) {
                wordTemp += temp.get(i);
            }
        }
        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x+len - 1, y - len +1);
            System.out.println(w + "    " + a.toString());
        }
    }
}
