package edu.gatech.seclass.words6300;

public class Letter {
    private String letter;
    private String numPlayed;
    private String numBackPool;
    private String percentChosen;

    public Letter(String letter, String numPlayed, String numBackPool, String percentChosen){
        this.letter = letter;
        this.numPlayed = numPlayed;
        this.numBackPool = numBackPool;
        this.percentChosen = percentChosen;


    }

    public String getLetter(){
        return letter;
    }

    public String getNumPlayed(){
        return numPlayed;
    }

    public String getNumBackPool() {
        return numBackPool;
    }

    public String getPercentChosen() {
        return percentChosen;
    }
}
