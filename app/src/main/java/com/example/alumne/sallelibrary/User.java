package com.example.alumne.sallelibrary;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Gerard on 11/03/2018.
 */

public class User implements Parcelable{

    private String nombre;
    private String email;
    private String pass;
    private ArrayList<Book> favoritos;

    public User(String nombre, String email, String pass) {
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFavoritos(ArrayList<Book> favoritos) {
        this.favoritos = favoritos;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    protected User(Parcel in) {
        nombre = in.readString();
        email = in.readString();
        pass = in.readString();
        favoritos = in.createTypedArrayList(Book.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(email);
        parcel.writeString(pass);
        parcel.writeTypedList(favoritos);
    }
}
