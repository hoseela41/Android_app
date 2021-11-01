package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class settings extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    Pool pool;
    EditText maxTurnInput;
    EditText freqLetter;
    EditText freqNum;
    EditText scoreLetter;
    EditText scoreNum;
    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        Intent intent = getIntent();
        maxTurnInput = (EditText) findViewById(R.id.maxTurnInput);
        freqLetter = (EditText) findViewById(R.id.SetFreqLetter);
        freqNum = (EditText) findViewById(R.id.SetFreqNum);
        scoreLetter = (EditText) findViewById(R.id.SetScoreLetter);
        scoreNum = (EditText) findViewById(R.id.SetScoreNum);
        dataHolder = DataHolder.getInstance();

    }

    public void handleClick(View view) {
        if (view.getId() == R.id.SaveMaxTurn) {
            int maxTurn = Integer.parseInt(maxTurnInput.getText().toString());
            dataHolder.setMaxTurn(maxTurn);
        }

        if (view.getId() == R.id.SetFreqButton) {
            char letter = freqLetter.getText().toString().charAt(0);
            int num = Integer.parseInt(freqNum.getText().toString());
            dataHolder.setFreq(letter, num);
        }

        if (view.getId() == R.id.SetScoreButton) {
            char letter = scoreLetter.getText().toString().charAt(0);
            int score = Integer.parseInt(scoreNum.getText().toString());
            dataHolder.setScore(letter, score);
        }
    }
}
