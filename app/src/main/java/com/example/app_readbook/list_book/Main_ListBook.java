package com.example.app_readbook.list_book;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_readbook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main_ListBook extends AppCompatActivity {
private RecyclerView recyclerView;
private ListBookAdaptor listBookAdaptor;
private List<list_book> mList;
private Toolbar toolbar;
private TextView textView_page;
private EditText txt_searchName ;
public static final String url = "http://192.168.1.6:8888/demo_app/sach.php";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_book);
        recyclerView = findViewById(R.id.rcv_book);
        textView_page = findViewById(R.id.name_page);
        txt_searchName = findViewById(R.id.txt_searchBook);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mList = new ArrayList<>();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadRequestData(url);
        txt_searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchName(s.toString());
            }
        });


    }

    private void loadRequestData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONArray response) {
                    try
                        {
                            for (int i = 0 ; i < response.length() ; i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            list_book listBook = new list_book(jsonObject.getString("idSach") , jsonObject.getString("Tensach") ,
                                    jsonObject.getString("idDanhmuc") , jsonObject.getString("Tendanhmuc") ,
                                    jsonObject.getString("Tentacgia") ,jsonObject.getInt("NgayXB") ,jsonObject.getString("TomtatND") ,
                                    jsonObject.getString("IMGsach")
                                    , jsonObject.getString("sotrang") , jsonObject.getString("Luotxem")
                                    ,  jsonObject.getString("slfeedback"), jsonObject.getString("slyeuthich"));
                            mList.add(listBook);
                    }
                        }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_ListBook.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                listBookAdaptor = new ListBookAdaptor(Main_ListBook.this , mList);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
                listBookAdaptor.notifyDataSetChanged();
                recyclerView.setAdapter(listBookAdaptor);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main_ListBook.this, "Error\n" +error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AAA" ,"lỗi");
            }
        });
        requestQueue.add(jsonRequest);
    }

    public void searchName(String name)
{
    ArrayList<list_book> arrayList = new ArrayList<>();
    for (list_book item : mList )
    {
        if(item.getTenSach().toLowerCase().contains(name.toLowerCase(Locale.ROOT)))
        {
            arrayList.add(item);
            list_book listBook;
        }
        listBookAdaptor.searchBook(arrayList);
    }
}


}