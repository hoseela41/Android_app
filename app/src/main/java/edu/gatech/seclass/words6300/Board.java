package edu.gatech.seclass.words6300;

import android.util.Log;
import android.widget.Button;

public class Board {
    private Button board1;
    private Button board2;
    private Button board3;
    private Button board4;

    Board(Button board1, Button board2, Button board3, Button board4) {
        this.board1 = board1;
        this.board2 = board2;
        this.board3 = board3;
        this.board4 = board4;
        char ch = (char) ((int) (Math.random() * 26) + 'A');
        board1.setText("" + ch);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board2.setText("" + ch);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board3.setText("" + ch);
        ch = (char) ((int) (Math.random() * 26) + 'A');
        board4.setText("" + ch);
    }

    void setBoard(int id, char ch) {
        if (id == 1) {
            board1.setText("" + ch);
        } else if (id == 2) {
            board2.setText("" + ch);
        } else if (id == 3) {
            board3.setText("" + ch);
        } else if (id == 4) {
            board4.setText("" + ch);
        }
    }
}
