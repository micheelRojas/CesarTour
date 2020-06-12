package com.example.cesartour.Presentacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cesartour.BLL.MisActividadesService;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Actividad;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.MiDetalle;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterActividades;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisActividadesFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Actividad> MisActividades;
    RecyclerView recyclerMisActividades;
    AdapterActividades adapterMisActividades;


    ArrayList<Actividad> listaAuxiliar;
    MisActividadesService misActividadesService = new MisActividadesService();
    ArrayList<String> idList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_actividades, container, false);
        recyclerMisActividades= (RecyclerView) view.findViewById(R.id.Recycler_mis_actividades);
        listaAuxiliar = new ArrayList<>();
        idList = new ArrayList<>();
        //misActividadesService = new MisActividadesService();
        //DAtos de prueba
        //cargarDatos();
        //mostrarDatos();
        consultarTodas();
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisActividades.add(new Actividad("Pedregoza","piscina",R.drawable.valledupar));
        MisActividades.add(new Actividad("Parapente manaure","Parapente",R.drawable.manaure));
      MisActividades.add(new Actividad("Avistamiento de aves","Avez",R.drawable.valledupar));
      MisActividades.add(new Actividad("Museo del acordeon","Museo",R.drawable.valledupar));

    }
    public void mostrarDatos(){
        recyclerMisActividades.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisActividades = new AdapterActividades(getContext(),MisActividades);
        recyclerMisActividades.setAdapter(adapterMisActividades);
    }

    public long registrarActividad(Actividad actividad){
        //ConexionSQLiteHelper_Actividad conn = new ConexionSQLiteHelper_Actividad(this.getContext(),"db_actividades",null,1);

        long idResultante = misActividadesService.registrarMisActividades(MainActivity.conexionActividad, actividad);
        return idResultante;
    }

    private void consultarTodas(){
        //ArrayList<Actividad> actividades = new ArrayList<>();
        //MisActividadesService misActividadesService = new MisActividadesService();
        MisActividades = misActividadesService.consultarTodas(MainActivity.conexionActividad);

        for (Actividad item: MisActividades) {
            listaAuxiliar.add(new Actividad(item.getNombre(), item.getCategoria(), item.getImageActividad()));
            idList.add(item.getCodigo()+"");
        }
        recyclerMisActividades.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisActividades = new AdapterActividades(getContext(),listaAuxiliar);
        recyclerMisActividades.setAdapter(adapterMisActividades);

        adapterMisActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                Selected(idList.get(recyclerMisActividades.getChildAdapterPosition(view)), MisActividades);
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

        Intent intent = new Intent(this.getActivity(), MiDetalle.class);
        String idActividad = Integer.toString(actividad.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", actividad.getNombre());
        intent.putExtra("categoria", actividad.getCategoria());
        intent.putExtra("descripcion", actividad.getDescripcion());
        intent.putExtra("municipio", actividad.getMunicipio());
        intent.putExtra("tipoObjeto", actividad.getTipoObjeto());
        intent.putExtra("imagen", Integer.toString(actividad.getImageActividad()));

        startActivity(intent);
        getActivity().finish();
    }

    public void eliminarActividad(String id){
        misActividadesService.eliminarActividad(MainActivity.conexionActividad, id);

    }

    public boolean validar(String id){
        boolean respuesta = misActividadesService.validar(id, MainActivity.conexionActividad);
        return respuesta;
    }

}
