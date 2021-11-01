package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    int maxTurn = 0;
    char chosenBoard;
    int chosenBoardId = 0;
    DataHolder dataHolder;

    String[] letterSwap = new String[7];


    Pool setting;
    //
    DatabaseHelper myDb;
    ArrayList<Word> arraylist = new ArrayList<>();

    private Map<Character, Integer> map1;
    private Map<Character, Integer> map2;

    private Map<Character, Integer> alphabetFreq;
    private Map<Character, Integer> alphabetScore;

    private HashMap<String, Integer> letterPlayedMap;
    private HashMap<String, Integer> letterDrawnMap;
    // fix bug 1.a, initiage letter pool hashmap to store number of letters back to pool
    private HashMap<String, Integer> letterPoolMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myDb = new DatabaseHelper(this);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        dataHolder = DataHolder.getInstance();

        // true : game is running
        if (dataHolder.getGameStatus()) {
            pool = dataHolder.getPool();

            board1 = findViewById(R.id.Board1);
            board2 = findViewById(R.id.Board2);
            board3 = findViewById(R.id.Board3);
            board4 = findViewById(R.id.Board4);
            board = new Board(board1, board2, board3, board4);
            board.setBoard(dataHolder.getBoardCharArray());

            rack1 = findViewById(R.id.Rack1);
            rack2 = findViewById(R.id.Rack2);
            rack3 = findViewById(R.id.Rack3);
            rack4 = findViewById(R.id.Rack4);
            rack5 = findViewById(R.id.Rack5);
            rack6 = findViewById(R.id.Rack6);
            rack7 = findViewById(R.id.Rack7);
            rack = new Rack(rack1, rack2, rack3, rack4, rack5, rack6, rack7);
            rack.setRack(dataHolder.getRackCharArray());

            currTurn = dataHolder.getCurrTurn();
            numSelectedBoard = dataHolder.getNumSelectedBoard();
            numSelectedRack = dataHolder.getNumSelectedRack();
            currScore = dataHolder.getCurrScore();
            totalScore = dataHolder.getTotalScore();
            chosenBoard = dataHolder.getChosenBoard();
            chosenBoardId = dataHolder.getChosenBoardId();
            maxTurn = pool.getMaxTurn();

            //get alphabet stats

            map1 = dataHolder.getSettingFreq();
            map2 = dataHolder.getSettingScore();

            alphabetFreq = new HashMap<Character, Integer>();
            alphabetScore = new HashMap<Character, Integer>();

            for (Map.Entry<Character,Integer> entry : map1.entrySet()){
                this.alphabetFreq.put(entry.getKey(), entry.getValue());
            }

            map2 = pool.getAlphabetScore();

            for (Map.Entry<Character,Integer> entry : map2.entrySet()){
                this.alphabetScore.put(entry.getKey(), entry.getValue());
            }

            //get letter stats
            letterPlayedMap = dataHolder.getLetterPlayedMap();
            if (letterPlayedMap == null){Toast.makeText(this, "letterPlayMapnotnull !", Toast.LENGTH_LONG).show();}

            letterDrawnMap = dataHolder.getLetterDrawnMap();
            letterPoolMap = dataHolder.getLetterPoolMap();
            Log.v(TAG, "letterdrawn = " + dataHolder.getLetterDrawnMap());

            textScore = (EditText) findViewById(R.id.Score);
            textScore.setEnabled(false);
            textScore.setText(Integer.toString(totalScore));
            inputWord = (EditText) findViewById(R.id.inputWord);
            Toast.makeText(this, "Resume the game !", Toast.LENGTH_LONG).show();

        } else {
            textScore = (EditText) findViewById(R.id.Score);
            textScore.setEnabled(false);
            inputWord = (EditText) findViewById(R.id.inputWord);

            pool = new Pool();

            maxTurn = pool.getMaxTurn();

            map1 = pool.getAlphabetFreq();
            map2 = pool.getAlphabetScore();

            alphabetFreq = new HashMap<Character, Integer>();
            alphabetScore = new HashMap<Character, Integer>();

            for (Map.Entry<Character,Integer> entry : map1.entrySet()){
                this.alphabetFreq.put(entry.getKey(), entry.getValue());
            }

            map2 = pool.getAlphabetScore();

            for (Map.Entry<Character,Integer> entry : map2.entrySet()){
                this.alphabetScore.put(entry.getKey(), entry.getValue());
            }

            board1 = findViewById(R.id.Board1);
            board2 = findViewById(R.id.Board2);
            board3 = findViewById(R.id.Board3);
            board4 = findViewById(R.id.Board4);
            board = new Board(board1, board2, board3, board4);
            pool.reduceAlphabetMaps(board1.getText().toString().charAt(0));
            pool.reduceAlphabetMaps(board2.getText().toString().charAt(0));
            pool.reduceAlphabetMaps(board3.getText().toString().charAt(0));
            pool.reduceAlphabetMaps(board4.getText().toString().charAt(0));

            rack1 = findViewById(R.id.Rack1);
            rack2 = findViewById(R.id.Rack2);
            rack3 = findViewById(R.id.Rack3);
            rack4 = findViewById(R.id.Rack4);
            rack5 = findViewById(R.id.Rack5);
            rack6 = findViewById(R.id.Rack6);
            rack7 = findViewById(R.id.Rack7);
            rack = new Rack(rack1, rack2, rack3, rack4, rack5, rack6, rack7);
            rack.setRack(pool.generateRack());
            // fix bug #17 - initialize letter stats hashmaps
            letterPlayedMap = new HashMap<>();
            letterDrawnMap = new HashMap<>();
            // fix bug 1.a, initiage letter pool hashmap to store number of letters back to pool
            letterPoolMap = new HashMap<>();
            //fix bug #13 - count letter as drawn in board and rack  in the new game
            updateHashMap();
            Log.v(TAG, "letterdrawn_initialize = " + letterDrawnMap);
            dataHolder.setGameStatus(true);
            Toast.makeText(this, "This is a new game !", Toast.LENGTH_LONG).show();
        }

        //myDb.addSetting(setting);

       // updateHashMap();
       // if (checkLetterStatisticsData() == 0){
            //InsertLetterStatisticsDrawnToTable(letterDrawnMap);
       //     InsertDefaultStatisticsToTable();
       // }
        //InsertDefaultStatisticsToTable();


        Log.v(TAG, "maxTurn = " + pool.getMaxTurn());
    }


    public void handleClick(View view) {
        if (view.getId() == R.id.Enter) {
            if (numSelectedBoard > 1) {
                inputWord.setText("");
                inputWord.setError("Only one letter from Board");
                numSelectedBoard = 0;
                numSelectedRack = 0;
                Arrays.fill(letterSwap, null);
                currScore = 0;
            } else if (numSelectedBoard == 0) {
                inputWord.setText("");
                inputWord.setError("One letter from Board");
                numSelectedBoard = 0;
                numSelectedRack = 0;
                Arrays.fill(letterSwap, null);
                currScore = 0;
            } else if (numSelectedRack == 0) {
                inputWord.setText("");
                inputWord.setError("No letter from Rack");
                numSelectedBoard = 0;
                numSelectedRack = 0;
                Arrays.fill(letterSwap, null);
                currScore = 0;
            } else if (pool.getLeftNums() > 7 && currTurn < pool.getMaxTurn()) {
                String input = inputWord.getText().toString();
                // fix bug 12
                for (int i = 0; i < arraylist.size(); i++) {
                    if (arraylist.get(i).getWord().equals(input)) {
                        inputWord.setText("");
                        inputWord.setError("Duplicated words");
                        numSelectedBoard = 0;
                        currScore = 0;
                        return;
                    }
                }
                // fix bug 12
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
                arraylist.add(new Word(inputWord.getText().toString()));
                Log.v(TAG, "letter_WordPlayed " + inputWord.getText().toString());

                // fix bug # 13 - count letter played when the word is played successfully - count board played
                char[] inputArray = input.toCharArray();
                for (int i=0; i<inputArray.length; i++){
                    String s =  Character.toString(inputArray[i]);
                    int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
                    letterPlayedMap.put(s, count + 1);
                    Log.v(TAG, "letterPlayed = " + letterPlayedMap);
                }

                Log.v(TAG, "LeftNum : = " + pool.getLeftNums());
                Toast.makeText(this, "Letter in pool : " + pool.getLeftNums(), Toast.LENGTH_LONG).show();

                inputWord.setText("");
                numSelectedBoard = 0;
                numSelectedRack = 0;
                currScore = 0;

                if (pool.getLeftNums() > 7 && currTurn < pool.getMaxTurn()) {

                    Log.v(TAG, "currTurn = " + currTurn);
                    char[] chosenRack = new char[input.length() - 1];
                    int j = 0;
                    // add a copyCount to solve bug #4
                    int copyCount = 0;


                   // String s_board = Character.toString(chosenBoard);
                    //int count = letterPlayedMap.containsKey(s_board) ? letterPlayedMap.get(s_board) : 0;
                    //letterPlayedMap.put(s_board, count + 1);
                    //Log.v(TAG, "letterPlayed_board = " + letterPlayedMap);

                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) != chosenBoard) {
                            chosenRack[j++] = input.charAt(i);
                            // fix bug #13 - count rack letter played
                           // String s_rack = Character.toString(input.charAt(i));
                           // count = letterPlayedMap.containsKey(s_rack) ? letterPlayedMap.get(s_rack) : 0;
                           // letterPlayedMap.put(s_rack, count + 1);
                           // Log.v(TAG, "letterPlayed_rack = " + letterPlayedMap);
                        }
                        else if (copyCount == 0) {
                            copyCount = 1;
                        }
                        else {
                            chosenRack[j++] = input.charAt(i);
                            // fix bug #13 - this need to be checked
                           // String s_rack = Character.toString(input.charAt(i));
                           // count = letterPlayedMap.containsKey(s_rack) ? letterPlayedMap.get(s_rack) : 0;
                           // letterPlayedMap.put(s_rack, count + 1);
                           // Log.v(TAG, "letterPlayed_rack = " + letterPlayedMap);
                        }

                    }
                    int idx = (int) (Math.random() * (chosenRack.length));
                    board.setBoard(chosenBoardId, chosenRack[idx]);
                   // updateHashMap();
                    // fix bug # 13 - when the played board letter was replaced with rack letter, it is counted as drawn once
                    int count = letterDrawnMap.containsKey(Character.toString(chosenRack[idx])) ? letterDrawnMap.get(Character.toString(chosenRack[idx])) : 0;
                    letterDrawnMap.put(Character.toString(chosenRack[idx]), count + 1);
                    Log.v(TAG, "letterdrawn_board = " + letterDrawnMap);
                    String[] letterRefilled = pool.generateRack();
                    rack.setRack(letterSwap, letterRefilled);


                    Log.v(TAG, "letterSwapRefilled = " + Arrays.toString(letterSwap));
                    Log.v(TAG, "letterRefilled = " + Arrays.toString(letterRefilled));


                    //updateHashMap();
                    // fix bug # 13 - when the rack letter was replaced after playing a word with pool letter, the letters are counted as "drawn"


                    for (int i=0; i < letterSwap.length; i++ )
                    {
                        String letterselected = letterSwap[i];
                        if (letterselected != null){

                            String letterToRack = letterRefilled[i];



                        //int count = letterPoolMap.containsKey(letterToPool) ? letterPoolMap.get(letterToPool) : 0;
                        //letterPoolMap.put(letterToPool, count + 1);

                            count = letterDrawnMap.containsKey(letterToRack) ? letterDrawnMap.get(letterToRack) : 0;
                            letterDrawnMap.put(letterToRack, count + 1);
                            Log.v(TAG, "letterdrawn_rack = " + letterDrawnMap);}


                    }

                    Arrays.fill(letterSwap, null);


                } else {
                    //at end of the game, update database;
                    UpdateWordTable(arraylist);

                    // fix bug #13 insert directly
                    InsertLetterStatisticsDrawnToTable(letterDrawnMap);
                    InsertLetterStatisticsToTable(letterPlayedMap);
                    InsertLetterStatisticsPoolToTable(letterPoolMap);
                    if (pool.getLeftNums() <= 7 ) {
                        totalScore += 10;
                    }
                    myDb.addGame(totalScore,currTurn,maxTurn,alphabetFreq,alphabetScore);
                    textScore.setText("Final Score : " + Integer.toString(totalScore));
                    dataHolder.setGameStatus(false);
                }
            } else if (pool.getLeftNums() <= 7 ) {
                textScore.setText("Final Score : " + Integer.toString(totalScore));
                inputWord.setError("Run out of letters");
                dataHolder.setGameStatus(false);
            } else {
                textScore.setText("Final Score : " + Integer.toString(totalScore));
                inputWord.setError("Reach max turns");
                dataHolder.setGameStatus(false);
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
            //int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
            //letterPlayedMap.put(s, count + 1);
        } else if (view.getId() == R.id.Board2) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 2;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
            //int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
            //letterPlayedMap.put(s, count + 1);
        } else if (view.getId() == R.id.Board3) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 3;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
            //int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
            //letterPlayedMap.put(s, count + 1);
        } else if (view.getId() == R.id.Board4) {
            inputWord.setError(null);
            numSelectedBoard++;
            Button button = (Button) view;
            String s = button.getText().toString();
            chosenBoard = s.charAt(0);
            chosenBoardId = 4;
            String prev = inputWord.getText().toString();
            inputWord.setText(prev + s);
            //letterPlayedMap.put(s, 1);
        } else if (view.getId() == R.id.Rack1) {
            if (letterSwap[0] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[0] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack2) {
            if (letterSwap[1] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[1] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack3) {
            if (letterSwap[2] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[2] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack4) {
            if (letterSwap[3] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[3] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack5) {
            if (letterSwap[4] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[4] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack6) {
            if (letterSwap[5] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[5] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        } else if (view.getId() == R.id.Rack7) {
            if (letterSwap[6] != null) {
                Toast.makeText(this, "The element have been used!", Toast.LENGTH_LONG).show();
            } else {
                Button button = (Button) view;
                numSelectedRack++;
                String s = button.getText().toString();
                String prev = inputWord.getText().toString();
                inputWord.setText(prev + s);
                letterSwap[6] = s;
             //   int count = letterPlayedMap.containsKey(s) ? letterPlayedMap.get(s) : 0;
             //   letterPlayedMap.put(s, count + 1);
            }
        }

        if (view.getId() == R.id.Swap) {
            if(currTurn < pool.getMaxTurn()) {
                if (numSelectedRack > 0) {
                    String[] poolToRack = pool.generateRack();
                    rack.setRack(letterSwap, poolToRack);
                    // fix bug 1.a # update letter back to pool hashmap
                    for (int i = 0; i < letterSwap.length; i++) {
                        String letterToPool = letterSwap[i];
                        if (letterToPool != null) {
                            String letterToRack = poolToRack[i];

                            int count = letterPoolMap.containsKey(letterToPool) ? letterPoolMap.get(letterToPool) : 0;
                            letterPoolMap.put(letterToPool, count + 1);
                            Log.v(TAG, "letterpool_swap = " + letterPoolMap);

                            count = letterDrawnMap.containsKey(letterToRack) ? letterDrawnMap.get(letterToRack) : 0;
                            letterDrawnMap.put(letterToRack, count + 1);
                            Log.v(TAG, "letterdrawn_swap = " + letterDrawnMap);
                        }


                    }
                    ;


                    Arrays.fill(letterSwap, null);
                    inputWord.setText("");
                    numSelectedRack = 0;
                    currTurn++;
                    if (currTurn >= pool.getMaxTurn()) {
                        textScore.setText("Final Score : " + Integer.toString(totalScore));
                        dataHolder.setGameStatus(false);


                        //at end of the game, update database
                        //update game table
                        myDb.addGame(totalScore, currTurn, maxTurn, alphabetFreq, alphabetScore);

                        //Update letter stats
                        InsertLetterStatisticsDrawnToTable(letterDrawnMap);
                        InsertLetterStatisticsToTable(letterPlayedMap);
                        InsertLetterStatisticsPoolToTable(letterPoolMap);


                    }
                }
                else {
                    Toast.makeText(this, "No rack selected", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Please start a new game", Toast.LENGTH_LONG).show();
            }
        }

        if (view.getId() == R.id.leave) {
            dataHolder.setPool(pool);
            dataHolder.setBoardCharArray(board.getCharArray());
            dataHolder.setRackCharArray(rack.getCharArray());
            dataHolder.setGameParameters(currTurn, numSelectedBoard, numSelectedRack, currScore,
                    totalScore, chosenBoard, chosenBoardId);

            // fix bug #17 save curent letter stats
            dataHolder.setLetterPlayedMap(letterPlayedMap);
            dataHolder.setLetterPoolMap(letterPoolMap);
            dataHolder.setLetterDrawnMap(letterDrawnMap);

            dataHolder.saveAlphabetScore((HashMap<Character, Integer>) alphabetScore);
            dataHolder.saveAlphabetFreq((HashMap<Character, Integer>) alphabetFreq);


            setContentView(R.layout.activity_main);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        dataHolder.setPool(pool);
        dataHolder.setBoardCharArray(board.getCharArray());
        dataHolder.setRackCharArray(rack.getCharArray());
        dataHolder.setGameParameters(currTurn, numSelectedBoard, numSelectedRack, currScore,
                totalScore, chosenBoard, chosenBoardId);

        // fix bug #17 save curent letter stats
        dataHolder.setLetterPlayedMap(letterPlayedMap);
        dataHolder.setLetterPoolMap(letterPoolMap);
        dataHolder.setLetterDrawnMap(letterDrawnMap);

        dataHolder.saveAlphabetScore((HashMap<Character, Integer>) alphabetScore);
        dataHolder.saveAlphabetFreq((HashMap<Character, Integer>) alphabetFreq);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





    public void UpdateWordTable(ArrayList wordlist){
        for (Object i : wordlist) {
            boolean isInserted = myDb.addWord((Word) i);
            if(isInserted == true)
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
        }

    }




    public void UpdateLetterStatisticsTable(HashMap<String, Integer> letterPlayedMap){
        for (Map.Entry<String, Integer> entry : letterPlayedMap.entrySet()) {
            boolean isUpdated = myDb.updateLetterStatistics(entry.getKey(), entry.getValue());
            if (isUpdated == true){
                Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Updated",Toast.LENGTH_LONG).show();
            }


        }
    }
    public void UpdateLetterStatisticsTableDrawn(HashMap<String, Integer> letterDrawnMap){
        for (Map.Entry<String, Integer> entry : letterDrawnMap.entrySet()) {
            boolean isUpdated = myDb.updateLetterStatisticsDrawn(entry.getKey(), entry.getValue());
            if (isUpdated == true){
                Toast.makeText(this,"Data Updated",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Updated",Toast.LENGTH_LONG).show();
            }


        }
    }

    // insert all statistic to table with all value as 0
    public void InsertDefaultStatisticsToTable(){
        List<String> LetterDefaultList = new ArrayList<>();


        // Button button = (Button) view;
        //String s = button.getText().toString();
        // add 4 different values to list
        LetterDefaultList.add("A");
        LetterDefaultList.add("B");
        LetterDefaultList.add("C");
        LetterDefaultList.add("D");
        LetterDefaultList.add("E");
        LetterDefaultList.add("F");
        LetterDefaultList.add("G");
        LetterDefaultList.add("H");
        LetterDefaultList.add("I");
        LetterDefaultList.add("J");
        LetterDefaultList.add("K");
        LetterDefaultList.add("L");
        LetterDefaultList.add("M");
        LetterDefaultList.add("N");
        LetterDefaultList.add("O");
        LetterDefaultList.add("P");
        LetterDefaultList.add("Q");
        LetterDefaultList.add("R");
        LetterDefaultList.add("S");
        LetterDefaultList.add("T");
        LetterDefaultList.add("U");
        LetterDefaultList.add("V");
        LetterDefaultList.add("W");
        LetterDefaultList.add("X");
        LetterDefaultList.add("Y");
        LetterDefaultList.add("Z");


        for (String letter : LetterDefaultList) {
            boolean isInserted = myDb.addLetterStatistics( letter, 0,0,0);
            if (isInserted == true){
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void InsertLetterStatisticsToTable(HashMap<String, Integer> letterPlayedMap) {
        for (Map.Entry<String, Integer> entry : letterPlayedMap.entrySet()) {
            boolean isInserted = myDb.addLetterStatistics( entry.getKey(), entry.getValue(),0,0);
            if (isInserted == true){
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void InsertLetterStatisticsDrawnToTable(HashMap<String, Integer> letterDrawnMap) {
        for (Map.Entry<String, Integer> entry : letterDrawnMap.entrySet()) {
            boolean isInserted = myDb.addLetterStatistics( entry.getKey(), 0,0,entry.getValue());
            if (isInserted == true){
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }

        }
    }


    public void InsertLetterStatisticsPoolToTable(HashMap<String, Integer> letterPoolMap) {
        for (Map.Entry<String, Integer> entry : letterPoolMap.entrySet()) {
            boolean isInserted = myDb.addLetterStatistics( entry.getKey(), 0,entry.getValue(),0);
            if (isInserted == true){
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }

        }
    }

    public int checkLetterStatisticsData(){
        Cursor res = myDb.getLetterStatistics();
        return res.getCount();

    }

    public void updateHashMap(){
        //create list
        List<Button> brList = new ArrayList<>();


       // Button button = (Button) view;
        //String s = button.getText().toString();
        // add 4 different values to list
        brList.add(board1);
        brList.add(board2);
        brList.add(board3);
        brList.add(board4);
        brList.add(rack1);
        brList.add(rack2);
        brList.add(rack3);
        brList.add(rack4);
        brList.add(rack5);
        brList.add(rack6);
        brList.add(rack7);

        for (Button button : brList) {
            String s = button.getText().toString();
            int count = letterDrawnMap.containsKey(s) ? letterDrawnMap.get(s) : 0;
            letterDrawnMap.put(s, count + 1);
        }
    }
}
