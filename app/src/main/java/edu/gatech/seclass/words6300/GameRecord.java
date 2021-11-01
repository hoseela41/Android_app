package edu.gatech.seclass.words6300;

public class GameRecord {
    private int id;
    private int turn;
    private int final_score;
    private float avg_score;
    private int max_turn;
    private byte[] alphabetFreq;
    private byte[] alphabetScore;
    public GameRecord(int id, int final_score, int turn, float avg_score, int max_turn, byte[] alphabetFreq, byte[] alphabetScore) {
        this.id = id;
        this.final_score = final_score;
        this.turn = turn;
        this.avg_score = avg_score;
        this.max_turn = max_turn;
        this.alphabetFreq = alphabetFreq;
        this.alphabetScore = alphabetScore;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getFinal_socre() { return final_score; }

    public void setFinal_socre(int final_socre) {
        this.final_score = final_socre;
    }

    public float getAvg_socre() {
        return avg_score;
    }

    public void setAvg_socre(float avg_score) {
        this.avg_score = avg_score;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getMax_turn() {
        return max_turn;
    }

    public byte[] getAlphabetFreq() {
        return alphabetFreq;
    }

    public byte[] getAlphabetScore() {
        return alphabetScore;
    }
}
