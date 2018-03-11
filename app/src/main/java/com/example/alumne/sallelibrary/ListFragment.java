package com.example.alumne.sallelibrary;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ArrayList<Book> books = new ArrayList<>();
    private BookAdapter adapter;
    private static final String DEFAULT_QUERY = "android";
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        //books = getBooks();
        ListView listView = view.findViewById(R.id.listView);
        adapter = new BookAdapter(books, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        fetchBooks(DEFAULT_QUERY);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView buscar = (ImageView) getActivity().findViewById(R.id.search_icon);
        final EditText text = (EditText) getActivity().findViewById(R.id.search_text);
        ImageView favoritos = (ImageView) getActivity().findViewById(R.id.favorites);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!text.getText().toString().isEmpty()){
                    books.clear();
                    fetchBooks(text.getText().toString());
                }else{
                    text.setError("No puede estar vacio");
                }



            }
        });
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                books.clear();

            }
        });

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


    private void fetchBooks(String query) {

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new BookClient().execute(query);

        }

    }

    public class BookClient extends AsyncTask<String, Void, JSONObject> {

        private static final String API_BASE_URL = "https://www.googleapis.com/";

        @Override
        protected JSONObject doInBackground(String... query) {

            try {
                return downloadUrl(API_BASE_URL + "books/v1/volumes?q=" + query[0]);
            } catch (IOException e) {
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(JSONObject result) {

            if (result != null) {
                try {

                    int totalItems = result.getInt("totalItems");

                    if(totalItems !=0){
                        // Get the docs json array
                        JSONArray docs = result.getJSONArray("items");
                        // Parse json array into array of model objects
                        final ArrayList<Book> booksFromApi = Book.fromJson(docs);
                        // Remove all books from the adapter

                        // Load model objects into the adapter
                        for (Book book : booksFromApi) {
                            books.add(book); // add book through the adapter
                            Log.d("LIBRO",book.toString());
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // Given a URL, establishes an HttpUrlConnection and retrieves
        // the web page content as a InputStream, which it returns as
        // a string.
        private JSONObject downloadUrl(String myurl) throws IOException {
            InputStream is = null;

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                is = conn.getInputStream();

                return readIt(is);

            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        private JSONObject readIt(InputStream is) {

            JSONObject jsonObject = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                jsonObject = new JSONObject(sb.toString());

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }
}
