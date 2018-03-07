package com.example.alumne.sallelibrary;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buttonedittext.ButtonEditText;

import java.util.HashMap;
import java.util.Map;
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
        final EditText password = (EditText) getView().findViewById(R.id.psswd);
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
                    password.setError("No coinciden los passwords");
                }else{
                    repasswordcheck = true;
                }
                if(namecheck && emailcheck && passwordcheck && repasswordcheck){
                    HashMap infoUser = new HashMap<String,String>();
                    infoUser.put(email.getText().toString(),password.getText().toString());
                    infoUser.put(email.getText().toString()+"/name",name.getText().toString());
                    if(!userExists(email.getText().toString())){
                        registerUser(infoUser);
                        Toast.makeText(getActivity(),"Usuario registrado correc", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Usuario ya registrado", Toast.LENGTH_SHORT).show();
                    }
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

    public boolean userExists(String email){
        SharedPreferences pref= getActivity().getApplicationContext().getSharedPreferences("MyFilename", Context.MODE_PRIVATE);
        String mail = pref.getString(email,null);
        return mail != null;

    }

    public void registerUser(HashMap<String,String> infoUser){
        SharedPreferences pref= getActivity().getApplicationContext().getSharedPreferences("MyFilename", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String get;
        for(Map.Entry<String, String> entry : infoUser.entrySet()) {
            editor.putString(entry.getKey(),entry.getValue());
            editor.apply();
            get = pref.getString("a@a.com","DEFAULT");
        }



    }
}
