package com.example.cesartour.DAL;

import android.content.Context;
import android.widget.ListView;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActividadRepository {
    private ArrayList<String> actividadList;
    private ArrayList<String> idList;
    private ArrayList<Actividad> actividades = new ArrayList<>();;
    private Context context;

    public ActividadRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Actividad> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.actividades);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        actividadList = new ArrayList<String>();

        try {
            comodo = new BufferedReader(new InputStreamReader(archivo));

            String linea, valores[];
            Actividad actividad;
            while ((linea = comodo.readLine()) != null) {
                valores = linea.split(";");
                actividad = new Actividad();
                actividad.setCodigo(Integer.parseInt(valores[0]));
                actividad.setNombre(valores[1]);
                actividad.setCategoria(valores[2]);
                actividad.setMunicipio(valores[3]);
                actividad.setDescripcion(valores[4]);
                actividades.add(actividad);

                String dateActividades = actividad.getCategoria() + "\n" + actividad.getNombre();
                actividadList.add(dateActividades);
                idList.add(actividad.getCodigo()+"");
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return actividades;
    }
}
