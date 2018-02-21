package com.example.alumne.sallelibrary;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements OnFragmentListener {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment login = new LoginFragment();

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.login, login).commit();

       /* TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                fragmentManager.beginTransaction().replace(R.id.login,registerFragment).addToBackStack(null).commit();

            }
        });*/

    }
    @Override
    public void fragmentTransaction() {
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentManager.beginTransaction().replace(R.id.login,registerFragment).addToBackStack(null).commit();
    }
}
