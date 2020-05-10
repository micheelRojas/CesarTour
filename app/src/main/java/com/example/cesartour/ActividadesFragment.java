package com.example.cesartour;

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

import com.example.cesartour.BLL.ActividadService;
import com.example.cesartour.BLL.SitioService;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;


public class ActividadesFragment extends Fragment {
    ListView listView_actividades;
    ArrayList<String> idList;
    ArrayList<Actividad> actividadList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_actividades, container, false);
        listView_actividades = (ListView) view.findViewById(R.id.listView_actividades);

        try{
            displayDatabaseInfoText();
        }catch(IOException e){}
        return  view;
    }

    private void displayDatabaseInfoText() throws IOException {
        ActividadService actividadService = new ActividadService(this.getContext());

        actividadList = actividadService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listActividades = new ArrayList<>();

        for(Actividad item: actividadList){
            String dateActividad = item.getCategoria() + "\n" + item.getNombre();
            listActividades.add(dateActividad);
            idList.add(item.getCodigo()+"");
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listActividades);
        listView_actividades.setAdapter(adapter);

        listView_actividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapter.getContext(), "Detalles actividad", Toast.LENGTH_SHORT).show();
                Selected(idList.get(i), actividadList);
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
            }
        }

        Intent intent = new Intent(this.getActivity(), Detalles.class);
        String idActividad = Integer.toString(actividad.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", actividad.getNombre());
        intent.putExtra("categoria", actividad.getCategoria());
        intent.putExtra("descripcion", actividad.getDescripcion());
        intent.putExtra("municipio", actividad.getMunicipio());

        startActivity(intent);
    }

}
