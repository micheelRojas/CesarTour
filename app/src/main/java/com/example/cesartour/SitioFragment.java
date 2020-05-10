package com.example.cesartour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cesartour.BLL.SitioService;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SitioFragment extends Fragment {

    ListView listView_sitios;
    ArrayList<String> idList;
    Spinner categoriaSpinner;

    ArrayList<Sitio> sitioList;
    Button buttonFilter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sitio, container, false);
        listView_sitios = (ListView) view.findViewById(R.id.listView_sitios);

        buttonFilter =  view.findViewById(R.id.buttonFilter);
        categoriaSpinner = (Spinner) view.findViewById(R.id.spinner_tipo);
        ArrayList<String> categoriaSpinnerList = new ArrayList<>();
        categoriaSpinnerList.add("Todas");
        categoriaSpinnerList.add("Restaurante");
        categoriaSpinnerList.add("Hotel");
        final ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                categoriaSpinnerList);
        categoriaSpinner.setAdapter(categoriaAdapter);
        //final String categoria = categoriaSpinner.getSelectedItem().toString();
        /*categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                categoriaAdapter.getFilter().filter(categoria);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        }); */

        try{
            displayDatabaseInfoText();
        }catch(IOException e){}
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filter(v);
            }
        });
        return  view;
    }



    public void filter(View view) {
        String categoria = categoriaSpinner.getSelectedItem().toString();
        ArrayList<Sitio> listAuxiliar = new ArrayList<>();
        for (Sitio item: sitioList) {
            if(item.getCategoria() == categoria){
                listAuxiliar.add(item);
            }
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listAuxiliar);
        listView_sitios.setAdapter(adapter);

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

        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listSitios);
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
