package com.example.cesartour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cesartour.BLL.SitioService;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SitioFragment extends Fragment {

    ListView listView_sitios;
    //ArrayList<String> sitioList;
    ArrayList<String> idList;
    //ArrayList<Sitio> sitios = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sitio, container, false);
        listView_sitios = (ListView) view.findViewById(R.id.listView_sitios);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_tipo);
        String[] datos = new String[] {"Hotel", "Restaurante"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, datos);

        try{
            displayDatabaseInfoText();
        }catch(IOException e){}
        return  view;
    }

    /*private void displayDatabaseInfoText() throws IOException {
        SitioService sitioService = new SitioService(this.getContext());
        sitioList = sitioService.displayDatabaseInfoText();
        idList = new ArrayList<>();

        sitios = sitioService.returnSitios();


        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, sitioList);
        listView_sitios.setAdapter(adapter);

        String tamaño = Integer.toString(sitioList.size());
        Toast.makeText(this.getActivity(), tamaño, Toast.LENGTH_SHORT ).show();

        //+++++++
        for (String item: sitioList) {
            idList.add(item+"");
        }

        listView_sitios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(adapter.getContext(), "Detalles sitio", Toast.LENGTH_SHORT).show();
                SiteSelected(idList.get(i));
            }
        });
    } */

    /*private void SiteSelected(String id){
        //String selection = id + "=?";
        //String[] args = new String[] {id};


        Sitio sitio = new Sitio();
        for (Sitio item: sitios) {
            if(Integer.toString(item.getCodigo()).equals(id)){
                sitio.setCodigo(item.getCodigo());
                sitio.setCategoria(item.getCategoria());
                sitio.setNombre(item.getNombre());
                sitio.setDescripcion(item.getDescripcion());
            }
        }

        Intent intent = new Intent(getActivity(), Detalles.class);
        intent.putExtra("id", sitio.getCodigo());
        intent.putExtra("nombre", sitio.getNombre());
        intent.putExtra("categoria", sitio.getCategoria());
        intent.putExtra("descripcion", sitio.getDescripcion());

        Toast.makeText(this.getActivity(), "Este" + sitios.size(), Toast.LENGTH_SHORT);

        startActivity(intent);

        Log.e(TAG, "Error*****************" + sitios.size());
    } */

    private void displayDatabaseInfoText() throws IOException {
        SitioService sitioService = new SitioService(this.getContext());
        final ArrayList<Sitio> sitioList;
        sitioList = sitioService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listSitios = new ArrayList<>();

        for(Sitio item: sitioList){
            String dateSitio = item.getCategoria() + "\n" + item.getNombre();
            listSitios.add(dateSitio);
            idList.add(item.getCodigo()+"");
        }

        //listView_sitios = (ListView) listView_sitios.findViewById(R.id.listView_sitios);
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
       String selection = sitio.getCodigo() + "=?";
       String[] args = new String[] {id};

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
