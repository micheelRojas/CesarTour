package com.example.cesartour.Presentacion.Adatadores_Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.util.ArrayList;

public class AdapterSitios extends RecyclerView.Adapter<AdapterSitios.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Sitio> Sitios;
    LayoutInflater inflater;
    private View.OnClickListener listener;
    public AdapterSitios(Context context, ArrayList<Sitio> sitios) {
        this.Sitios = sitios;
        this.inflater= LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = inflater.inflate(R.layout.item_list,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.categoria.setText(Sitios.get(position).getCategoria());
        holder.nombre.setText(Sitios.get(position).getNombre());
        holder.foto.setImageResource(Sitios.get(position).getImageSitio());
    }

    @Override
    public int getItemCount() {

        return Sitios.size();
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;

    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView categoria;
        TextView nombre;
        ImageView foto;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            categoria= itemView.findViewById(R.id.textview_campo1);
            nombre= itemView.findViewById(R.id.textview_campo2);
            foto= itemView.findViewById(R.id.imageView_foto);
        }


    }
}
