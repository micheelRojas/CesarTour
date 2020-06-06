package com.example.cesartour.Presentacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cesartour.BLL.ActividadService;
import com.example.cesartour.BLL.CulturaService;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterCultura;
import com.example.cesartour.Entity.Cultura;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.R;

import java.io.IOException;
import java.util.ArrayList;


public class CulturaFragment extends Fragment {
    ListView listView_culturas;
    ArrayList<String> idList;
    ArrayList<Cultura> culturaList;
    Spinner categoriaSpinner;
    Spinner municipioSpinner;
    Button buttonFilter;
    ArrayAdapter adapter;
    // lo del recycler
    ArrayList<Cultura> Culturas;
    RecyclerView recyclerCultura;
   AdapterCultura adapterCultura;
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cultura, container, false);
        //listView_culturas = (ListView) view.findViewById(R.id.listView_culturas);

        recyclerCultura= view.findViewById(R.id.Recycler_cultura);
        Culturas= new ArrayList<>();

        buttonFilter =  view.findViewById(R.id.buttonFilter);
        categoriaSpinner = (Spinner) view.findViewById(R.id.spinner_categoria);
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
        return view;
    }
    public  void cargarDatos(){
       Culturas.add(new Cultura("Sirena del rio","Leyenda",R.drawable.valledupar));
        Culturas.add(new Cultura("Vallenato","Musica",R.drawable.valledupar));
        Culturas.add(new Cultura("El monte","Junglar",R.drawable.valledupar));
    }
    public void mostrarDatos(){
        recyclerCultura.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCultura = new AdapterCultura(getContext(),Culturas);
        recyclerCultura.setAdapter(adapterCultura);
    }
    private void crearSpinners(Spinner categoriaSpinner, Spinner municipioSpinner){
        ArrayList<String> categoriaSpinnerList = new ArrayList<>();
        categoriaSpinnerList.add("Todas");
        categoriaSpinnerList.add("Cancion");
        categoriaSpinnerList.add("Leyenda");
        categoriaSpinnerList.add("Mito");
        categoriaSpinnerList.add("Musico");
        final ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                categoriaSpinnerList);
        categoriaSpinner.setAdapter(categoriaAdapter);

        ArrayList<String> municipioSpinnerList = new ArrayList<>();
        municipioSpinnerList.add("Todos");
        municipioSpinnerList.add("Valledupar");
        municipioSpinnerList.add("El Paso");
        municipioSpinnerList.add("Tamalameque");
        municipioSpinnerList.add("La Paz");
        municipioSpinnerList.add("Codazzi");
        final ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                municipioSpinnerList);
        municipioSpinner.setAdapter(municipioAdapter);
    }

    public void filter() {
        String categoria = categoriaSpinner.getSelectedItem().toString();
        String municipio = municipioSpinner.getSelectedItem().toString();

        ArrayList<String> listAuxiliar = new ArrayList<>();
        final ArrayList<Cultura> listAuxiliarCultura = new ArrayList<>();
        idList.clear();
        for (Cultura item: culturaList) {
            if((item.getTipo().equals(categoria)) && (item.getMunicipio().equals(municipio))){
                listAuxiliarCultura.add(item);
                idList.add(item.getCodigo()+"");
            }else{
                if(categoria.equals("Todas") && item.getMunicipio().equals(municipio)){
                    listAuxiliarCultura.add(item);
                    idList.add(item.getCodigo()+"");
                }else{
                    if(item.getTipo().equals(categoria) && municipio.equals("Todos")){
                        listAuxiliarCultura.add(item);
                        idList.add(item.getCodigo()+"");
                    }else{
                        if(categoria.equals("Todas") && municipio.equals("Todos")) {
                            listAuxiliarCultura.add(item);
                            idList.add(item.getCodigo()+"");
                        }
                    }
                }
            }
        }

        recyclerCultura.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCultura = new AdapterCultura(getContext(),listAuxiliarCultura);
        recyclerCultura.setAdapter(adapterCultura);

        adapterCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Detalles actividad", Toast.LENGTH_SHORT).show();
                Selected(idList.get(recyclerCultura.getChildAdapterPosition(view)), culturaList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void displayDatabaseInfoText() throws IOException {
        CulturaService culturaService = new CulturaService(this.getContext());

        culturaList = culturaService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listCulturas = new ArrayList<>();

        for(Cultura item: culturaList){
            Culturas.add(new Cultura(item.getNombre(), item.getTipo(), item.getImageCultura()));
            idList.add(item.getCodigo()+"");
        }
        recyclerCultura.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCultura = new AdapterCultura(getContext(),Culturas);
        recyclerCultura.setAdapter(adapterCultura);

        adapterCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                Selected(idList.get(recyclerCultura.getChildAdapterPosition(view)), culturaList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Selected(String id, ArrayList<Cultura> culturaList){
        Cultura cultura = new Cultura();

        int Id = Integer.parseInt(id);

        for (Cultura item: culturaList) {
            if(item.getCodigo() == Id){
                cultura.setCodigo(item.getCodigo());
                cultura.setTipo(item.getTipo());
                cultura.setNombre(item.getNombre());
                cultura.setDescripcion(item.getDescripcion());
                cultura.setMunicipio(item.getMunicipio());
                cultura.setImageCultura(item.getImageCultura());
            }
        }

        Intent intent = new Intent(this.getActivity(), Detalles.class);
        String idCultura = Integer.toString(cultura.getCodigo());
        intent.putExtra("id", idCultura);
        intent.putExtra("nombre", cultura.getNombre());
        intent.putExtra("categoria", cultura.getTipo());
        intent.putExtra("descripcion", cultura.getDescripcion());
        intent.putExtra("municipio", cultura.getMunicipio());
        intent.putExtra("imagen", Integer.toString(cultura.getImageCultura()));

        startActivity(intent);
    }

}
