package com.example.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

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

public class LayoutNegara extends AppCompatActivity {
    private LinearLayoutManager layoutManager;
    private List<Country> attributesList;
    private String url = "https://api.kawalcorona.com/";
    private ViewModelAttributes viewModelAttributes;
    RecyclerViewAdapter recyclerViewAdapter;
    private EditText srcNegara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_negara);
        srcNegara = (EditText)findViewById(R.id.ed_src);
        setTitle(getString(R.string.world));
        viewModelAttributes = ViewModelProviders.of(this)
                .get(ViewModelAttributes.class);
        viewModelAttributes.getCountry().observe(LayoutNegara.this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                attributesList = countries;
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler1);
                layoutManager = new LinearLayoutManager(LayoutNegara.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerViewAdapter =new RecyclerViewAdapter(attributesList, getApplicationContext());
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
        srcNegara.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text){
        List<Country> list = new ArrayList<Country>();
        for (Country country : attributesList){
            if (country.getAttributes().getCountry_Region().toLowerCase().contains(text.toLowerCase())){
                list.add(country);
            }
        }
        recyclerViewAdapter.filterNegara(list);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
