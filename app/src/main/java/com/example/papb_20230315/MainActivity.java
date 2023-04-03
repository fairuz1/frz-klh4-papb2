package com.example.papb_20230315;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText custom;
    private TextView mangga, apel, es_teh_lemon, coklat_panas, pangsit, lumpia, total, data1, data2, data3, data4, data5, data6;
    private Button reset, hitung, hitung2;
    private RadioGroup rd1, rd2, rd3;
    private RadioButton opsi1, opsi2, opsi3;
    private double totalHarga = 0;
    private String data_text1, data_text2, data_text3, data_text4, data_text5, data_text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mangga = findViewById(R.id.detail1);
        apel = findViewById(R.id.detail2);
        es_teh_lemon = findViewById(R.id.detail3);
        coklat_panas = findViewById(R.id.detail4);
        pangsit = findViewById(R.id.detail5);
        lumpia = findViewById(R.id.detail6);

        data1 = findViewById(R.id.data1);
        data2 = findViewById(R.id.data2);
        data3 = findViewById(R.id.data3);
        data4 = findViewById(R.id.data4);
        data5 = findViewById(R.id.data5);
        data6 = findViewById(R.id.data6);
        total = findViewById(R.id.total);

        data_text1 = String.valueOf(data1.getText());
        data_text2 = String.valueOf(data2.getText());
        data_text3 = String.valueOf(data3.getText());
        data_text4 = String.valueOf(data4.getText());
        data_text5 = String.valueOf(data5.getText());
        data_text6 = String.valueOf(data6.getText());

        reset = findViewById(R.id.hapus);
        hitung = findViewById(R.id.hitung);
        hitung2 = findViewById(R.id.hitung2);

        custom = findViewById(R.id.custom);

        rd1 = findViewById(R.id.group1);
        rd2 = findViewById(R.id.group2);
        rd3 = findViewById(R.id.group3);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungTotalHarga();
                String language = Locale.getDefault().toString();
                Log.d("language", language);
                if (language.equals("de_DE")) {
                    total.setText(String.format("€ %s", String.valueOf(totalHarga)));
                } else if (language.equals("ja_JP")) {
                    total.setText(String.format("¥ %s", String.valueOf(totalHarga)));
                } else {
                    total.setText(String.format("Rp. %s", String.valueOf(totalHarga)));
                }
            }
        });

        hitung2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String language = Locale.getDefault().toString();
                Log.d("language", language);
                if (language.equals("de_DE")) {
                    double value = Double.parseDouble(String.valueOf(custom.getText())) * 100 * 0.00000062;
                    totalHarga += value;
                    total.setText(String.format("€ %s", String.valueOf(totalHarga)));
                } else if (language.equals("ja_JP")) {
                    double value = Double.parseDouble(String.valueOf(custom.getText())) * 100 * 0.0088;
                    totalHarga += value;
                    total.setText(String.format("¥ %s", String.valueOf(totalHarga)));
                } else {
                    double value = Double.parseDouble(String.valueOf(custom.getText())) * 100;
                    totalHarga += value;
                    total.setText(String.format("Rp. %s", String.valueOf(totalHarga)));
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rd1.clearCheck();
                rd2.clearCheck();
                rd3.clearCheck();

                totalHarga = 0;
                String language = Locale.getDefault().toString();
                if (language.equals("de_DE")) {
                    total.setText(String.format("€ %s", String.valueOf(totalHarga)));
                } else if (language.equals("ja_JP")) {
                    total.setText(String.format("¥ %s", String.valueOf(totalHarga)));
                } else {
                    total.setText(String.format("Rp. %s", String.valueOf(totalHarga)));
                }
            }
        });
    }

    public void hitungTotalHarga() {
        int selected1 = rd1.getCheckedRadioButtonId();
        int selected2 = rd2.getCheckedRadioButtonId();
        int selected3 = rd3.getCheckedRadioButtonId();

        opsi1 = findViewById(selected1);
        opsi2 = findViewById(selected2);
        opsi3 = findViewById(selected3);

        if (opsi1 != null) {
            totalHarga += cekHarga(String.valueOf(opsi1.getText()));
            Log.d("hitungTotalHarga1", String.valueOf(opsi1.getText()));
        }

        if (opsi2 != null) {
            totalHarga += cekHarga(String.valueOf(opsi2.getText()));
            Log.d("hitungTotalHarga2", String.valueOf(opsi2.getText()));
        }

        if (opsi3 != null) {
            totalHarga += cekHarga(String.valueOf(opsi3.getText()));
            Log.d("hitungTotalHarga3", String.valueOf(opsi3.getText()));
        }
    }

    public double cekHarga(String data) {
        if (data.equals(data_text1)) {
            return Double.parseDouble(String.valueOf(mangga.getText())) * 100;
        } else if (data.equals(data_text2)) {
            return Double.parseDouble(String.valueOf(apel.getText())) * 100;
        } else if (data.equals(data_text3)) {
            return Double.parseDouble(String.valueOf(es_teh_lemon.getText())) * 100;
        } else if (data.equals(data_text4)) {
            return Double.parseDouble(String.valueOf(coklat_panas.getText())) * 100;
        } else if (data.equals(data_text5)) {
            return Double.parseDouble(String.valueOf(pangsit.getText())) * 100;
        } else if (data.equals(data_text6)) {
            return Double.parseDouble(String.valueOf(lumpia.getText())) * 100;
        } else {
            Log.d("data", data);
            return 0;
        }
    }
}