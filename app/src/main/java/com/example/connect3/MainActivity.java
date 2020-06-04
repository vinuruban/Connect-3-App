package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    0 = red, 1 = yellow
    private int activePlayer = 0;

//    array to keep track of game state: 0 = red, 1 = yellow, 2 = empty. ---> activePlayer is used
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropPiece(View view) {
        ImageView counter = (ImageView) view; //"view" since it know which ImageView was clicked on. Easy!
        counter.setTranslationY(-1500); //preset position of pieces away from screen
        int tag = Integer.parseInt(view.getTag().toString()); //to get the tag of the view that is clicked on. Labelled them from 0 to 8 to help with indexing, e.g. I labelled the first view as 0, so that I could set value to it, ie. gameState[0] = 1
        gameState[tag] = activePlayer; //updates the gameState array!

        //alternate between red and yellow
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.red);
            activePlayer = 1;
        }
        else {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 0;
        }


        if ( //if red wins...
                //horizontal
                (gameState[0] == 0 && gameState[1] == 0 && gameState[2] == 0)
                        || (gameState[3] == 0 && gameState[4] == 0 && gameState[5] == 0)
                        || (gameState[6] == 0 && gameState[7] == 0 && gameState[8] == 0)
                //vertical
                        || (gameState[0] == 0 && gameState[3] == 0 && gameState[6] == 0)
                        || (gameState[1] == 0 && gameState[4] == 0 && gameState[7] == 0)
                        || (gameState[2] == 0 && gameState[5] == 0 && gameState[8] == 0)
                //diagonal
                        || (gameState[0] == 0 && gameState[4] == 0 && gameState[8] == 0)
                        || (gameState[2] == 0 && gameState[4] == 0 && gameState[6] == 0)
        )
        {
            Toast.makeText(this, "Red wins!", Toast.LENGTH_SHORT).show();
            disableGrid(); //disables from inserting further pieces after winning
        }
        else if ( //if yellow wins...
                //horizontal
                (gameState[0] == 1 && gameState[1] == 1 && gameState[2] == 1)
                        || (gameState[3] == 1 && gameState[4] == 1 && gameState[5] == 1)
                        || (gameState[6] == 1 && gameState[7] == 1 && gameState[8] == 1)
                //vertical
                        || (gameState[0] == 1 && gameState[3] == 1 && gameState[6] == 1)
                        || (gameState[1] == 1 && gameState[4] == 1 && gameState[7] == 1)
                        || (gameState[2] == 1 && gameState[5] == 1 && gameState[8] == 1)
                //diagonal
                        || (gameState[0] == 1 && gameState[4] == 1 && gameState[8] == 1)
                        || (gameState[2] == 1 && gameState[4] == 1 && gameState[6] == 1)
        )
        {
            Toast.makeText(this, "Yellow wins!", Toast.LENGTH_SHORT).show();
            disableGrid(); //disables from inserting further pieces after winning
        }

        counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
        view.setClickable(false);
        view.setEnabled(false);
    }

    private void disableGrid() {
        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridView);
        for (int i = 0; i < grid.getChildCount(); i++) {
            View child = grid.getChildAt(i);
            child.setClickable(false);
            child.setEnabled(false);
        }
    }

}