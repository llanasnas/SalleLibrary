package com.example.alumne.sallelibrary;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.buttonedittext.ButtonEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText name = (EditText) getView().findViewById(R.id.name);
        final EditText email = (EditText) getView().findViewById(R.id.email);
        final EditText password = (EditText) getView().findViewById(R.id.passwd);
        final EditText repassword = (EditText) getView().findViewById(R.id.rpsswd);
        Button submit = (Button) getView().findViewById(R.id.register);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean namecheck=false,emailcheck=false,passwordcheck=false,repasswordcheck=false;

                if(name.getText().toString().isEmpty()){
                    name.setError("No puede estar vacio");
                }else{namecheck=true;}
                if(!checkEmail(email.getText().toString())){
                    email.setError("Formato de email invalido");
                }else{emailcheck=true;}
                if(password.getText().toString().length()<6){
                    password.setError("Mínimo 6 carácteres");
                }else{passwordcheck=true;}
                if(!repassword.getText().toString().equals(password.getText().toString())){
                    repassword.setError("No coinciden los passwords");
                    password.setError("No coincinciden los passwords");
                }else{repasswordcheck=true;}
                if(namecheck&&emailcheck&&passwordcheck&&repasswordcheck){
                    
                }

            }
        });


    }


    public boolean checkEmail(String email){

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
