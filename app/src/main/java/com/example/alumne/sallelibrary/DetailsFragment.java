package com.example.alumne.sallelibrary;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Book book;
    private User currentUser;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            book = bundle.getParcelable("book");
        }else{
            book = new Book();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView title = view.findViewById(R.id.titulo_detail);
        title.setText(book.getTitle());
        TextView description = view.findViewById(R.id.description);
        description.setText(book.getDescription());
        TextView autor = (TextView) view.findViewById(R.id.autor_details);
        autor.setText(book.getAutor());
        TextView desc = (TextView) view.findViewById(R.id.description);
        desc.setText(book.getDescription());
        ImageView foto = (ImageView) view.findViewById(R.id.imagen_detail);
        Picasso.get().load(book.getImagen()).into(foto);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView favoritos  = (ImageView) getActivity().findViewById(R.id.favoritostar);
        currentUser = getArguments().getParcelable("user");
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                SharedPreferences pref= getActivity().getApplicationContext().getSharedPreferences("MyFilename", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(currentUser.getFavoritos().isEmpty()){
                    ArrayList<Book> books = new ArrayList<Book>() ;
                    books.add(book);
                    currentUser.setFavoritos(books);
                    Gson gson = new Gson();
                    String jsonText = gson.toJson(currentUser.getFavoritos());
                    editor.putString(currentUser.getEmail().concat("f"),jsonText);
                    editor.apply();
                    Toast.makeText(getActivity(), getResources().getString(R.string.added_book), Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<Book> books = currentUser.getFavoritos();
                    books.add(book);
                    currentUser.setFavoritos(books);
                    Gson gson = new Gson();
                    String jsonText = gson.toJson(currentUser.getFavoritos());
                    editor.putString(currentUser.getEmail().concat("f"),jsonText);
                    editor.apply();
                    Toast.makeText(getActivity(), getResources().getString(R.string.added_book), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
