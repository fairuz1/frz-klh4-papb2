package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AuthActivity extends AppCompatActivity {

    TextView auth_change, auth_type;
    Button auth_submit;

    private String auth_status = "login";
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        auth_change = findViewById(R.id.auth_alternative);
        auth_type = findViewById(R.id.auth_type);
        auth_submit = findViewById(R.id.submit_auth_button);

        auth_change.setOnClickListener(view -> {
            if (auth_status.equals("login")) {
                // update auth status
                auth_status = "register";

                // update view and apply fragment
                auth_change.setText(R.string.login_instead);
                auth_type.setText(R.string.auth_Register);
                auth_submit.setText(R.string.register);

                checkFragment();

            } else if (auth_status.equals("register")) {
                // update auth status
                auth_status = "login";

                // update view and remove fragment
                auth_change.setText(R.string.register_instead);
                auth_type.setText(R.string.auth_login);
                auth_submit.setText(R.string.login);

                checkFragment();
            }
        });

        auth_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate(auth_status);
            }
        });

    }

    public void displayFragment() {
        AuthFragmentActivity AuthFragmentActivity = com.example.maps.AuthFragmentActivity.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment, AuthFragmentActivity).addToBackStack(null).commit();
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AuthFragmentActivity AuthFragmentActivity = (AuthFragmentActivity) fragmentManager.findFragmentById(R.id.fragment);
        if (AuthFragmentActivity != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(AuthFragmentActivity).commit();
        }

        isFragmentDisplayed = false;
    }

    public void checkFragment() {
        if (!isFragmentDisplayed) {
            displayFragment();
        } else {
            closeFragment();
        }
    }

    public void authenticate(String status) {
        if (status.equals("login")) {
            EditText password = findViewById(R.id.password);
            EditText username = findViewById(R.id.username);

            String user_name = String.valueOf(username.getText());
            String user_password = String.valueOf(password.getText());

            if (user_name.equals("fairuz") && user_password.equals("123")) {
                Intent authenticated = new Intent(this, MapsActivity.class);
                authenticated.putExtra("username", username.getText());
                startActivity(authenticated);
            } else {
                Toast.makeText(this, R.string.wrong_auth,Toast.LENGTH_SHORT).show();
            }

        } else if (status.equals("register")) {
            Toast.makeText(this, R.string.coming_soon,Toast.LENGTH_SHORT).show();
        }
    }
}
