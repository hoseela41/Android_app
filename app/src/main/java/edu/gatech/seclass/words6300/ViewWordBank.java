package edu.gatech.seclass.words6300;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewWordBank extends AppCompatActivity {
    DatabaseHelper myDb;
    ArrayList<Word> wordList = new ArrayList<Word>();
    ListView wordListView;
    Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_word_bank);
        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getWordData();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(this, "Nothing found!", Toast.LENGTH_LONG).show();
            return;
        }
        while (res.moveToNext()) {
            word = new Word(res.getString(0), res.getInt(1));
            wordList.add(word);
        }

        WordBankAdapter adapter = new WordBankAdapter(this, R.layout.adapter_view_wordbank_layout, wordList);
        wordListView = findViewById(R.id.wordList);
        wordListView.setAdapter(adapter);
    }
}
