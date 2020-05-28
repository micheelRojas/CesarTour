package com.example.cesartour.Presentacion.Adatadores_Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cesartour.Entity.Cultura;
import com.example.cesartour.R;

import java.util.ArrayList;

public class AdapterCultura extends RecyclerView.Adapter<AdapterCultura.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Cultura> Culturas;
    private View.OnClickListener listener;
    public AdapterCultura(ArrayList<Cultura> culturas) {
        this.Culturas = culturas;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.tipo.setText(Culturas.get(position).getTipo().toString());
        holder.nombre.setText(Culturas.get(position).getNombre());
        holder.foto.setImageResource(Culturas.get(position).getImageCultura());

    }

    @Override
    public int getItemCount() {
        return Culturas.size();
    }
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;

    }
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tipo;
        TextView nombre;
        ImageView foto;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            tipo= itemView.findViewById(R.id.textview_campo1);
            nombre= itemView.findViewById(R.id.textview_campo2);
            foto= itemView.findViewById(R.id.imageView_foto);

        }


    }
}
