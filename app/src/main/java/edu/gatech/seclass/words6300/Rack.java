package edu.gatech.seclass.words6300;

import android.util.Log;
import android.widget.Button;

public class Rack {
    private static final String TAG = "MyActivity";
    private Button Rack1;
    private Button Rack2;
    private Button Rack3;
    private Button Rack4;
    private Button Rack5;
    private Button Rack6;
    private Button Rack7;
    private char[] charArray = new char[7];

    Rack(Button Rack1, Button Rack2, Button Rack3, Button Rack4, Button Rack5, Button Rack6, Button Rack7) {
        this.Rack1 = Rack1;
        this.Rack2 = Rack2;
        this.Rack3 = Rack3;
        this.Rack4 = Rack4;
        this.Rack5 = Rack5;
        this.Rack6 = Rack6;
        this.Rack7 = Rack7;
    }

    void setRack(String[] array) {
        if (array[0] != null) {
            Rack1.setText(array[0]);
            charArray[0] = array[0].charAt(0);
        }
        if (array[1] != null) {
            Rack2.setText(array[1]);
            charArray[1] = array[1].charAt(0);
        }
        if (array[2] != null) {
            Rack3.setText(array[2]);
            charArray[2] = array[2].charAt(0);
        }
        if (array[3] != null) {
            Rack4.setText(array[3]);
            charArray[3] = array[3].charAt(0);
        }
        if (array[4] != null) {
            Rack5.setText(array[4]);
            charArray[4] = array[4].charAt(0);
        }
        if (array[5] != null) {
            Rack6.setText(array[5]);
            charArray[5] = array[5].charAt(0);
        }
        if (array[6] != null) {
            Rack7.setText(array[6]);
            charArray[6] = array[6].charAt(0);
        }
    }

    void setRack(String[] index, String[] array) {
        if (index[0] != null) {
            Rack1.setText(array[0]);
            charArray[0] = array[0].charAt(0);
            Log.v(TAG, "setRack1 = " + array[0]);
        }
        if (index[1] != null) {
            Rack2.setText(array[1]);
            charArray[1] = array[1].charAt(0);
            Log.v(TAG, "setRack2 = " + array[1]);
        }
        if (index[2] != null) {
            Rack3.setText(array[2]);
            charArray[2] = array[2].charAt(0);
            Log.v(TAG, "setRack3 = " + array[2]);
        }
        if (index[3] != null) {
            Rack4.setText(array[3]);
            charArray[3] = array[3].charAt(0);
            Log.v(TAG, "setRack4 = " + array[3]);
        }
        if (index[4] != null) {
            Rack5.setText(array[4]);
            charArray[4] = array[4].charAt(0);
            Log.v(TAG, "setRack5 = " + array[4]);
        }
        if (index[5] != null) {
            Rack6.setText(array[5]);
            charArray[5] = array[5].charAt(0);
            Log.v(TAG, "setRack6 = " + array[5]);
        }
        if (index[6] != null) {
            Rack7.setText(array[6]);
            charArray[6] = array[6].charAt(0);
            Log.v(TAG, "setRack7 = " + array[6]);
        }
    }

    void setRack(char[] array) {
        Rack1.setText("" + array[0]);
        charArray[0] = array[0];
        Rack2.setText("" + array[1]);
        charArray[1] = array[1];
        Rack3.setText("" + array[2]);
        charArray[2] = array[2];
        Rack4.setText("" + array[3]);
        charArray[3] = array[3];
        Rack5.setText("" + array[4]);
        charArray[4] = array[4];
        Rack6.setText("" + array[5]);
        charArray[5] = array[5];
        Rack7.setText("" + array[6]);
        charArray[6] = array[6];
    }

    void setRackSingle(int id, char ch) {
        if (id == 1) {
            Rack1.setText("" + ch);
            charArray[0] = ch;
        } else if (id == 2) {
            Rack2.setText("" + ch);
            charArray[1] = ch;
        } else if (id == 3) {
            Rack3.setText("" + ch);
            charArray[2] = ch;
        } else if (id == 4) {
            Rack4.setText("" + ch);
            charArray[3] = ch;
        } else if (id == 5) {
            Rack5.setText("" + ch);
            charArray[4] = ch;
        } else if (id == 6) {
            Rack6.setText("" + ch);
            charArray[5] = ch;
        } else if (id == 7) {
            Rack7.setText("" + ch);
            charArray[6] = ch;
        }
    }

    char[] getCharArray() {
        return charArray;
    }
}
