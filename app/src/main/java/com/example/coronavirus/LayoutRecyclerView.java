package com.example.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

import Model.Attributes;
import Model.Country;
import Model.ViewModelAttributes;
import Response.ApiCorona;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LayoutRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Attributes> attributesList;
    ArrayList<Attributes> listFilter = new ArrayList<Attributes>();
    private RecyclerViewAdapter recyclerViewAdapter;
    private String url = "https://api.kawalcorona.com/";
    private EditText search;
    private ViewModelAttributes viewModelAttributes;
    private String textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_recycler_view);
        search = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(LayoutRecyclerView.this);
        recyclerView.setLayoutManager(layoutManager);
        setTitle(getString(R.string.Indonesia));
        viewModelAttributes = ViewModelProviders.of(this).
                get(ViewModelAttributes.class);
        viewModelAttributes.getAttributes().observe(LayoutRecyclerView.this, new Observer<List<Attributes>>() {
            @Override
            public void onChanged(List<Attributes> attributes) {
                attributesList = attributes;
                recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), attributes);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textSearch = s.toString();
                filter(s.toString());
            }
        });

    }

    private void filter(String text){
        listFilter = new ArrayList<Attributes>();
        for (Attributes atr : attributesList){
            if (atr.getAttributes().getProvinsi().toLowerCase().contains(text.toLowerCase())){
                listFilter.add(atr);
            }
        }
        recyclerViewAdapter.filterList(listFilter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("KeyString", textSearch);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textSearch = savedInstanceState.getString("KeyString");
    }
}
