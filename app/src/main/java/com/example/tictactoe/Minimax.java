package com.example.tictactoe;

import android.util.Log;

public class Minimax {
    private int [][] board = new int[3][3];
    private static final boolean human = true, ai=false;
    private static final String TAG ="MyLog";
    // 0 unused
    // 1 x
    // 2 O
    private static final int X = 1, O = 2, U=0;

    public void reset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = U;
            }
        }
    }
    public Minimax(){
       reset();
    }

    public void setBoard(int r,int c,int val){
        board[r][c] = val;
    }

    public int checkWin(){
        for(int i=0;i<3;i++) {
            // any row same
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            // any column same
            else if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // check diagonals
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2])
            return board[0][0];
        if(board[0][2]==board[1][1] && board[1][1]== board[2][0])
            return  board[0][2];
        // return no win
        return U;

    }

    public MinimaxResult bestMove(boolean chance){
        int win = checkWin();
        if(win!=U){
            MinimaxResult result;
            if(win==X)
                result = new MinimaxResult(-10,-1,-1);
            else
                result = new MinimaxResult(10,-1,-1);
            return result;
        }
        int bestScore;
        MinimaxResult result ;
        if(chance == ai) {
            // maximize in ai
            bestScore = Integer.MIN_VALUE;
            result = new MinimaxResult(Integer.MIN_VALUE,-1,-1);
        }
        else {
            // minimize in human
            bestScore = Integer.MAX_VALUE;
            result = new MinimaxResult(Integer.MAX_VALUE,-1,-1);
        }
        boolean turnleft = false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                // spot available
                if (board[i][j] == U) {
                    turnleft = true;
                    int score;
                    if(chance==ai) {
                        board[i][j] = O;
                        score = bestMove(human).score;
                    }
                    else {
                        board[i][j] = X;
                        score = bestMove(ai).score;
                    }

                    board[i][j] = U;
                    Log.d(TAG,String.valueOf(score));
                    if(bestScore<score && score!=-1 && chance == ai) {
                        result.score = score;
                        result.movei = i;
                        result.movej = j;
                        bestScore = score;
                    }
                    if(bestScore>score && score!=-1 && chance == human) {
                        result.score = score;
                        result.movei = i;
                        result.movej = j;
                        bestScore = score;
                    }

                }
            }
        }
        // tie;
        if(turnleft==false)
            result.score = 0;
        return result;
    }
}
