package edu.gatech.seclass.words6300;

public class Word {

    private String word;
    private int frequency;

    public Word(String word)
    { this.word=word; }

    public Word(String word, int frequency){
        this.word=word;
        this.frequency = frequency;}

    public String getWord() {
        return word;
    }
    public int getFrequency(){return frequency;}
}
