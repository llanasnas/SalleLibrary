package com.example.alumne.sallelibrary;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Book book;

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
        return view;
    }

}
