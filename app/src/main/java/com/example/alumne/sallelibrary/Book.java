package com.example.alumne.sallelibrary;

/**
 * Created by Alumne on 09/03/2018.
 */

public class Book {

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
}
