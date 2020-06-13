package com.example.cesartour.Presentacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cesartour.BLL.MisActividadesService;
import com.example.cesartour.BLL.MisEventosService;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.MiDetalle;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisEventosFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Evento> MisEventos;
    RecyclerView recyclerMisEventos;
    AdapterEventos adapterMisEventos;

    ArrayList<Evento> listaAuxiliar;
    MisEventosService misEventosService = new MisEventosService();
    ArrayList<String> idList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_eventos, container, false);
        recyclerMisEventos= (RecyclerView) view.findViewById(R.id.Recycler_mis_eventos);
        listaAuxiliar = new ArrayList<>();
        idList = new ArrayList<>();
        //Datosa de prueba
        //cargarDatos();
        //mostrarDatos();
        consultarTodas();
        if(listaAuxiliar.size() == 0){
            Toast.makeText(this.getContext(), "No ha guardado ningún evento", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisEventos.add(new Evento("Festival","Valledupar",R.drawable.valledupar));
        MisEventos.add(new Evento("Feria del parque","Valledupar",R.drawable.pueblo_bello));
        MisEventos.add(new Evento("Feria del dulce","Valledupar",R.drawable.manaure));
    }
    public void mostrarDatos(){
        recyclerMisEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisEventos = new AdapterEventos(getContext(),MisEventos);
        recyclerMisEventos.setAdapter(adapterMisEventos);
    }

    public long registrarEvento(Evento evento){
        //ConexionSQLiteHelper_Actividad conn = new ConexionSQLiteHelper_Actividad(this.getContext(),"db_actividades",null,1);

        long idResultante = misEventosService.registrarMisEventos(MainActivity.conexionEvento, evento);
        return idResultante;
    }

    private void consultarTodas(){
        //ArrayList<Actividad> actividades = new ArrayList<>();
        //MisActividadesService misActividadesService = new MisActividadesService();
        MisEventos = misEventosService.consultarTodas(MainActivity.conexionEvento);

        for (Evento item: MisEventos) {
            listaAuxiliar.add(new Evento(item.getNombre(), item.getMunicipio(), item.getImageEvento()));
            idList.add(item.getCodigo()+"");
        }
        recyclerMisEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisEventos = new AdapterEventos(getContext(),listaAuxiliar);
        recyclerMisEventos.setAdapter(adapterMisEventos);

        adapterMisEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                Selected(idList.get(recyclerMisEventos.getChildAdapterPosition(view)), MisEventos);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
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
                evento.setMunicipio(item.getMunicipio());
                evento.setFecha(item.getFecha());
                evento.setDescripcion(item.getDescripcion());
                evento.setTipoObjeto(item.getTipoObjeto());
                evento.setImageEvento(item.getImageEvento());
            }
        }

        Intent intent = new Intent(this.getActivity(), MiDetalle.class);
        String idActividad = Integer.toString(evento.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", evento.getNombre());
        intent.putExtra("fecha", evento.getFecha());
        intent.putExtra("descripcion", evento.getDescripcion());
        intent.putExtra("municipio", evento.getMunicipio());
        intent.putExtra("tipoObjeto", evento.getTipoObjeto());
        intent.putExtra("imagen", Integer.toString(evento.getImageEvento()));

        startActivity(intent);
        getActivity().finish();
    }

    public void eliminarEvento(String id){
        misEventosService.eliminarEvento(MainActivity.conexionEvento, id);

    }

    public boolean validar(String id){
        boolean respuesta = misEventosService.validar(id, MainActivity.conexionEvento);
        return respuesta;
    }
}
