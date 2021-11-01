package edu.gatech.seclass.words6300;

import android.database.Cursor;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final String TAG = "MyActivity";
    private Button board1;
    private Button board2;
    private Button board3;
    private Button board4;
    private char[] charArray = new char[4];
    HashMap<String, Integer> letterDrawn = new HashMap<>();

    Board(Button board1, Button board2, Button board3, Button board4) {
        this.board1 = board1;
        this.board2 = board2;
        this.board3 = board3;
        this.board4 = board4;
        char ch = (char) ((int) (Math.random() * 26) + 'A');
        board1.setText("" + ch);
        charArray[0] = ch;
        //int count = letterDrawn.containsKey(ch) ? letterDrawn.get(ch) : 0;
        //letterDrawn.put(String.valueOf(ch), count + 1);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board2.setText("" + ch);
        charArray[1] = ch;
        //count = letterDrawn.containsKey(ch) ? letterDrawn.get(ch) : 0;
        //letterDrawn.put(String.valueOf(ch), count + 1);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board3.setText("" + ch);
        charArray[2] = ch;
        //count = letterDrawn.containsKey(ch) ? letterDrawn.get(ch) : 0;
        //letterDrawn.put(String.valueOf(ch), count + 1);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board4.setText("" + ch);
        charArray[3] = ch;
        //count = letterDrawn.containsKey(ch) ? letterDrawn.get(ch) : 0;
        //letterDrawn.put(String.valueOf(ch), count + 1);
    }

    void setBoard(int id, char ch) {
        if (id == 1) {
            board1.setText("" + ch);
            charArray[0] = ch;
        } else if (id == 2) {
            board2.setText("" + ch);
            charArray[1] = ch;
        } else if (id == 3) {
            board3.setText("" + ch);
            charArray[2] = ch;
        } else if (id == 4) {
            board4.setText("" + ch);
            charArray[3] = ch;
        }
    }

    void setBoard(char[] array) {
        board1.setText("" + array[0]);
        charArray[0] = array[0];
        board2.setText("" + array[1]);
        charArray[1] = array[1];
        board3.setText("" + array[2]);
        charArray[2] = array[2];
        board4.setText("" + array[3]);
        charArray[3] = array[3];
    }

    char[] getCharArray() {
        return charArray;
    }



}
