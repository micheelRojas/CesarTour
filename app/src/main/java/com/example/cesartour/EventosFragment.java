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

import com.example.cesartour.BLL.EventoService;
import com.example.cesartour.BLL.SitioService;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;


public class EventosFragment extends Fragment {
    ListView listView_eventos;
    ArrayList<String> idList;
    ArrayList<Evento> eventoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_eventos, container, false);
        listView_eventos = (ListView) view.findViewById(R.id.listView_eventos);

        try{
            displayDatabaseInfoText();
        }catch(IOException e){}
        return  view;
    }

    private void displayDatabaseInfoText() throws IOException {
        EventoService eventoService = new EventoService(this.getContext());

        eventoList = eventoService.displayDatabaseInfoText();
        idList = new ArrayList<>();
        ArrayList<String> listEventos = new ArrayList<>();

        for(Evento item: eventoList){
            String dateEvento = item.getNombre() + "\n" + item.getMunicipio();
            listEventos.add(dateEvento);
            idList.add(item.getCodigo()+"");
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listEventos);
        listView_eventos.setAdapter(adapter);

        listView_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapter.getContext(), "Detalles evento", Toast.LENGTH_SHORT).show();
                Selected(idList.get(i), eventoList);
            }
        });
    }

    private void Selected(String id, ArrayList<Evento> eventoList){
        Evento evento = new Evento();

        int Id = Integer.parseInt(id);

        for (Evento item: eventoList) {
            if(item.getCodigo() == Id){
                evento.setCodigo(item.getCodigo());
                evento.setNombre(item.getNombre());
                evento.setDescripcion(item.getDescripcion());
                evento.setFecha(item.getFecha());
                evento.setMunicipio(item.getMunicipio());
            }
        }

        Intent intent = new Intent(this.getActivity(), Detalles.class);
        String idActividad = Integer.toString(evento.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", evento.getNombre());
        intent.putExtra("fecha", evento.getFecha());
        intent.putExtra("descripcion", evento.getDescripcion());
        intent.putExtra("municipio", evento.getMunicipio());

        startActivity(intent);
    }


}
