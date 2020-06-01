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
    RecyclerView recycler;
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sitio, container, false);
        listView_sitios = (ListView) view.findViewById(R.id.listView_sitios);

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
        //pongo esto aqui como guia si lo haces diferente no hay problema
        // lo del recycler

        Sitios= new ArrayList<>();
        recycler= view.findViewById(R.id.Recycler_sitios);
        //el tipo de recycler
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //Aqui deberia seer llamado el metodo  de llenadao, aunque no se como lo quieras hacer
        //llenarRecycler();
        // aqui se le manda la lista de sitios  al adatador No olvides que aun no se a llenado
        AdapterSitios adapter = new AdapterSitios(Sitios);
        // luego se le manda el adatador al recycler
        recycler.setAdapter(adapter);

        //

        return  view;
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
                String dateSitio = item.getCategoria() + "\n" + item.getNombre();
                listAuxiliar.add(dateSitio);
                listAuxiliarSitios.add(item);
                idList.add(item.getCodigo()+"");
            }else{
                if(categoria.equals("Todas") && item.getMunicipio().equals(municipio)){
                    String dateSitio = item.getCategoria() + "\n" + item.getNombre();
                    listAuxiliar.add(dateSitio);
                    listAuxiliarSitios.add(item);
                    idList.add(item.getCodigo()+"");
                }else{
                    if(item.getCategoria().equals(categoria) && municipio.equals("Todos")){
                        String dateSitio = item.getCategoria() + "\n" + item.getNombre();
                        listAuxiliar.add(dateSitio);
                        listAuxiliarSitios.add(item);
                        idList.add(item.getCodigo()+"");
                    }else{
                        if(categoria.equals("Todas") && municipio.equals("Todos")) {
                            String dateSitio = item.getCategoria() + "\n" + item.getNombre();
                            listAuxiliar.add(dateSitio);
                            listAuxiliarSitios.add(item);
                            idList.add(item.getCodigo()+"");
                        }
                    }
                }
            }
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listAuxiliar);
        listView_sitios.setAdapter(adapter);
        listView_sitios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapter.getContext(), "Detalles sitio", Toast.LENGTH_SHORT).show();
                SiteSelected(idList.get(i), listAuxiliarSitios);
            }
        });
    }

    private void displayDatabaseInfoText() throws IOException {
        SitioService sitioService = new SitioService(this.getContext());

        sitioList = sitioService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listSitios = new ArrayList<>();

        for(Sitio item: sitioList){
            String dateSitio = item.getCategoria() + "\n" + item.getNombre();
            listSitios.add(dateSitio);
            idList.add(item.getCodigo()+"");
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listSitios);
        listView_sitios.setAdapter(adapter);

        listView_sitios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapter.getContext(), "Detalles sitio", Toast.LENGTH_SHORT).show();
                SiteSelected(idList.get(i), sitioList);
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

        startActivity(intent);
    }


}
