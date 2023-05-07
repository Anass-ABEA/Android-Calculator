package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnIncrement;
    private TextView tvValue;
    private int displayedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        clickListeners();
    }

    private void clickListeners() {
        this.btnIncrement.setOnClickListener((v) -> {

            // increment a value
            this.displayedValue += 1;

            Log.d("displayedValue", String.valueOf(displayedValue));

            this.tvValue.setText(String.valueOf(this.displayedValue));

        });
    }

    private void iniViews() {
        this.btnIncrement = findViewById(R.id.btn_increment);
        this.tvValue = findViewById(R.id.tv_value);
    }
}