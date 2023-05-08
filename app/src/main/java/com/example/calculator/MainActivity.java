package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.models.EvaluateOperations;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btnClear, btnPlus, btnEquals, btnMul, btnDiv, btnErase, btnMinus, btnComma,btnPlusMinus;
    private List<Button> btnList;
    private TextView tvValue, tvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        clickListeners();
    }

    private void clickListeners() {
        this.btnClear.setOnClickListener(v -> {
            this.tvValue.setText("");
            this.tvHistory.setText("");
        });
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
            String value = this.tvValue.getText().toString();
            this.tvHistory.setText(value);

            double result = EvaluateOperations.evaluateWithPriority(value);
            DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
            DecimalFormat df = new DecimalFormat("#.####",formatSymbols);

            this.tvValue.setText(df.format(result));
        });

        this.btnPlusMinus.setOnClickListener(v->{
            String value = this.tvValue.getText().toString();
            if(value.length() == 0 || value.endsWith(" ")) return;
            int index = value.lastIndexOf(" ");

            if(index!=-1){ // 3 * 5
                Double lastNumber = Double.parseDouble(value.substring(index+1));
                value = value.substring(0,index+1)+ (-lastNumber);
                this.tvValue.setText(value);
            }else{
                this.tvValue.setText(String.valueOf(-Double.parseDouble(value)));
            }
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
        this.btnPlusMinus = findViewById(R.id.btn_plus_minus);

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
        this.tvHistory = findViewById(R.id.tv_history);
    }
}