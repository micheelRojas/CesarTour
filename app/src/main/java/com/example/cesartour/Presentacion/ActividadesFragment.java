package com.example.cesartour.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cesartour.Entity.Cultura;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.BLL.ActividadService;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterCultura;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterSitios;
import com.example.cesartour.R;

import java.io.IOException;
import java.util.ArrayList;


public class ActividadesFragment extends Fragment {
    ListView listView_actividades;
    ArrayList<String> idList;
    ArrayList<Actividad> actividadList;
    Spinner categoriaSpinner;
    Spinner municipioSpinner;
    Button buttonFilter;
    ArrayAdapter adapter;
    // lo del recycler
    ArrayList<Actividad> Actividades;
    RecyclerView recyclerActividad;
    AdapterActividades adapterActividades;
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_actividades, container, false);
        //listView_actividades = (ListView) view.findViewById(R.id.listView_actividades);

        recyclerActividad= view.findViewById(R.id.Recycler_actividades);
        Actividades= new ArrayList<>();

        buttonFilter =  view.findViewById(R.id.buttonFilter);
        categoriaSpinner = (Spinner) view.findViewById(R.id.spinner_actividad);
        municipioSpinner = (Spinner) view.findViewById(R.id.spinner_Municipio);
        crearSpinners(categoriaSpinner, municipioSpinner);

        try{
            displayDatabaseInfoText();
        }catch(IOException e){}
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });

        // lo del recycler
        //cargarDatos();
        //mostrarDatos();
        //


        return  view;
    }
    public  void cargarDatos(){
        Actividades.add(new Actividad("Pedregoza","piscina",R.drawable.valledupar));
        Actividades.add(new Actividad("Parapente manaure","Parapente",R.drawable.manaure));
        Actividades.add(new Actividad("Avistamiento de aves","Avez",R.drawable.valledupar));
        Actividades.add(new Actividad("Museo del acordeon","Museo",R.drawable.valledupar));

    }
    public void mostrarDatos(){
        recyclerActividad.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterActividades = new AdapterActividades(getContext(),Actividades);
        recyclerActividad.setAdapter(adapterActividades);
    }
    private void crearSpinners(Spinner categoriaSpinner, Spinner municipioSpinner){
        ArrayList<String> categoriaSpinnerList = new ArrayList<>();
        categoriaSpinnerList.add("Todas");
        categoriaSpinnerList.add("Rios");
        categoriaSpinnerList.add("Parapente");
        categoriaSpinnerList.add("Caminata");
        categoriaSpinnerList.add("Gastronomia");
        categoriaSpinnerList.add("Avistamiento de aves");
        categoriaSpinnerList.add("Canotaje");
        final ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                categoriaSpinnerList);
        categoriaSpinner.setAdapter(categoriaAdapter);

        ArrayList<String> municipioSpinnerList = new ArrayList<>();
        municipioSpinnerList.add("Todos");
        municipioSpinnerList.add("Valledupar");
        municipioSpinnerList.add("Manaure");
        municipioSpinnerList.add("La Paz");
        municipioSpinnerList.add("Chimichagua");
        municipioSpinnerList.add("La Mina");
        municipioSpinnerList.add("Patillal");
        final ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                municipioSpinnerList);
        municipioSpinner.setAdapter(municipioAdapter);
    }

    public void filter() {
        String categoria = categoriaSpinner.getSelectedItem().toString();
        String municipio = municipioSpinner.getSelectedItem().toString();

        ArrayList<String> listAuxiliar = new ArrayList<>();
        final ArrayList<Actividad> listAuxiliarActividades = new ArrayList<>();
        idList.clear();
        for (Actividad item: actividadList) {
            if((item.getCategoria().equals(categoria)) && (item.getMunicipio().equals(municipio))){
                listAuxiliarActividades.add(item);
                idList.add(item.getCodigo()+"");
            }else{
                if(categoria.equals("Todas") && item.getMunicipio().equals(municipio)){
                    listAuxiliarActividades.add(item);
                    idList.add(item.getCodigo()+"");
                }else{
                    if(item.getCategoria().equals(categoria) && municipio.equals("Todos")){
                        listAuxiliarActividades.add(item);
                        idList.add(item.getCodigo()+"");
                    }else{
                        if(categoria.equals("Todas") && municipio.equals("Todos")) {
                            listAuxiliarActividades.add(item);
                            idList.add(item.getCodigo()+"");
                        }
                    }
                }
            }
        }

        recyclerActividad.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterActividades = new AdapterActividades(getContext(),listAuxiliarActividades);
        recyclerActividad.setAdapter(adapterActividades);

        adapterActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Detalles cultura", Toast.LENGTH_SHORT).show();
                Selected(idList.get(recyclerActividad.getChildAdapterPosition(view)), actividadList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayDatabaseInfoText() throws IOException {
        ActividadService actividadService = new ActividadService(this.getContext());

        actividadList = actividadService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listActividades = new ArrayList<>();

        for(Actividad item: actividadList){
            Actividades.add(new Actividad(item.getNombre(), item.getCategoria(), item.getImageActividad()));
            idList.add(item.getCodigo()+"");
        }

        recyclerActividad.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterActividades = new AdapterActividades(getContext(),Actividades);
        recyclerActividad.setAdapter(adapterActividades);

        adapterActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                Selected(idList.get(recyclerActividad.getChildAdapterPosition(view)), actividadList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Selected(String id, ArrayList<Actividad> actividadList){
        Actividad actividad = new Actividad();

        int Id = Integer.parseInt(id);

        for (Actividad item: actividadList) {
            if(item.getCodigo() == Id){
                actividad.setCodigo(item.getCodigo());
                actividad.setCategoria(item.getCategoria());
                actividad.setNombre(item.getNombre());
                actividad.setDescripcion(item.getDescripcion());
                actividad.setMunicipio(item.getMunicipio());
                actividad.setTipoObjeto(item.getTipoObjeto());
                actividad.setImageActividad(item.getImageActividad());
            }
        }

        Intent intent = new Intent(this.getActivity(), Detalles.class);
        String idActividad = Integer.toString(actividad.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", actividad.getNombre());
        intent.putExtra("categoria", actividad.getCategoria());
        intent.putExtra("descripcion", actividad.getDescripcion());
        intent.putExtra("municipio", actividad.getMunicipio());
        intent.putExtra("tipoObjeto", actividad.getTipoObjeto());
        intent.putExtra("imagen", Integer.toString(actividad.getImageActividad()));

        startActivity(intent);
    }

}
