package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "ScoreHistory.json";
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter =null;
    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataHolder = DataHolder.getInstance();
        dataHolder.setCurrDirectory(this.getFilesDir());

        File file = new File(this.getFilesDir(), FILE_NAME);
        if(!file.exists()) {
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        WriteToJSON.WriteJSON("yding318", 10);
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.EnterName) {
            EditText inputName = (EditText) findViewById(R.id.inputName);
            EditText showWelcome = (EditText) findViewById(R.id.showWelcome);
            String name = inputName.getText().toString();
            dataHolder.setCurrUserID(name);
            String welcomeMessage = WriteToJSON.WriteUserIDJSON(name);
            showWelcome.setText(welcomeMessage);
        }


        if (view.getId() == R.id.play) {
            Intent intent = new Intent(this, game.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.Settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.ViewScoreStats) {
            Intent intent = new Intent(this, ViewScoreStats.class);
            startActivity(intent);
        }
    }

}
