package edu.gatech.seclass.words6300;

// https://stackoverflow.com/questions/4878159/whats-the-best-way-to-share-data-between-activities
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    private static DataHolder holder = null;

    private int maxTurn = 20;
    private String currUserID = "yding318";
    private File currDirectory = null;
    private Map<Character, Integer> alphabetFreq;
    private Map<Character, Integer> alphabetScore;

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

    void setCurrUserID(String s) {
        currUserID = s;
    }

    void setCurrDirectory(File s) {
        currDirectory = s;
    }

    File getCurrDirectory() {
        return currDirectory;
    }

    String getCurrID() {
        return currUserID;
    }

}
