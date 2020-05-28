package com.example.cesartour;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cesartour.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.Adatadores_Recycler.AdapterCultura;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Cultura;

import java.util.ArrayList;


public class CulturaFragment extends Fragment {
    // lo del recycler
    ArrayList<Cultura> Culturas;
    RecyclerView recycler;
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cultura, container, false);


        //pongo esto aqui como guia si lo haces diferente no hay problema
        // lo del recycler

        Culturas= new ArrayList<>();
        recycler= view.findViewById(R.id.Recycler_cultura);
        //el tipo de recycler
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //Aqui deberia seer llamado el metodo  de llenadao, aunque no se como lo quieras hacer
        //llenarRecycler();
        // aqui se le manda la lista de cultura  al adatador No olvides que aun no se a llenado
        AdapterCultura adapter = new AdapterCultura(Culturas);
        // luego se le manda el adatador al recycler
        recycler.setAdapter(adapter);

        //

        return view;
    }
}
