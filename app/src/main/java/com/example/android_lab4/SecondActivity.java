package com.example.android_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewImya;
    private DatePicker datePicker;
    private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewImya = findViewById(R.id.textViewImya);
        datePicker = findViewById(R.id.datePicker);
        buttonOk = findViewById(R.id.buttonOk);

        Intent intent = getIntent();
        String imya = intent.getStringExtra("user_imya");
        textViewImya.setText(imya);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                long dateMillis = calendar.getTimeInMillis();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("vybrannaya_data", dateMillis);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}