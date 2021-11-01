package edu.gatech.seclass.words6300;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Pool implements Serializable {
    private static final String TAG = "MyActivity";
    private int maxTurn = 1;
    private int size = 100;
    private Map<Character, Integer> alphabetMaps;
    private Map<Character, Integer> alphabetScore;
    private Map<Character, Integer> map1;
    private Map<Character, Integer> map2;

    private DataHolder dataHolder;
    Pool() {
        dataHolder = DataHolder.getInstance();
        maxTurn = dataHolder.getMaxTurn();

        //deep copy
        map1 = dataHolder.getAlphabetFreq();
        map2 = dataHolder.getAlphabetScore();

        alphabetMaps = new HashMap<Character, Integer>();
        alphabetScore = new HashMap<Character, Integer>();

        for (Map.Entry<Character,Integer> entry : map1.entrySet()){
            this.alphabetMaps.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Character,Integer> entry : map2.entrySet()){
            this.alphabetScore.put(entry.getKey(), entry.getValue());
        }


        //alphabetMaps = dataHolder.getAlphabetFreq();
        //alphabetScore = dataHolder.getAlphabetScore();
        generateRack();
    }

    String[] generateRack () {
        String[] res = new String[7];
        for (int i = 0; i < 7; i++) {
            int idx = (int) (Math.random() * 26);
            char ch = (char) (idx + 'A');
            while(alphabetMaps.get(ch) <= 0) {
                idx = (int) (Math.random() * 26);
                ch = (char) (idx + 'A');
            }
            res[i] = "" + ch;
        }
        return res;
    }

    int getScore(char ch) {
        return alphabetScore.get(ch);
    }

    void reduceAlphabetMaps(char ch) {
        size--;
        Integer temp = alphabetMaps.get(ch);
        if (temp != null && temp > 0) {
            alphabetMaps.put(ch, temp - 1);
        }
    }

    int getLeftNums() {
        return size;
    }

    public int getMaxTurn() {
        return maxTurn;
    }

    Map<Character, Integer> getAlphabetFreq() {
        return this.alphabetMaps;
    }

    Map<Character, Integer> getAlphabetScore() {
        return this.alphabetScore;
    }


}
