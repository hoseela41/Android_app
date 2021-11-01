package edu.gatech.seclass.words6300;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LetterTest {

    @Test
    public void getLetter() {
        Letter letter = new Letter("a","10","5","0.2");
        assertEquals("a", letter.getLetter());
    }

    @Test
    public void getNumPlayed() {
        Letter letter = new Letter("a","10","5","0.2");
        assertEquals("10", letter.getNumPlayed());
    }

    @Test
    public void getNumBackPool() {
        Letter letter = new Letter("a","10","5","0.2");
        assertEquals("5", letter.getNumBackPool());
    }

    @Test
    public void getPercentChosen() {
        Letter letter = new Letter("a","10","5","0.2");
        assertEquals("0.2", letter.getPercentChosen());
    }
}