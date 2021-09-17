package com.example.xoassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button resetButton;
    TextView turnOutputTextView;
    Button[][] boardButton = new Button[3][3];
    int[][] boardState = new int[boardButton.length][boardButton.length];
    int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.resetButton);
        turnOutputTextView = findViewById(R.id.turnOutputTextView);

        boardButton[0][0] = findViewById(R.id.topLeftButton);
        boardButton[0][1] = findViewById(R.id.topMidButton);
        boardButton[0][2] = findViewById(R.id.topRightButton);

        boardButton[1][0] = findViewById(R.id.midLeftButton);
        boardButton[1][1] = findViewById(R.id.midMidButton);
        boardButton[1][2] = findViewById(R.id.midRightButton);

        boardButton[2][0] = findViewById(R.id.botLeftButton);
        boardButton[2][1] = findViewById(R.id.botMidButton);
        boardButton[2][2] = findViewById(R.id.botRightButton);

        for (int i = 0; i < boardButton.length; i++)
            for (int j = 0; j < boardButton.length; j++)
                boardButton[i][j].setOnClickListener(this);

        turnOutputTextView.setText("It is 'x' turn");
    }

    public void DeclareWinner(int[][] boardState, int x, int y) {
        boolean winner = false;
        if (boardState[x][0] == boardState[x][1] && boardState[x][0] == boardState[x][2] && boardState[x][0] != 0 && boardState[x][1] != 0 && boardState[x][2] != 0)
            winner = true;
        if (boardState[0][y] == boardState[1][y] && boardState[0][y] == boardState[2][y] && boardState[0][y] != 0 && boardState[1][y] != 0 && boardState[2][y] != 0)
            winner = true;
        if (boardState[0][0] == boardState[1][1] && boardState[0][0] == boardState[2][2] && boardState[0][0] != 0 && boardState[1][1] != 0 && boardState[2][2] != 0)
            winner = true;
        if (boardState[0][2] == boardState[1][1] && boardState[0][2] == boardState[2][0] && boardState[0][2] != 0 && boardState[1][1] != 0 && boardState[2][0] != 0)
            winner = true;
        if (winner) {
            if (turnCount % 2 == 0)
                Toast.makeText(this, "Player 'x' won!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Player 'o' won!", Toast.LENGTH_LONG).show();
            Reset();
        }
    }



    public void Reset(View view) {
        Reset();
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < boardButton.length; i++)
            for (int j = 0; j < boardButton.length; j++) {
                if (v == boardButton[i][j]) {
                    if (turnCount % 2 == 0) {
                        boardButton[i][j].setText("X");
                        boardState[i][j] = 1;
                    } else {
                        boardButton[i][j].setText("O");
                        boardState[i][j] = 2;
                    }
                }
                DeclareWinner(boardState, i, j);
                turnCount++;
                if (turnCount % 2 == 0)
                    turnOutputTextView.setText("It is 'x' turn");
                else
                    turnOutputTextView.setText("It is 'o' turn");
            }
    }


    //---------------------------------
    public void Reset() {
        for (int i = 0; i < boardButton.length; i++)
            for (int j = 0; j < boardButton.length; j++) {
                boardButton[i][j].setText("");
                boardState[i][j] = 0;
                turnCount = 0;
                turnOutputTextView.setText("It is 'x' turn");
            }
    }

}