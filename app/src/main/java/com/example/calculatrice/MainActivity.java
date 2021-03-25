package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textV;
    private int op1=0;
    private int op2=0;
    private Ops operator=null;
    private boolean isOp1=true;

    public enum Ops {
        PLUS,
        MOINS,
        FOIS,
        DIV;
    }




    public void compute() {
        if(!isOp1){
            switch(operator) {
                case PLUS : op1 = op1 + op2;
                break;
                case MOINS : op1 = op1 - op2;
                break;
                case FOIS : op1 = op1 * op2;
                break;
                case DIV : op1 = op1 / op2;
                break;
                default : return;
            }
            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textV = (TextView) findViewById(R.id.textV);


        Button egal = (Button)findViewById(R.id.egal);
        egal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        } );



    }

    public void setOperator(View v) {
        switch (v.getId()) {
            case R.id.plus:
                operator = Ops.PLUS;
                break;
            case R.id.moins:
                operator = Ops.MOINS;
                break;
            case R.id.multiplication:
                operator = Ops.FOIS;
                break;
            case R.id.division:
                operator = Ops.DIV;
                break;
            default:
                return;
        }
        isOp1=false;
        updateDisplay();
    }

    public void addNumber(View v){
        try {
            int val = Integer.parseInt( ((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    private void updateDisplay() {
        int v=op1;
        if(!isOp1) {
            v=op2;
        }
        textV.setText(String.format("%9d",v));
    }
}