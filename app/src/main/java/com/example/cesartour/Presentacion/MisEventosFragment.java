package com.example.cesartour.Presentacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisEventosFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Evento> MisEventos;
    RecyclerView recyclerMisEventos;
    AdapterEventos adapterMisEventos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_eventos, container, false);
        recyclerMisEventos= (RecyclerView) view.findViewById(R.id.Recycler_mis_eventos);
        MisEventos= new ArrayList<>();
        //Datosa de prueba
        cargarDatos();
        mostrarDatos();
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisEventos.add(new Evento("Festival","Valledupar",R.drawable.valledupar));
        MisEventos.add(new Evento("Feria del parque","Valledupar",R.drawable.pueblo_bello));
        MisEventos.add(new Evento("Feria del dulce","Valledupar",R.drawable.manaure));
    }
    public void mostrarDatos(){
        recyclerMisEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisEventos = new AdapterEventos(getContext(),MisEventos);
        recyclerMisEventos.setAdapter(adapterMisEventos);
    }
}
