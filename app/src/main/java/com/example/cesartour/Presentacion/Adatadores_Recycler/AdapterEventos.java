package com.example.cesartour.Presentacion.Adatadores_Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cesartour.Entity.Evento;
import com.example.cesartour.R;

import java.util.ArrayList;

public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.ViewHolderDatos> implements View.OnClickListener  {
    ArrayList<Evento> Eventos;
    private View.OnClickListener listener;
    public AdapterEventos(ArrayList<Evento> eventos) {
        this.Eventos = eventos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {

        holder.nombre.setText(Eventos.get(position).getNombre());
        holder.municipio.setText(Eventos.get(position).getMunicipio().toString());
        holder.foto.setImageResource(Eventos.get(position).getImageEvento());
    }

    @Override
    public int getItemCount() {

        return Eventos.size();
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
        TextView municipio;
        TextView nombre;
        ImageView foto;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            municipio= itemView.findViewById(R.id.textview_campo2);
            nombre= itemView.findViewById(R.id.textview_campo1);
            foto= itemView.findViewById(R.id.imageView_foto);

        }


    }
}
