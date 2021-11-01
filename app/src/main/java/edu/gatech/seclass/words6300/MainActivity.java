package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataHolder = DataHolder.getInstance();
        if (dataHolder.getGameStatus()) {
            Toast.makeText(this, "Your previous game status have been cached", Toast.LENGTH_LONG).show();
        }
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.play) {
            Intent intent = new Intent(this, game.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.Settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.view_stats) {
            Intent intent = new Intent(this, Stats.class);
            startActivity(intent);
        }
    }
}






/*
package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editText, editScore;
    Button btnAddData;
    private Button viewStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //click to Statistics main page
        viewStatistics = (Button) findViewById(R.id.viewStatistics);

        viewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewStatisticsActivity();


            }
        });
    }


    private void viewStatisticsActivity(){
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.play) {
            Intent intent = new Intent(this, game.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.Settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.viewStatistics){
            Intent intent = new Intent (this, Stats.class);
            startActivity(intent);
        }

    }
}



package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button viewStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //click to Statistics main page
        viewStatistics = (Button) findViewById(R.id.viewStatistics);

        viewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewStatisticsActivity();


            }
        });
    }

    private void viewStatisticsActivity(){
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.play) {
            Intent intent = new Intent(this, game.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.Settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.viewStatistics) {
            Intent intent = new Intent(this, Stats.class);
            startActivity(intent);
        }
    }
}
*/

