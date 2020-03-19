package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b[]= new Button[9];
    boolean chance = true;

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


    }

    @Override
    protected void onStart() {
        super.onStart();
        try{

            for(int i=0;i<9;i++){
                b[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button)findViewById(v.getId()) ;
                        if(chance)
                            b.setText("X");
                        else
                            b.setText("O");
                        chance = !chance;
                    }
                });
            }
        }
        catch (Exception e){
            Log.d(TAG,e.toString());
        }
    }

}
