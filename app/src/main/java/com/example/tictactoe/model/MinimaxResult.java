package com.example.tictactoe.model;

public class MinimaxResult {
    int movei,movej,score;

    public int getMovei() {
        return movei;
    }

    public void setMovei(int movei) {
        this.movei = movei;
    }

    public void setMovej(int movej) {
        this.movej = movej;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMovej() {
        return movej;
    }

    public int getScore() {
        return score;
    }

    public  MinimaxResult(int bestScore, int i, int j){
        score = bestScore;
        movei = i;
        movej = j;
    }

}
