package com.example.cesartour.Presentacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisActividadesFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Actividad> MisActividades;
    RecyclerView recyclerMisActividades;
    AdapterActividades adapterMisActividades;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_actividades, container, false);
        recyclerMisActividades= (RecyclerView) view.findViewById(R.id.Recycler_mis_actividades);
        MisActividades= new ArrayList<>();
        //DAtos de prueba
        cargarDatos();
        mostrarDatos();
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisActividades.add(new Actividad("Pedregoza","piscina",R.drawable.valledupar));
        MisActividades.add(new Actividad("Parapente manaure","Parapente",R.drawable.manaure));
      MisActividades.add(new Actividad("Avistamiento de aves","Avez",R.drawable.valledupar));
      MisActividades.add(new Actividad("Museo del acordeon","Museo",R.drawable.valledupar));

    }
    public void mostrarDatos(){
        recyclerMisActividades.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisActividades = new AdapterActividades(getContext(),MisActividades);
        recyclerMisActividades.setAdapter(adapterMisActividades);
    }
}
