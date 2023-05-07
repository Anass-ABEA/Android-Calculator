package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.models.EvaluateOperations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btnClear, btnPlus, btnEquals;
    private List<Button> btnList;
    private TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        clickListeners();
    }

    private void clickListeners() {
        this.btnClear.setOnClickListener(v-> this.tvValue.setText(""));
        this.btnPlus.setOnClickListener(v->{
            String value = this.tvValue.getText().toString();

            if(value.length() == 0)  return;
            if(value.charAt(value.length()-1) == ' ') return;
            this.tvValue.append(" + ");
        });
        for (int i = 0; i < this.btnList.size(); i++) {
            final int iFinal = i;
            this.btnList.get(i).setOnClickListener(v->this.tvValue.append(String.valueOf(iFinal)));
        }

        this.btnEquals.setOnClickListener((v)->{
            long result = EvaluateOperations.evaluate(this.tvValue.getText().toString());
            this.tvValue.setText(String.valueOf(result));
        });

    }

    private void iniViews() {
        this.btnPlus = findViewById(R.id.btn_plus);
        this.btnEquals = findViewById(R.id.btn_equals);
        this.btnClear = findViewById(R.id.btn_clear);

        this.btnList = new ArrayList<>();
        this.btnList.add( findViewById(R.id.button0) );
        this.btnList.add( findViewById(R.id.button1) );
        this.btnList.add( findViewById(R.id.button2) );
        this.btnList.add( findViewById(R.id.button3) );
        this.btnList.add( findViewById(R.id.button4) );
        this.btnList.add( findViewById(R.id.button5) );
        this.btnList.add( findViewById(R.id.button6) );
        this.btnList.add( findViewById(R.id.button7) );
        this.btnList.add( findViewById(R.id.button8) );
        this.btnList.add( findViewById(R.id.button9) );

        this.tvValue = findViewById(R.id.tv_value);
    }
}