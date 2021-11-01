package edu.gatech.seclass.words6300;


import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    private static DataHolder holder = null;

    private int maxTurn = 20;
    private Map<Character, Integer> alphabetFreq;
    private Map<Character, Integer> alphabetScore;
    private Map<Character, Integer> setting_Freq;
    private Map<Character, Integer> setting_Score;

    // gameStatus = true  : game is running
    private boolean gameStatus = false;
    private Pool pool;

    private char[] boardCharArray;
    private char[] rackCharArray;

    private int currTurn = 0;
    private int numSelectedBoard = 0;
    private int numSelectedRack = 0;
    private int currScore = 0;
    private int totalScore = 0;
    private char chosenBoard;
    private int chosenBoardId = 0;

    //save current letter stats - fix bug 17
    private HashMap<String, Integer> letterPlayedMap ;
    private HashMap<String, Integer> letterDrawnMap ;
    private HashMap<String, Integer> letterPoolMap ;


    private DataHolder() {
        alphabetFreq = new HashMap<>();
        alphabetScore = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char ch = (char) (i + 'A');
            if (ch == 'E') {
                alphabetFreq.put(ch, 12);
            } else if (ch == 'A') {
                alphabetFreq.put(ch, 9);
            } else if (ch == 'I' || ch == 'O') {
                alphabetFreq.put(ch, 9);
            } else if (ch == 'N' || ch == 'R' || ch == 'T') {
                alphabetFreq.put(ch, 6);
            } else if (ch == 'L' || ch == 'S' || ch == 'U' || ch == 'D') {
                alphabetFreq.put(ch, 4);
            } else if (ch == 'G') {
                alphabetFreq.put(ch, 3);
            } else if (ch == 'B' || ch == 'C' || ch == 'M' || ch == 'P' || ch == 'F' || ch == 'H'||
                    ch == 'V' || ch == 'W' || ch == 'Y') {
                alphabetFreq.put(ch, 2);
            } else if (ch == 'K' || ch == 'J' || ch == 'X' || ch == 'Q' || ch == 'Z') {
                alphabetFreq.put(ch, 1);
            }

            if (ch == 'E' || ch == 'A' || ch == 'I' || ch == 'O' || ch == 'N' || ch == 'R' ||
                    ch == 'T' || ch == 'L' || ch == 'S' || ch == 'U') {
                alphabetScore.put(ch, 1);
            } else if (ch == 'D' || ch == 'G') {
                alphabetScore.put(ch, 2);
            } else if (ch == 'B' || ch == 'C' || ch == 'M' || ch == 'P') {
                alphabetScore.put(ch, 3);
            } else if (ch == 'F' || ch == 'H' || ch == 'V' || ch == 'W' | ch == 'Y') {
                alphabetScore.put(ch, 4);
            } else if (ch == 'K') {
                alphabetScore.put(ch, 5);
            } else if (ch == 'J' || ch == 'X') {
                alphabetScore.put(ch, 8);
            } else if (ch == 'Q' || ch == 'Z') {
                alphabetScore.put(ch, 10);
            }
        }
    }

    public static DataHolder getInstance() {
        // Lazy initialization
        if (holder == null) {
            holder = new DataHolder();
        }
        return holder;
    }

    int getMaxTurn() {
        return this.maxTurn;
    }

    Map<Character, Integer> getAlphabetFreq() {
        return this.alphabetFreq;
    }

    Map<Character, Integer> getAlphabetScore() {
        return this.alphabetScore;
    }

    void setMaxTurn(int num) {
        maxTurn = num;
    }

    void setFreq(char ch, int num) {
        alphabetFreq.put(ch, num);
    }

    void setScore(char ch, int score) {
        alphabetScore.put(ch, score);
    }

    boolean getGameStatus() {
        return gameStatus;
    }

    void setGameStatus(boolean status) {
        gameStatus = status;
    }

    void setPool(Pool inputPool) {
        pool = inputPool;
    }

    Pool getPool() { return pool; }

    void setBoardCharArray(char[] inputCharArray) {
        boardCharArray = inputCharArray;
    }

    char[] getBoardCharArray() {
        return boardCharArray;
    }

    void setRackCharArray(char[] inputCharArray) {
        rackCharArray = inputCharArray;
    }

    char[] getRackCharArray() {
        return rackCharArray;
    }

    //store current game settings
    void setLetterPlayedMap( HashMap<String, Integer> map){this.letterPlayedMap = map;};
    void setLetterDrawnMap(HashMap<String, Integer> map){this.letterDrawnMap = map;};
    void setLetterPoolMap (HashMap<String, Integer> map){this.letterPoolMap = map;};

    void setAlphabetFreq(HashMap<Character, Integer> map) {this.alphabetFreq = map;}
    void setAlphabetScore(HashMap<Character, Integer> map) {this.alphabetScore = map;}

    void saveAlphabetFreq(HashMap<Character, Integer> map) {this.setting_Freq = map;}
    void saveAlphabetScore(HashMap<Character, Integer> map) {this.setting_Score = map;}

    Map<Character, Integer> getSettingFreq() {
        return this.setting_Freq;
    }

    Map<Character, Integer> getSettingScore() {
        return this.setting_Score;
    }


    public HashMap<String, Integer> getLetterPlayedMap() {
        return letterPlayedMap;
    }

    public HashMap<String, Integer> getLetterDrawnMap() {

        return letterDrawnMap;
    }

    public HashMap<String, Integer> getLetterPoolMap() {
        return letterPoolMap;
    }

    void setGameParameters(int currTurn, int numSelectedBoard, int numSelectedRack, int currScore, int totalScore,
                           char chosenBoard, int chosenBoardId) {
        this.currTurn = currTurn;
        this.numSelectedBoard = numSelectedBoard;
        this.numSelectedRack = numSelectedRack;
        this.currScore = currScore;
        this.totalScore = totalScore;
        this.chosenBoard = chosenBoard;
        this.chosenBoardId = chosenBoardId;
    }

    int getCurrTurn() {
        return currTurn;
    }

    int getNumSelectedBoard() {
        return numSelectedBoard;
    }

    int getNumSelectedRack() {
        return numSelectedRack;
    }

    int getCurrScore() {
        return currScore;
    }

    int getTotalScore() {
        return totalScore;
    }

    char getChosenBoard() {
        return chosenBoard;
    }

    int getChosenBoardId() {
        return chosenBoardId;
    }


}
