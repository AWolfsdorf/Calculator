package com.projects.awolfsdorf.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean borrar;
    private int valorAnterior;
    private int operacionAnterior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        borrar = false;

        final TextView display = (TextView) findViewById(R.id.display);
        View.OnClickListener clickNumeros = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoActual = display.getText().toString();
                if (textoActual.equals("0") || borrar ){
                    textoActual = "";
                    borrar = false;
                }
                if(operacionAnterior == R.id.btnEquals) {
                    textoActual = "";
                    operacionAnterior = 0;

                }
                Button numero = (Button) v;
                textoActual +=  numero.getText().toString();
                display.setText(textoActual);


            }
        };

        View.OnClickListener clickOperacion = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int valorActual = Integer.parseInt(display.getText().toString());
                if (operacionAnterior != 0 && !borrar){
                    valorActual = gestionarOperacion(valorActual);
                }
                display.setText(Integer.toString(valorActual));
                valorAnterior = Integer.parseInt(display.getText().toString());
                operacionAnterior = v.getId();
                borrar = true;

            }
        };



        View.OnClickListener clickEraseOrEquals = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int valorActual = Integer.parseInt(display.getText().toString());
                switch (v.getId()){
                    case R.id.btnEquals:
                        valorActual = gestionarOperacion(valorActual);
                        display.setText(Integer.toString(valorActual));
                        break;
                    case R.id.btnC:
                        String textoActual = Integer.toString(valorActual);
                        if (textoActual.length() == 1) {
                            display.setText("0");
                        } else {
                            textoActual = textoActual.substring(0, textoActual.length()-1);
                            display.setText(textoActual);
                        }
                        break;
                    case R.id.btnCE:
                        display.setText("0");
                        break;
                }
                borrar = false;
                operacionAnterior = v.getId();
                valorAnterior = Integer.parseInt(display.getText().toString());
            }
        };

        findViewById(R.id.btnOne).setOnClickListener(clickNumeros);
        findViewById(R.id.btnTwo).setOnClickListener(clickNumeros);
        findViewById(R.id.btnThree).setOnClickListener(clickNumeros);
        findViewById(R.id.btnFour).setOnClickListener(clickNumeros);
        findViewById(R.id.btnFive).setOnClickListener(clickNumeros);
        findViewById(R.id.btnSix).setOnClickListener(clickNumeros);
        findViewById(R.id.btnSeven).setOnClickListener(clickNumeros);
        findViewById(R.id.btnEight).setOnClickListener(clickNumeros);
        findViewById(R.id.btnNine).setOnClickListener(clickNumeros);
        findViewById(R.id.btnZero).setOnClickListener(clickNumeros);

        findViewById(R.id.btnPlus).setOnClickListener(clickOperacion);
        findViewById(R.id.btnRest).setOnClickListener(clickOperacion);
        findViewById(R.id.btnMulti).setOnClickListener(clickOperacion);
        findViewById(R.id.btnDivide).setOnClickListener(clickOperacion);

        findViewById(R.id.btnEquals).setOnClickListener(clickEraseOrEquals);
        findViewById(R.id.btnC).setOnClickListener(clickEraseOrEquals);
        findViewById(R.id.btnCE).setOnClickListener(clickEraseOrEquals);

    }

    private int gestionarOperacion(int valorActual){
        switch (operacionAnterior){
            case R.id.btnPlus:
                valorActual = valorAnterior + valorActual;
                break;
            case R.id.btnRest:
                valorActual = valorAnterior - valorActual;
                break;
            case R.id.btnMulti:
                valorActual = valorAnterior * valorActual;
                break;
            case R.id.btnDivide:
                valorActual = valorAnterior / valorActual;
        }
        return valorActual;
    }
}
