package com.example.alumne.sallelibrary;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListFragment login = new ListFragment();

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainActivity, login).commit();

    }
}
