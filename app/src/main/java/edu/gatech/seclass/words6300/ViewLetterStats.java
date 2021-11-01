package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewLetterStats extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<Letter> letterStats;
    ListView listview_;
    //private LetterAdapter mAdapter;
    Letter letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        myDB = new DatabaseHelper(this);

        Log.d("check ListView", "listview works fine now");

        letterStats = new ArrayList<Letter>();
        Cursor data = myDB.getLetterStatistics();
       // letterStats.add(new Letter("A", 1,2, 0.9));
       // letterStats.add(new Letter("B", 2,2, 0.99));
       // Toast.makeText(this, String.valueOf(letterStats.get(0)), Toast.LENGTH_LONG).show();

      //  LetterAdapter mAdapter = new LetterAdapter(this, R.layout.letterlayout, letterStats);
      //  listview_ = (ListView) findViewById(R.id.listviewTest);

     //   listview_.setAdapter(mAdapter);
        int numRows = data.getCount();
        if (numRows == 0){
            Toast.makeText(this, "There is nothing in this database!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "There is something in this database!", Toast.LENGTH_LONG).show();
                letter = new Letter("letter", "Played", "Back_to_pool", "percent_played");
                letterStats.add(letter);
                while(data.moveToNext()){
                    letter = new Letter(data.getString(0), String.valueOf(data.getInt(1)), String.valueOf(data.getInt(2)), Double.toString(data.getDouble(3)));
                    letterStats.add(letter);
                }
                LetterAdapter adapter = new LetterAdapter(this, R.layout.letterlayout, letterStats);
                listview_ = (ListView) findViewById(R.id.listviewTest);
                //listview_.AddHeaderView(headerView);
                listview_.setAdapter(adapter);
    }
}}
