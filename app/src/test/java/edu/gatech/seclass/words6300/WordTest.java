package edu.gatech.seclass.words6300;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void getWord() {
        Word word = new Word("happy");
        assertEquals("happy", word.getWord());
    }

    @Test
    public void getFrequency() {
        Word word = new Word("happy",2);
        assertEquals(2, word.getFrequency());
    }
}