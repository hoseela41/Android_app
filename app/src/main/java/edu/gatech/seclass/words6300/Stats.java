package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {
    Button gameStats;
    Button wordBank;
    Button letterstats;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        myDb = new DatabaseHelper(this);
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.game_score_stats) {
            Intent intent = new Intent(this, ViewScoreStats.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.word_bank) {
            Intent intent = new Intent(this, ViewWordBank.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.letter_stats) {
            Intent intent = new Intent(this, ViewLetterStats.class);
            startActivity(intent);
        }
    }

}


