package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class game extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    EditText textScore;
    EditText inputWord;
    Pool pool;

    Button board1;
    Button board2;
    Button board3;
    Button board4;
    Board board;

    Button rack1;
    Button rack2;
    Button rack3;
    Button rack4;
    Button rack5;
    Button rack6;
    Button rack7;
    Rack rack;

    int currTurn = 0;
    int numSelectedBoard = 0;
    int numSelectedRack = 0;
    int currScore = 0;
    int totalScore = 0;
    char chosenBoard;
    int chosenBoardId = 0;
    DataHolder dataHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        textScore = (EditText) findViewById(R.id.Score);
        textScore.setEnabled(false);

        inputWord = (EditText) findViewById(R.id.inputWord);

        pool = new Pool();

        board1 = findViewById(R.id.Board1);
        board2 = findViewById(R.id.Board2);
        board3 = findViewById(R.id.Board3);
        board4 = findViewById(R.id.Board4);
        board = new Board(board1, board2, board3, board4);

        rack1 = findViewById(R.id.Rack1);
        rack2 = findViewById(R.id.Rack2);
        rack3 = findViewById(R.id.Rack3);
        rack4 = findViewById(R.id.Rack4);
        rack5 = findViewById(R.id.Rack5);
        rack6 = findViewById(R.id.Rack6);
        rack7 = findViewById(R.id.Rack7);
        rack = new Rack(rack1, rack2, rack3, rack4, rack5, rack6, rack7);
        rack.setRack(pool.generateRack());
        dataHolder = DataHolder.getInstance();

        Log.v(TAG, "maxTurn = " + pool.getMaxTurn());
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.Enter) {
            if (numSelectedBoard > 1) {
                inputWord.setText("");
                inputWord.setError("Only one letter from Board");
                numSelectedBoard = 0;
                currScore = 0;
            } else if (numSelectedBoard == 0) {
                inputWord.setText("");
                inputWord.setError("One letter from Board");
                numSelectedBoard = 0;
                currScore = 0;
            } else if (numSelectedRack == 0) {
                inputWord.setText("");
                inputWord.setError("No letter from Rack");
                numSelectedBoard = 0;
                currScore = 0;
            } else if (pool.getLeftNums() > 7 && currTurn < pool.getMaxTurn()) {
                String input = inputWord.getText().toString();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                        currScore += pool.getScore(input.charAt(i));
                        if (input.charAt(i) != chosenBoard) {
                            pool.reduceAlphabetMaps(input.charAt(i));
                        }
                    } else {
                        inputWord.setError("Invalid Input");
                    }

                }

                currTurn++;
                totalScore += currScore;
                textScore.setText(Integer.toString(totalScore));
                inputWord.setText("");
                numSelectedBoard = 0;
                currScore = 0;

                if (pool.getLeftNums() > 7 && currTurn < pool.getMaxTurn()) {
                    char[] chosenRack = new char[input.length() - 1];
                    int j = 0;
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != chosenBoard) {
                            chosenRack[j++] = input.charAt(i);
                        }
                    }
                    int idx = (int) (Math.random() * chosenRack.length);
                    board.setBoard(chosenBoardId, chosenRack[idx]);
                    rack.setRack(pool.generateRack());
                } else {
                    WriteToJSON.WriteJSON(dataHolder.getCurrID(), totalScore);
                    textScore.setText("Final Score : " + Integer.toString(totalScore));
                }
            } else if (pool.getLeftNums() <= 7 ) {
                textScore.setText("Final Score : " + Integer.toString(totalScore));
                inputWord.setError("Run out of letters");
            } else {
                textScore.setText("Final Score : " + Integer.toString(totalScore));
                inputWord.setError("Reach max turns");
            }
        }


        if (view.getId() == R.id.Board1) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 1;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Board2) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 2;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Board3) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 3;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Board4) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 4;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack1) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack2) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack3) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack4) {
            Button button = (Button) view;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack5) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack6) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        } else if (view.getId() == R.id.Rack7) {
            Button button = (Button) view;
            numSelectedRack++;
            String s = button.getText().toString();
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
        }

        if (view.getId() == R.id.Swap) {
            rack.setRack(pool.generateRack());
        }

        if (view.getId() == R.id.leave) {
            setContentView(R.layout.activity_main);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }
}
