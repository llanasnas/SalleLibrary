package com.example.alumne.sallelibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




/**
 * Created by Alumne on 09/03/2018.
 */

public class Book implements Parcelable{

    private String title;
    private String autor;
    private String description;
    private String imagen;

    private static final String BOOK_NODE_NAME = "volumeInfo";
    private static final String IMAGES_NODE_NAME = "imageLinks";
    private static final String IMAGES_NODE_AUTHORS = "authors";

    private static final String TITLE_ELEMENT_NAME = "title";
    private static final String IMAGE_ELEMENT_NAME = "smallThumbnail";

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", autor='" + autor + '\'' + ", description='" + description + '\'' + ", imagen='" + imagen + '\'' + '}';
    }


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

    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        JSONObject volumeInfo = null;
        JSONObject images = null;

        try {
            volumeInfo = jsonObject.getJSONObject(BOOK_NODE_NAME);
            images = volumeInfo.getJSONObject(IMAGES_NODE_NAME);

            book.title = volumeInfo.has(TITLE_ELEMENT_NAME) ? volumeInfo.getString(TITLE_ELEMENT_NAME) : "";
            book.autor = getAuthor(volumeInfo);
            if (images.has(IMAGE_ELEMENT_NAME)) {
                book.imagen = images.getString(IMAGE_ELEMENT_NAME);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray(IMAGES_NODE_AUTHORS);
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Book> fromJson(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }


}
