package com.example.ppapb_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Boolean clicked = false;
    private Button colorfulButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorfulButton = findViewById(R.id.colorfulButton);
        colorfulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked.equals(false)) {
                    clicked = true;
                    colorfulButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.purple_700, null));
                    colorfulButton.setTextColor(Color.WHITE);
                } else {
                    clicked = false;
                    colorfulButton.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.teal_700, null));
                    colorfulButton.setTextColor(Color.WHITE);
                }
            }
        });
    }
}