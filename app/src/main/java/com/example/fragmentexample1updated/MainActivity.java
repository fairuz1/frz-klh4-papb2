package com.example.fragmentexample1updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    private boolean displayStatic = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // change displayStatic value to true to display static views
        if (displayStatic) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_statis);

            mButton = findViewById(R.id.btn_toggle_fragment);
            mButton.setEnabled(false);
        } else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mButton = findViewById(R.id.btn_toggle_fragment);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkFragment();
                }
            });

            closeFragment();
        }
    }

    public void displayFragment() {
        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment, simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        mButton.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    public void checkFragment() {
        if (!isFragmentDisplayed) {
            displayFragment();
        } else {
            closeFragment();
        }
    }
}