package com.example.alumne.sallelibrary;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter{

    private ArrayList<Book> books;
    private Context context;

    public BookAdapter(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_book, viewGroup, false);
        }
        Book book = books.get(i);
        TextView titol = (TextView) view.findViewById(R.id.titulo);
        titol.setText(book.getTitle());
        TextView autor = (TextView) view.findViewById(R.id.autor);
        autor.setText(book.getAutor());
        ImageView foto = (ImageView) view.findViewById(R.id.imagen);
        Picasso.get().load(book.getImagen()).into(foto);
        return view;

    }
}
