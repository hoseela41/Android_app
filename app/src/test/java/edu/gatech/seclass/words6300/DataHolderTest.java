package edu.gatech.seclass.words6300;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DataHolderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {
    }

    @Test
    public void getMaxTurn() {
    }

    @Test
    public void getAlphabetFreq() {
    }

    @Test
    public void getAlphabetScore() {
    }

    @Test
    public void setMaxTurn() {
        int NewMaxTurn = 10;
        DataHolder.getInstance().setMaxTurn(NewMaxTurn);
        int actual = DataHolder.getInstance().getMaxTurn();
        assertEquals(10, actual);
    }

    @Test
    public void setFreq() {
        char letter = 'A';
        int freqA = 50;
        DataHolder.getInstance().setFreq(letter,freqA);
        int actual = DataHolder.getInstance().getAlphabetFreq().get('A');
        assertEquals(50, actual);
    }

    @Test
    public void setScore() {
        char letter = 'A';
        int scoreA = 50;
        DataHolder.getInstance().setScore(letter,scoreA);
        int actual = DataHolder.getInstance().getAlphabetScore().get('A');
        assertEquals(50, actual);
    }

    @Test
    public void getGameStatus() {
        DataHolder.getInstance().setGameStatus(true);
        assertEquals(true, DataHolder.getInstance().getGameStatus());
    }

    @Test
    public void setGameStatus() {
        DataHolder.getInstance().setGameStatus(true);
        assertEquals(true, DataHolder.getInstance().getGameStatus());
    }

    @Test
    public void setPool() {
    }

    @Test
    public void getPool() {
    }

    @Test
    public void setBoardCharArray() {
    }

    @Test
    public void getBoardCharArray() {
    }

    @Test
    public void setRackCharArray() {
    }

    @Test
    public void getRackCharArray() {
    }

    @Test
    public void setLetterPlayedMap() {
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
    public void setLetterDrawnMap() {
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

    @Test
    public void setLetterPoolMap() {
    }

    @Test
    public void setAlphabetFreq() {
    }

    @Test
    public void setAlphabetScore() {
    }

    @Test
    public void saveAlphabetFreq() {
    }

    @Test
    public void saveAlphabetScore() {
    }

    @Test
    public void getSettingFreq() {
    }

    @Test
    public void getSettingScore() {
    }

    @Test
    public void getLetterPlayedMap() {

    }

    @Test
    public void getLetterDrawnMap() {

    }

    @Test
    public void getLetterPoolMap() {
    }

    @Test
    public void setGameParameters() {
        DataHolder.getInstance().setGameParameters(1, 1,
                1, 1,1, 'c', 1);
        assertEquals(1, DataHolder.getInstance().getCurrTurn());
        assertEquals(1, DataHolder.getInstance().getNumSelectedBoard());
        assertEquals(1, DataHolder.getInstance().getNumSelectedRack());
        assertEquals(1, DataHolder.getInstance().getCurrScore());
        assertEquals(1, DataHolder.getInstance().getTotalScore());
        assertEquals('c', DataHolder.getInstance().getChosenBoard());
        assertEquals(1, DataHolder.getInstance().getChosenBoardId());
    }

    @Test
    public void getCurrTurn() {
    }

    @Test
    public void getNumSelectedBoard() {
    }

    @Test
    public void getNumSelectedRack() {
    }

    @Test
    public void getCurrScore() {
    }

    @Test
    public void getTotalScore() {
    }

    @Test
    public void getChosenBoard() {
    }

    @Test
    public void getChosenBoardId() {
    }
}

