package com.example.android_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText editTextImya;
    private Button buttonOtpravit;
    private TextView textViewRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextImya = findViewById(R.id.editTextImya);
        buttonOtpravit = findViewById(R.id.buttonOtpravit);
        textViewRezultat = findViewById(R.id.textViewRezultat);

        buttonOtpravit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imya = editTextImya.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("user_imya", imya);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            long dateMillis = data.getLongExtra("vybrannaya_data", -1);
            if (dateMillis != -1) {
                String imya = editTextImya.getText().toString();
                Calendar denRozh = Calendar.getInstance();
                denRozh.setTimeInMillis(dateMillis);

                Calendar segodnya = Calendar.getInstance();
                Calendar sleduyushchiyDR = (Calendar) denRozh.clone();
                sleduyushchiyDR.set(Calendar.YEAR, segodnya.get(Calendar.YEAR));
                if (segodnya.after(sleduyushchiyDR)) {
                    sleduyushchiyDR.add(Calendar.YEAR, 1);
                }

                long raznica = sleduyushchiyDR.getTimeInMillis() - segodnya.getTimeInMillis();
                long dniDoSDR = TimeUnit.MILLISECONDS.toDays(raznica);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String formattedDR = dateFormat.format(denRozh.getTime());
                String rezultatText = String.format("У %s день рождения %s, следующий день рождения через %d дней", imya, formattedDR, dniDoSDR);
                textViewRezultat.setText(rezultatText);
            }
        }
    }
}