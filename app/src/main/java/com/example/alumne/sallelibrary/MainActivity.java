package com.example.alumne.sallelibrary;

import android.app.FragmentManager;
import android.os.Parcelable;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListFragment login = new ListFragment();

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainActivity, login).commit();

    }


    @Override
    public void onItemClick(Book book) {
        FragmentManager fragmentManager = getFragmentManager();
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("book", book);
        fragment.setArguments(bundle);

            fragmentManager.
                    beginTransaction().
                    replace(R.id.mainActivity, fragment).
                    addToBackStack(null).
                    commit();

    }

}
