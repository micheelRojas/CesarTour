package com.example.cesartour.Presentacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterSitios;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisSitiosFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Sitio> MisSitios;
    RecyclerView recyclerMisSitios;
    AdapterSitios adapterMisSitios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_sitios, container, false);
        recyclerMisSitios= (RecyclerView) view.findViewById(R.id.Recycler_mis_sitios);
        MisSitios= new ArrayList<>();
        //Datos de prueba
        cargarDatos();
        mostrarDatos();
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisSitios.add(new Sitio("Rio la mesa","Baleneario",R.drawable.manaure));
        MisSitios.add(new Sitio("Pimienta y sazon ","Restaurante",R.drawable.valledupar));
        MisSitios.add(new Sitio("Rio Guatapuri","Baleneario",R.drawable.valledupar));
    }
    public void mostrarDatos(){
        recyclerMisSitios.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisSitios = new AdapterSitios(getContext(),MisSitios);
        recyclerMisSitios.setAdapter(adapterMisSitios);
    }
}
