package edu.gatech.seclass.words6300;

import android.widget.Button;

public class Rack {
    private Button Rack1;
    private Button Rack2;
    private Button Rack3;
    private Button Rack4;
    private Button Rack5;
    private Button Rack6;
    private Button Rack7;

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
        Rack1.setText(array[0]);
        Rack2.setText(array[1]);
        Rack3.setText(array[2]);
        Rack4.setText(array[3]);
        Rack5.setText(array[4]);
        Rack6.setText(array[5]);
        Rack7.setText(array[6]);
    }
}
