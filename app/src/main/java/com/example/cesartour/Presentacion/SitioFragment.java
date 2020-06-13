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
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterCultura;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterSitios;
import com.example.cesartour.BLL.SitioService;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.io.IOException;
import java.util.ArrayList;

public class SitioFragment extends Fragment {

    ListView listView_sitios;
    ArrayList<String> idList;
    Spinner categoriaSpinner;
    Spinner municipioSpinner;

    ArrayList<Sitio> sitioList;
    Button buttonFilter;
    ArrayAdapter adapter;
    // lo del recycler
    ArrayList<Sitio> Sitios;
    RecyclerView recyclerSitio;
    AdapterSitios adapterSitios;
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sitio, container, false);
        //listView_sitios = (ListView) view.findViewById(R.id.listView_sitios);

        recyclerSitio= view.findViewById(R.id.Recycler_sitios);
        Sitios= new ArrayList<>();

        buttonFilter =  view.findViewById(R.id.buttonFilter);
        categoriaSpinner = (Spinner) view.findViewById(R.id.spinner_tipo);
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
        Sitios.add(new Sitio("Rio la mesa","Baleneario",R.drawable.manaure));
        Sitios.add(new Sitio("Pimienta y sazon ","Restaurante",R.drawable.valledupar));
        Sitios.add(new Sitio("Rio Guatapuri","Baleneario",R.drawable.valledupar));
    }
    public void mostrarDatos(){
        recyclerSitio.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSitios = new AdapterSitios(getContext(),Sitios);
        recyclerSitio.setAdapter(adapterSitios);
    }

    private void crearSpinners(Spinner categoriaSpinner, Spinner municipioSpinner){
        ArrayList<String> categoriaSpinnerList = new ArrayList<>();
        categoriaSpinnerList.add("Todas");
        categoriaSpinnerList.add("Restaurante");
        categoriaSpinnerList.add("Hotel");
        categoriaSpinnerList.add("Casa Campo");
        final ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                categoriaSpinnerList);
        categoriaSpinner.setAdapter(categoriaAdapter);

        ArrayList<String> municipioSpinnerList = new ArrayList<>();
        municipioSpinnerList.add("Todos");
        municipioSpinnerList.add("Valledupar");
        municipioSpinnerList.add("Manaure");
        municipioSpinnerList.add("Bosconia");
        municipioSpinnerList.add("Aguachica");
        municipioSpinnerList.add("Chinichagua");
        final ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                municipioSpinnerList);
        municipioSpinner.setAdapter(municipioAdapter);
    }

    public void filter() {
        String categoria = categoriaSpinner.getSelectedItem().toString();
        String municipio = municipioSpinner.getSelectedItem().toString();

        final ArrayList<String> listAuxiliar = new ArrayList<>();
        final ArrayList<Sitio> listAuxiliarSitios = new ArrayList<>();
        idList.clear();
        for (Sitio item: sitioList) {
            if((item.getCategoria().equals(categoria)) && (item.getMunicipio().equals(municipio))){
                listAuxiliarSitios.add(item);
                idList.add(item.getCodigo()+"");
            }else{
                if(categoria.equals("Todas") && item.getMunicipio().equals(municipio)){
                    listAuxiliarSitios.add(item);
                    idList.add(item.getCodigo()+"");
                }else{
                    if(item.getCategoria().equals(categoria) && municipio.equals("Todos")){
                        listAuxiliarSitios.add(item);
                        idList.add(item.getCodigo()+"");
                    }else{
                        if(categoria.equals("Todas") && municipio.equals("Todos")) {
                            listAuxiliarSitios.add(item);
                            idList.add(item.getCodigo()+"");
                        }
                    }
                }
            }
        }

        recyclerSitio.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSitios = new AdapterSitios(getContext(),listAuxiliarSitios);
        recyclerSitio.setAdapter(adapterSitios);

        adapterSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Detalles sitio", Toast.LENGTH_SHORT).show();
                SiteSelected(idList.get(recyclerSitio.getChildAdapterPosition(view)), sitioList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void displayDatabaseInfoText() throws IOException {
        SitioService sitioService = new SitioService(this.getContext());

        sitioList = sitioService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listSitios = new ArrayList<>();

        for(Sitio item: sitioList){
            Sitios.add(new Sitio(item.getNombre(), item.getCategoria(), item.getImageSitio()));
            idList.add(item.getCodigo()+"");
        }

        recyclerSitio.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterSitios = new AdapterSitios(getContext(),Sitios);
        recyclerSitio.setAdapter(adapterSitios);

        adapterSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                SiteSelected(idList.get(recyclerSitio.getChildAdapterPosition(view)), sitioList);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void SiteSelected(String id, ArrayList<Sitio> sitioList){
        Sitio sitio = new Sitio();

        int Id = Integer.parseInt(id);
        for (Sitio item: sitioList) {
            if(item.getCodigo() == Id){
                sitio.setCodigo(item.getCodigo());
                sitio.setCategoria(item.getCategoria());
                sitio.setNombre(item.getNombre());
                sitio.setDescripcion(item.getDescripcion());
                sitio.setDireccion(item.getDireccion());
                sitio.setMunicipio(item.getMunicipio());
                sitio.setTipoObjeto(item.getTipoObjeto());
                sitio.setImageSitio(item.getImageSitio());
            }
        }

        Intent intent = new Intent(this.getActivity(), Detalles.class);
        String idSitio = Integer.toString(sitio.getCodigo());
        intent.putExtra("id", idSitio);
        intent.putExtra("nombre", sitio.getNombre());
        intent.putExtra("categoria", sitio.getCategoria());
        intent.putExtra("descripcion", sitio.getDescripcion());
        intent.putExtra("direccion", sitio.getDireccion());
        intent.putExtra("municipio", sitio.getMunicipio());
        intent.putExtra("tipoObjeto", sitio.getTipoObjeto());
        intent.putExtra("imagen", Integer.toString(sitio.getImageSitio()));

        startActivity(intent);
    }


}
