package com.example.cesartour.DAL;

import android.content.Context;
import android.widget.ListView;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.ImagenActividad;
import com.example.cesartour.Entity.ImagenSitio;
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

    private ArrayList<ImagenActividad> imagenes;

    public ActividadRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Actividad> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.actividades);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        actividadList = new ArrayList<String>();

        imagenes = new ArrayList<>();
        imagenes.add(new ImagenActividad(0, R.drawable.actrioguatapuri));
        imagenes.add(new ImagenActividad(1, R.drawable.actparapentemanaure));
        imagenes.add(new ImagenActividad(2, R.drawable.actcaminatamanaure));
        imagenes.add(new ImagenActividad(3, R.drawable.actcaminatavalledupar));
        imagenes.add(new ImagenActividad(4, R.drawable.actcaminatamanaureladanta));
        imagenes.add(new ImagenActividad(5, R.drawable.actcomervalledupar));
        imagenes.add(new ImagenActividad(6, R.drawable.actcomeralmojabanas));
        imagenes.add(new ImagenActividad(7, R.drawable.actavistamientodeaves));
        imagenes.add(new ImagenActividad(8, R.drawable.actnavegarzapatosa));
        imagenes.add(new ImagenActividad(9, R.drawable.actbalneariolamina));
        imagenes.add(new ImagenActividad(10, R.drawable.actriobadillo));


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
                actividad.setTipoObjeto(valores[5]);
                for (ImagenActividad item: imagenes) {
                    if(item.getCodigo() == actividad.getCodigo()){
                        actividad.setImageActividad(item.getImagen());
                    }
                }

                actividades.add(actividad);
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return actividades;
    }
}
