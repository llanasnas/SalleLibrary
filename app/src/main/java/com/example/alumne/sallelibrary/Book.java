package com.example.alumne.sallelibrary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alumne on 09/03/2018.
 */

public class Book implements Parcelable{

    private String title;
    private String autor;
    private String description;
    private String imagen;


    public Book(String title, String autor, String description, String imagen) {
        this.title = title;
        this.autor = autor;
        this.description = description;
        this.imagen = imagen;
    }
    public Book(){}

    protected Book(Parcel in) {
        title = in.readString();
        autor = in.readString();
        description = in.readString();
        imagen = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescription() {
        return description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(autor);
        parcel.writeString(imagen);
        parcel.writeString(description);
    }
}
