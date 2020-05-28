package com.example.cesartour.Adatadores_Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.R;

import java.util.ArrayList;

public class AdapterActividades extends RecyclerView.Adapter<AdapterActividades.ViewHolderDatos> implements View.OnClickListener {
    ArrayList<Actividad> Actividades;
    private View.OnClickListener listener;
    public AdapterActividades(ArrayList<Actividad> actividades) {
        this.Actividades = actividades;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.categoria.setText(Actividades.get(position).getCategoria().toString());
        holder.nombre.setText(Actividades.get(position).getNombre());
        holder.foto.setImageResource(Actividades.get(position).getImageActividad());
    }

    @Override
    public int getItemCount() {

        return Actividades.size();
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
