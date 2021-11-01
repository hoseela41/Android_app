package edu.gatech.seclass.words6300;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameRecordTest {
    GameRecord gameRecord = null;
    String string1;
    String string2;

    @Before
    public void setUp() throws Exception {
        string1 = "A:"+5 +"B:"+5+"C:"+5+"D:"+5+"E:"+5+"F:"+5+"G:"
                +5+"H:"+5+"I:"+5+"J:"+5+"K:"+5+"L:"+5+"M:"+5+"N:"+5+"O:"+5+"P:"+5+"Q:"+5
                +"R:"+5+"S:"+5+"T:"+5+"U:"+5+"V:"+5+"W:"+5+"X:"+5+"Y:"+5+"Z:"+5;
        string2 = "A:"+5 +"B:"+5+"C:"+5+"D:"+5+"E:"+5+"F:"+5+"G:"
                +5+"H:"+5+"I:"+5+"J:"+5+"K:"+5+"L:"+5+"M:"+5+"N:"+5+"O:"+5+"P:"+5+"Q:"+5
                +"R:"+5+"S:"+5+"T:"+5+"U:"+5+"V:"+5+"W:"+5+"X:"+5+"Y:"+5+"Z:"+5;

        gameRecord = new GameRecord(2,
                50,10,5,10, string1.getBytes(),string2.getBytes());
    }

    @After
    public void tearDown() throws Exception {
        GameRecord gameRecord = null;
    }

    @Test
    public void getId() {
        assertEquals(2, gameRecord.getId());
    }

    @Test
    public void setId() {
        gameRecord.setId(5);
        assertEquals(5, gameRecord.getId());
    }

    @Test
    public void getFinal_socre() {
        assertEquals(50, gameRecord.getFinal_socre());
    }

    @Test
    public void setFinal_socre() {
        gameRecord.setFinal_socre(100);
        assertEquals(100, gameRecord.getFinal_socre());
    }

    @Test
    public void getAvg_socre() {
        assertEquals(5, gameRecord.getAvg_socre(),0.01);
    }

    @Test
    public void setAvg_socre() {
        gameRecord.setAvg_socre(10);
        assertEquals(10, gameRecord.getAvg_socre(),0.01);
    }

    @Test
    public void getTurn() {
        assertEquals(10, gameRecord.getTurn());
    }

    @Test
    public void setTurn() {
        gameRecord.setTurn(20);
        assertEquals(20, gameRecord.getTurn());
    }

    @Test
    public void getMax_turn() {
        assertEquals(10, gameRecord.getMax_turn());
    }

    @Test
    public void getAlphabetFreq() {
        byte[] bytes = gameRecord.getAlphabetFreq();
        String s = new String(bytes);
        assertEquals(string1, s);
    }

    @Test
    public void getAlphabetScore() {
        byte[] bytes = gameRecord.getAlphabetScore();
        String s = new String(bytes);
        assertEquals(string2, s);
    }
}