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

import com.example.cesartour.BLL.MisEventosService;
import com.example.cesartour.BLL.MisSitiosService;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.MiDetalle;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterEventos;
import com.example.cesartour.Presentacion.Adatadores_Recycler.AdapterSitios;
import com.example.cesartour.R;

import java.util.ArrayList;


public class MisSitiosFragment extends Fragment {
    //creo que se puede hacer el recycler de los mis con el mismo adatados de los originales
    ArrayList<Sitio> MisSitios;
    RecyclerView recyclerMisSitios;
    AdapterSitios adapterMisSitios;

    ArrayList<Sitio> listaAuxiliar;
    MisSitiosService misSitiosService = new MisSitiosService();
    ArrayList<String> idList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mis_sitios, container, false);
        recyclerMisSitios= (RecyclerView) view.findViewById(R.id.Recycler_mis_sitios);
        listaAuxiliar = new ArrayList<>();
        idList = new ArrayList<>();
        //Datos de prueba
        //cargarDatos();
        //mostrarDatos();
        consultarTodas();
        if(listaAuxiliar.size() == 0){
            Toast.makeText(this.getContext(), "No ha guardado ningún sitio", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    //DATOS DE PRUEBA
    public  void cargarDatos(){
        MisSitios.add(new Sitio("Rio la mesa","Baleneario",R.drawable.manaure));
        MisSitios.add(new Sitio("Pimienta y sazon ","Restaurante",R.drawable.valledupar));
        MisSitios.add(new Sitio("Rio Guatapuri","Baleneario",R.drawable.valledupar));
    }
    public void mostrarDatos(){
        recyclerMisSitios.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisSitios = new AdapterSitios(getContext(),MisSitios);
        recyclerMisSitios.setAdapter(adapterMisSitios);
    }

    public long registrarSitio(Sitio sitio){
        //ConexionSQLiteHelper_Actividad conn = new ConexionSQLiteHelper_Actividad(this.getContext(),"db_actividades",null,1);

        long idResultante = misSitiosService.registrarMisSitios(MainActivity.conexionSitio, sitio);
        return idResultante;
    }

    private void consultarTodas(){
        //ArrayList<Actividad> actividades = new ArrayList<>();
        //MisActividadesService misActividadesService = new MisActividadesService();
        MisSitios = misSitiosService.consultarTodas(MainActivity.conexionSitio);

        for (Sitio item: MisSitios) {
            listaAuxiliar.add(new Sitio(item.getNombre(), item.getCategoria(), item.getImageSitio()));
            idList.add(item.getCodigo()+"");
        }
        recyclerMisSitios.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMisSitios = new AdapterSitios(getContext(),listaAuxiliar);
        recyclerMisSitios.setAdapter(adapterMisSitios);

        adapterMisSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String posicion = RecyclerView.getChildAdapterPosition(v)+"";
                Selected(idList.get(recyclerMisSitios.getChildAdapterPosition(view)), MisSitios);
                //Toast.makeText(getApplicationContext(), "click in "+posicion, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Selected(String id, ArrayList<Sitio> sitioList){
        Sitio sitio = new Sitio();

        int Id = Integer.parseInt(id);

        for (Sitio item: sitioList) {
            if(item.getCodigo() == Id){
                sitio.setCodigo(item.getCodigo());
                sitio.setNombre(item.getNombre());
                sitio.setMunicipio(item.getMunicipio());
                sitio.setCategoria(item.getCategoria());
                sitio.setDescripcion(item.getDescripcion());
                sitio.setTipoObjeto(item.getTipoObjeto());
                sitio.setImageSitio(item.getImageSitio());
                sitio.setDireccion(item.getDireccion());
            }
        }

        Intent intent = new Intent(this.getActivity(), MiDetalle.class);
        String idActividad = Integer.toString(sitio.getCodigo());
        intent.putExtra("id", idActividad);
        intent.putExtra("nombre", sitio.getNombre());
        intent.putExtra("categoria", sitio.getCategoria());
        intent.putExtra("direccion", sitio.getDireccion());
        intent.putExtra("descripcion", sitio.getDescripcion());
        intent.putExtra("municipio", sitio.getMunicipio());
        intent.putExtra("tipoObjeto", sitio.getTipoObjeto());
        intent.putExtra("imagen", Integer.toString(sitio.getImageSitio()));

        startActivity(intent);
        getActivity().finish();
    }

    public void eliminarSitio(String id){
        misSitiosService.eliminarSitio(MainActivity.conexionSitio, id);

    }

    public boolean validar(String id){
        boolean respuesta = misSitiosService.validar(id, MainActivity.conexionSitio);
        return respuesta;
    }
}
