package com.example.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AuthFragmentActivity extends Fragment {

    public AuthFragmentActivity() {
        // Required empty public constructor
    }

    public static AuthFragmentActivity newInstance() {
        return new AuthFragmentActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity_fragment_auth, container, false);
        return rootView;
    }
}