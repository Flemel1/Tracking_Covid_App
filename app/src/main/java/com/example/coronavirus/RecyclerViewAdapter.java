package com.example.coronavirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import Model.Attributes;
import Model.Corona;
import Model.Country;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Attributes> listData;
    private List<Country> listCountry;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Attributes> listData) {
        this.listData = listData;
        this.context = context;
    }

    public RecyclerViewAdapter(List<Country> listCountry, Context context) {
        this.listCountry = listCountry;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_view, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(listData != null){
            Collections.sort(listData, new CompareProvinsi());
            holder.provinsi.setText("Nama Provinsi: " + listData.get(position).getAttributes().getProvinsi());
            holder.positif.setText("Positif: " + listData.get(position).getAttributes().getKasus_Posi());
            holder.sembuh.setText("Sembuh: " + listData.get(position).getAttributes().getKasus_Semb());
            holder.meninggal.setText("Meninggal: " + listData.get(position).getAttributes().getKasus_Meni());
            holder.totalKasus.setVisibility(View.GONE);
        }
        else {
            Collections.sort(listCountry, new CompareNegara());
            holder.provinsi.setText("Nama Negara: " + listCountry.get(position).getAttributes().getCountry_Region());
            holder.positif.setText("Jumlah Kasus: " + listCountry.get(position).getAttributes().getConfirmed());
            holder.sembuh.setText("Sembuh: " + listCountry.get(position).getAttributes().getRecovered());
            holder.meninggal.setText("Meninggal: " + listCountry.get(position).getAttributes().getDeaths());
            holder.totalKasus.setText("Positif: " + listCountry.get(position).getAttributes().getActive());
        }
    }



    @Override
    public int getItemCount() {
        if (listData != null)
            return listData.size();
        else {
            return listCountry.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView provinsi;
        TextView positif;
        TextView sembuh;
        TextView meninggal;
        TextView totalKasus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            provinsi = (TextView) itemView.findViewById(R.id.provinsi);
            positif = (TextView) itemView.findViewById(R.id.positif);
            sembuh = (TextView) itemView.findViewById(R.id.sembuh);
            meninggal = (TextView) itemView.findViewById(R.id.meninggal);
            totalKasus = (TextView) itemView.findViewById(R.id.jml_kasus);
        }
    }

    public void filterList(List<Attributes> list){
        this.listData = list;
        notifyDataSetChanged();
    }

    public void filterNegara(List<Country> list){
        this.listCountry = list;
        notifyDataSetChanged();
    }
}
