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

import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.BLL.EventoService;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.R;

import java.io.IOException;
import java.util.ArrayList;


public class EventosFragment extends Fragment {
    ListView listView_eventos;
    ArrayList<String> idList;
    ArrayList<Evento> eventoList;
    Spinner municipioSpinner;
    Button buttonFilter;
    ArrayAdapter adapter;
    // lo del recycler
    ArrayList<Evento> Eventos;
    RecyclerView recyclerEventos;
    AdapterEventos adapterEventos;

//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_eventos, container, false);
        listView_eventos = (ListView) view.findViewById(R.id.listView_eventos);

        buttonFilter =  view.findViewById(R.id.buttonFilter);
        municipioSpinner = (Spinner) view.findViewById(R.id.spinner_Municipio);
        crearSpinners(municipioSpinner);

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

        Eventos= new ArrayList<>();
        recyclerEventos= view.findViewById(R.id.Recycler_eventos);
     cargarDatos();
     mostrarDatos();


        //
        return  view;
    }
    public  void cargarDatos(){
        Eventos.add(new Evento("Festival","Valledupar",R.drawable.valledupar));
        Eventos.add(new Evento("Feria del parque","Valledupar",R.drawable.pueblo_bello));
        Eventos.add(new Evento("Feria del dulce","Valledupar",R.drawable.manaure));
    }
    public void mostrarDatos(){
        recyclerEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterEventos = new AdapterEventos(getContext(),Eventos);
        recyclerEventos.setAdapter(adapterEventos);
    }
    private void crearSpinners(Spinner municipioSpinner){

        ArrayList<String> municipioSpinnerList = new ArrayList<>();
        municipioSpinnerList.add("Todos");
        municipioSpinnerList.add("Valledupar");
        municipioSpinnerList.add("Bosconia");
        municipioSpinnerList.add("Manaure");
        municipioSpinnerList.add("Aguachica");
        municipioSpinnerList.add("Pueblo Bello");
        municipioSpinnerList.add("Chimichagua");
        final ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,
                municipioSpinnerList);
        municipioSpinner.setAdapter(municipioAdapter);
    }

    public void filter() {
        String municipio = municipioSpinner.getSelectedItem().toString();

        ArrayList<String> listAuxiliar = new ArrayList<>();
        final ArrayList<Evento> listAuxiliarEventos = new ArrayList<>();
        idList.clear();
        for (Evento item: eventoList) {
            if((item.getMunicipio().equals(municipio))){
                String dateSitio = item.getNombre() + "\n" + item.getMunicipio();
                listAuxiliar.add(dateSitio);
                listAuxiliarEventos.add(item);
                idList.add(item.getCodigo()+"");
            }else{
                if(municipio.equals("Todos")){
                    String dateSitio = item.getNombre() + "\n" + item.getMunicipio();
                    listAuxiliar.add(dateSitio);
                    listAuxiliarEventos.add(item);
                    idList.add(item.getCodigo()+"");
                }
            }
        }
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listAuxiliar);
        listView_eventos.setAdapter(adapter);
        listView_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapter.getContext(), "Detalles evento", Toast.LENGTH_SHORT).show();
                Selected(idList.get(i), listAuxiliarEventos);
            }
        });

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

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listEventos);
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
