package edu.gatech.seclass.words6300;


import org.junit.Test;

import static org.junit.Assert.*;

import android.provider.ContactsContract;
import android.view.View;

import java.util.HashMap;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAdjustTurnSetting() { // Change the input maxturn in settings to 10 and see it the database is changed
        int NewMaxTurn = 10;
        DataHolder.getInstance().setMaxTurn(NewMaxTurn);
        int actual = DataHolder.getInstance().getMaxTurn();
        assertEquals(10, actual);
    }
    @Test
    public void testAdjustLetterSetting() { // Change the frequency letter of A to 50
        char letter = 'A';
        int freqA = 50;
        DataHolder.getInstance().setFreq(letter,freqA);
        int actual = DataHolder.getInstance().getAlphabetFreq().get('A');
        assertEquals(50, actual);
    }
    @Test
    public void testAdjustScoreSetting() { // Change the score for letter A to be 50
        char letter = 'A';
        int scoreA = 50;
        DataHolder.getInstance().setScore(letter,scoreA);
        int actual = DataHolder.getInstance().getAlphabetScore().get('A');
        assertEquals(50, actual);
    }
    @Test
    public void testGetScore() { // get the correct score count for letter
        Pool testPool = new Pool();
        int actual = testPool.getScore('A');
        assertEquals(1, actual);
    }
    @Test
    public void testGetLeftNum() { // get the correct number of letter left (at the beginning it should be 100)
        Pool testPool = new Pool();
        testPool.reduceAlphabetMaps('E');
        testPool.reduceAlphabetMaps('T'); // played a word "ET"
        int actual = testPool.getLeftNums();
        assertEquals(98, actual);
    }
    @Test
    public void testGeneratedRack() {// see if the method generates a string with 7 works for Rack
        Pool testPool = new Pool();
        String[] letterRack = testPool.generateRack();
        int actual = letterRack.length;
        assertEquals(7, actual);
    }
    @Test
    public void testGetWordFreq(){ // test if the Word object save the frequency of each word
        Word playedWord = new Word("Hello", 10);
        int actual = playedWord.getFrequency();
        assertEquals(10, actual);
    }
    @Test
    public void testGetWord(){
        Word playedWord = new Word("Hello", 10); // test if the word is saved and achievable
        String actual = playedWord.getWord();
        assertEquals("Hello", actual);
    }

    // dataHolder.setLetterPlayedMap(letterPlayedMap);
    //            dataHolder.setLetterPoolMap(letterPoolMap);
    //            dataHolder.setLetterDrawnMap(letterDrawnMap);
    @Test
    public void testGetLetterPlayedMap(){
        HashMap<String, Integer> letterPlayedMap = new HashMap<>(); // test if letterPlayed map is saved
        letterPlayedMap.put("A", 2);
        letterPlayedMap.put("W", 1);

        HashMap<String, Integer> expectedMap = new HashMap<>(); // test if letterPlayed map is saved
        expectedMap.put("W", 1);
        expectedMap.put("A", 2);

        DataHolder.getInstance().setLetterPlayedMap(letterPlayedMap);
        HashMap<String, Integer> actualMap = DataHolder.getInstance().getLetterPlayedMap();
        assertEquals( expectedMap, actualMap);
    }

    @Test
    public void testGetLetterDrawnMap(){
        HashMap<String, Integer> letterDrawnMap = new HashMap<>(); // test if letterPlayed map is saved
        letterDrawnMap.put("A", 2);
        letterDrawnMap.put("W", 1);
        letterDrawnMap.put("C", 5);

        HashMap<String, Integer> expectedMap = new HashMap<>(); // test if letterPlayed map is saved
        expectedMap.put("W", 1);
        expectedMap.put("A", 2);
        expectedMap.put("C", 5);

        DataHolder.getInstance().setLetterDrawnMap(letterDrawnMap);
        HashMap<String, Integer> actualMap = DataHolder.getInstance().getLetterDrawnMap();
        assertEquals( expectedMap, actualMap);
    }
}