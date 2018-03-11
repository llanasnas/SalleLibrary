package com.example.alumne.sallelibrary;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buttonedittext.ButtonEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ButtonEditText.onButtonEditTextClickedListener {




    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButtonEditText buttonEditText = getView().findViewById(R.id.loginComponent);
        buttonEditText.setOnButtonEditTextClickedListener(this);
        buttonEditText.setFirstText(getResources().getString(R.string.account));
        buttonEditText.setSecondText(getResources().getString(R.string.password));
        buttonEditText.setText(getResources().getString(R.string.Submit));
        TextView register = (TextView) getView().findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.fragmentTransaction();
            }
        });
    }

    @Override
    public void onButtonEditTextClicked(ButtonEditText source, String email, String passwd) {
        loginUser(email,passwd);
    }

    public void loginUser(String email, String passwd){
        SharedPreferences pref= getActivity().getApplicationContext().getSharedPreferences("MyFilename", Context.MODE_PRIVATE);
        String password = pref.getString(email,null);
        if (password != null){
            if (password.equals(passwd)){
                Bundle bundle = new Bundle();
                User user = new User("coaxial",email,passwd);
                bundle.putParcelable("user",user);
                Intent startMain = new Intent(getActivity(),MainActivity.class);
                startMain.putExtras(bundle);
                startActivity(startMain);
            }else{
                Toast.makeText(getActivity(),"No te sabes tu contraseña!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"Email incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
