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


    private Button btnClear, btnPlus, btnEquals, btnMul, btnDiv, btnErase, btnMinus, btnComma;
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
        this.btnClear.setOnClickListener(v -> this.tvValue.setText(""));
        this.btnPlus.setOnClickListener(v -> addOperation(" + "));
        this.btnMinus.setOnClickListener(v -> addOperation(" - "));
        this.btnMul.setOnClickListener(v -> addOperation(" * "));
        this.btnDiv.setOnClickListener(v -> addOperation(" / "));

        this.btnComma.setOnClickListener(v-> this.tvValue.append("."));

        this.btnErase.setOnClickListener(v->{
            String value = this.tvValue.getText().toString();
            if(value.length() == 0) return;
            if(value.endsWith(" ")) this.tvValue.setText(value.substring(0,value.length()-3));
            else this.tvValue.setText(value.substring(0,value.length()-1));

        });

        for (int i = 0; i < this.btnList.size(); i++) {
            final int iFinal = i;
            this.btnList.get(i).setOnClickListener(v -> this.tvValue.append(String.valueOf(iFinal)));
        }

        this.btnEquals.setOnClickListener((v) -> {
            double result = EvaluateOperations.evaluateWithPriority(this.tvValue.getText().toString());
            this.tvValue.setText(String.valueOf(result));
        });

    }

    private void addOperation(String operation) {
        String value = this.tvValue.getText().toString();

        if (value.length() == 0) return;
        if (value.charAt(value.length() - 1) == ' ') return;
        this.tvValue.append(operation);
    }

    private void iniViews() {
        this.btnPlus = findViewById(R.id.btn_plus);
        this.btnEquals = findViewById(R.id.btn_equals);
        this.btnClear = findViewById(R.id.btn_clear);
        this.btnMinus = findViewById(R.id.btn_minus);
        this.btnDiv = findViewById(R.id.btn_div);
        this.btnErase = findViewById(R.id.btn_erase);
        this.btnMul = findViewById(R.id.btn_mul);
        this.btnComma = findViewById(R.id.btn_comma);

        this.btnList = new ArrayList<>();
        this.btnList.add(findViewById(R.id.button0));
        this.btnList.add(findViewById(R.id.button1));
        this.btnList.add(findViewById(R.id.button2));
        this.btnList.add(findViewById(R.id.button3));
        this.btnList.add(findViewById(R.id.button4));
        this.btnList.add(findViewById(R.id.button5));
        this.btnList.add(findViewById(R.id.button6));
        this.btnList.add(findViewById(R.id.button7));
        this.btnList.add(findViewById(R.id.button8));
        this.btnList.add(findViewById(R.id.button9));

        this.tvValue = findViewById(R.id.tv_value);
    }
}