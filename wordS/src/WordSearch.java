import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordSearch {

    public ArrayList<String> boardTemp = new ArrayList<>(); // to put text file into
    public ArrayList<Character> boardCharTemp = new ArrayList<>(); //separate string into chars
    public char[][] board = new char[15][15]; //actual board
    public ArrayList<String> wordList = new ArrayList<>();

    public void init(){
        try {
            File f = new File("C:\\Users\\jwalp\\IdeaProjects\\untitled6\\src\\WORDSEARCH.txt");
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

    public void format(){
        for (int i = 0; i < boardTemp.size(); i++){
            for (int j = 0; j < boardTemp.get(i).length(); j++){
                boardCharTemp.add(boardTemp.get(i).charAt(j));
            }
        }

        for (int i = 0; i < boardCharTemp.size(); i++){
            if (boardCharTemp.get(i).equals(' ')){
                boardCharTemp.remove(i);
            }
        }

        int count = 0;
        while (count < boardCharTemp.size()){
            for (int i = 0; i < 15; i++){
                for (int j = 0; j < 15; j++){
                    board[i][j] = boardCharTemp.get(count);
                    count++;
                }
            }
        }
        wordList.remove(0);

    }


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


    public void findWords(){
        String word = wordList.get(0);
        //for (String word : wordList){
        char firstLetter = word.charAt(0);
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                if (board[i][j] == firstLetter){
//              String x = Integer.toString(i);
//              String y = Integer.toString(j);
//              System.out.println(word + " Letter Found: " + x + ", " + y);
//              continue;
                    if (checkUp(i, j, word.length())){
                        lookUp(i, j, word.length(), word);
                    }
                }
            }
        }
        //}
    }
    //checking up
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

    public void lookUp(int x, int y, int len, String w){
        ArrayList<Character> temp = new ArrayList<>();
        String wordTemp = "";
        w = "ALBUSLA";
        for (int i = x; i >= 0; i--){
            temp.add(board[i][y]);
        }

        for (int i = 0; i < len; i++){
            wordTemp += temp.get(i);
        }
        System.out.println(temp);
        System.out.println(wordTemp);

        if (wordTemp.equals(w)){
            Coord a = new Coord(x, y, x-len + 1, y);
            System.out.println(a.toString());
        }


    }
}
