package com.example.alumne.sallelibrary;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ArrayList<Book> books;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        books = getBooks();
        ListView listView = view.findViewById(R.id.listView);
        BookAdapter adapter = new BookAdapter(books, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }
    private ArrayList<Book> getBooks(){

        ArrayList<Book> books = new ArrayList<Book>();
        Book book = new Book("titulo guachi piruli","Jennifer Polaka","Tiene verdugos","hola imagen");
        Book book2 = new Book("castigado","Oskar Domingas","Tiene verdugos","hola imagen");
        Book book3 = new Book("Bennet","Jennifer Polaka","Tiene verdugos","hola imagen");

        books.add(book);
        books.add(book2);
        books.add(book3);
        return books;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.d("Listview", "clicked");
        MainActivity activity = (MainActivity) getActivity();
        activity.onItemClick(books.get(i));

    }
}
