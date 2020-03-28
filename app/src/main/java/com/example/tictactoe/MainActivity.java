package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button b[]= new Button[9];
    Minimax cpu = new Minimax();
    private static final boolean human = true, ai=false;
    boolean chance = human;
    private static final int X = 1, O = 2, U=0;

    private static final String TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        b[0] = (Button)findViewById(R.id.b1);
        b[1] = (Button)findViewById(R.id.b2);
        b[2] = (Button)findViewById(R.id.b3);
        b[3] = (Button)findViewById(R.id.b4);
        b[4] = (Button)findViewById(R.id.b5);
        b[5] = (Button)findViewById(R.id.b6);
        b[6] = (Button)findViewById(R.id.b7);
        b[7] = (Button)findViewById(R.id.b8);
        b[8] = (Button)findViewById(R.id.b9);
        Button reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<9;i++){
                    b[i].setText("");
                    cpu.reset();
                    chance = human;
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        try{

            for(int i=0;i<9;i++){
                final int finalI = i;
                b[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button)findViewById(v.getId()) ;
                        if(chance == human && b.getText()=="") {
                            b.setText("X");
                            cpu.setBoard(finalI /3, finalI %3,X);
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
            MinimaxResult result = cpu.bestMove(ai);
            if(result.movei!=-1) {
                b[result.movei * 3 + result.movej].setText("O");
                cpu.setBoard(result.movei, result.movej, O);
                if(cpu.checkWin()==O) {
                    Toast.makeText(this,"CPU Wins",Toast.LENGTH_LONG).show();
                }
                else
                    chance = human;

            }
            else{
                Toast.makeText(this,"Tie!",Toast.LENGTH_LONG).show();
            }

        }

    }


}
