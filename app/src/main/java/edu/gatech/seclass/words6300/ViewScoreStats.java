package edu.gatech.seclass.words6300;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ViewScoreStats extends AppCompatActivity {
    DatabaseHelper myDb;
    ArrayList<GameRecord> gameRecordArrayList = new ArrayList<GameRecord>();
    ListView gameScoreListView;
    GameRecord gameRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score_stats);
        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getGameData();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(this, "Nothing found!", Toast.LENGTH_LONG).show();
            return;
        }
        while (res.moveToNext()) {
            gameRecord = new GameRecord(res.getInt(0), res.getInt(1), res.getInt(2), res.getFloat(3), res.getInt(4), res.getBlob(5), res.getBlob(6));
            gameRecordArrayList.add(gameRecord);
        }

        GameScoreAdapter adapter = new GameScoreAdapter(this, R.layout.adapter_view_gamescore_layout, gameRecordArrayList);
        gameScoreListView = findViewById(R.id.gameScore);
        gameScoreListView.setAdapter(adapter);

        //add listener to listview
        gameScoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GameRecord selected_game = gameRecordArrayList.get(position);
                byte[] alphabetfreq;
                byte[] alphabetscore;

                StringBuffer setting_buffer = new StringBuffer();
                setting_buffer.append("Maximum number of Turns :" + selected_game.getMax_turn() + "\n\n");

                alphabetfreq = selected_game.getAlphabetFreq();
                String value1 = new String(alphabetfreq);

                alphabetscore = selected_game.getAlphabetScore();
                String value2 = new String(alphabetscore);

                String[] buff1 = value1.substring(1,value1.length()-1).split(",");
                String[] buff2 = value2.substring(1,value1.length()-1).split(",");

                for(int i=0;i<buff1.length;i++){
                    String[] freq_buff = buff1[i].split(":");
                    String[] score_buff = buff2[i].split(":");
                    setting_buffer.append("Letter :" + freq_buff[0].charAt(1)+"\n");
                    setting_buffer.append("Frequency :" + freq_buff[1]+"\n");
                    setting_buffer.append("Score :" + score_buff[1]+"\n\n");
                }




                /*
                while (value.moveToNext()) {
                    letter_buffer.append("Letter:"+ res.getString(0)+"\n");
                    letter_buffer.append("Num of played :"+ res.getString(1)+"\n");
                    letter_buffer.append("Num of back to pool:"+ res.getString(2)+"\n");
                    letter_buffer.append("Use percentage:"+ res.getString(3)+"\n\n");
                }

                 */

                showMessage("Game setting: ",setting_buffer.toString());

                //Toast.makeText(ViewScoreStats.this, "clicked item:"+game_id,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}