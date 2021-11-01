package edu.gatech.seclass.words6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "stats";

    // WordBank table name
    private static final String TABLE_WORD = "word";


    // Words Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "word";
    private static final String KEY_SCORE = "score";

    // Game table name
    private static final String TABLE_GAME = "games";
    //Game table columns names
    private static final String GAME_ID = "game_id";
    private static final String FINAL_SCORE = "final_score";
    private static final String TURNS = "turn";
    private static final String MAX_TURN = "max_turn";
    private static final Object ALPHABETFREQ = "alphabetfreq";
    private static final Object ALPHABETSCORE = "alphabetscore";

    private static final String TABLE_Letter = "letter_statistics";
    private static final String TABLE_Letter2 = "letter_statistics2";

    //column names
    private static final String Letter_ID = "letter_id";
    private static final String Letter_Name = "letter";
    private static final String Letter_Played = "played_in_word";
    private static final String Letter_to_Pool = "back_to_pool";
    private static final String Letter_Percent = "percentChosen";
    private static final String Letter_Drawn = "drawn";
    //sql query to creating table in database



    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORD_TABLE = "CREATE TABLE " + TABLE_WORD + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                + KEY_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_WORD_TABLE);

        //Create game table
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_GAME + "(" + GAME_ID
                + " INTEGER PRIMARY KEY," + FINAL_SCORE + " TEXT,"
                + TURNS + " TEXT," + MAX_TURN + " TEXT," + ALPHABETFREQ + " BLOB," + ALPHABETSCORE + " BLOB" + ")";
        db.execSQL(CREATE_SETTING_TABLE);

        String CREATE_TABLE_Letter = "CREATE TABLE " + TABLE_Letter + " (" +
                Letter_ID + " INTEGER PRIMARY KEY," + Letter_Name + " Text,"+ Letter_Played + " INTEGER, "+Letter_to_Pool+" INTEGER, "+Letter_Drawn+" INTEGER, " + Letter_Percent + " Double)";
        db.execSQL(CREATE_TABLE_Letter);

        String CREATE_TABLE_Letter2 = "CREATE TABLE " + TABLE_Letter2 + " (" +
                Letter_ID + " INTEGER PRIMARY KEY," + Letter_Name + " Text," + Letter_Played + " INTEGER, "+Letter_to_Pool+" INTEGER, "+Letter_Drawn+" INTEGER)";
        db.execSQL(CREATE_TABLE_Letter2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Letter);
        // Creating tables again
        onCreate(db);
    }

    // Adding new word
    public boolean addWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getWord()); // Word

        // Inserting Row
        long result = db.insert(TABLE_WORD, null, values);
        if(result == -1)
            return false;
        else
            return true;
        //db.close(); // Closing database connection
    }
    // Adding game info
    public boolean addGame(int final_score, int turns, int maxTurn, Map alphabetFreq, Map alphabetScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(FINAL_SCORE,final_score);
        values.put(TURNS,turns);
        values.put(MAX_TURN,maxTurn);
        values.put((String) ALPHABETFREQ, gson.toJson(alphabetFreq).getBytes());
        values.put((String) ALPHABETSCORE, gson.toJson(alphabetScore).getBytes());
        // Inserting Row
        long result = db.insert(TABLE_GAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    //function for adding letter statistics
    public boolean addLetterStatistics( String letter, int letter_played, int letter_pool, int letter_drawn ) {
        SQLiteDatabase db = this.getWritableDatabase();

        //creating the contentValues object
        //read more here -> http://developer.android.com/reference/android/content/ContentValues.html
        ContentValues cv = new ContentValues();
        cv.put(Letter_Name, letter);
        cv.put(Letter_Played, letter_played);
        cv.put(Letter_to_Pool, letter_pool);
        cv.put(Letter_Percent, 1.0 * letter_played / letter_drawn);
        cv.put( Letter_Drawn, letter_drawn);

        //inserting the note to database
        long result = db.insert(TABLE_Letter, null, cv);


        if (result == -1){
            return false;
        } else {
            return true;
        }
        //closing the database connection
        //db.close();

        //see that all database connection stuff is inside this method
        //so we don't need to open and close db connection outside this class

    }


    //getting all statisitcs
    public Cursor getLetterStatistics() {
        //db.query is like normal sql query
        //cursor contains all notes
        SQLiteDatabase db = this.getWritableDatabase();
        //round to two decimal -  printf("%.2f", floatField)
        //Cursor c = db.rawQuery("SELECT " + Letter_Name + " ," + Letter_Played + " ," + Letter_to_Pool + " ,"  + "ROUND(1.0*"+Letter_Played + "/"+Letter_Drawn+ ",2) AS percentChosen" +  " FROM " + TABLE_Letter  + " ORDER BY " + Letter_Played, null); //+ " ORDER BY " + Letter_Played + " DESC",null);
       // Cursor c = db.rawQuery("SELECT letter, played_in_word, back_to_pool, percentChosen FROM (SELECT  DISTINCT 'letter' AS letter,  1 AS filter , 'number_of_played' AS played_in_word, 'number_back_Pool' as back_to_pool, 'percent_of_played' as percentChosen FROM " + TABLE_Letter + " UNION SELECT " + Letter_Name + ", 2 AS filter , SUM(" + Letter_Played  + ") AS letter_played , SUM(" + Letter_to_Pool + ") AS letter_pool , "+ "ROUND(1.0*SUM("+Letter_Played + ")/SUM("+Letter_Drawn+ "),2) AS percentChosen" +  " FROM " + TABLE_Letter  + " GROUP BY " + Letter_Name + ", filter) ORDER BY filter, played_in_word", null);
        Cursor c = db.rawQuery("SELECT " + Letter_Name + " , SUM(" + Letter_Played  + ") AS letter_played , SUM(" + Letter_to_Pool + ") AS letter_pool , "+ "ROUND(1.0*SUM("+Letter_Played + ")/SUM("+Letter_Drawn+ "),2) AS percentChosen" +  " FROM " + TABLE_Letter  + " GROUP BY " + Letter_Name + " ORDER BY SUM(" + Letter_Played +")", null);
        //Cursor res = db.rawQuery("select * from "+Table_Name, null)'
        //return res
        //moving to the first note
        // c.moveToFirst();
        //and returning Cursor object
        return c;
    }

    //get letter statistics for specific letter
    public Cursor getOneLetterStatistics(String letter) {
        //db.query is like normal sql query
        //cursor contains all notes
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_Letter + " WHERE " + Letter_Name + " = " + letter, null); //+ " ORDER BY " + Letter_Played + " DESC",null);
        //Cursor res = db.rawQuery("select * from "+Table_Name, null)'
        //return res
        //moving to the first note
        // c.moveToFirst();
        //and returning Cursor object
        return c;
    }



    public boolean updateLetterStatistics(String letter, int letter_played){//, int letter_pool, int letter_drawn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Letter_Name, letter);
        cv.put(Letter_Played, letter_played);
        //cv.put(Letter_to_Pool, letter_pool);
        //cv.put(Letter_Drawn, letter_drawn);

        db.update(TABLE_Letter, cv, Letter_Name + " = ?", new String[]{ letter });
        return true;
        }


    public boolean updateLetterStatisticsDrawn(String letter, int letter_drawn){//, int letter_pool, int letter_drawn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Letter_Name, letter);
        cv.put(Letter_Drawn, letter_drawn);
        //cv.put(Letter_to_Pool, letter_pool);
        //cv.put(Letter_Drawn, letter_drawn);

        db.update(TABLE_Letter, cv, Letter_Name + " = ?", new String[]{ letter });
        return true;
    }


    public Cursor getGameData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT  game_id, final_score, turn, (final_score/cast(turn as float)) AS avg_score, max_turn, alphabetfreq,alphabetscore FROM games ORDER BY final_score + 0 DESC ",null);
        return res;
    }

    public Cursor getWordData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT word, COUNT(word) FROM word GROUP BY word ORDER BY id DESC; ",null);
        return res;
    }


    }

