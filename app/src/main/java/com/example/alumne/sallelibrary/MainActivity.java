package com.example.alumne.sallelibrary;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{


    public static FragmentManager fragmentManager;
    private static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = this.getIntent().getExtras();

        currentUser = parametros.getParcelable("user" );
        Bundle bundle = new Bundle();
        bundle.putParcelable("user",currentUser);
        ListFragment login = new ListFragment();
        login.setArguments(bundle);
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainActivity, login).commit();

    }


    @Override
    public void onItemClick(Book book) {
        FragmentManager fragmentManager = getFragmentManager();
        DetailsFragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("book", book);
        bundle.putParcelable("user",currentUser);
        fragment.setArguments(bundle);

            fragmentManager.
                    beginTransaction().
                    replace(R.id.mainActivity, fragment).
                    addToBackStack(null).
                    commit();
    }


}
