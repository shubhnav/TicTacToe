package com.example.tictactoe;

public class MinimaxResult {
    int movei,movej,score;
    public  MinimaxResult(int bestScore,int i,int j){

        score = bestScore;
        movei = i;
        movej = j;
    }

}
