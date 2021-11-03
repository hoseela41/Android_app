package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.PriorityQueue;

public class ViewScoreStats extends AppCompatActivity {
    EditText showScore1;
    EditText showScore2;
    EditText showScore3;
    EditText showSetting;
    PriorityQueue<Map.Entry<String, Integer>> minHeap;
    Map.Entry<String, Integer> mapEntry1;
    Map.Entry<String, Integer> mapEntry2;
    Map.Entry<String, Integer> mapEntry3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score_stats);

        minHeap = WriteToJSON.getScoreStats();

        mapEntry1 = minHeap.poll();
        mapEntry2 = minHeap.poll();
        mapEntry3 = minHeap.poll();

        if (mapEntry1 != null) {
            showScore1 = (EditText) findViewById(R.id.viewScore1);
            showScore1.setText(mapEntry1.getKey() + "   " + mapEntry1.getValue());
        }
        if (mapEntry2 != null) {
            showScore2 = (EditText) findViewById(R.id.viewScore2);
            showScore2.setText(mapEntry2.getKey() + "   " + mapEntry2.getValue());
        }
        if (mapEntry3 != null) {
            showScore3 = (EditText) findViewById(R.id.viewScore3);
            showScore3.setText(mapEntry3.getKey() + "   " + mapEntry3.getValue());
        }
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.getSetting) {
            String s = null;
            if (mapEntry1 != null) {
                s = WriteToJSON.getSettingJSON(mapEntry1.getKey());
            }

            showSetting = findViewById(R.id.showSetting);
            showSetting.setText(s);
        }
    }
}
