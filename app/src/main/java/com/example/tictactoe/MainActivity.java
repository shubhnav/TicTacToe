package com.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.model.MinimaxResult;


public class MainActivity extends AppCompatActivity {

    Button b[]= new Button[9];
    Minimax cpu = new Minimax();
    private static final boolean human = true, ai=false;
    boolean chance = ai;
    private static final int X = 1, O = 2, U = 0;

    private static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b[0] = findViewById(R.id.b1);
        b[1] = findViewById(R.id.b2);
        b[2] = findViewById(R.id.b3);
        b[3] = findViewById(R.id.b4);
        b[4] = findViewById(R.id.b5);
        b[5] = findViewById(R.id.b6);
        b[6] = findViewById(R.id.b7);
        b[7] = findViewById(R.id.b8);
        b[8] = findViewById(R.id.b9);
        Button reset = findViewById(R.id.reset);

        // reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<9;i++){
                    b[i].setText("");
                }
                cpu.reset();
                chance = ai;
                cputurn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cputurn();
        try{

            for(int i=0;i<9;i++){
                final int finalI = i;
                b[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button)findViewById(v.getId()) ;
                        if(chance == human && b.getText()=="") {
                            b.setText("O");
                            cpu.setBoard(finalI /3, finalI %3,O);
                            chance = ai;
                            cputurn();
                        }

                    }
                });
            }
        }
        catch (Exception e){
            Log.d(TAG,e.toString());
        }
    }

    public void cputurn(){
        if(chance == ai){
            MinimaxResult result = cpu.bestMove(ai,0);

            if(result.getMovei()!=-1) {
                b[result.getMovei() * 3 + result.getMovej()].setText("X");
                cpu.setBoard(result.getMovei(), result.getMovej(), X);
                if(cpu.checkWin()==X) {
                    Toast.makeText(this,"CPU Wins",Toast.LENGTH_LONG).show();
                }
                else {
                    if(cpu.checkTurnLeft())
                        chance = human;
                    else
                        Toast.makeText(this,"Tie!",Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this,"Tie!",Toast.LENGTH_LONG).show();
            }
        }
    }
}